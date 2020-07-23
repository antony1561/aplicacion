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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class loginActivity extends AppCompatActivity {

    private EditText e1,e2,e3;
    private Button b1;

    // variables de datos a registrar
    private String nombre ="";
    private String correo ="";
    private String contraseña ="";

    FirebaseAuth auth;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
        e1 = (EditText) findViewById(R.id.nombre);
        e2 = (EditText) findViewById(R.id.correo);
        e3 = (EditText) findViewById(R.id.contraseña);
        b1 = (Button) findViewById(R.id.btnregistrar);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                nombre = e1.getText().toString();
                correo = e2.getText().toString();
                contraseña = e3.getText().toString();

                if(!nombre.isEmpty() &&!correo.isEmpty()&&! contraseña.isEmpty()){

                    if (contraseña.length()>= 8) {
                        registerUser();
                    }
                    else{
                        Toast.makeText(loginActivity.this, "La contraseña debe contener minimo 8 caracteres",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(loginActivity.this, "Campos Vacios",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void registerUser(){
        auth.createUserWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre", nombre);
                    map.put("correo", correo);
                    map.put("contraseña", contraseña);

                    String id =auth.getCurrentUser().getUid();

                    db.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                Intent i= new Intent(loginActivity.this, login2Activity.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Toast.makeText(loginActivity.this, "No se crearon los datos correctamente",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(loginActivity.this, "No se puede registrar este usuario",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   // @Override
   // protected void onStart(){
     //   super.onStart();
       // if(auth.getCurrentUser()!= null){
          //  startActivity(new Intent(loginActivity.this,MainActivity.class));
        //    finish();
        //}
    //}
}

