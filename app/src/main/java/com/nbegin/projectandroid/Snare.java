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

public class Snare extends View {

    private Bitmap snareImage;
    private ScaleGestureDetector mScaleDetector;
    private SoundPool snareSoundPool;
    private int snareSoundID;
    private SoundPool snareSoundPool2;
    private int snareSound2ID;
    private boolean fingerMove;
    private Animation anim;
    private int animDuration;
    public Snare(Context context) {
        super(context);
    }

    public Snare(Context context, @Nullable AttributeSet attrs){
        super(context);
        
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        snareImage = BitmapFactory.decodeResource(getResources(), R.raw.snareimage);

        fingerMove = false;
        initialiserAnimation();
        initialiserSound(context);
    }
    private void initialiserAnimation() {
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
    private void playAnimation() {
        this.startAnimation(anim);
    }
    private void initialiserSound(Context context) {
        
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        snareSoundPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();
        snareSoundID = snareSoundPool.load(context, R.raw.snaresound, 1);
        
        snareSoundPool2 = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();
        snareSound2ID = snareSoundPool2.load(context, R.raw.snare2sound, 1);
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
                playSnareSound(snareSoundPool,snareSoundID, 1);
                playAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }

//        switch (event.getAction()) {
//            case MotionEvent.ACTION_UP:
//                if (fingerMove != true) {
//                    playSnareSound(snareSoundPool,snareSoundID, 1);
//                    playAnimation();
//                }else{
//                    //fingerMove = false;
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (fingerMove != true) {
//                    fingerMove = true;
//                    playSnareSound(snareSoundPool2, snareSound2ID, 0.8f);
//                }
//                break;
//        }
        return true;
    }
    
    private void playSnareSound(SoundPool soundPool, int soundID, float levelVolume){ soundPool.play(soundID, levelVolume, levelVolume, 0, 0, 1);}

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        drawBitmap(canvas);
    }

    public void drawBitmap(Canvas canvas) {
        canvas.save();
        canvas.scale(0.1f,0.1f);
        canvas.drawBitmap(snareImage, 0, 0, null);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(275, 275);
    }

}

