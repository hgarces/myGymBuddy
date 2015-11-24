package com.android.ipm.mygymbuddy;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tyczj.extendedcalendarview.CalendarProvider;
import com.tyczj.extendedcalendarview.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        init();
        initEventListeners();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercises_list, android.R.layout.simple_spinner_dropdown_item);
        exercisesSpinner.setAdapter(adapter);

    }

    private void updateLabel() {
        mDateText.setText(new StringBuilder().append(mDay).append("/")
        .append(mMonth).append("/").append(mYear));
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

    private void initEventListeners() {
        exercisesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int sid = exercisesSpinner.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
                Intent intent = new Intent(ExercisesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                updateLabel();
            }

        };

        mDateText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(ExercisesActivity.this, datePickerListener, mCalendar
                            .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                            mCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
    }

    private void addEvent() {
        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Event.COLOR_BLUE);
        values.put(CalendarProvider.EVENT, mTitleText.getText().toString());
        values.put(CalendarProvider.DESCRIPTION, mDescText.getText().toString());

        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();

        cal.set(mYear, mMonth, mDay, 15, 5);
        int julianDay = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

        values.put(CalendarProvider.START, cal.getTimeInMillis());
        values.put(CalendarProvider.START_DAY, julianDay);

        cal.set(mYear, mMonth, mDay, 16, 0);
        int endJulianDay = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

        values.put(CalendarProvider.END, cal.getTimeInMillis());
        values.put(CalendarProvider.END_DAY, endJulianDay);

        getContentResolver().insert(CalendarProvider.CONTENT_URI, values);
        Toast.makeText(ExercisesActivity.this, "Evento criado com sucesso",
                Toast.LENGTH_SHORT).show();
    }

}
