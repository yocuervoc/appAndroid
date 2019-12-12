package co.edu.unal.dinnerqr.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.unal.dinnerqr.R;
import co.edu.unal.dinnerqr.clases.Cliente;
import co.edu.unal.dinnerqr.clases.Plato;
import co.edu.unal.dinnerqr.soport.Entidad;
import co.edu.unal.dinnerqr.soport.PlatoSoport;

import static co.edu.unal.dinnerqr.activities.RestaurantActivity.qrContend;


public class DetalleLista extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView tvTitulo, tvDescripcion, tvPrecio;
    private ImageView imgFoto;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    static String idUser;
    private Button agregar;
    private Context context;
    private String nombre;
    private double precio;
    static ArrayList<String> billName = new ArrayList<>();
    static ArrayList<Double> billprice = new ArrayList<>();
    //final String id = getIntent().getStringExtra("idPlato");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lista);
        mAuth = FirebaseAuth.getInstance();
        agregar = (Button)findViewById(R.id.btAdd);
        context = this;
        //final String qrContend = getIntent().getStringExtra("code");
        final DatabaseReference platos = database.getInstance().getReference("restaurant");
        final String id = getIntent().getStringExtra("idPlato");
        final DatabaseReference cliente = database.getInstance().getReference("client");
        platos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for( DataSnapshot snapshot: dataSnapshot.getChildren()){
                    for(DataSnapshot p: snapshot.getChildren()){
                        try {
                            Plato plato = p.getValue(Plato.class);

                            if(plato.getId().equals(id)){
                                Log.e("plato", ""+p);

                                tvTitulo = findViewById(R.id.tvTitulo);
                                tvPrecio = findViewById(R.id.Precio);
                                tvDescripcion = findViewById(R.id.tvDescripcion);
                                imgFoto = findViewById(R.id.imgFoto);

                                tvTitulo.setText(plato.getName());
                                tvDescripcion.setText(plato.getDescription());
                                imgFoto.setImageResource(R.drawable.plato);
                                tvPrecio.setText(Double.toString(plato.getPrice()));
                                nombre = plato.getName();
                                precio = plato.getPrice();
                                break;
                            }
                        }catch (Exception e){

                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        final FirebaseUser currentUser = mAuth.getCurrentUser();

        cliente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for( DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Cliente currentClient = snapshot.getValue(Cliente.class);

                    if(currentClient.geteMail().equals(currentUser.getEmail())){
                        idUser = currentClient.getId();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(context);
                dialogo1.setTitle("Confirmacion:");
                dialogo1.setMessage("¿Desea pedir "+ nombre +" con valor de "+precio+"?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        aceptar();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        cancelar();
                    }
                });
                dialogo1.show();
            }
            //final FirebaseUser currentUser = mAuth.getCurrentUser();
            DatabaseReference bill = database.getInstance().getReference("bill");
            public void aceptar() {
                billName.add(nombre);
                billprice.add(precio);
                String idBill = bill.child(qrContend).child(idUser).push().getKey();
                PlatoSoport p = new PlatoSoport();
                p.setNombre(nombre);
                p.setPrecio(precio);
                bill.child(qrContend).child(idUser).child(idBill).setValue(p);

                cliente.child(idUser).child("bill").setValue(qrContend);
                Toast.makeText(context,"Tu plato esta en camino", Toast.LENGTH_SHORT).show();

            }

            public void cancelar() {
                finish();
            }

        });


    }
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        //nombreUser.setText(currentUser.getEmail());
    }
}
