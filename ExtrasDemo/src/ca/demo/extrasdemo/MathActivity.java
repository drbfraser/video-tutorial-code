package ca.demo.extrasdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Simple activity to accept a value and return a new value.
 */
public class MathActivity extends Activity {
	
	public static final String EXTRA_ANSWER = "the answer";
	private int valueToWorkWith;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_math);
		
		// Get the intent that started us to find the parameter (extra)
		Intent intent = getIntent();
		valueToWorkWith = intent.getIntExtra(MainActivity.EXTRA_USER_NUMBER, 0);
		
		// Display the value to the screen.
		TextView displayNumber = (TextView) findViewById(R.id.textViewUserNumber);
		displayNumber.setText("" + valueToWorkWith);
		
		setupDoubleButton();
		setupCancelButton();
	}
	
	private void setupCancelButton() {
		Button btn =  (Button) findViewById(R.id.btnCancel);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Return answer to calling activity
				Intent intent = new Intent();
				setResult(Activity.RESULT_CANCELED, intent);
				finish();
			}
		});
	}

	private void setupDoubleButton() {
		Button btn =  (Button) findViewById(R.id.btnDoubleIt);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Compute the answer:
				int answer = valueToWorkWith * 2;
				
				// Return answer to calling activity
				Intent intent = new Intent();
				intent.putExtra(EXTRA_ANSWER, answer);
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.math, menu);
		return true;
	}

}
