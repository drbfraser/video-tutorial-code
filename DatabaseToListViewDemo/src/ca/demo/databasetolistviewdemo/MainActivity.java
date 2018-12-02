package ca.demo.databasetolistviewdemo;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


/**
 * Demo application to show how to use the 
 * built-in SQLite database with a cursor to populate
 * a ListView.
 */
public class MainActivity extends Activity {
	int[] imageIDs = {
			R.drawable.bug,
			R.drawable.down,
			R.drawable.fish,
			R.drawable.heart,
			R.drawable.help,
			R.drawable.lightning,
			R.drawable.star,
			R.drawable.up	
			};
	int nextImageIndex = 0;
	
	DBAdapter myDb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		openDB();
		populateListViewFromDB();
		registerListClickCallback();
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

	/* 
	 * UI Button Callbacks
	 */
	public void onClick_AddRecord(View v) {
		int imageId = imageIDs[nextImageIndex];
		nextImageIndex = (nextImageIndex + 1) % imageIDs.length;

		// Add it to the DB and re-draw the ListView
		myDb.insertRow("Jenny" + nextImageIndex, imageId, "Green");
		populateListViewFromDB();
	}

	public void onClick_ClearAll(View v) {
		myDb.deleteAll();
		populateListViewFromDB();
	}


	private void populateListViewFromDB() {
		Cursor cursor = myDb.getAllRows();
		
		// Allow activity to manage lifetime of the cursor.
		// DEPRECATED! Runs on the UI thread, OK for small/short queries.
		startManagingCursor(cursor);
		
		// Setup mapping from cursor to view fields:
		String[] fromFieldNames = new String[] 
				{DBAdapter.KEY_NAME, DBAdapter.KEY_STUDENTNUM, DBAdapter.KEY_FAVCOLOUR, DBAdapter.KEY_STUDENTNUM};
		int[] toViewIDs = new int[]
				{R.id.item_name,     R.id.item_icon,           R.id.item_favcolour,     R.id.item_studentnum};
		
		// Create adapter to may columns of the DB onto elemesnt in the UI.
		SimpleCursorAdapter myCursorAdapter = 
				new SimpleCursorAdapter(
						this,		// Context
						R.layout.item_layout,	// Row layout template
						cursor,					// cursor (set of DB records to map)
						fromFieldNames,			// DB Column names
						toViewIDs				// View IDs to put information in
						);
		
		// Set the adapter for the list view
		ListView myList = (ListView) findViewById(R.id.listViewFromDB);
		myList.setAdapter(myCursorAdapter);
	}
	
	private void registerListClickCallback() {
		ListView myList = (ListView) findViewById(R.id.listViewFromDB);
		myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, 
					int position, long idInDB) {

				updateItemForId(idInDB);
				displayToastForId(idInDB);
			}
		});
	}
	
	private void updateItemForId(long idInDB) {
		Cursor cursor = myDb.getRow(idInDB);
		if (cursor.moveToFirst()) {
			long idDB = cursor.getLong(DBAdapter.COL_ROWID);
			String name = cursor.getString(DBAdapter.COL_NAME);
			int studentNum = cursor.getInt(DBAdapter.COL_STUDENTNUM);
			String favColour = cursor.getString(DBAdapter.COL_FAVCOLOUR);

			favColour += "!";
			myDb.updateRow(idInDB, name, studentNum, favColour);
		}
		cursor.close();
		populateListViewFromDB();		
	}
	
	private void displayToastForId(long idInDB) {
		Cursor cursor = myDb.getRow(idInDB);
		if (cursor.moveToFirst()) {
			long idDB = cursor.getLong(DBAdapter.COL_ROWID);
			String name = cursor.getString(DBAdapter.COL_NAME);
			int studentNum = cursor.getInt(DBAdapter.COL_STUDENTNUM);
			String favColour = cursor.getString(DBAdapter.COL_FAVCOLOUR);
			
			String message = "ID: " + idDB + "\n" 
					+ "Name: " + name + "\n"
					+ "Std#: " + studentNum + "\n"
					+ "FavColour: " + favColour;
			Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
		}
		cursor.close();
	}
}










