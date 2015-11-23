package com.android.ipm.mygymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.ipm.mygymbuddy.content.User;

public class RegisterActivity extends AppCompatActivity {

    Button cancelButton;
    Button createAccButton;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cancelButton = (Button) findViewById(R.id.register_cancel_button);
        createAccButton = (Button) findViewById(R.id.register_create_acc_button);
        email = (EditText) findViewById(R.id.register_email_editText);
        password = (EditText) findViewById(R.id.register_password_editText);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        createAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().matches("") || password.getText().toString().matches("")) {
                    Toast.makeText(RegisterActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(RegisterActivity.this,CreateProfileActivity.class);
                    intent.putExtra(User.EXTRA, new User (null, password.getText().toString(), email.getText().toString(), null, 0, 0, 0));
                    startActivity(intent);
                }
            }
        });
    }
}
