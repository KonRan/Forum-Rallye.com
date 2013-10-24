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
import android.widget.TextView;
import android.widget.Toast;

public class SelectListEtapesActivity extends ListActivity {

	// url to make request
	private static String url = "http://projetfr.zz.mu/selectListeEtapes.php";
	
	// JSON Node names
	private static final String TAG_RACES = "Etapes";
	private static final String TAG_IDCOURSE = "id_course";
	private static final String TAG_NAME = "nom";
	private static final String TAG_RENS = "renseignements";
	private static final String TAG_DIST = "distance";
	private static final String TAG_IDETAPE = "id_etape";
	private static final String TAG_STATUT = "statut";

	private ArrayList<HashMap<String, String>> etapesList;

	// contacts JSONArray
	JSONArray etapes = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_list_etapes);
		
		Intent intent = getIntent();
    	String id_course = intent.getStringExtra(TAG_IDCOURSE);
    	Toast.makeText(getApplicationContext(), id_course, Toast.LENGTH_SHORT).show(); //TODO Supprimer après les tests
		
		// Hashmap for ListView
		etapesList = new ArrayList<HashMap<String, String>>();

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		//JSONObject json = jParser.getJSONFromUrl(url);

		JSONObject json = jParser.getJSONFromUrlWithParam(url, id_course);

		try {
			// Getting Array of Contacts
			etapes = json.getJSONArray(TAG_RACES);
			
			// looping through All Contacts
			for(int i = 0; i < etapes.length(); i++){
				JSONObject c = etapes.getJSONObject(i);
				
				// Storing each json item in variable
				
				String name = c.getString(TAG_NAME);
				String rens = c.getString(TAG_RENS);
				String dist = c.getString(TAG_DIST)+" Km";
				String id_etape = c.getString(TAG_IDETAPE);
				String statut = c.getString(TAG_STATUT);
				System.out.println(id_etape);
				System.out.println(name);
				System.out.println(rens);
				System.out.println(dist);
				System.out.print(statut);
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				
				// adding each child node to HashMap key => value
				map.put(TAG_IDETAPE, id_etape);
				map.put(TAG_NAME, name);
				map.put(TAG_RENS, rens);
				map.put(TAG_DIST, dist);
				map.put(TAG_STATUT, statut);

				// adding HashList to ArrayList
				etapesList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		/**
		 * Updating parsed JSON data into ListView
		 * */
		ListAdapter adapter = new SimpleAdapter(this, etapesList,
				R.layout.list_item_etapes,
				new String[] { TAG_NAME, TAG_RENS, TAG_DIST, TAG_STATUT}, new int[] {
						R.id.name, R.id.rens, R.id.dist, R.id.statut});

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				HashMap<String, String> map = (HashMap<String, String>) etapesList.get(position);

				//Starting new intent
				Toast.makeText(getApplicationContext(), map.get("id_etape"),
						Toast.LENGTH_SHORT).show();				
				Intent in = new Intent(getApplicationContext(), SelectListEtapesActivity.class);
				in.putExtra(TAG_IDETAPE, map.get("id_etape"));

			}
		});

	}

}