package com.example.crudusuariosproductos.repository;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.crudusuariosproductos.dominio.Usuario;
import com.example.crudusuariosproductos.infraestructura.persistencia.AppDatabase;
import com.example.crudusuariosproductos.infraestructura.persistencia.UsuarioDao;
import com.example.crudusuariosproductos.network.ApiService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class UsuarioRepository {

    private UsuarioDao usuarioDao;
    private ApiService apiServicio;
    private MutableLiveData<List<Usuario>> usuariosLiveData = new MutableLiveData<>();

    public UsuarioRepository(Context contexto) {
        AppDatabase baseDatos = AppDatabase.getInstance(contexto);
        usuarioDao = baseDatos.usuarioDao();

    }

    // Obtener usuarios de la API y almacenarlos en ROOM
    public void obtenerUsuariosDeApi() {
        apiServicio.getUsers().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> llamada, Response<List<Usuario>> respuesta) {
                if (respuesta.isSuccessful() && respuesta.body() != null) {
                    List<Usuario> usuarios = respuesta.body();
                    usuariosLiveData.postValue(usuarios);
                    // Guardar en la base de datos local
                    new Thread(() -> usuarioDao.insertarUsuario((List<Usuario>) usuarios.get(0))).start(); // Inserta solo el primero para evitar conflictos
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> llamada, Throwable t) {
                Log.e("UsuarioRepository", "Error al obtener usuarios", t);
            }
        });
    }

    // Obtener usuarios desde ROOM
    public LiveData<List<Usuario>> obtenerUsuariosDeBaseDatos() {
        return new MutableLiveData<>(usuarioDao.obtenerTodosLosUsuarios());
    }

    // Insertar un usuario en la API y SQLite
    public void insertarUsuario(Usuario usuario) {
        apiServicio.createUser(usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> llamada, Response<Usuario> respuesta) {
                if (respuesta.isSuccessful()) {
                    new Thread(() -> usuarioDao.insertarUsuario((List<Usuario>) respuesta.body())).start();
                }
            }

            @Override
            public void onFailure(Call<Usuario> llamada, Throwable t) {
                Log.e("UsuarioRepository", "Error al insertar usuario", t);
            }
        });
    }
}

