package com.marlenepaper.walkers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = findViewById(R.id.welcomeText);
        TextView addedText = findViewById(R.id.addedText);

        SharedPreferences sharedPreferences = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("userName", "noIdentificado");
        String password = sharedPreferences.getString("password", "contraseña");

        welcomeText.setText("Hola, " + name);
        addedText.setText("Tu contraseña es: " + password);

        Button callDialogButton = findViewById(R.id.callDialogButton);
        callDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("Alerta");
                dialogBuilder.setMessage("Es " + name + ", tu nombre?");
                dialogBuilder.setCancelable(true);
                dialogBuilder.setPositiveButton("Sí, correcto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getApplicationContext(), "Continuamos contigo "+name, Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                dialogBuilder.setNegativeButton("No, incorrecto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Para cambiar tus datos, da click en Abril Material Dialog", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });

        Button callMaterialDialogButton = findViewById(R.id.callMaterialDialogButton);
        callMaterialDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this)
                        .setTitle("Hola, " + name)
                        .setMessage("Si quieres cambiar tus datos dar click a continuar")
                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Snackbar.make(v, "Procede a cambiar Nombre y Contraseña", Snackbar.LENGTH_SHORT)
                                        .setAction("Cambiar", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                                                LayoutInflater inflater = getLayoutInflater();
                                                View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                                                builder.setView(dialogView);

                                                EditText editTextNombre = dialogView.findViewById(R.id.newUserName);
                                                EditText editPassword = dialogView.findViewById(R.id.newPassword);

                                                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        String newName = editTextNombre.getText().toString();
                                                        String newPass = editPassword.getText().toString();
                                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                                        editor.putString("userName", newName);
                                                        editor.putString("password", newPass);
                                                        editor.apply();
                                                        Toast.makeText(getApplicationContext(), "Datos actualizados", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                builder.setNegativeButton("Cancelar", null);
                                                builder.show();
                                            }
                                        }).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Toast.makeText(getApplicationContext(), "Cancelaste", Toast.LENGTH_SHORT).show();
                            }
                        });
                materialAlertDialogBuilder.show();
            }
        });
    }
}
