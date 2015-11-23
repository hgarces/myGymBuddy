package com.android.ipm.mygymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.ipm.mygymbuddy.content.User;

public class CongratsActivity extends AppCompatActivity {

    User mUser;

    Button startBtn;
    TextView congrats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        mUser = (User) getIntent().getSerializableExtra(User.EXTRA);

        congrats = (TextView) findViewById(R.id.congrats_textView);
        congrats.setText("Parabens " + mUser.getNome() + "!!");
        startBtn = (Button) findViewById(R.id.start_training_button);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CongratsActivity.this,MainActivity.class);
                intent.putExtra(mUser.EXTRA,mUser);
                startActivity(intent);
            }
        });
    }
}
