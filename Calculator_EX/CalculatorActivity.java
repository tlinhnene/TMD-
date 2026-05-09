package com.LeThiTrucLinh.k234111e_mobileapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {
    Button btnCalculate;
    TextView txtMC, txtMR, txtMPlus, txtMMinus, txtMS, txtM;
    EditText editFormular;

    View.OnClickListener click_m_listener;

    String shared_pref_key = "CalculatorInfo";
    String formular_key = "saved_formular";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        addView();
        addEvents();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void addEvents() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processFormular();
            }
        });
        click_m_listener=new View.OnClickListener (){
            @Override
            public void onClick (View view) {
                if(view.equals(txtM))
                {

                }
                else if(view.equals(txtMC))
                {

                }
            }
        };
        txtM.setOnClickListener(click_m_listener);
        txtMC.setOnClickListener(click_m_listener);
        txtMR.setOnClickListener(click_m_listener);
        txtMPlus.setOnClickListener(click_m_listener);
        txtMMinus.setOnClickListener(click_m_listener);
        txtMS.setOnClickListener(click_m_listener);
    }

    private void processFormular() {
        EditText editFormular = findViewById(R.id.editFormular);
        String formular=editFormular.getText().toString();
        String result="";
        editFormular.setText(result);
    }

    private void addView() {
        btnCalculate = findViewById(R.id.btnCalculate);
        editFormular = findViewById(R.id.editFormular);

        txtMC = findViewById(R.id.txtMC);
        txtMR = findViewById(R.id.txtMR);
        txtMPlus = findViewById(R.id.txtMPLUS);
        txtMMinus = findViewById(R.id.txtMMINUS);
        txtMS = findViewById(R.id.txtMS);
        txtM = findViewById(R.id.txtM);
    }

    public void processChooseValue(View view) {
        //get current view clicked
        Button btn = (Button) view;
        EditText editFormular = findViewById(R.id.editFormular);
        String old_value = editFormular.getText().toString();
        String new_value = btn.getText().toString();
        String result = old_value + new_value;
        editFormular.setText(result);
    }
    public void processBackspace(View view) {
        EditText edtFormular=findViewById(R.id.editFormular);
        String old_value=edtFormular.getText().toString();
        String new_value="";
        if(old_value.length()>1)
        {
            //remove the last character:
            new_value=old_value.substring(0,old_value.length()-1);
        }
        edtFormular.setText(new_value);

    }
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences preferences = getSharedPreferences(shared_pref_key, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String formular = editFormular.getText().toString();
        editor.putString(formular_key, formular);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences(shared_pref_key, MODE_PRIVATE);
        String savedFormular = preferences.getString(formular_key, "");

        editFormular.setText(savedFormular);
    }

}