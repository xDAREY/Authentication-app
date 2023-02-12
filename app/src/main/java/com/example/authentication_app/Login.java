package com.example.authentication_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.authentication_app.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    DataBaseCon dataBaseCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataBaseCon = new DataBaseCon(this);

        binding.loginBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.inputUsername.getText().toString();
                String password = binding.inputPassword.getEditText().getText().toString();

                if (username.equals("") || password.equals(""))
                    Toast.makeText(Login.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkDetails = dataBaseCon.checkEmailPassword(username, password);

                    if (checkDetails == true){
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), landingPage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Invalid details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.signUpRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }
}