package co.edu.unal.dinnerqr.activities;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;

import co.edu.unal.dinnerqr.R;
import co.edu.unal.dinnerqr.clases.Bill;
import co.edu.unal.dinnerqr.clases.Cliente;
import co.edu.unal.dinnerqr.clases.Plato;

public class RegisterActivity extends AppCompatActivity {

    private EditText Epassword, Eemail, name;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cliente = database.getInstance().getReference("client");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Epassword = (EditText)findViewById(R.id.etPassword);
        Eemail = (EditText)findViewById(R.id.etMail);
        name = (EditText)findViewById(R.id.etName);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
    }

    public void registrarUsuario(View view){

        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = Eemail.getText().toString().trim();
        String password  = Epassword.getText().toString().trim();
        final String nombre  = name.getText().toString().trim();

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
        //registerDB(nombre, email);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            registerDB(nombre, email);
                            Toast.makeText(RegisterActivity.this,"Se ha registrado el usuario con el email: "+ Eemail.getText(),Toast.LENGTH_LONG).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(RegisterActivity.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });

    }

    public void registerDB(String name, String email){

        String id = cliente.push().getKey();
        //Cliente client = new Cliente( id, name, email, "0");
        Cliente client = new Cliente();
        client.setId(id);
        client.setBill("0");
        client.seteMail(email);
        client.setName(name);



        cliente.child(id).setValue(client);

    }
}
