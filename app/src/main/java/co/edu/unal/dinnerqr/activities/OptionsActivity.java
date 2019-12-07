package co.edu.unal.dinnerqr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.unal.dinnerqr.R;
import co.edu.unal.dinnerqr.clases.Cliente;

import static co.edu.unal.dinnerqr.activities.DetalleLista.idUser;
import static co.edu.unal.dinnerqr.activities.RestaurantActivity.qrContend;


public class OptionsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView nombreUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cliente = database.getInstance().getReference("client");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        nombreUser = (TextView)findViewById(R.id.Tvname);
        mAuth = FirebaseAuth.getInstance();

        final FirebaseUser currentUser = mAuth.getCurrentUser();
        nombreUser.setText(currentUser.getEmail());
        cliente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for( DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Cliente currentClient = snapshot.getValue(Cliente.class);

                    if(currentClient.geteMail().equals(currentUser.getEmail())){
                        nombreUser.setText(currentClient.getName());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        readBill();
    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        nombreUser.setText(currentUser.getEmail());
    }

    public void scan(View view){
        Intent scanear = new Intent(this, ScannAcativity.class);
        startActivity(scanear);
    }

    @Override public void onBackPressed() {
        Intent optionsActivity = new Intent(this, OptionsActivity.class);
        startActivity(optionsActivity);
    }
    public void myBill(View view){
        Intent bill = new Intent(this, BillActivity.class);
        startActivity(bill);
    }
    public void readBill(){
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference cliente1 = database.getInstance().getReference("client");
        if(qrContend==null){
            final FirebaseUser currentUser = mAuth.getCurrentUser();
            cliente1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for( DataSnapshot snapshot: dataSnapshot.getChildren()){

                        Cliente currentClient = snapshot.getValue(Cliente.class);

                        if(currentClient.geteMail().equals(currentUser.getEmail())){
                            qrContend = currentClient.getBill();
                            idUser = currentClient.getId();
                            break;
                        }else{

                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        Log.e("qrcontend", ""+qrContend);
        Log.e("idUser", ""+idUser);
    }

}
