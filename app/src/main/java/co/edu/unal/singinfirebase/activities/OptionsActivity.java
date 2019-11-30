package co.edu.unal.singinfirebase.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import co.edu.unal.singinfirebase.R;


public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

    }

    public void scan(View view){
        Intent scanear = new Intent(this, ScannAcativity.class);
        startActivity(scanear);
    }

}
