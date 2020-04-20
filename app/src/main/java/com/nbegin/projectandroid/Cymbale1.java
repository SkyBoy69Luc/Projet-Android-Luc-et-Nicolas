package com.nbegin.projectandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.Nullable;

public class Cymbale1 extends View {

    private Bitmap cymbale1Image;
    private ScaleGestureDetector mScaleDetector;

    private SoundPool soundPool;
    private int sound;

    private Animation anim;
    private int animDuration;

    public Cymbale1(Context context) {
        super(context);
    }

    public Cymbale1(Context context, @Nullable AttributeSet attrs){
        super(context);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        cymbale1Image = BitmapFactory.decodeResource(getResources(), R.raw.cymbaleimage1);

        InitialiserAnimation();
        InitialiserSound(context);
    }

    private void InitialiserAnimation() {
        animDuration = 300;
        float startScale = 0.9f;
        float endScale = 1f;

        anim = new ScaleAnimation(
                startScale, endScale, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(animDuration);
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
        sound = soundPool.load(context, R.raw.cymbalsound1, 1);

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
                playAnimation();
                playMediaPlayer();
                break;
            case MotionEvent.ACTION_MOVE:
                //cymbale2Sound.start();
                break;
        }
        return true;
    }

    private void playAnimation() {
        this.startAnimation(anim);
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
        canvas.drawBitmap(cymbale1Image, 0, 0, null);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(250, 250);
    }

}
