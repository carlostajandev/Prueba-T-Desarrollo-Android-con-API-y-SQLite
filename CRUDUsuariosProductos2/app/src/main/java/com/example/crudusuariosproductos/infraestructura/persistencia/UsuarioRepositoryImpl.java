package com.example.crudusuariosproductos.infraestructura.persistencia;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.crudusuariosproductos.dominio.Usuario;
import com.example.crudusuariosproductos.dominio.UsuarioRepository;
import com.example.crudusuariosproductos.network.ApiService;

public abstract class UsuarioRepositoryImpl implements UsuarioRepository {

    private UsuarioDao usuarioDao;
    private ApiService apiService;
    private MutableLiveData<List<Usuario>> usuariosLiveData = new MutableLiveData<>();

    public UsuarioRepositoryImpl(UsuarioDao usuarioDao, ApiService apiService) {
        this.usuarioDao = usuarioDao;
        this.apiService = apiService;
    }

    @Override
    public LiveData<List<Usuario>> obtenerUsuarios() {
        // Primero intenta obtener los datos desde la API
        apiService.getUsers().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Usuario> usuarios = response.body();
                    usuariosLiveData.postValue(usuarios);
                    // Guardar en la base de datos local (ROOM)
                    new Thread(() -> usuarioDao.insertarUsuario(usuarios)).start();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("UsuarioRepositoryImpl", "Error al obtener usuarios de la API", t);
            }
        });

        // Si falla la API, retorna los datos locales desde ROOM
        return (LiveData<List<Usuario>>) usuarioDao.obtenerTodosLosUsuarios();
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        apiService.createUser(usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    new Thread(() -> usuarioDao.insertarUsuario((List<Usuario>) response.body())).start();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("UsuarioRepositoryImpl", "Error al insertar usuario en la API", t);
            }
        });
    }
}
