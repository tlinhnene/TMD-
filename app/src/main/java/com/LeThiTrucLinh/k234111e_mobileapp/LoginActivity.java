package com.LeThiTrucLinh.k234111e_mobileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    /*
     declare all view as variable
     **/
    EditText edt_username;
    EditText edt_password;
    TextView txtMessage;
    CheckBox chkSaveInfo;
    String shared_pref_key="LoginInfo";

    RadioButton radAdmin,radEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        addViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews() {
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        txtMessage=findViewById(R.id.txtMessage);
        chkSaveInfo=findViewById(R.id.chkSaveInfo);
        radAdmin=findViewById(R.id.radAdmin);
        radEmployee=findViewById(R.id.radEmployee);
    }

    public void loginSystem(View view) {
        String username = edt_username.getText().toString();
        String password = edt_password.getText().toString();
        boolean saved = chkSaveInfo.isChecked();
        if (username.equalsIgnoreCase("admin") && password.equals("123")) {
            txtMessage.setText(getString(R.string.str_login_successful));
            //save Login In4
            SharedPreferences preferences=getSharedPreferences(shared_pref_key,MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("Username",username);
            editor.putString("Password",password);
            editor.putBoolean("Saved", saved);

            editor.commit();
            if (radAdmin.isChecked()){
                Intent intent =new Intent (LoginActivity.this,MainActivity.class);
                startActivity(intent);

            }
            else{
                Intent intent =new Intent(LoginActivity.this, EmployeeManagementActivity.class);
                startActivity(intent);
            }


        } else {
            txtMessage.setText(getString(R.string.str_login_failed));
        }
    }

    public void exitSystem(View view) {
        finish();
    }
    @Override
    protected void onResume() {

        super.onResume();

        SharedPreferences preferences = getSharedPreferences(shared_pref_key, MODE_PRIVATE);

        String username = preferences.getString("Username", "");
        String password = preferences.getString("Password", "");
        boolean saved = preferences.getBoolean("Saved", false);

        if (saved) {
            edt_username.setText(username);
            edt_password.setText(password);
        }

        chkSaveInfo.setChecked(saved);
    }


}