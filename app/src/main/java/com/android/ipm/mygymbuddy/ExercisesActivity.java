package com.android.ipm.mygymbuddy;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ExercisesActivity extends AppCompatActivity {
    Toolbar mToolbar;
    Spinner exercisesSpinner;
    EditText mTitleText;
    EditText mDescText;
    EditText mRepsText;
    EditText mDateText;
    EditText mTimeText;
    Calendar mCalendar;
    Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        init();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercises_list, android.R.layout.simple_spinner_dropdown_item);
        exercisesSpinner.setAdapter(adapter);
        exercisesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int sid = exercisesSpinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        mDateText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    new DatePickerDialog(ExercisesActivity.this, date, mCalendar
                            .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                            mCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExercisesActivity.this, "Evento criado com sucesso",
                        Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putAll(getBundle());
                Intent intent = new Intent(ExercisesActivity.this, MainActivity.class);
                intent.putExtra("Extras", bundle);
                startActivity(intent);
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mDateText.setText(sdf.format(mCalendar.getTime()));
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitleText = (EditText) findViewById(R.id.exercise_title);
        mDescText = (EditText) findViewById(R.id.exercise_desc);
        exercisesSpinner = (Spinner) findViewById(R.id.exercises_spinner);
        mRepsText = (EditText) findViewById(R.id.exercise_reps);
        mDateText = (EditText) findViewById(R.id.exercise_date);
        mTimeText = (EditText) findViewById(R.id.exercise_time);
        mSaveButton = (Button) findViewById(R.id.save_workout_button);
        mCalendar = Calendar.getInstance();
    }

    private Bundle getBundle() {
        Bundle b = new Bundle();
        b.putString("Title", mTitleText.toString());
        b.putString("Desc", mDescText.toString());
        b.putString("Exerc", exercisesSpinner.getSelectedItem().toString());
        b.putString("Reps", mRepsText.toString());
        b.putString("Date", mDateText.toString());
        return b;
    }
}
