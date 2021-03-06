package com.forum_rallye.com;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SelectRegionalActivity extends ListActivity {

	// url to make request
	private static String url = "http://projetfr.zz.mu/selectRegio.php";
	
	// JSON Node names
	private static final String TAG_RACES = "Courses";
	private static final String TAG_ID = "id_course";
	private static final String TAG_NAME = "nom";
	private static final String TAG_DATED = "jourDepart";
	private static final String TAG_DATEA = "jourArrive";
	private ArrayList<HashMap<String, String>> coursesList;

	// contacts JSONArray
	JSONArray courses = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_regional);
		
		// Hashmap for ListView
		coursesList = new ArrayList<HashMap<String, String>>();

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);

		try {
			// Getting Array of Contacts
			courses = json.getJSONArray(TAG_RACES);
			
			// looping through All Contacts
			for(int i = 0; i < courses.length(); i++){
				JSONObject c = courses.getJSONObject(i);
				
				// Storing each json item in variable
				String id_rallye = c.getString(TAG_ID);
				String name = c.getString(TAG_NAME);
				String dated = c.getString(TAG_DATED);
				String datea = c.getString(TAG_DATEA);
				System.out.println(id_rallye);
				System.out.println(name);
				System.out.println(dated);
				System.out.println(datea);
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				
				// adding each child node to HashMap key => value
				map.put(TAG_ID, id_rallye);
				map.put(TAG_NAME, name);
				map.put(TAG_DATED, dated);
				map.put(TAG_DATEA, datea);

				// adding HashList to ArrayList
				coursesList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		/**
		 * Updating parsed JSON data into ListView
		 * */
		ListAdapter adapter = new SimpleAdapter(this, coursesList,
				R.layout.list_item,
				new String[] { TAG_NAME, TAG_DATED, TAG_DATEA, TAG_ID}, new int[] {
						R.id.name, R.id.dated, R.id.datea});

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				finish();
				HashMap<String, String> map = (HashMap<String, String>) coursesList.get(position);			
				Intent in = new Intent(getApplicationContext(), SelectListEtapesRegionalActivity.class);
				in.putExtra(TAG_ID, map.get("id_course"));
				startActivity(in);
				
				
			}
		});

	}

}