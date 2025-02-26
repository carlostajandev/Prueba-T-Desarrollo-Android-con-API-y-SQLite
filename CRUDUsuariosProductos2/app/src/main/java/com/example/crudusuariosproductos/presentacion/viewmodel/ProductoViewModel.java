package com.example.crudusuariosproductos.presentacion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.crudusuariosproductos.aplicacion.ProductoUseCase;
import com.example.crudusuariosproductos.dominio.Producto;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductoViewModel extends ViewModel {
    private final ProductoUseCase productoUseCase;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final MutableLiveData<List<Producto>> productosLiveData = new MutableLiveData<>();

    public ProductoViewModel(ProductoUseCase productoUseCase) {
        this.productoUseCase = productoUseCase;
    }

    public LiveData<List<Producto>> obtenerProductosLiveData() {
        return productosLiveData;
    }

    public void cargarProductos(int usuarioId) {
        executorService.execute(() -> {
            List<Producto> productos = productoUseCase.obtenerProductosPorUsuario(usuarioId);
            productosLiveData.postValue(productos);
        });
    }

    public void registrarProducto(String nombre, double precio, int usuarioId) {
        executorService.execute(() -> {
            productoUseCase.registrarProducto(nombre, precio, usuarioId);
            cargarProductos(usuarioId); // Recargar lista
        });
    }

    public void eliminarProducto(Producto producto) {
        executorService.execute(() -> {
            productoUseCase.eliminarProducto(producto);
            cargarProductos(producto.getUsuarioId()); // Recargar lista
        });
    }
}
