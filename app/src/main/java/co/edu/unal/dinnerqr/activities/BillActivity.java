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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        precio = (TextView)findViewById(R.id.tvBillPrice);
        precio.setText(billprice.get(0).toString());
        lvBill = (ListView)findViewById(R.id.listabill);

        BillSoport john = new BillSoport("John","12-20-1998");
        BillSoport steve = new BillSoport("Steve","08-03-1987");
        BillSoport stacy = new BillSoport("Stacy","11-15-2000");
        BillSoport ashley = new BillSoport("Ashley","07-02-1999");
        BillSoport matt = new BillSoport("Matt","03-29-2001");

        ArrayList<BillSoport> peopleList = new ArrayList<>();
        peopleList.add(john);
        peopleList.add(steve);
        peopleList.add(stacy);
        peopleList.add(ashley);
        peopleList.add(matt);

        BillListAdapter adapter = new BillListAdapter(this, R.layout.adapter_view_layout, peopleList);
        lvBill.setAdapter(adapter);
    }
}
