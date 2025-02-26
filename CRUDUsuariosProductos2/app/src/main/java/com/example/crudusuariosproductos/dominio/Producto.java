package com.example.crudusuariosproductos.dominio;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "productos",
        foreignKeys = @ForeignKey(
                entity = Usuario.class,
                parentColumns = "id",
                childColumns = "usuarioId",
                onDelete = ForeignKey.CASCADE
        )
)
public class Producto {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private double precio;
    private int usuarioId; // FK

    // Constructor
    public Producto(String nombre, double precio, int usuarioId) {
        this.nombre = nombre;
        this.precio = precio;
        this.usuarioId = usuarioId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
}
