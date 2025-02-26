package com.example.crudusuariosproductos.infraestructura.persistencia;

import com.example.crudusuariosproductos.dominio.Usuario;
import com.example.crudusuariosproductos.dominio.UsuarioRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final UsuarioDao usuarioDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public UsuarioRepositoryImpl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        executorService.execute(() -> usuarioDao.insertarUsuario(usuario));
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        executorService.execute(() -> usuarioDao.actualizarUsuario(usuario));
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        executorService.execute(() -> usuarioDao.eliminarUsuario(usuario));
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioDao.obtenerUsuarioPorId(id);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDao.obtenerTodosLosUsuarios();
    }
}
