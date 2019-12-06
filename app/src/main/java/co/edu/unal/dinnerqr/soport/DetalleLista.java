package co.edu.unal.dinnerqr.soport;

import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unal.dinnerqr.R;

public class DetalleLista extends AppCompatActivity {
    private TextView tvTitulo, tvDescripcion;
    private ImageView imgFoto;
    private Entidad item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lista);
        item = (Entidad) getIntent().getSerializableExtra("item");
        if (item != null){
            tvTitulo = findViewById(R.id.tvTitulo);
            tvDescripcion = findViewById(R.id.tvDescripcion);
            imgFoto = findViewById(R.id.imgFoto);

            tvTitulo.setText(item.getTitulo());
            tvDescripcion.setText(item.getContenido());
            imgFoto.setImageResource(item.getImgFoto());
        }
    }
}
