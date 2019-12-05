package co.edu.unal.dinnerqr.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import co.edu.unal.dinnerqr.R;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText TextEmail, TextPassword;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextEmail = (EditText)findViewById(R.id.etMail);
        TextPassword = (EditText)findViewById(R.id.edPassworlogin);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);


    }


    public void login(View view){
        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = TextEmail.getText().toString().trim();
        String password  = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Ingresando  en linea...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Welcome :)", Toast.LENGTH_SHORT).show();
                            Intent opcione = new Intent(MainActivity.this, OptionsActivity.class);
                            startActivity(opcione);

                        } else {
                            Toast.makeText(MainActivity.this, "Error en unsuario o contraseña", Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });

    }

    public void registeractivity(View view){
        Intent resgistrar = new Intent(this, RegisterActivity.class);
        startActivity(resgistrar);
    }




}
