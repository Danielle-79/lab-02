package com.example.lab2_w;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
ListView cityList;
ArrayAdapter <String> cityAdapter;
ArrayList<String> dataList;
Button buttonAdd;
Button buttonDelete;
Button buttonConfirm;
EditText input;
int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        buttonAdd = findViewById(R.id.AddButton);
        buttonDelete = findViewById(R.id.DeleteButton);
        buttonConfirm = findViewById(R.id.ConfirmButton);
        input = findViewById(R.id.editText);
        String []cities = {"Edmonton","Edmonton","Edmonton","Edmonton","Edmonton","Edmonton","Edmonton","Edmonton","Edmonton","Edmonton"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this,R.layout.content,dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setVisibility(View.VISIBLE);
                input.requestFocus();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedIndex == -1) {

                    return;
                }
                dataList.remove(selectedIndex);
                cityAdapter.notifyDataSetChanged();
                selectedIndex = -1;
            }
        });
        buttonConfirm = findViewById(R.id.ConfirmButton);

        buttonConfirm.setOnClickListener(v -> {
            String newCity = input.getText().toString().trim();
            if (newCity.isEmpty()) {
                return;
            }
            dataList.add(newCity);
            cityAdapter.notifyDataSetChanged();

            input.setText("");
            input.setVisibility(View.GONE);
        });

    }
}