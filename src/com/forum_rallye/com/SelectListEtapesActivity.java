package com.forum_rallye.com;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SelectListEtapesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_list_etapes);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_etape, menu);
		return true;
	}

}
