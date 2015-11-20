package com.android.ipm.mygymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    Button cancelButton;
    Button createAccButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        cancelButton = (Button) findViewById(R.id.register_cancel_button);
        createAccButton = (Button) findViewById(R.id.register_create_acc_button);

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
                Intent intent = new Intent(RegisterActivity.this,CreateProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
