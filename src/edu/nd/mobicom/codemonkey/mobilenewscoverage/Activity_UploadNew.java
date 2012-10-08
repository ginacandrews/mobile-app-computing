package edu.nd.mobicom.codemonkey.mobilenewscoverage;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Activity_UploadNew extends Activity_Main {
	private Bitmap receipt;
	private Bitmap mImageBitmap;
	private static final String BITMAP_STORAGE_KEY = "viewbitmap";
	private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadnew);
        
        //doesn't crash but doesn't show image
        
        Bitmap receiptimage = (Bitmap) getIntent().getExtras().getParcelable("uploadintent");        
//        receipt.setImageBitmap(receiptimage);
        ImageView mImageView = (ImageView) findViewById(R.id.imageView1);
        mImageBitmap = (Bitmap) getIntent().getExtras().getParcelable("uploadintent");
//		mImageBitmap = savedInstanceState.getParcelable("uploadintent");        
		mImageView.setImageBitmap(mImageBitmap);
        
//      mImageBitmap = null;
        
        
        
        
        
        
        
        
        
        
        
    }

}
