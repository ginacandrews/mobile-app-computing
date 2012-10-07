package edu.nd.mobicom.codemonkey.mobilenewscoverage;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Activity_MyUploads extends Activity_Main {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myuploads);
        
        ImageButton nextmain = (ImageButton) findViewById(R.id.main_btn);
        nextmain.setOnClickListener(new Button.OnClickListener() {
			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent uploadIntent = new Intent(view.getContext(), Activity_Main.class);
				startActivityForResult(uploadIntent,0);
			}
		});
 
        ImageButton nextmymessages = (ImageButton) findViewById(R.id.mymessages_btn);
        nextmymessages.setOnClickListener(new Button.OnClickListener() {
			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent uploadIntent = new Intent(view.getContext(), Activity_MyMessages.class);
				startActivityForResult(uploadIntent,0);
			}
		});
    }

}
