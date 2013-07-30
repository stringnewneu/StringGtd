package com.string.testhelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AnStringTestHelperActivity extends Activity {

	private Button btnTime1 = null;
	private Button btnTime2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_an_string_test_helper);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		final int minutes = 1;
		getMenuInflater().inflate(R.menu.an_string_test_helper, menu);
		btnTime1 = (Button) findViewById(R.id.btnTime1);
		btnTime1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TestHelper.setSystemTime(01, minutes);
			}
		});

		btnTime2 = (Button) findViewById(R.id.btnTime2);
		btnTime2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TestHelper.setSystemTime(02, minutes);
			}
		});

		return true;
	}


}
