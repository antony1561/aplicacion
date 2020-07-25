package com.example.aplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class recordarActivity extends AppCompatActivity {

    private EditText r1;
    private Button b1;

    private String correo ="";

    private FirebaseAuth auth;

    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordar);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        r1 = (EditText) findViewById(R.id.correo3);
        b1 = (Button) findViewById(R.id.btnrestablecer);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                correo = r1.getText().toString();
                if(!correo.isEmpty()){
                    dialog.setMessage("Espera un momento ... ");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    resetpassword();
                }
                else{
                    Toast.makeText(recordarActivity.this, "Debe ingresar el correo",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void resetpassword(){

        auth.setLanguageCode("es");
        auth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
             if(task.isSuccessful()){
                 Toast.makeText(recordarActivity.this, "Se envio el correo",Toast.LENGTH_SHORT).show();

             }else{
                 Toast.makeText(recordarActivity.this, "No se pudo enviar el correo",Toast.LENGTH_SHORT).show();
             }
             dialog.dismiss();
            }
        });

    }
}
