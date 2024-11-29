package com.marlenepaper.walkers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.text.BreakIterator;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);


        Button loginButton = findViewById(R.id.loginButton);
        TextView loginRegisterText = findViewById(R.id.loginRegisterText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputs();
            }
        });

        loginRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegister();
            }
        });
    }

    public void launchMain() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void launchRegister() {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

    private void validateInputs() {
        TextInputLayout userInput = findViewById(R.id.userinput);
        TextInputLayout passwordInput = findViewById(R.id.passwordinput);

        String username = userInput.getEditText().getText().toString().trim();
        String password = passwordInput.getEditText().getText().toString();

        SharedPreferences preferences = getSharedPreferences("Usuario", MODE_PRIVATE);
        String registeredUsername = preferences.getString("userName", null);
        String registeredPassword = preferences.getString("userPassword", null);

//        if (username.isEmpty() || password.isEmpty() ) {
//            Toast.makeText(this, "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show();
//        } else if (username.length() < 3) {
//            Toast.makeText(this, "El usuario debe tener al menos 3 caracteres.", Toast.LENGTH_SHORT).show();
//        } else if (!password.equals("walkers")) {
//            Toast.makeText(this, "La contraseña debe ser 'walkers'.", Toast.LENGTH_SHORT).show();
//        } else {
//            launchMain();
//            finish();
//        }

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show();
        } else if (username.length() < 3) {
            Toast.makeText(this, "El usuario debe tener al menos 3 caracteres.", Toast.LENGTH_SHORT).show();
        } else if (registeredUsername == null || registeredPassword == null) {
            Toast.makeText(this, "No hay un usuario registrado. Regístrate primero.", Toast.LENGTH_SHORT).show();
        } else if (!username.equals(registeredUsername)) {
            Toast.makeText(this, "El usuario no coincide con el registrado.", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(registeredPassword)) {
            Toast.makeText(this, "La contraseña es incorrecta.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show();
            launchMain();
            finish();
        }
    }


}