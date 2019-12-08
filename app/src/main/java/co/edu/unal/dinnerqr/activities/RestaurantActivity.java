package co.edu.unal.dinnerqr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.unal.dinnerqr.R;
import co.edu.unal.dinnerqr.clases.Plato;
import co.edu.unal.dinnerqr.soport.Adaptador;

public class RestaurantActivity extends AppCompatActivity {

    private TextView nombreRestauranteTextView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ListView listView;

    /////
    private ListView itemsListView;
    private Adaptador adaptador;
    private ArrayList<Plato> arrayEntidad = new ArrayList<>();
    /////
    static String qrContend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        //listView = (ListView)findViewById(R.id.lvPlatos);
        nombreRestauranteTextView = (TextView)findViewById(R.id.tvRestaurantName);
        qrContend = getIntent().getStringExtra("code");
        nombreRestauranteTextView.setText(qrContend);
        itemsListView = (ListView) findViewById(R.id.lvItems);

        String qrContent = getIntent().getStringExtra("code");
        nombreRestauranteTextView.setText(qrContent);
        itemsListView = (ListView) findViewById(R.id.lvItems);

        DatabaseReference restaurante = database.getInstance().getReference("restaurant").child(qrContent);
        restaurante.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for( DataSnapshot snapshot: dataSnapshot.getChildren()){
                    try {
                        Plato plato = snapshot.getValue(Plato.class);
                        //Log.e("Nombre", ""+plato.getName());
                        addDishToView(plato.getId(), plato.getName(), plato.getPrice(), plato.getDescription());
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addDishToView(String id, String nombre, double precio, String descripcion){
        Plato plato = new Plato();
        plato.setId(id);
        plato.setName(nombre);
        plato.setPrice(precio);
        plato.setDescription(descripcion);

        arrayEntidad.add(plato);

        adaptador = new Adaptador(this, arrayEntidad);
        itemsListView.setAdapter(adaptador);
    }

    @Override
    public void onBackPressed() {
        Intent optionsActivity = new Intent(this, OptionsActivity.class);
        startActivity(optionsActivity);
    }
}
