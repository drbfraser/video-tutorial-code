package ca.demo.extrasdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Demonstrate passing a value to an activity, and returning a value 
 * from that activity using intents.
 */
public class MainActivity extends Activity {

    private static final int RESULT_CODE_DO_MATH = 42;
	public static final String EXTRA_USER_NUMBER = "user number";


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupDoMathButton();
    }


    private void setupDoMathButton() {
    	
    	Button btn = (Button) findViewById(R.id.btnDoMath);
    	btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText userTextEntry = (EditText) findViewById(R.id.editTextUserNumber);
				String userData = userTextEntry.getText().toString();
				int userNumber = Integer.parseInt(userData);

				Intent intent = new Intent(MainActivity.this, MathActivity.class);
				intent.putExtra(EXTRA_USER_NUMBER, userNumber);
				startActivityForResult(intent, RESULT_CODE_DO_MATH);
			}
		});
	}

    // Gets called when the activity we started, finishes.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == Activity.RESULT_CANCELED) {
			TextView answerView = (TextView) findViewById(R.id.textViewAnswer);
			answerView.setText("User canceled!");
			return;
		}
		
		switch (requestCode) {
		case RESULT_CODE_DO_MATH:
			int answer = data.getIntExtra(MathActivity.EXTRA_ANSWER, 0);
			TextView answerView = (TextView) findViewById(R.id.textViewAnswer);
			answerView.setText("" + answer);
			break;
		}
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
