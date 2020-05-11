package com.nbegin.projectandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.Nullable;

public class Tom2 extends View {

    private Bitmap tom2Image;
    private ScaleGestureDetector mScaleDetector;
    public SoundPool soundPool;
    private int sound;

    public Tom2(Context context) {
        super(context);
    }

    public Tom2(Context context, @Nullable AttributeSet attrs){
        super(context);

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        tom2Image = BitmapFactory.decodeResource(getResources(), R.raw.tomimage);

        initialiserSound(context);
    }

    private void initialiserSound(Context context) {

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();
        sound = soundPool.load(context, R.raw.tomsound1, 1);
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
                break;
        }
        return true;
    }

    private void playMediaPlayer(){
        soundPool.play(sound, 1, 1, 0, 0, 1);
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        drawBitmap(canvas);
    }

    public void drawBitmap(Canvas canvas) {
        canvas.save();
        canvas.scale(0.2f,0.2f);
        canvas.drawBitmap(tom2Image, 0, 0, null);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(300, 300);
    }

}
