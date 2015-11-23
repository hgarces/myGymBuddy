package com.android.ipm.mygymbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.ipm.mygymbuddy.content.User;

public class CreateProfileActivity extends AppCompatActivity {

    User mUser;

    Button createProfBtn;
    EditText name, age, weight, height;
    RadioGroup sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        mUser = (User) getIntent().getSerializableExtra(User.EXTRA);

        name = (EditText) findViewById(R.id.create_profile_name);
        age = (EditText) findViewById(R.id.create_profile_age_editText);
        weight = (EditText) findViewById(R.id.create_profile_weight_editText);
        height = (EditText) findViewById(R.id.create_profile_height_editText);
        sex = (RadioGroup) findViewById(R.id.create_profile_radioSex);
        createProfBtn = (Button) findViewById(R.id.create_profile_button);

        createProfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserInfo();
                Intent intent = new Intent(CreateProfileActivity.this, CongratsActivity.class);
                intent.putExtra(mUser.EXTRA, mUser);
                startActivity(intent);
            }
        });
    }

    private void setUserInfo() {
        mUser.setNome(name.getText().toString());
        mUser.setIdade(Integer.parseInt(age.getText().toString()));
        mUser.setPeso(Integer.parseInt(weight.getText().toString()));
        mUser.setAltura(Integer.parseInt(height.getText().toString()));

        int selectedSex = sex.getCheckedRadioButtonId();
        RadioButton radioSexButton = (RadioButton) findViewById(selectedSex);
        mUser.setSexo(radioSexButton.getText().toString());
    }

}