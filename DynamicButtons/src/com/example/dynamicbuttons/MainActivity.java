package com.example.dynamicbuttons;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Demo on creating dynamic buttons in a table and 
 * adding background icons.
 */
public class MainActivity extends Activity {

    private static final int NUM_ROWS = 7;
	private static final int NUM_COLS = 10;
	
	Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        populateButtons();
    }


    private void populateButtons() {
    	TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);
    	
    	for (int row = 0; row < NUM_ROWS; row++) {
    		TableRow tableRow = new TableRow(this);
    		tableRow.setLayoutParams(new TableLayout.LayoutParams(
    				TableLayout.LayoutParams.MATCH_PARENT,
    				TableLayout.LayoutParams.MATCH_PARENT,
    				1.0f));
    		table.addView(tableRow);
    		
    		for (int col = 0; col < NUM_COLS; col++){ 
    			final int FINAL_COL = col;
    			final int FINAL_ROW = row;
    			
    			Button button = new Button(this);
    			button.setLayoutParams(new TableRow.LayoutParams(
    					TableRow.LayoutParams.MATCH_PARENT,
    					TableRow.LayoutParams.MATCH_PARENT,
    					1.0f));
    			
    			button.setText("" + col + "," + row);
    			
    			// Make text not clip on small buttons
    			button.setPadding(0, 0, 0, 0);
    			
    			button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						gridButtonClicked(FINAL_COL, FINAL_ROW);
					}
				});
    			
    			tableRow.addView(button);
    			buttons[row][col] = button;
    		}
    	}
	}

    private void gridButtonClicked(int col, int row) {
    	Toast.makeText(this, "Button clicked: " + col + "," + row,
    			Toast.LENGTH_SHORT).show();
    	Button button = buttons[row][col];
    	
    	// Lock Button Sizes:
    	lockButtonSizes();
    	
    	// Does not scale image.
//    	button.setBackgroundResource(R.drawable.action_lock_pink);
    	
    	// Scale image to button: Only works in JellyBean!
    	// Image from Crystal Clear icon set, under LGPL
    	// http://commons.wikimedia.org/wiki/Crystal_Clear
		int newWidth = button.getWidth();
		int newHeight = button.getHeight();
		Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.action_lock_pink);
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
		Resources resource = getResources();
		button.setBackground(new BitmapDrawable(resource, scaledBitmap));
		
		// Change text on button:
		button.setText("" + col);

    }

	private void lockButtonSizes() {
    	for (int row = 0; row < NUM_ROWS; row++) {
    		for (int col = 0; col < NUM_COLS; col++) {
    			Button button = buttons[row][col];
    			
    			int width = button.getWidth();
    			button.setMinWidth(width);
    			button.setMaxWidth(width);
    			
				int height = button.getHeight();
				button.setMinHeight(height);
				button.setMaxHeight(height);
    		}
    	}		
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
