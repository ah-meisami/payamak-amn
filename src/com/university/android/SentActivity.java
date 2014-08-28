package com.university.android;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.university.android.util.CherryEncoderUtil;

public class SentActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sent);

        String valuePK = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getString(R.string.str_publickey6), "");

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
        cursor.moveToFirst();
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout_inbox_content = (LinearLayout) findViewById(R.id.layout_content);
        do{
            String smsAddress = cursor.getString(cursor.getColumnIndex("address"));
            String smsBody = cursor.getString(cursor.getColumnIndex("body"));
            try {
                smsBody = CherryEncoderUtil.getAESDecrypt(valuePK, smsBody);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            Log.d("sms-address", smsAddress);
//            Log.d("sms-body", smsBody);
            View view = inflater.inflate(R.layout.layout_sms , layout_inbox_content , false);
            ((TextView) view.findViewById(R.id.layout_sms_address)).setText(smsAddress);
            ((TextView) view.findViewById(R.id.layout_sms_body)).setText(smsBody);
            layout_inbox_content.addView(view);
        }while(cursor.moveToNext());
    }
}
