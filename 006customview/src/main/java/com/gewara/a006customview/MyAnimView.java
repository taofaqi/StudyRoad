package com.gewara.a006customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by taofaqi on 2016/10/8.
 */
public class MyAnimView extends View {

    private final float RADIUS = 50f;
    private Paint mPaint;
    private Point currentPoint;
    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint == null){
            currentPoint = new Point(RADIUS,RADIUS);//定义初始化的位置
            drawCircle(canvas);
            startAnim();
        }else
            drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x,y,RADIUS,mPaint);
    }

    private void startAnim() {
//        Point startPoint = new Point(RADIUS, RADIUS);
        Point endPoint = new Point(getWidth()-RADIUS, getHeight()-RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), currentPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setDuration(5000);
        anim.start();
    }
}
