package edu.nd.mobicom.codemonkey.mobilenewscoverage;


import com.parse.ParseObject;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Activity_Signup extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button nextmain = (Button) findViewById(R.id.compsignup_btn);
        nextmain.setOnClickListener(new Button.OnClickListener() {
			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent uploadIntent = new Intent(view.getContext(), Activity_Main.class);
				startActivityForResult(uploadIntent,0);
			}
		});
    }
}
