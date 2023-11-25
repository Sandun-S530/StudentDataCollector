package com.example.nibm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminPanel extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> id, index, name, age, gender, mNo, pmNo;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(AdminPanel.this);
        id = new ArrayList<>();
        index = new ArrayList<>();
        name = new ArrayList<>();
        age = new ArrayList<>();
        gender = new ArrayList<>();
        mNo = new ArrayList<>();
        pmNo = new ArrayList<>();

        StoreDataInArrays();

        customAdapter = new CustomAdapter(AdminPanel.this, id, index, name, age, gender, mNo, pmNo);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminPanel.this));
    }

    void StoreDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                index.add(cursor.getString(1));
                name.add(cursor.getString(2));
                age.add(cursor.getString(3));
                gender.add(cursor.getString(4));
                mNo.add(cursor.getString(5));
                pmNo.add(cursor.getString(6));
            }
        }
    }
}