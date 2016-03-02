package com.zhongmian.mall.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by L on 2016/3/1 0001.
 */
public class BrandView01 extends TextView {
    private Paint paint;
    public BrandView01(Context context) {
        this(context, null);
    }

    public BrandView01(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BrandView01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        canvas.drawLine(0,0,0,getHeight(),paint);
    }
}
