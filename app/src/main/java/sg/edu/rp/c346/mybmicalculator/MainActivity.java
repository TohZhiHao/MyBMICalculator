package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;
    TextView tvDate, tvBMI, tvDisplay;
    Button calculate, reset;

    @Override
    protected void onPause() {
        super.onPause();

        String date = tvDate.getText().toString();
        String bmi = tvBMI.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("date", date);
        //prefEdit.putString("gpa", strGPA);
        prefEdit.putString("bmi", bmi);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String date = prefs.getString("date", "Last calculated date: ");
        String bmi = prefs.getString("bmi", "Last calculated BMI: ");


        tvDate.setText(date);
        tvBMI.setText(String.valueOf(bmi));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etWeight = findViewById(R.id.editTextWeight);
        etHeight = findViewById(R.id.editTextHeight);

        tvDate = findViewById(R.id.textViewDate);
        tvBMI = findViewById(R.id.textViewBMI);
        tvDisplay = findViewById(R.id.textViewDisplay);

        calculate = findViewById(R.id.buttonCalculate);
        reset = findViewById(R.id.buttonResetData);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weight = Float.parseFloat(etWeight.getText().toString());
                float height = Float.parseFloat(etHeight.getText().toString());
                float bmi = (weight/(height*height));

                String msg = String.format("Last calculated BMI: "+ bmi);
                tvBMI.setText(msg);

                Calendar now = Calendar.getInstance();
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);
                tvDate.setText("Last calculated date: " + datetime);

                etWeight.setText("");
                etHeight.setText("");

            }
        });

    }
}
