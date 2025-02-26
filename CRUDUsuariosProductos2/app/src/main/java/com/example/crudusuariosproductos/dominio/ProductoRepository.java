package com.example.crudusuariosproductos.dominio;
import java.util.List;
public interface ProductoRepository {
    void insertarProducto(Producto producto);
    void actualizarProducto(Producto producto);
    void eliminarProducto(Producto producto);
    Producto obtenerProductoPorId(int id);
    List<Producto> obtenerProductosPorUsuario(int usuarioId);
}
