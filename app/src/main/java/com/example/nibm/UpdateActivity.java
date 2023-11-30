package com.example.nibm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText id_input, index_input, name_input, age_input, gender_input, mobile_input, home_input;
    Button update_button, delete_button;
    String id, index ,name ,age, gender, mobile, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        id_input = findViewById(R.id.id);
        index_input = findViewById(R.id.index2);
        name_input = findViewById(R.id.name2);
        age_input = findViewById(R.id.age2);
        gender_input = findViewById(R.id.gender2);
        mobile_input = findViewById(R.id.mNo2);
        home_input = findViewById(R.id.pmNo2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(index);
            Log.d("YourActivity", "Up button enabled");
        } else {
            Log.e("YourActivity", "Action bar is null");
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper mydb = new MyDatabaseHelper(UpdateActivity.this);
                id = id_input.getText().toString().trim();
                index = index_input.getText().toString().trim();
                name = name_input.getText().toString().trim();
                age = age_input.getText().toString().trim();
                gender = gender_input.getText().toString().trim();
                mobile = mobile_input.getText().toString().trim();
                home = home_input.getText().toString().trim();

                // Check if a record with the same index (excluding the current record) already exists before updating
                if (mydb.isIndexAlreadyExists(id, index)) {
                    Toast.makeText(UpdateActivity.this, "Record with the same index already exists. Update failed.", Toast.LENGTH_SHORT).show();
                }
                if (index.isEmpty() || name.isEmpty() || age.isEmpty() || gender.isEmpty() || mobile.isEmpty() || home.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    mydb.updateData(id, index, name, age, gender, mobile, home);
                    finish();
                }
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
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
            id_input.setText(id);
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

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + index + " ?");
        builder.setMessage("Are you sure you want to delete this record ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}