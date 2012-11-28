package edu.nd.mobicom.codemonkey.mobilenewscoverage;


import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Activity_Signup extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button nextmain = (Button) findViewById(R.id.compsignup_btn);
        nextmain.setOnClickListener(new Button.OnClickListener() {
			
			public void onClick(final View view) {
				// TODO Auto-generated method stub
				ParseUser user = new ParseUser();
				EditText uname = (EditText)findViewById(R.id.username);
				EditText pwd = (EditText)findViewById(R.id.password);
				EditText em = (EditText)findViewById(R.id.email);
				
				//check for input in username, pw, and email boxes
				
				user.setUsername(uname.getText().toString());
				user.setPassword(pwd.getText().toString());
				user.setEmail(em.getText().toString());
				
				CheckBox terms  = (CheckBox)findViewById(R.id.terms_checkbox);
				if (terms.isChecked()) 
				{
					// other fields can be set just like with ParseObject
					user.put("terms", true);
					 
					user.signUpInBackground(new SignUpCallback() {
					  public void done(ParseException e) {
					    if (e == null) {
							   Intent uploadIntent = new Intent(view.getContext(), Activity_Main.class);
							   startActivityForResult(uploadIntent,0);
					    } else {
					      Log.wtf("pError", "Signup failed");
					    }
					  }
					});
				}
				else {
					Log.wtf("check box error", "checkbox is empty");
					
				}
			}
		});
    }
}
