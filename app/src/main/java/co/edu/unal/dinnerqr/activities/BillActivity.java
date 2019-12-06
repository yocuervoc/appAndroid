package co.edu.unal.dinnerqr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import co.edu.unal.dinnerqr.R;

import static co.edu.unal.dinnerqr.activities.DetalleLista.billName;
import static co.edu.unal.dinnerqr.activities.DetalleLista.billprice;

public class BillActivity extends AppCompatActivity {
    private TextView precio;
    private ListView lvBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        precio = (TextView)findViewById(R.id.tvBillPrice);
        precio.setText(billprice.get(0).toString());


        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.list_item_bill, billName);
        //lvBill.setAdapter(adaptador);
    }
}
