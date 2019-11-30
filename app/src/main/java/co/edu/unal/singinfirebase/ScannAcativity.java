package co.edu.unal.singinfirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class ScannAcativity extends AppCompatActivity  {
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    public TextView tvBarCode;
    public ImageButton imageButtonQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scann_acativity);
        tvBarCode = (TextView)findViewById(R.id.QrInfo);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Explicamos porque necesitamos el permiso
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            // Acá continuamos el procesos deseado a hacer
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        new IntentIntegrator(ScannAcativity.this).initiateScan();
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null)
            if (result.getContents() != null){
                tvBarCode.setText("El código de barras es:\n" + result.getContents());
            }else{
                tvBarCode.setText("Error al escanear el código de barras");

            }
    }


}
