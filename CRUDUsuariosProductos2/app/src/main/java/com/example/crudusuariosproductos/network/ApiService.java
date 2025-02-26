package com.example.crudusuariosproductos.network;


import com.example.crudusuariosproductos.dominio.Usuario;
import com.google.firebase.firestore.auth.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import java.util.List;

public interface ApiService {

    @GET("users")  // Endpoint para obtener usuarios
    Call<List<Usuario>> getUsers();

    @POST("users")  // Endpoint para crear usuario
    Call<Usuario> createUser(@Body Usuario user);

    @PUT("users/{id}")  // Endpoint para actualizar usuario
    Call<Usuario> updateUser(@Body Usuario user);
}
