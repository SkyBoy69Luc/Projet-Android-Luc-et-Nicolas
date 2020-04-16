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

import androidx.annotation.Nullable;

public class Basedrum extends View {

    private Bitmap baseDrumImage;
    private MediaPlayer baseDrumSound;
    private ScaleGestureDetector mScaleDetector;

    public Basedrum(Context context) {
        super(context);
    }

    public Basedrum(Context context, @Nullable AttributeSet attrs){
        super(context);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        baseDrumImage = BitmapFactory.decodeResource(getResources(), R.raw.basedrumimage);
        baseDrumSound = MediaPlayer.create(context, R.raw.bassdrumsound);
        baseDrumSound.setLooping(false);

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
            baseDrumSound.start();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        drawBitmap(canvas);
    }

    public void drawBitmap(Canvas canvas) {
        canvas.save();
        canvas.scale(0.6f,0.6f);
        canvas.drawBitmap(baseDrumImage, 0, 0, null);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(500, 450);
    }




}
