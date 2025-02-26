package com.example.crudusuariosproductos.aplicacion;
import com.example.crudusuariosproductos.dominio.Producto;
import com.example.crudusuariosproductos.dominio.ProductoRepository;

import java.util.List;

public class ProductoUseCase {
    private final ProductoRepository productoRepository;

    public ProductoUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public void registrarProducto(String nombre, double precio, int usuarioId) {
        Producto producto = new Producto(nombre, precio, usuarioId);
        productoRepository.insertarProducto(producto);
    }

    public void actualizarProducto(Producto producto) {
        productoRepository.actualizarProducto(producto);
    }

    public void eliminarProducto(Producto producto) {
        productoRepository.eliminarProducto(producto);
    }

    public Producto obtenerProductoPorId(int id) {
        return productoRepository.obtenerProductoPorId(id);
    }

    public List<Producto> obtenerProductosPorUsuario(int usuarioId) {
        return productoRepository.obtenerProductosPorUsuario(usuarioId);
    }
}

