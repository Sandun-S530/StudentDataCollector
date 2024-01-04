package com.example.nibm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText index, name, age, mNo, pmNo;
    Spinner gender;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        index = findViewById(R.id.index);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.genderSpinner);
        mNo = findViewById(R.id.mNo);
        pmNo = findViewById(R.id.pmNo);
        add_button = findViewById(R.id.add_button);

        // Set the input filter to limit the length to 10 characters
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(10);
        mNo.setFilters(filters);
        pmNo.setFilters(filters);

        // Set the input filter to limit the length to 2 characters
        InputFilter[] ageFilters = new InputFilter[1];
        ageFilters[0] = new InputFilter.LengthFilter(2);
        age.setFilters(ageFilters);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.genders_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        gender.setAdapter(adapter);

        // Set a prompt to be displayed in the Spinner
        gender.setPrompt("Choose Gender");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Add Student");
            Log.d("YourActivity", "Up button enabled");
        } else {
            Log.e("YourActivity", "Action bar is null");
        }

        add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Get the selected item from the Spinner
                String selectedGender = String.valueOf(gender.getSelectedItem());

                // Trim the value
                String trimmedGender = selectedGender.trim();

                if (trimmedGender.equals("Choose gender")) {
                    // Show a Toast message indicating that the user needs to choose a gender
                    Toast.makeText(AddActivity.this, "Please choose a gender", Toast.LENGTH_SHORT).show();
                    // Optionally, you can prevent further processing until a gender is chosen
                    return;
                }

                MyDatabaseHelper mydb =  new MyDatabaseHelper(AddActivity.this);
                mydb.addStudent(index.getText().toString().trim(),
                        name.getText().toString().trim(),
                        age.getText().toString().trim(),
                        trimmedGender,
                        mNo.getText().toString().trim(),
                        pmNo.getText().toString().trim());
            }
        });
    }
}