package com.emmyvera.emmydev.mybmiapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView mTextResult, mTextReport;
    EditText mEditWeigth, mEditHeigth;
    Button mCalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextResult = findViewById(R.id.textResult);
        mTextReport = findViewById(R.id.textReport);
        mEditWeigth = findViewById(R.id.inputWeigth);
        mEditHeigth = findViewById(R.id.inputHeight);
        mCalButton = findViewById(R.id.calButton);

        mCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalBMI();
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
            }
        });
    }

    public void CalBMI(){

        String height = mEditHeigth.getText().toString();
        String weight = mEditWeigth.getText().toString();
        String result;
        String report;

        if (!height.isEmpty() && !weight.isEmpty()){
            double numHeight = Double.parseDouble(height);
            double numWeight = Double.parseDouble(weight);

            double numResult = (numWeight)/(numHeight * numHeight);
            result = Double.toString(numResult);
            report = ReportBMI(numResult);
            mTextResult.setText(result);
            mTextReport.setText(report);

        }else {
            Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_LONG).show();
            mTextResult.setText("0");
            mTextReport.setText("");
        }
    }

    public String ReportBMI(double BMI){

        String BMI_Cat = "";

        if (BMI < 15){
            BMI_Cat = "Very severely underweight";
            mTextReport.setTextColor(getApplication().getResources().getColor(R.color.colorAccent));
        }
        else if (BMI < 16){
            BMI_Cat = "Severely underweight";
            mTextReport.setTextColor(getApplication().getResources().getColor(R.color.colorAccent));
        }
        else if (BMI < 18.5) {
            BMI_Cat = "Underweight";
            mTextReport.setTextColor(getApplication().getResources().getColor(R.color.colorSecondary));
        }
        else if (BMI < 25) {
            BMI_Cat = "Normal";
            mTextReport.setTextColor(getApplication().getResources().getColor(R.color.colorPrimary));
        }
        else if (BMI < 30) {
            BMI_Cat = "Overweight";
            mTextReport.setTextColor(getApplication().getResources().getColor(R.color.colorSecondary));
        }
        else if (BMI < 35) {
            BMI_Cat = "Obese Class 1 - Moderately Obese";
            mTextReport.setTextColor(getApplication().getResources().getColor(R.color.colorAccent));
        }
        else if (BMI < 40) {
            BMI_Cat = "Obese Class 2 - Severely Obese";
            mTextReport.setTextColor(getApplication().getResources().getColor(R.color.colorAccent));
        }
        else {
            BMI_Cat = "Obese Class 3 - Very Severely Obese";
            mTextReport.setTextColor(getApplication().getResources().getColor(R.color.colorAccent));
        }

        return BMI_Cat;
    }

}
