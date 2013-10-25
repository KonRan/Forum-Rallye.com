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

public class SelectClassementActivity extends ListActivity {

	// url to make request
	private static String url = "http://projetfr.zz.mu/selectClassementEtape.php";
	
	// JSON Node names
	private static final String TAG_CLASS = "Classement";
	private static final String TAG_NOMP = "nom";
	private static final String TAG_TEMPS = "temps";
	private static final String TAG_IDETAPE = "id_etape";

	private ArrayList<HashMap<String, String>> classementList;

	// contacts JSONArray
	JSONArray classement = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_classement);
		
		Intent intent = getIntent();
    	String id_etape = intent.getStringExtra(TAG_IDETAPE);
		
		// Hashmap for ListView
		classementList = new ArrayList<HashMap<String, String>>();

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		//JSONObject json = jParser.getJSONFromUrl(url);

		JSONObject json = jParser.getJSONFromUrlWithParam(url, id_etape);

		try {
			// Getting Array of Contacts
			classement = json.getJSONArray(TAG_CLASS);
			
			// looping through All Contacts
			for(int i = 0; i < classement.length(); i++){
				JSONObject c = classement.getJSONObject(i);
				
				// Storing each json item in variable
				
				String nomp = c.getString(TAG_NOMP);
				String temps = c.getString(TAG_TEMPS);
				System.out.println(nomp);
				System.out.println(temps);
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				
				// adding each child node to HashMap key => value
				map.put(TAG_NOMP, nomp);
				map.put(TAG_TEMPS, temps);

				// adding HashList to ArrayList
				classementList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		/**
		 * Updating parsed JSON data into ListView
		 * */
		ListAdapter adapter = new SimpleAdapter(this, classementList,
				R.layout.list_item_classement,
				new String[] { TAG_NOMP, TAG_TEMPS}, new int[] {
						R.id.nomp, R.id.temps});

		setListAdapter(adapter);
	
		
		
		
		

	}

}