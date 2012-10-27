package edu.nd.mobicom.codemonkey.mobilenewscoverage;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.parse.ParseObject;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;




public class Activity_Main extends Activity {
	protected ImageButton _button;
	protected ImageView _image;
	protected TextView _field;
	protected String _path;
	protected boolean _taken;
		
	protected static final String PHOTO_TAKEN = "photo_taken";

	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        _button = ( ImageButton ) findViewById( R.id.photo_btn );
	        _button.setOnClickListener( new ButtonClickHandler() );
	        File file = null;
	        //***CREATE DIR IF DOES NOT EXIST****//
/*	        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
		        String albumName = "/Pictures/MobileNewsCoverage2/";
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
					file = new File (
							  Environment.getExternalStoragePublicDirectory(
							    Environment.DIRECTORY_PICTURES
							  ), 
							  albumName);
				} else {
					file = new File (
							Environment.getExternalStorageDirectory()
							+ "/dcim/"
							+ albumName);
				}
			
	        }
			
    		if (! file.mkdirs()) {
    			if (! file.exists()){
    				Log.d("CameraSample", "failed to create directory");
    			}
    		}
*/

			// Create an image file name
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        
	         _path = Environment.getExternalStorageDirectory() + "/Pictures/MobileNewsCoverage2/"+timeStamp+".jpg";
	         
	         File dir = new File(Environment.getExternalStorageDirectory() , "/Pictures/MobileNewsCoverage2/");
	         if (!dir.exists()) {
	        	 if (!file.mkdirs()) {
	                 Log.e("TravellerLog :: ", "Problem creating Image folder");
	             }
	         }
	         
	        ImageButton nextupload = (ImageButton) findViewById(R.id.upload_btn);
	        nextupload.setOnClickListener(new Button.OnClickListener() {
				
				public void onClick(View view) {
					// TODO Auto-generated method stub
					Intent uploadIntent = new Intent(view.getContext(), Activity_UploadNew.class);
					startActivityForResult(uploadIntent,0);
				}
			});
	        
	        
	        ImageButton nextmessages = (ImageButton) findViewById(R.id.mymessages_btn);
	        nextmessages.setOnClickListener(new Button.OnClickListener() {
				
				public void onClick(View view) {
					// TODO Auto-generated method stub
					Intent uploadIntent = new Intent(view.getContext(), Activity_MyMessages.class);
					startActivityForResult(uploadIntent,0);
				}
			});
	        
	        ImageButton nextmyuploads = (ImageButton) findViewById(R.id.myuploads_btn);
	        nextmyuploads.setOnClickListener(new Button.OnClickListener() {
				
				public void onClick(View view) {
					// TODO Auto-generated method stub
					Intent uploadIntent = new Intent(view.getContext(), Activity_MyUploads.class);
					startActivityForResult(uploadIntent,0);
				}
			});
	        

/*	        ImageButton nextphoto = (ImageButton) findViewById(R.id.photo_btn);
	        nextphoto.setOnClickListener(new Button.OnClickListener() {
				
				public void onClick(View view) {
					// TODO Auto-generated method stub
					Intent uploadIntent = new Intent(view.getContext(), Activity_Main.class);
					startActivityForResult(uploadIntent,0);
				}
			});*/
	    }
	   
	   public class ButtonClickHandler implements View.OnClickListener 
	   {
	       public void onClick( View view ){
	       	startCameraActivity();
	       }
	   }	
	   
	   protected void startCameraActivity()
	   {
	       File file = new File( _path );
	       Uri outputFileUri = Uri.fromFile( file );
	       	
	       Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
	       intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
	       	
	       startActivityForResult( intent, 0 );
	   }
	   
	   @Override
	   protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	   {	
	       Log.i( "MakeMachine", "resultCode: " + resultCode );
	       switch( resultCode )
	       {
	       	case 0:
	       		Log.i( "MakeMachine", "User cancelled" );
	       		break;
	       			
	       	case -1:
	       		onPhotoTaken();
	       		break;
	       }
	   }
	   
	   protected void onPhotoTaken()
	   {
	       _taken = true;
	       	
	       BitmapFactory.Options options = new BitmapFactory.Options();
	       options.inSampleSize = 4;
	       	
	       Bitmap bitmap = BitmapFactory.decodeFile( _path, options );
	   }
	   
	   @Override
	   protected void onSaveInstanceState( Bundle outState ) {
	       outState.putBoolean( Activity_Main.PHOTO_TAKEN, _taken );
	   }
	   @Override 
	   protected void onRestoreInstanceState( Bundle savedInstanceState)
	   {
	       Log.i( "MakeMachine", "onRestoreInstanceState()");
	       if( savedInstanceState.getBoolean( Activity_Main.PHOTO_TAKEN ) ) {
	       	onPhotoTaken();
	       }
	   }
}
