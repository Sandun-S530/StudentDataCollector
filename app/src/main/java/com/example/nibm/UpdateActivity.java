package com.example.nibm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText index_input, name_input, age_input, gender_input, mobile_input, home_input;
    Button update_button;

    String id, index, name, age, gender, mobile, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        index_input = findViewById(R.id.index2);
        name_input = findViewById(R.id.name2);
        age_input = findViewById(R.id.age2);
        gender_input = findViewById(R.id.gender2);
        mobile_input = findViewById(R.id.mNo2);
        home_input = findViewById(R.id.pmNo2);
        update_button = findViewById(R.id.update_button);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper mydb = new MyDatabaseHelper(UpdateActivity.this);
                index = index_input.getText().toString().trim();
                name = name_input.getText().toString().trim();
                age = age_input.getText().toString().trim();
                gender = gender_input.getText().toString().trim();
                mobile = mobile_input.getText().toString().trim();
                home = home_input.getText().toString().trim();
                mydb.updateData(id, index, name, age, gender, mobile, home);
            }
        });



    }

    void getAndSetIntentData (){
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("index") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("age") &&
                getIntent().hasExtra("gender") &&
                getIntent().hasExtra("mobile") &&
                getIntent().hasExtra("home") ){

            //Getting data from intent
            id = getIntent().getStringExtra("id");
            index = getIntent().getStringExtra("index");
            name = getIntent().getStringExtra("name");
            age = getIntent().getStringExtra("age");
            gender = getIntent().getStringExtra("gender");
            mobile = getIntent().getStringExtra("mobile");
            home = getIntent().getStringExtra("home");

            //Setting Intent Data
            index_input.setText(index);
            name_input.setText(name);
            age_input.setText(age);
            gender_input.setText(gender);
            mobile_input.setText(mobile);
            home_input.setText(home);

        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}