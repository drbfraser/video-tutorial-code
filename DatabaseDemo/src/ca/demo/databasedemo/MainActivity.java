package ca.demo.databasedemo;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
 * Steps to using the DB:
 * 1. [DONE] Instantiate the DB Adapter
 * 2. [DONE] Open the DB
 * 3. [DONE] use get, insert, delete, .. to change data.
 * 4. [DONE]Close the DB
 */

/**
 * Demo application to show how to use the 
 * built-in SQL lite database.
 */
public class MainActivity extends Activity {
	
	DBAdapter myDb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		openDB();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();	
		closeDB();
	}


	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}
	private void closeDB() {
		myDb.close();
	}

	
	
	private void displayText(String message) {
        TextView textView = (TextView) findViewById(R.id.textDisplay);
        textView.setText(message);
	}
	
	

	public void onClick_AddRecord(View v) {
		displayText("Clicked add record!");
		
		long newId = myDb.insertRow("Jenny", 5559, "Green");
		
		// Query for the record we just added.
		// Use the ID:
		Cursor cursor = myDb.getRow(newId);
		displayRecordSet(cursor);
	}

	public void onClick_ClearAll(View v) {
		displayText("Clicked clear all!");
		myDb.deleteAll();
	}

	public void onClick_DisplayRecords(View v) {
		displayText("Clicked display record!");
		
		Cursor cursor = myDb.getAllRows();
		displayRecordSet(cursor);
	}
	
	// Display an entire recordset to the screen.
	private void displayRecordSet(Cursor cursor) {
		String message = "";
		// populate the message from the cursor
		
		// Reset cursor to start, checking to see if there's data:
		if (cursor.moveToFirst()) {
			do {
				// Process the data:
				int id = cursor.getInt(DBAdapter.COL_ROWID);
				String name = cursor.getString(DBAdapter.COL_NAME);
				int studentNumber = cursor.getInt(DBAdapter.COL_STUDENTNUM);
				String favColour = cursor.getString(DBAdapter.COL_FAVCOLOUR);
				
				// Append data to the message:
				message += "id=" + id
						   +", name=" + name
						   +", #=" + studentNumber
						   +", Colour=" + favColour
						   +"\n";
			} while(cursor.moveToNext());
		}
		
		// Close the cursor to avoid a resource leak.
		cursor.close();
		
		displayText(message);
	}
}










