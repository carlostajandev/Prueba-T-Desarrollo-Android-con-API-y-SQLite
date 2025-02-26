package com.example.crudusuariosproductos.presentacion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.crudusuariosproductos.aplicacion.UsuarioUseCase;
import com.example.crudusuariosproductos.dominio.Usuario;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsuarioViewModel extends ViewModel {
    private final UsuarioUseCase usuarioUseCase;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final MutableLiveData<List<Usuario>> usuariosLiveData = new MutableLiveData<>();

    public UsuarioViewModel(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    public LiveData<List<Usuario>> obtenerUsuariosLiveData() {
        return usuariosLiveData;
    }

    public void cargarUsuarios() {
        executorService.execute(() -> {
            List<Usuario> usuarios = usuarioUseCase.obtenerTodosLosUsuarios();
            usuariosLiveData.postValue(usuarios);
        });
    }

    public void registrarUsuario(String nombre, String correo) {
        executorService.execute(() -> {
            usuarioUseCase.registrarUsuario(nombre, correo);
            cargarUsuarios(); // Recargar lista
        });
    }

    public void eliminarUsuario(Usuario usuario) {
        executorService.execute(() -> {
            usuarioUseCase.eliminarUsuario(usuario);
            cargarUsuarios(); // Recargar lista
        });
    }
}
