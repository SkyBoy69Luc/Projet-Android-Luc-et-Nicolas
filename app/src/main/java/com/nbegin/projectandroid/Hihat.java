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

public class Hihat extends View {

    private Bitmap hihatImage;
    //private MediaPlayer hihatSound;
    private ScaleGestureDetector mScaleDetector;

    public SoundPool soundPool;
    private int sound;

    public Hihat(Context context) {
        super(context);
    }

    public Hihat(Context context, @Nullable AttributeSet attrs){
        super(context);

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        hihatImage = BitmapFactory.decodeResource(getResources(), R.raw.hihatimage);

        InitialiserSound(context);
    }

    private void InitialiserSound(Context context) {

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();
        sound = soundPool.load(context, R.raw.hihatsound1, 1);
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
        canvas.scale(0.15f,0.15f);
        canvas.drawBitmap(hihatImage, 0, 0, null);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(375, 250);
    }






}
