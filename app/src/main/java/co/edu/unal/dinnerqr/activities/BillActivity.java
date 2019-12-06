package co.edu.unal.dinnerqr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.unal.dinnerqr.R;
import co.edu.unal.dinnerqr.soport.BillListAdapter;
import co.edu.unal.dinnerqr.soport.BillSoport;

import static co.edu.unal.dinnerqr.activities.DetalleLista.billName;
import static co.edu.unal.dinnerqr.activities.DetalleLista.billprice;

public class BillActivity extends AppCompatActivity {
    private TextView precio;
    private ListView lvBill;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        precio = (TextView)findViewById(R.id.tvBillPrice);
        
        lvBill = (ListView)findViewById(R.id.listabill);

        ArrayList<BillSoport> peopleList = new ArrayList<>();
        for(int i=0; i<billName.size(); i++){
            peopleList.add(new BillSoport(billName.get(i), billprice.get(i).toString()));
            total += billprice.get(i);
        }
        precio.setText(Double.toString(total));


        BillListAdapter adapter = new BillListAdapter(this, R.layout.adapter_view_layout, peopleList);
        lvBill.setAdapter(adapter);
    }
}
