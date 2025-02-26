package com.example.crudusuariosproductos.dominio;

import java.util.List;

public interface UsuarioRepository {
    void insertarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(int id);
    List<Usuario> obtenerTodosLosUsuarios();
}
