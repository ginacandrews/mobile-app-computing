package edu.nd.mobicom.codemonkey.mobilenewscoverage;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.InputStream;
import java.io.FileInputStream;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;




public class Activity_Main extends Activity {
	protected ImageButton _picbutton;
	protected ImageButton _vidbutton;
	protected ImageView _image;
	protected TextView _field;
	protected String _path;
	protected String _dirpath;
	protected boolean _taken;
	public Location test,realLoc;
	public String timeStamp;
	public String Text;
	public Double lat;
	public Double longi;
	public static final int LOCATION_UPDATE_PERIOD_MSEC = 3 * 60 * 1000;//3 mins
	public static final int LOCATION_UPDATE_DISTANCE_METERS = 1609; //1 mile
	public LocationManager mlocManager;
	
	
	protected static final String PHOTO_TAKEN = "photo_taken";

	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        // Location
	        
	        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	        LocationListener mlocListener = new MyLocationListener();
	        mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, LOCATION_UPDATE_PERIOD_MSEC, LOCATION_UPDATE_DISTANCE_METERS, mlocListener);
	        test = new Location("dummyprovider");

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
	   // Location Listener
	   public class MyLocationListener implements LocationListener{
		   
	        //@Override

	        public void onLocationChanged(Location loc){
	        	if (loc.getLatitude()<1){
	        		loc.setLatitude(20.2);
	        		loc.setLongitude(52.6);
	        		test = loc;
	        	}else  
	            test = loc;
	        }

	        //@Override

	        public void onProviderDisabled(String provider){
	            Toast.makeText( getApplicationContext(),"Gps Disabled", Toast.LENGTH_SHORT ).show();
	        }

	        //@Override

	        public void onProviderEnabled(String provider){
	            Toast.makeText( getApplicationContext(),"Gps Enabled",Toast.LENGTH_SHORT).show();
	            lat = test.getLatitude();
	      	    longi = test.getLongitude();
	      	    Text = "Lat = " + test.getLatitude() + "|Long = " + test.getLongitude();
	      	    Toast.makeText( getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
	        }

	        //@Override

	        public void onStatusChanged(String provider, int status, Bundle extras){
	        }

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
		   timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		   _path=_dirpath+"Pictures/"+timeStamp+".png";
		   Log.wtf("the pic path is", _path);
	       File file = new File( _path );
	       Uri outputFileUri = Uri.fromFile( file );
	       	
	       Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE );
	       //intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
	     
	       
	       
	       if (test==null){
	    	   Context context = getApplicationContext();
		    	CharSequence text = "null: no gps information";
		    	int duration = Toast.LENGTH_SHORT;
		    	Toast toast = Toast.makeText(context, text, duration);
		    	toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
		    	
		    	toast.show();
		    	//Toast.makeText(Activity_Main.this, "null: no gps information", Toast.LENGTH_SHORT).show();
           }
           else{
        	   Context context = getApplicationContext();
		    	CharSequence text = "(long,lat): (" + String.valueOf(test.getLongitude()) + "," + String.valueOf(test.getLatitude()) + ")";
		    	int duration = Toast.LENGTH_SHORT;

		    	Toast toast = Toast.makeText(context, text, duration);
		    	toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
		    	
		    	toast.show();
        	   /*
        	   test.getLatitude();
               String ausgabe = String.valueOf(test.getLatitude());
               Toast.makeText(AndroidCamera.this, ausgabe, Toast.LENGTH_SHORT).show();
               */
           }
	
	       startActivityForResult( intent, 2 );
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
	       		onPhotoTaken(data);
	       		
	       		break;
	       }
	   }
	   
	   protected void onPhotoTaken(Intent intentish)
	   {
	       _taken = true;
	       	
	       BitmapFactory.Options options = new BitmapFactory.Options();
	       options.inSampleSize = 1;
	       Bundle extras = intentish.getExtras();
	       Bitmap bitmap = (Bitmap) extras.get("data");//BitmapFactory.decodeFile( "data", options );
	       //System.out.println("dbg:"+_path);
	       
	       ByteArrayOutputStream stream = new ByteArrayOutputStream();
	       bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
	       byte[] data = stream.toByteArray();
	       
	       timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	       //Log.wtf("the timestamp is", timeStamp);
	       ParseFile testupload = new ParseFile(timeStamp+".png", data);
	       testupload.saveInBackground();
	
	       ParseObject uploadmedia = new ParseObject("Media");
	       test = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	       ParseGeoPoint point = new ParseGeoPoint(test.getLatitude(),test.getLongitude());
	       uploadmedia.put("Location",point);
	       uploadmedia.put("filename", timeStamp+".png");
	       uploadmedia.put("file", testupload);
	       uploadmedia.saveInBackground();
	       
	       
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
	       	//onPhotoTaken(Intent );
	       }
	   }
	   
  
}
