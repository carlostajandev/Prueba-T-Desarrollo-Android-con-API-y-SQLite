package com.example.crudusuariosproductos.infraestructura.persistencia;

import com.example.crudusuariosproductos.dominio.Producto;
import com.example.crudusuariosproductos.dominio.ProductoRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductoRepositoryImpl implements ProductoRepository {
    private final ProductoDao productoDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ProductoRepositoryImpl(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @Override
    public void insertarProducto(Producto producto) {
        executorService.execute(() -> productoDao.insertarProducto(producto));
    }

    @Override
    public void actualizarProducto(Producto producto) {
        executorService.execute(() -> productoDao.actualizarProducto(producto));
    }

    @Override
    public void eliminarProducto(Producto producto) {
        executorService.execute(() -> productoDao.eliminarProducto(producto));
    }

    @Override
    public Producto obtenerProductoPorId(int id) {
        return productoDao.obtenerProductoPorId(id);
    }

    @Override
    public List<Producto> obtenerProductosPorUsuario(int usuarioId) {
        return productoDao.obtenerProductosPorUsuario(usuarioId);
    }
}
