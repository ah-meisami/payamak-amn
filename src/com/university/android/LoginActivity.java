package com.university.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private Button layout_publickey_btn_save;
    private EditText layout_publickey_edittext;
    private String value;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_publickey);


        layout_publickey_edittext = (EditText) findViewById(R.id.layout_publickey_edittext);
        layout_publickey_btn_save = (Button) findViewById(R.id.layout_publickey_btn_save);
        layout_publickey_btn_save.setText(getString(R.string.str_Login1));
        layout_publickey_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = layout_publickey_edittext.getText().toString().trim();

                if (value.equals("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.str_publickey3), Toast.LENGTH_SHORT).show();
                } else {
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(getString(R.string.str_publickey6), value).commit();
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

   @Override
    protected void onRestart() {
        super.onRestart();
        layout_publickey_edittext.setText("");
        finish();
    }
}
