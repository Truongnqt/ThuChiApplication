package com.example.thuchiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SQLiteThuChi dbHelper;
    private ThuchiAdapter adapter;
    BroacastReceiver broacastReceiver;
    ListView listView;
    TextView sodu;
    ArrayList<ThuChi> tax = new ArrayList<>();
    ArrayList<ThuChi> filteredTax = new ArrayList<>();  // New array list to store filtered results
    SearchView searchView;
    FloatingActionButton FloAT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new SQLiteThuChi(this);
        dbHelper.addKhoanTien(new ThuChi("taikhoan", 400, "1/2/2003", true));
        dbHelper.addKhoanTien(new ThuChi("taikhoan", 500, "1/2/2003", true));
        dbHelper.addKhoanTien(new ThuChi("taikhoan", 300, "1/2/2003", false));
        tax = (ArrayList<ThuChi>) dbHelper.getAllSP();
        filteredTax.addAll(tax);  // Initialize the filtered list with all data
        adapter = new ThuchiAdapter(filteredTax, this);  // Use filteredTax instead of tax
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        sodu = findViewById(R.id.textView);
        sodu.setText("Số dư              " + TinhSodu());
        searchView = findViewById(R.id.searchView);
        FloAT=findViewById(R.id.btnAdd);
        FloAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AddThucChi.class);
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterData(query);  // Filter the data based on the query
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.isEmpty()) {
                    filteredTax.clear();
                    filteredTax.addAll(tax);

                }

// Show all items when the query is empty
                    adapter.notifyDataSetChanged();
                return false;
                }





        });
    }

    public void filterData(String query) {

            filteredTax.clear();  // Clear the filtered list
            double searchAmount = Double.valueOf(query);
            for (ThuChi thuChi : tax) {
                if (thuChi.getSotien() < searchAmount) {
                    filteredTax.add(thuChi);
                }
            }
            adapter.notifyDataSetChanged();  // Notify the adapter of the changes
        }



    public double TinhSodu() {
        double tongtien = 0;
        for (int i = 0; i < tax.size(); i++) {
            if (tax.get(i).isThuChi() == 1)
                tongtien += tax.get(i).getSotien();
            else if (tax.get(i).isThuChi() == 0)
                tongtien -= tax.get(i).getSotien();
        }
        return tongtien;
    }

}
