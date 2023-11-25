package com.example.nibm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText index, name, age, gender, mNo, pmNo;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        index = findViewById(R.id.index);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        mNo = findViewById(R.id.mNo);
        pmNo = findViewById(R.id.pmNo);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyDatabaseHelper mydb =  new MyDatabaseHelper(AddActivity.this);
                mydb.addStudent(index.getText().toString().trim(),
                        name.getText().toString().trim(),
                        age.getText().toString().trim(),
                        gender.getText().toString().trim(),
                        Integer.valueOf(mNo.getText().toString().trim()),
                        Integer.valueOf(pmNo.getText().toString().trim()));
            }
        });
    }
}