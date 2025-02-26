package com.example.crudusuariosproductos.infraestructura.persistencia;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.crudusuariosproductos.dominio.Usuario;
import com.example.crudusuariosproductos.dominio.Producto;

@Database(entities = {Usuario.class, Producto.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instancia;

    public abstract UsuarioDao usuarioDao();
    public abstract ProductoDao productoDao();

    public static AppDatabase getInstance(Context context) {
        if (instancia == null) {
            synchronized (AppDatabase.class) {
                if (instancia == null) {
                    instancia = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "mi_base_de_datos"
                    ).build();
                }
            }
        }
        return instancia;
    }
}
