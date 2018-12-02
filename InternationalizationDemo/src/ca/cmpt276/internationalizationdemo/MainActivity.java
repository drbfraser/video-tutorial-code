package ca.cmpt276.internationalizationdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setupButton(R.id.btnHello, R.string.hello_world);
		setupButton(R.id.btnBye, R.string.goodbye_world);
	}

	private void setupButton(int buttonId, final int messageId) {
		Button btn = (Button) findViewById(buttonId);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textView = (TextView) findViewById(R.id.txtMessage);
//				textView.setText(messageId);
				
				// Get string from strings.xml
				String message = getString(messageId);
				textView.setText(message);

			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
