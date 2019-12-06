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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.unal.dinnerqr.R;
import co.edu.unal.dinnerqr.clases.Plato;
import co.edu.unal.dinnerqr.soport.Entidad;

public class DetalleLista extends AppCompatActivity {
    private TextView tvTitulo, tvDescripcion, tvPrecio;
    private ImageView imgFoto;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Button agregar;
    private Context context;
    private String nombre;
    private double precio;
    static ArrayList<String> billName = new ArrayList<>();
    static ArrayList<Double> billprice = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lista);
        final String id = getIntent().getStringExtra("idPlato");
        agregar = (Button)findViewById(R.id.btAdd);
        context = this;
        String qrContend = getIntent().getStringExtra("code");
        DatabaseReference platos = database.getInstance().getReference("restaurant");

        platos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for( DataSnapshot snapshot: dataSnapshot.getChildren()){
                    for(DataSnapshot p: snapshot.getChildren()){
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

            public void aceptar() {
                billName.add(nombre);
                billprice.add(precio);
                Toast.makeText(context,"Tu plato esta en camino", Toast.LENGTH_SHORT).show();

                //Intent i = new Intent(context, BillActivity.class);
                //startActivity(i);
            }

            public void cancelar() {
                finish();
            }

        });


    }


}
