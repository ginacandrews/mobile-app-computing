package edu.nd.mobicom.codemonkey.mobilenewscoverage;


import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.PushService;

public class Activity_Welcome extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Parse.initialize(this, "SeUAymviO4A0SpMUMeUdIIYTAb6rei0zSUqDLPxg", "a7Czpo8wIoUL6cOUA0f8x0tXa25DCimwJLNaV9eq"); 

    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button nextsignup = (Button) findViewById(R.id.signup_btn);
    
        nextsignup.setOnClickListener(new Button.OnClickListener() {	
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent signupIntent = new Intent(view.getContext(), Activity_Signup.class);
				startActivityForResult(signupIntent,0);
			}
		});
    }


@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
}
}