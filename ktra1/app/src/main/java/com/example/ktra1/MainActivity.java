package com.example.ktra1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ktra1.model.Logo;
import com.example.ktra1.model.LogoAdapter;
import com.example.ktra1.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LogoAdapter.LogoItemListener, SearchView.OnQueryTextListener{

    private Spinner sp;
    private RecyclerView recyclerView;
    private LogoAdapter adapter;
    private EditText eName, eDesc, ePrice;
    private Button btAdd, btUpdate;
    private int posCurent;
    private CheckBox c1,c2,c3;
    private SearchView searchView;
    private int imgs[] = {R.drawable.anh1, R.drawable.anh2, R.drawable.anh3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter = new LogoAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logo logo = new Logo();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String d = eDesc.getText().toString();
                String p = ePrice.getText().toString();
                boolean iswifi = c1.isChecked();
                boolean isdh = c2.isChecked();
                boolean ismg = c3.isChecked();
                int img = R.drawable.anh1;
                double price = 0;
                double dientich = 0;
                try {
                    img = imgs[Integer.parseInt(i)];
                    dientich = Double.parseDouble(d);
                    price = Double.parseDouble(p);
                    logo.setImg(img);
                    logo.setName(name);
                    logo.setDesc(dientich);
                    logo.setPrice(price);
                    logo.setDieuhoa(isdh);
                    logo.setWifi(iswifi);
                    logo.setMaygiat(ismg);
                    adapter.add(logo);

                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logo logo = new Logo();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String d = eDesc.getText().toString();
                String p = ePrice.getText().toString();
                boolean iswifi = c1.isChecked();
                boolean isdh = c2.isChecked();
                boolean ismg = c3.isChecked();
                int img = R.drawable.anh1;
                double price = 0;
                double dientich=0;
                try {
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    dientich = Double.parseDouble(d);
                    logo.setImg(img);
                    logo.setName(name);
                    logo.setDesc(dientich);
                    logo.setPrice(price);
                    logo.setDieuhoa(isdh);
                    logo.setWifi(iswifi);
                    logo.setMaygiat(ismg);
                    adapter.update(posCurent, logo);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        sp = findViewById(R.id.sp);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        sp.setAdapter(adapter);
        recyclerView = findViewById(R.id.recycleView);
        eName = findViewById(R.id.name);
        eDesc = findViewById(R.id.decribe);
        ePrice = findViewById(R.id.price);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);
        c1 = findViewById(R.id.checkbox1);
        c2 = findViewById(R.id.checkbox2);
        c3 = findViewById(R.id.checkbox3);

    }

    @Override
    public void onItemClick(View view, int pos) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        posCurent = pos;
        Logo logo = adapter.getItem(pos);
        int img = logo.getImg();
        int p = 0;
        for (int i = 0; i<imgs.length; i++) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
        sp.setSelection(p);
        eName.setText(logo.getName());
        eDesc.setText(logo.getDesc()+"");
        ePrice.setText(logo.getPrice()+"");
        c1.setChecked(logo.isWifi());
        c2.setChecked(logo.isDieuhoa());
        c3.setChecked(logo.isMaygiat());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    private void filter(String s) {
        List<Logo> filterlist = new ArrayList<>();
        for(Logo i:adapter.getBackup()){
            if(i.getName().toLowerCase().contains(s.toLowerCase())){
                filterlist.add(i);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.filterList(filterlist);
        }
    }
}