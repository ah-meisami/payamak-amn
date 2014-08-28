package com.university.android;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.university.android.util.CherryEncoderUtil;

public class SendActivity extends Activity {

	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_send);

		buttonSend = (Button) findViewById(R.id.layout_send_btnSend);
		textPhoneNo = (EditText) findViewById(R.id.layout_send_phoneno);
		textSMS = (EditText) findViewById(R.id.layout_send_editsms);

		buttonSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String phoneNo = textPhoneNo.getText().toString();
				String sms = textSMS.getText().toString();

                String valuePK = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getString(R.string.str_publickey6), "");

                String strSecureSMS = "";
                try {
                   strSecureSMS = CherryEncoderUtil.getAESEncrypt(valuePK, sms);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    //Send SMS
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo, null, strSecureSMS, null, null);
                    //Save SMS
                    ContentValues values = new ContentValues();
                    values.put("address", phoneNo);
                    values.put("body", strSecureSMS);
                    getApplicationContext().getContentResolver().insert(Uri.parse("content://sms/sent"), values);

					Toast.makeText(getApplicationContext(), getString(R.string.str_send3), Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), getString(R.string.str_send4), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});

	}
}