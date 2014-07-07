package com.example.savingetc.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.GestureDetector;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
	private Context myContext;
	public boolean m_bool;
	final GestureDetector gestureDetector = new GestureDetector(new MyGestureDetector());
	private ArrayList<String> itemURLs = new ArrayList<String>();
    private int m_itemNum = 0;
	
	public class MyGestureDetector extends SimpleOnGestureListener {
		
		@Override
		public boolean onSingleTapConfirmed(MotionEvent evt) {
			Toast.makeText(getApplicationContext(), "pop-up image preview", Toast.LENGTH_SHORT).show();	
			return super.onSingleTapUp(evt);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2
				, float velocityX, float velocityY) {

			if(e1.getX() - e2.getX() > 100 && Math.abs(velocityX) > 20) {
				Toast.makeText(getApplicationContext(), "swiped left", Toast.LENGTH_SHORT).show();
				m_bool = true;
			}
			else if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > 20) {
				Toast.makeText(getApplicationContext(), "swiped right", Toast.LENGTH_SHORT).show();
				m_bool = true;
			}
			else if (e1.getY() - e2.getY() > 100 && e2.getX() > 10 && Math.abs(velocityY) > 20) {
				Toast.makeText(getApplicationContext(), "swiped up", Toast.LENGTH_SHORT).show();
				m_bool = true;
			}
			else if (e2.getY() - e1.getY() > 100 && Math.abs(velocityY) > 20) {
				Toast.makeText(getApplicationContext(), "swiped down", Toast.LENGTH_SHORT).show();
			}

            if (velocityX > 20 || velocityY > 20) {
                getNextItemImage();
            }

			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView mainItemImage = (ImageView) findViewById(R.id.mainImage);
		mainItemImage.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return false;
				}
				
				return true;
			}
			
			
		});
		Log.d("Activity", "onCreate Started");
	}

    public int getSuggestedItem() {
        return m_itemNum++;
    }

	public void getNextItemImage() {
        itemURLs.add("http://slimages.macys.com/is/image/MCY/products/4/optimized/2008224_fpx.tif");
        itemURLs.add("http://slimages.macys.com/is/image/MCY/products/2/optimized/1446402_fpx.tif");
        itemURLs.add("http://slimages.macys.com/is/image/MCY/products/2/optimized/2008232_fpx.tif");

        new loadNextItemAsyncTask().execute(itemURLs.get(getSuggestedItem()));
    }

    private class loadNextItemAsyncTask extends AsyncTask<String, Integer, Bitmap> {
        ImageView mainItemImage = (ImageView) findViewById(R.id.mainImage);

        @Override
        protected Bitmap doInBackground(String...urls) {
            Bitmap item = null;
            System.out.println(urls[0]);
            try {
                InputStream in = new URL(urls[0]).openStream();
                item = BitmapFactory.decodeStream(in);
            }
            catch (Exception e) {
                Log.e("Error", e.getMessage());
                Log.e("error", urls[0]);
                e.printStackTrace();
            }

            return item;
        }

        protected void OnProgressUpdate(Integer...progress) {
            setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(Bitmap item) {
            super.onPostExecute(item);
            mainItemImage.setImageBitmap(item);
            Log.d("Activity", "Success");
        }
    }
	
	protected void onResume(Bundle savedInstanceState) {
		super.onResume();
		
	}
	



}
