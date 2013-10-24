package com.forum_rallye.com;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

    @Override
    //test de commentaire
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnQuit = (Button) findViewById(R.id.ButtonQuit);
        btnQuit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });
        
        ImageView imgFR = (ImageView) findViewById(R.id.imageForum);
        imgFR.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.forum-rallye.com"));
            	startActivity(intent);     
            	}
        });
        
        Button btnReg = (Button) findViewById(R.id.ButtonReg);
        btnReg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                doSelectRegional();
            }

			
        });
        
        Button btnWRC = (Button) findViewById(R.id.ButtonWRC);
        btnWRC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	doSelectWRC();
            }

			
        });
        
        Button btnFrance = (Button) findViewById(R.id.ButtonFra);
        btnFrance.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	doSelectFrance();
            }

			
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void doSelectRegional() {
    	finish();
		Intent intent = new Intent(getApplicationContext(), SelectRegionalActivity.class);
		startActivity(intent);
		
	}
    
    private void doSelectWRC() {
    	finish();
		Intent intent = new Intent(getApplicationContext(), SelectWRCActivity.class);
		startActivity(intent);
		
	}
    
    private void doSelectFrance() {
    	finish();
		Intent intent = new Intent(getApplicationContext(), SelectFranceActivity.class);
		startActivity(intent);
		
	}
    
}
