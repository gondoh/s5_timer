package sample.application.countdowntimer;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class CountdownTimerActivity extends Activity {
	
	public static TextView tv;
	public static SeekBar sb;
	public static Context mContext;
	public static int timeLeft = 0;
	public static Button btnStart;
	public static Button btnStop;
	
	
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_timer);
        

    	CountdownTimerActivity.mContext = this;
    	CountdownTimerActivity.tv = (TextView)this.findViewById(R.id.textView1);
    	CountdownTimerActivity.btnStart = (Button)this.findViewById(R.id.buttonStart);        
    	CountdownTimerActivity.btnStop = (Button)this.findViewById(R.id.buttonStop);        
        CountdownTimerActivity.sb = (SeekBar)this.findViewById(R.id.seekBar1);
        CountdownTimerActivity.sb.setBackgroundDrawable(this.drowScale());
        this.setListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_countdown_timer, menu);
        return true;
    }

    public BitmapDrawable drowScale(){
    	Paint paint;
    	Path path;
    	Canvas canvas;
    	Bitmap bitmap;
    	
    	paint = new Paint();
    	paint.setStrokeWidth(0);
    	paint.setStyle(Paint.Style.STROKE);
    	bitmap = Bitmap.createBitmap(241, 30, Bitmap.Config.ARGB_8888);
    	path = new Path();
    	canvas = new Canvas(bitmap);
    	
    	for(int i = 0; i < 17; i++){
    		path.reset();
    		if (i % 5 == 0){
    			paint.setColor(Color.WHITE);
    		} else {
    			paint.setColor(Color.GRAY);
    		}
    		path.moveTo(i*16, 5);
    		path.quadTo(i*16, 5, i*16, 15);
    		canvas.drawPath(path, paint);
    	}
    	BitmapDrawable bd = new BitmapDrawable(bitmap);
    	
    	return bd;
    }
    
    
    
    public void setListeners(){
    	CountdownTimerActivity.sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				timeLeft = progress*60;
				if (fromUser) showTime(progress*60);
				if (fromUser && (progress > 0)) btnStart.setEnabled(true);
				else 							btnStart.setEnabled(false);
				if (progress == 0) btnStop.setEnabled(false);
			}
		});
    }
    
    public static void showTime(int timeSeconds){
    	SimpleDateFormat format = new SimpleDateFormat("mm:ss");
    	CountdownTimerActivity.tv.setText(format.format(timeSeconds*1000));
    }
    
    public static void countDown(int counter){
    	showTime(counter);
    	timeLeft = counter;
    	if (counter % 60 == 0) sb.setProgress(counter/60);
    	else     			   sb.setProgress(counter/60 + 1);
    	
    	if (counter != 0) {
    		btnStop.setEnabled(true);
    		btnStart.setEnabled(false);
    		sb.setEnabled(false);
    	} else {
    		btnStop.setEnabled(false);
    		btnStart.setEnabled(false);
    		sb.setEnabled(true);
    	}
    }
    
}
