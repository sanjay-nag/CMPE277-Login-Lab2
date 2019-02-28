package com.sanjay.cmpe277_login_lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    DatabaseHelper db;
    private EditText userName;
    private EditText password;
    private EditText confirmPassword;
    private Button register;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseHelper(this);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);

        userName.setText(getIntent().getStringExtra(MainActivity.USERNAME));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString().trim();
                String pswd = password.getText().toString().trim();
                String confirmPswd = confirmPassword.getText().toString().trim();

                if (pswd.equals(confirmPswd)) {
                    if (db.addUser(user, pswd) > 0) {
                        Toast.makeText(RegistrationActivity.this, "You have successfully registered!", Toast.LENGTH_SHORT).show();
                        Intent moveToLoginIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                        moveToLoginIntent.putExtra(MainActivity.USERNAME, user);
                        startActivity(moveToLoginIntent);
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Registration Error!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(RegistrationActivity.this, "Passwords not matching!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
