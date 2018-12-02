package ca.demo.demolistview.complex;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Demonstrate how to populate a complex ListView with icon and text.
 * Icon images taken from icon pack by Everaldo Coelho (http://www.everaldo.com)
 */
public class MainActivity extends Activity {
	private List<Car> myCars = new ArrayList<Car>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		populateCarList();
		populateListView();
		registerClickCallback();
	}
	private void populateCarList() {
		myCars.add(new Car("Ford", 1940, R.drawable.help, "Needing work"));
		myCars.add(new Car("Toyota", 1994, R.drawable.heart, "Lovable"));
		myCars.add(new Car("Honda", 1999, R.drawable.fish, "Wet"));
		myCars.add(new Car("Porche", 2005, R.drawable.lightning, "Fast!"));
		myCars.add(new Car("Jeep", 200, R.drawable.star, "Awesome"));
		myCars.add(new Car("Rust-Bucket", 2010, R.drawable.bug, "Not *very* good"));
		myCars.add(new Car("Moon Lander", 1971, R.drawable.up, "Out of this world"));
	}
	
	private void populateListView() {
		ArrayAdapter<Car> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.carsListView);
		list.setAdapter(adapter);
	}
	
	private void registerClickCallback() {
		ListView list = (ListView) findViewById(R.id.carsListView);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked,
					int position, long id) {
				
				Car clickedCar = myCars.get(position);
				String message = "You clicked position " + position
								+ " Which is car make " + clickedCar.getMake();
				Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
			}
		});
	}
	
	private class MyListAdapter extends ArrayAdapter<Car> {
		public MyListAdapter() {
			super(MainActivity.this, R.layout.item_view, myCars);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure we have a view to work with (may have been given null)
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
			}
			
			// Find the car to work with.
			Car currentCar = myCars.get(position);
			
			// Fill the view
			ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
			imageView.setImageResource(currentCar.getIconID());
			
			// Make:
			TextView makeText = (TextView) itemView.findViewById(R.id.item_txtMake);
			makeText.setText(currentCar.getMake());

			// Year:
			TextView yearText = (TextView) itemView.findViewById(R.id.item_txtYear);
			yearText.setText("" + currentCar.getYear());
			
			// Condition:
			TextView condionText = (TextView) itemView.findViewById(R.id.item_txtCondition);
			condionText.setText(currentCar.getCondition());

			return itemView;
		}				
	}
}












