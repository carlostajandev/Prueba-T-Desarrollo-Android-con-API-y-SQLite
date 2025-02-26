package com.example.crudusuariosproductos.dominio;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface UsuarioRepository {
    LiveData<List<Usuario>> obtenerUsuarios();

    void insertarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(int id);
    List<Usuario> obtenerTodosLosUsuarios();
}
