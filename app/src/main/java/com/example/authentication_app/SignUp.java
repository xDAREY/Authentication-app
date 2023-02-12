package com.example.authentication_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.authentication_app.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DataBaseCon DbCon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        binding =ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DbCon = new DataBaseCon(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.hintUsername.getText().toString();
                String password = binding.inputPassword.getEditText().getText().toString();
                String confirmPass = binding.inputRePassword.getEditText().getText().toString();

                if (username.equals("") || password.equals("") || confirmPass.equals(""))
                    Toast.makeText(SignUp.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirmPass)) {
                        Boolean checkUserEmail = DbCon.checkEmail(username);

                        if (checkUserEmail == false) {
                            Boolean insert = DbCon.insertData(username, password);

                            if (insert == true) {
                                Toast.makeText(SignUp.this, "Account was created successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUp.this, "Failed to create an account", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUp.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        binding.redirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}