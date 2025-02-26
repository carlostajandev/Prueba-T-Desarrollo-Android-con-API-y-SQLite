package com.example.crudusuariosproductos.infraestructura.persistencia;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.crudusuariosproductos.dominio.Producto;

import java.util.List;

@Dao
public interface ProductoDao {
    @Insert
    void insertarProducto(Producto producto);

    @Update
    void actualizarProducto(Producto producto);

    @Delete
    void eliminarProducto(Producto producto);

    @Query("SELECT * FROM productos WHERE id = :id LIMIT 1")
    Producto obtenerProductoPorId(int id);

    @Query("SELECT * FROM productos WHERE usuarioId = :usuarioId")
    List<Producto> obtenerProductosPorUsuario(int usuarioId);
}
