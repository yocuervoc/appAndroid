package co.edu.unal.dinnerqr.activities;

import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.unal.dinnerqr.R;
import co.edu.unal.dinnerqr.clases.Plato;
import co.edu.unal.dinnerqr.soport.Entidad;

public class DetalleLista extends AppCompatActivity {
    private TextView tvTitulo, tvDescripcion, tvPrecio;
    private ImageView imgFoto;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lista);
        final String id = getIntent().getStringExtra("idPlato");
        String qrContend = getIntent().getStringExtra("code");
        Log.e("CCCCCCCCCCCCCCCCCCCCCC", ""+qrContend);
        DatabaseReference platos = database.getInstance().getReference("restaurant");
        platos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for( DataSnapshot snapshot: dataSnapshot.getChildren()){
                    for(DataSnapshot p: snapshot.getChildren()){


                            Plato plato = p.getValue(Plato.class);
                            Log.e("NUUUUUUUUUUUUUUUUUUUUUUU", ""+p);
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
                                break;
                            }



                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*
        if (id != null){
            tvTitulo = findViewById(R.id.tvTitulo);
            tvDescripcion = findViewById(R.id.tvDescripcion);
            imgFoto = findViewById(R.id.imgFoto);

            tvTitulo.setText(item.getName());
            tvDescripcion.setText(item.getDescription());
            imgFoto.setImageResource(Integer.parseInt(item.getId()));
        }

         */


    }
}
