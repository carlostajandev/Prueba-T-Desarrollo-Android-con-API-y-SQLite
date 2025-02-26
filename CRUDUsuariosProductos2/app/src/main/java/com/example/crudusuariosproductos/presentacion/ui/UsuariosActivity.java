package com.example.crudusuariosproductos.presentacion.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudusuariosproductos.R;
import com.example.crudusuariosproductos.dominio.Usuario;
import com.example.crudusuariosproductos.presentacion.viewmodel.UsuarioViewModel;

import java.util.List;

public class UsuariosActivity extends AppCompatActivity {
    private UsuarioViewModel usuarioViewModel;
    private UsuarioAdapter usuarioAdapter;
    private EditText etNombreUsuario, etCorreoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        etCorreoUsuario = findViewById(R.id.etCorreoUsuario);
        Button btnAgregarUsuario = findViewById(R.id.btnAgregarUsuario);
        RecyclerView rvUsuarios = findViewById(R.id.rvUsuarios);

        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);
        usuarioAdapter = new UsuarioAdapter();
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));
        rvUsuarios.setAdapter(usuarioAdapter);

        usuarioViewModel.obtenerUsuariosLiveData().observe(this, usuarios -> {
            usuarioAdapter.actualizarLista(usuarios);
        });

        btnAgregarUsuario.setOnClickListener(view -> {
            String nombre = etNombreUsuario.getText().toString();
            String correo = etCorreoUsuario.getText().toString();
            usuarioViewModel.registrarUsuario(nombre, correo);
            etNombreUsuario.setText("");
            etCorreoUsuario.setText("");
        });

        usuarioViewModel.cargarUsuarios();
    }
}
