package com.example.crudusuariosproductos.infraestructura.persistencia;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.crudusuariosproductos.dominio.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Insert
    void insertarUsuario(Usuario usuario);

    @Update
    void actualizarUsuario(Usuario usuario);

    @Delete
    void eliminarUsuario(Usuario usuario);

    @Query("SELECT * FROM usuarios WHERE id = :id LIMIT 1")
    Usuario obtenerUsuarioPorId(int id);

    @Query("SELECT * FROM usuarios")
    List<Usuario> obtenerTodosLosUsuarios();
}

