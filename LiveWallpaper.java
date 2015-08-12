import java.util.Random;
import com.google.ads.*;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import android.widget.Gallery.LayoutParams;

public class LiveWallpaper extends WallpaperService {

	public static final String SHARED_PREFS_NAME = "livewallpapersettings";
	private static final int AERIAL_MIN = 0;
	private static final int AERIAL_MAX = 10;
	private static final int PITCH_MIN	= 11;
	private static final int PITCH_MAX 	= 15;
	private static final int STAD_MIN	= 16;
	private static final int STAD_MAX	= 25;
	
    @Override
    public Engine onCreateEngine() {
        context = this;
        return new MyWallpaperEngine();
      
    }

    Context context;

    private class MyWallpaperEngine extends Engine implements SharedPreferences.OnSharedPreferenceChangeListener{
        private final Handler handler = new Handler();
        private SharedPreferences	mPreferences;
        private String 				mMode = "random";
        private final Runnable drawRunner = new Runnable() {
            public void run() {
                draw();
            }
        };
        public Bitmap[] image = new Bitmap[26];
        private boolean visible;
        private int i ;
        
        public MyWallpaperEngine() {

            image[0] = BitmapFactory.decodeResource(getResources(),
                    R.drawable.image1);
            image[1] = BitmapFactory.decodeResource(getResources(),
                    R.drawable.image2);
            image[2] = BitmapFactory.decodeResource(getResources(),
                    R.drawable.image3);
            image[3] = BitmapFactory.decodeResource(getResources(),
                    R.drawable.image4);
            image[4] = BitmapFactory.decodeResource(getResources(),
                    R.drawable.image5);
            image[5] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image6);
            image[6] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image7);
            image[7] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image8);
            image[8] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image9);
            image[9] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image10);
            image[10] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image11);
            image[11] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image12);
            image[12] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image13);
            image[13] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image14);
            image[14] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image15);
            image[15] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image16);
            image[16] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image17);
            image[17] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image18);
            image[18] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image19);
            image[19] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image20);
            image[20] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image21);
            image[21] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image22);
            image[22] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image23);
            image[23] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image24);
            image[24] = BitmapFactory.decodeResource(getResources(),
            		R.drawable.image25);
            mPreferences = LiveWallpaper.this.getSharedPreferences(SHARED_PREFS_NAME, 0);
            mPreferences.registerOnSharedPreferenceChangeListener(this);
            onSharedPreferenceChanged(mPreferences,null); 
            
            handler.post(drawRunner);
        }
        
    	public void onSharedPreferenceChanged(
				SharedPreferences prefs, String key) {
			// TODO Auto-generated method stub
			mMode = prefs.getString("livewallpaper_mode", "random");
			
		}

		@Override
		public void onCreate(SurfaceHolder surfaceHolder)
		{
			super.onCreate(surfaceHolder);
			setTouchEventsEnabled(true);
		}
		
		@Override
		public void onDestroy()
		{
			super.onDestroy();
			handler.removeCallbacks(drawRunner);
	        System.gc();
		}
        
        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                handler.post(drawRunner);
            } else {
                handler.removeCallbacks(drawRunner);
            }
        }

    	@Override
		public void onSurfaceCreated(SurfaceHolder holder)
		{
			super.onSurfaceCreated(holder);
		}
        
        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            this.visible = false;
            handler.removeCallbacks(drawRunner);
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format,
                int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
        }

        private void draw() {
        	Random random = new Random();
            SurfaceHolder holder = getSurfaceHolder();
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                if (canvas != null) {
                        if(mMode.compareToIgnoreCase("aerial") == 0){
                        	DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                    	    int width = metrics.widthPixels;
                            int height = metrics.heightPixels;
                        	 i = AERIAL_MIN + random.nextInt(AERIAL_MAX);
                        	 canvas.drawBitmap(Bitmap.createScaledBitmap(image[i],
                                     width, height, false), 0, 0, null);
                        }else if(mMode.compareToIgnoreCase("pitchside")==0){
                        	DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                    	    int width = metrics.widthPixels;
                            int height = metrics.heightPixels;
                        	i = AERIAL_MAX + random.nextInt(PITCH_MAX-AERIAL_MAX);
                        	 canvas.drawBitmap(Bitmap.createScaledBitmap(image[i],
                                     width, height, false), 0, 0, null);
                        }else if(mMode.compareToIgnoreCase("stadium")==0){
                        	DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                    	    int width = metrics.widthPixels;
                            int height = metrics.heightPixels;
                        	i = PITCH_MAX + random.nextInt(STAD_MAX-PITCH_MAX);
                        	 canvas.drawBitmap(Bitmap.createScaledBitmap(image[i],
                                     width, height, false), 0, 0, null);
                        }else {
                        	DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                    	    int width = metrics.widthPixels;
                            int height = metrics.heightPixels;
                        	i = random.nextInt(STAD_MAX);
                        	 canvas.drawBitmap(Bitmap.createScaledBitmap(image[i],
                                     width, height, false), 0, 0, null);
                        }       
                }
            } finally {
                if (canvas != null)
                    holder.unlockCanvasAndPost(canvas);
            }
            handler.removeCallbacks(drawRunner);
            if (visible) {
                handler.postDelayed(drawRunner, 10000);
            }
        }
    }
   }
