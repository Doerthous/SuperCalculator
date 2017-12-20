package com.sc.ui.plot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.sc.R;

import java.util.ArrayList;
import java.util.List;

public class PaintBoard extends View {
    private Bitmap mBitmap = null;
    private Canvas mBitmapCanvas = null;

    private int background;
    private int width;
    private int height;
    public PaintBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private List<T> t = new ArrayList<>();
    private float pixelPerGrid = 40f;//
    private Paint gradPaint;
    private Paint exprPaint;
    private Paint axisPaint;
    private float moveX, moveY, scale = 1;
    private void translate(float x, float y){
        mBitmapCanvas.translate(x, y);
        t.add(new T(x,y,true));
        moveX += x;
        moveY += y;
    }
    private void scale(float x, float y){
        mBitmapCanvas.scale(x, y);
        t.add(new T(x,y,false));
    }
    public void init(){
        width = getWidth();
        height = getHeight();
        background = ContextCompat.getColor(getContext(), R.color.colorBackgroud);
        //
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mBitmapCanvas = new Canvas(mBitmap);
        gradPaint = new Paint();
        gradPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorF8F8F8));
        exprPaint = new Paint();
        exprPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorGreen));
        axisPaint = new Paint();
        axisPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorBlue));
        gradPaint.setStrokeWidth(1/10);
        exprPaint.setStrokeWidth(2/10);
        axisPaint.setStrokeWidth(2/10);
        //
        mBitmapCanvas.translate(width/2, height/2);
        mBitmapCanvas.scale(pixelPerGrid, -pixelPerGrid);
        //
        drawAxis();
        update();
    }
    private void drawAxis(){
        float span = (1/scale);
        float xMin = getXMin();
        float xMax = getXMax();
        float yMin = getYMin();
        float yMax = getYMax();
        float xmin = 0, ymin = 0;
        while(xMin > xmin){
            xmin += span;
        }
        while(xMin < xmin){
            xmin -= span;
        }
        while(yMin > ymin){
            ymin += span;
        }
        while(yMin < ymin){
            ymin -= span;
        }
        for(float i = xmin; i < xMax; i+=span){
            mBitmapCanvas.drawLine(i, yMin, i, yMax, gradPaint);
        }
        for(float i = ymin; i < yMax; i+=span){
            mBitmapCanvas.drawLine(xMin, i, xMax, i, gradPaint);
        }
        mBitmapCanvas.drawLine(0,yMin,0,yMax, axisPaint);
        mBitmapCanvas.drawLine(xMin,0,xMax,0,axisPaint);
    }
    public void update(){
        invalidate();
    }
    public void plot(Double[] x, Double[] y){
        for(int i = 0; i < x.length-1; ++i){
            mBitmapCanvas.drawLine(
                    x[i].floatValue(),
                    y[i].floatValue(),
                    x[i+1].floatValue(),
                    y[i+1].floatValue(),
                    exprPaint);
        }
    }
    public void zoomIn(){
        float mx = moveX, my = moveY;
        translate(-mx,-my);
        scale(2,2);
        translate(mx,my);
        pixelPerGrid *= 2;
        scale *= 2;
        clear();
    }
    public void zoomOut(){
        float mx = moveX, my = moveY;
        translate(-mx,-my);
        scale(0.5f,0.5f);
        translate(mx,my);
        pixelPerGrid /= 2;
        scale /= 2;
        clear();
    }
    public void toOrigin(){
        List<T> ttt = new ArrayList<>();
        for(int i = t.size()-1; i >= 0; --i){
            T tt = t.get(i);
            if(tt.isT){
                mBitmapCanvas.translate(-tt.x, -tt.y);
                moveX -= tt.x;
                moveY -= tt.y;
            } else {
                mBitmapCanvas.scale(1.0f/tt.x, 1.0f/tt.y);
                ttt.add(ttt.size(), tt);
            }
        }
        t = ttt;
        for(int i = 0; i < t.size(); ++i){
            T tt = t.get(i);
            mBitmapCanvas.scale(tt.x, tt.y);
        }
        moveX = moveY = 0;
        clear();
    }
    public void clear(){
        mBitmapCanvas.drawColor(background);
        drawAxis();
    }
    //
    public float getXMin(){
        return -moveX - ((float)width)/(2*pixelPerGrid);
    }
    public float getXMax(){
        return -moveX + ((float)width)/(2*pixelPerGrid);
    }
    public float getYMin(){
        return -moveY - ((float)height)/(2*pixelPerGrid);
    }
    public float getYMax(){
        return -moveY + ((float)height)/(2*pixelPerGrid);
    }
    public float getScale() { return scale; }


    // 在onDraw时画bitmap：
    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, new Paint());
        }
    }
    private float startX;
    private float startY ;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                Log.e(">>>>>>>>>>>>>>>>>D", event.getX()+","+event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = (event.getX()-startX)/(pixelPerGrid);
                float dy = (event.getY()-startY)/(pixelPerGrid);
                translate(dx, -dy);
                clear();
                startX = event.getX();
                startY = event.getY();
                update();
                break;
            case MotionEvent.ACTION_UP:
                Log.e(">>>>>>>>>>>>>>>>>U", event.getX()+","+event.getY());
            //canvas.drawBitmap(mBitmap, 0, yLocation, mBitmapPaint);
        }
        for(View.OnTouchListener listener : listeners){
            listener.onTouch(this, event);
        }
        return true;
    }


    private List<View.OnTouchListener> listeners = new ArrayList<>();
    @Override
    public void setOnTouchListener(View.OnTouchListener listener){
        listeners.add(listener);
    }
}

class T {
    public float x, y;
    public boolean isT = false;
    public T(float x, float y, boolean isT){
        this.x = x;
        this.y = y;
        this.isT = isT;
    }
}

