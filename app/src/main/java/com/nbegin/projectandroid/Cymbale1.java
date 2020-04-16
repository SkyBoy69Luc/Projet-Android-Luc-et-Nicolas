package com.nbegin.projectandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Cymbale1 extends View {

    private Bitmap cymbale1Image;
    private MediaPlayer cymbale1Sound;
    private MediaPlayer cymbale2Sound;
    private ScaleGestureDetector mScaleDetector;

    public Cymbale1(Context context) {
        super(context);
    }

    public Cymbale1(Context context, @Nullable AttributeSet attrs){
        super(context);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        cymbale1Image = BitmapFactory.decodeResource(getResources(), R.raw.cymbaleimage1);
        cymbale1Sound = MediaPlayer.create(context, R.raw.cymbalsound1);
        cymbale1Sound.setLooping(false);
        cymbale2Sound = MediaPlayer.create(context, R.raw.floortom1);
        cymbale2Sound.setLooping(false);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector){
            invalidate();
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        mScaleDetector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                playMediaPlayer();
                break;
            case MotionEvent.ACTION_MOVE:
                cymbale2Sound.start();
                break;
        }
        return true;
    }
    private void playMediaPlayer(){
        cymbale1Sound.start();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        drawBitmap(canvas);
    }

    public void drawBitmap(Canvas canvas) {
        canvas.save();
        canvas.scale(0.2f,0.2f);
        canvas.drawBitmap(cymbale1Image, 0, 0, null);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(250, 250);
    }

}
