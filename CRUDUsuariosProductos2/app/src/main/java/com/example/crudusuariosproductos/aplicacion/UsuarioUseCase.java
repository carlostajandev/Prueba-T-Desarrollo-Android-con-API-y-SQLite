package com.example.crudusuariosproductos.aplicacion;

import com.example.crudusuariosproductos.dominio.Usuario;
import com.example.crudusuariosproductos.dominio.UsuarioRepository;

import java.util.List;

public class UsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public UsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void registrarUsuario(String nombre, String correo) {
        Usuario usuario = new Usuario(nombre, correo);
        usuarioRepository.insertarUsuario(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.eliminarUsuario(usuario);
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioRepository.obtenerUsuarioPorId(id);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.obtenerTodosLosUsuarios();
    }
}
