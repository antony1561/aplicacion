package com.example.aplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login2Activity extends AppCompatActivity {

    private EditText e1,e2;
    private Button b1,b2,b3;

    // variables de datos a registrar
    private String correo2 ="";
    private String contraseña2 ="";

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        auth = FirebaseAuth.getInstance();
        e1 = (EditText) findViewById(R.id.correo2);
        e2 = (EditText) findViewById(R.id.contraseña2);
        b1 = (Button) findViewById(R.id.btnregistrarse2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login2Activity.this,loginActivity.class);
                startActivity(i);
            }
        });
        b2 = (Button) findViewById(R.id.btningresar);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo2 = e1.getText().toString();
                contraseña2 = e2.getText().toString();

                if(!correo2.isEmpty() &&! contraseña2.isEmpty()){
                    loginUser();

                }else{
                    Toast.makeText(login2Activity.this, "Rellene los campos vacios",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b3 = (Button) findViewById(R.id.btnrestablecer2);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login2Activity.this,recordarActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUser(){
          auth.signInWithEmailAndPassword(correo2, contraseña2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                      startActivity(new Intent(login2Activity.this,MainActivity.class));
                      finish();
                  }
                  else{
                      Toast.makeText(login2Activity.this, "No se pudo Inciar Sesion , compruebe los datos",Toast.LENGTH_SHORT).show();
                  }
              }
          });
    }
}
