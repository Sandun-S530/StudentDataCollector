package com.example.nibm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username_input, password_input;
    String username, password;
    AppCompatButton btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_input = findViewById(R.id.username);
        password_input = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username_input.getText().toString().trim();
                password = password_input.getText().toString().trim();

                if("admin".equals(username) && "1234".equals(password)){
                    Intent intent = new Intent(Login.this, AdminPanel.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}