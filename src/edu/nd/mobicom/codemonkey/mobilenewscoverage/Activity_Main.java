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
	protected ImageButton _picbutton;
	protected ImageButton _vidbutton;
	protected ImageView _image;
	protected TextView _field;
	protected String _path;
	protected String _dirpath;
	protected boolean _taken;
		
	protected static final String PHOTO_TAKEN = "photo_taken";

	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        _picbutton = ( ImageButton ) findViewById( R.id.photo_btn );
	        _picbutton.setOnClickListener( new PicButtonClickHandler() );
	        
	        _vidbutton = (ImageButton) findViewById(R.id.video_btn);
	        _vidbutton.setOnClickListener(new VidButtonClickHandler() );

			// Create an image file name
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        
	         _dirpath = Environment.getExternalStorageDirectory() +"/MobileNewsCoverage/";
	         Log.wtf("help", _dirpath);
	         
	         File picdir = new File(_dirpath+ "/Pictures/");
	         if (!picdir.exists()) {
	        	 if (!picdir.mkdirs()) {
	                 Log.e("TravellerLog :: ", "Problem creating Image folder");
	             }
	         }
	         
	         File viddir = new File(_dirpath+  "/Videos/");
	         if (!viddir.exists()) {
	        	 if (!viddir.mkdirs()) {
	                 Log.e("TravellerLog :: ", "Problem creating Video folder");
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
	        
	    }
	   
	   public class PicButtonClickHandler implements View.OnClickListener 
	   {
	       public void onClick( View view ){
	       	startCameraActivity();
	       }
	   }
	   
	   public class VidButtonClickHandler implements View.OnClickListener 
	   {
	       public void onClick( View view ){
	       	startVideoActivity();
	       }
	   }
	   
	   protected void startCameraActivity()
	   {
		   String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		   _path=_dirpath+"Pictures/"+timeStamp+".jpg";
		   Log.wtf("the pic path is", _path);
	       File file = new File( _path );
	       Uri outputFileUri = Uri.fromFile( file );
	       	
	       Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
	       intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
	       	
	       startActivityForResult( intent, 0 );
	   }
	   
	   protected void startVideoActivity()
	   {
		   String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		   _path=_dirpath+"Videos/"+timeStamp+".mp4";
		   Log.wtf("the vid path is", _path);
	       File file = new File( _path );
	       Uri outputFileUri = Uri.fromFile( file );  
		   
	       Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE );
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
