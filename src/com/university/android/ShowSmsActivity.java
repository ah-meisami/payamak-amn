package com.university.android;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.university.android.util.CherryEncoderUtil;


public class ShowSmsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_showsms);

        Bundle extras = getIntent().getExtras();
        String str_reading_mode = "";
        if (extras != null) {
            str_reading_mode = extras.getString(getString(R.string.str_reading_mode));
        }

        String valuePK = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getString(R.string.str_publickey6), "");

        Cursor cursor = null;
        if(str_reading_mode.equals("inbox")){
            cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        } else if (str_reading_mode.equals("sent")){
            cursor = getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
        }
        // Get UI service for creating dynamic UI
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout_inbox_content = (LinearLayout) findViewById(R.id.layout_content); // Get parent UI id

        int i = 0;
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            i++;
            String smsAddress = cursor.getString(cursor.getColumnIndex("address"));
            String smsBody = cursor.getString(cursor.getColumnIndex("body"));
            try {
                smsBody = CherryEncoderUtil.getAESDecrypt(valuePK, smsBody);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("sms-address", smsAddress);
            Log.d("sms-body", smsBody);

            //*********************************************** Create UI Dynamically for each sms
            View view = inflater.inflate(R.layout.layout_sms , layout_inbox_content , false);
            ((TextView) view.findViewById(R.id.layout_sms_address)).setText(smsAddress);
            ((TextView) view.findViewById(R.id.layout_sms_body)).setText(smsBody);
            if(i%2==0){
                view.setBackgroundColor(Color.parseColor("#636161"));
            }else {
                view.setBackgroundColor(Color.parseColor("#6E954B"));
            }
            layout_inbox_content.addView(view);
            //***********************************************123
         }
    }
}
