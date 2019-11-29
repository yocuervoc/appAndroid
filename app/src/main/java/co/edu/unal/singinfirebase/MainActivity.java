package co.edu.unal.singinfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText TextEmail, TextPassword;
    private Button registrar;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextEmail = (EditText)findViewById(R.id.etMail);
        TextPassword = (EditText)findViewById(R.id.etPassword);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);


    }

    public void registrarUsuario(View view){

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


        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"Se ha registrado el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(MainActivity.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });

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
                            Toast.makeText(MainActivity.this, "Welcome :)", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error en unsuario o contraseña", Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });

    }




}
