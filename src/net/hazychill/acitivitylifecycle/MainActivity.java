package net.hazychill.acitivitylifecycle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static final String STATE_MESSAGES = "messages";
	private StringBuffer messages;

	public MainActivity() {
		super();
		messages = new StringBuffer();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		updateMessage("onCreate");
	}

	@Override
	protected void onDestroy() {
		updateMessage("onDestroy");

		super.onDestroy();
	}

	@Override
	protected void onPause() {
		updateMessage("onPause");

		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();

		updateMessage("onResume");
	}

	@Override
	protected void onStart() {
		super.onStart();

		updateMessage("onStart");
	}

	@Override
	protected void onStop() {
		updateMessage("onStop");

		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		updateMessage("onSaveInstanceState");
		outState.putString(STATE_MESSAGES, messages.toString());

		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		messages.append(savedInstanceState.getString(STATE_MESSAGES));

		updateMessage("onRestoreInstanceState");

		super.onRestoreInstanceState(savedInstanceState);
	}

	private void updateMessage(String event) {
		String message = getMessages(event);
		messages.append(message).append("\n");

		TextView messageView = (TextView) findViewById(R.id.message);
		if (messageView != null) {
			String[] array = messages.toString().split("\n");
			Arrays.sort(array);
			messages.setLength(0);
			for (String m : array) {
				if (m.length() >= 1) {
					messages.append(m).append("\n");
				}
			}
			messageView.setText(messages.toString());
		}
	}

	private String getMessages(String event) {
		Calendar nowCal = Calendar.getInstance();
		Date nowDate = nowCal.getTime();
		DateFormat format = new SimpleDateFormat(DATETIME_FORMAT,
				Locale.getDefault());
		String dateTimeString = format.format(nowDate);
		String message = dateTimeString + " " + event;

		return message;
	}

}
