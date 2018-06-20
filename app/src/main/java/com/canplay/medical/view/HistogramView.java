package com.canplay.medical.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;


import com.canplay.medical.R;
import com.canplay.medical.bean.KPI;

import java.util.ArrayList;
import java.util.List;

public class HistogramView extends View {

    private Paint xLinePaint;// 坐标轴 轴线 画笔：
    private Paint hLinePaint;// 坐标轴水平内部 虚线画笔
    private Paint hLinePaints;// 坐标轴水平内部 虚线画笔
    private Paint titlePaint;// 绘制文本的画笔
    private Paint chartLinePaint;// 折线
    private Paint pointLinePaint;// 折线
    private Paint pointLinePaint1;// 折线
    private Paint paint;// 矩形画笔 柱状图的样式信息
    private int[] progress = {2000, 5000, 6000, 8000, 500, 6000, 9000};// 7
    // 条，显示各个柱状的数据
    private int[] aniProgress;// 实现动画的值
    private List<Integer> aniProgres;// 实现动画的值

    private final int TRUE = 1;// 在柱状图上显示数字
    private int[] text;// 设置点击事件，显示哪一条柱状的信息
    private Bitmap bitmap;
    // 坐标轴左侧的数标
    private String[] ySteps;
    private String[] yStept;
    private List<String> yStep = new ArrayList<>();
    // 坐标轴底部的星期数
    private String[] xWeeks;
    private List<String> xWeek;
    private int flag;// 是否使用动画
    private Context context;
    private int cout = 7;
    private HistogramAnimation ani;

    public HistogramView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private List<KPI> data=new ArrayList<>();
    int max = 0;

    public void setDatas(List<KPI> list,String[] xWeeks) {
        this.data = list;
        this.xWeeks = xWeeks;

//        "month": "Dec",
//                "y_axis": 0
    }

    private void init() {

        ySteps = new String[]{"10","9","8", "7", "6", "5", "4", "3", "2", "1","0"};
        yStept = new String[]{"0","2","4","6","8","10","12","14","16","18","20","22","24","26","28","30"};;
        xWeeks = new String[]{"1", "2", "3", "4", "5", "6", "7"};

        text = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        aniProgress = new int[]{500, 300, 900, 350, 680, 690, 720, 900, 350, 680, 690, 720};
        ani = new HistogramAnimation();
        ani.setDuration(2000);

        xLinePaint = new Paint();
        hLinePaint = new Paint();
        hLinePaints = new Paint();
        titlePaint = new Paint();
        chartLinePaint = new Paint();
        pointLinePaint = new Paint();
        pointLinePaint1 = new Paint();

        paint = new Paint();

        // 给画笔设置颜色
        xLinePaint.setColor(getResources().getColor(R.color.blues));
        xLinePaint.setStrokeWidth(1);
        hLinePaint.setStrokeWidth(1);
        hLinePaints.setStrokeWidth(0.8f);

        hLinePaint.setColor(getResources().getColor(R.color.blues));
        hLinePaints.setColor(getResources().getColor(R.color.blu));
        titlePaint.setColor(getResources().getColor(R.color.color9));
        //绘制的折线
        chartLinePaint.setStyle(Paint.Style.FILL);
        chartLinePaint.setStrokeWidth(3);
        chartLinePaint.setColor(getResources().getColor(R.color.one));//(1)黄色
        chartLinePaint.setAntiAlias(true);
        pointLinePaint.setStyle(Paint.Style.FILL);
        pointLinePaint.setStrokeWidth(6);
        pointLinePaint.setColor(getResources().getColor(R.color.red));//(1)黄色
        pointLinePaint.setAntiAlias(true);
        pointLinePaint1.setStyle(Paint.Style.FILL);
        pointLinePaint1.setStrokeWidth(6);
        pointLinePaint1.setColor(getResources().getColor(R.color.one));//(1)黄色
        pointLinePaint1.setAntiAlias(true);


    }

    public void start(int flag) {
        this.flag = flag;
        this.startAnimation(ani);
    }

    private float xsqr;
    private float ysqr;
    private float NorValue =75;

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth() - dp2px(68);
        float height = getHeight() - dp2px(25);

        float hPerHeight = (height ) / (ySteps.length - 0.6f);// 分成四部分
        ysqr=(height-dp2px(15))/10.0f;
        // 绘制底部的线条
        canvas.drawLine(dp2px(28), height, width, height, xLinePaint);
        canvas.drawLine(dp2px(28), dp2px(2), dp2px(28), height, hLinePaint);
        hLinePaint.setTextAlign(Align.CENTER);
        // 设置四条虚线
        for (int i = 0; i < ySteps.length - 1; i++) {

            canvas.drawLine(dp2px(30), i * hPerHeight + hPerHeight*0.4f, width
                    , i * hPerHeight + hPerHeight*0.4f, hLinePaints);
            canvas.drawLine(dp2px(30), i * hPerHeight + hPerHeight*0.4f + 0.25f, dp2px(36), i * hPerHeight + hPerHeight*0.4f + 0.25f, hLinePaint);

        }

        // 绘制 Y 周坐标
        titlePaint.setTextAlign(Align.RIGHT);
        titlePaint.setTextSize(sp2px(12));
        titlePaint.setAntiAlias(true);
        titlePaint.setStyle(Paint.Style.FILL);
        // 设置左部的数字
        if(ySteps.length==30){
            for (int i = 0; i < ySteps.length; i++) {
                if(i%2==0){
                    canvas.drawText(ySteps[i], dp2px(25), hPerHeight*0.4f+dp2px(2) + i * (hPerHeight),
                            titlePaint);
                }

            }
        }else {
            for (int i = 0; i < ySteps.length; i++) {
                canvas.drawText(ySteps[i], dp2px(25), hPerHeight*0.4f+dp2px(2) + i * (hPerHeight),
                        titlePaint);
            }
        }


        // 绘制 X 周 做坐标

        int columCount = xWeeks.length;
        float step = (width - dp2px(14)) / (columCount);
        xsqr = step;
//            // 设置底部的数字
        if(xWeeks.length==30){
            for (int i = 0; i < xWeeks.length; i++) {
                // text, baseX, baseY, textPaint
                if(i%2==0){
                    canvas.drawText(xWeeks[i], dp2px(29) + step * (i), height + dp2px(18), titlePaint);
                }

                if(i!=xWeeks.length-1){
                    canvas.drawLine(dp2px(28) + step * (i + 1), height, dp2px(28) + step * (i + 1), height - dp2px(6), xLinePaint);
                }

            }
        }else {
            for (int i = 0; i < xWeeks.length; i++) {
                // text, baseX, baseY, textPaint
                canvas.drawText(xWeeks[i], dp2px(29) + step * (i), height + dp2px(18), titlePaint);
                if(i!=xWeeks.length-1){
                    canvas.drawLine(dp2px(28) + step * (i + 1), height, dp2px(28) + step * (i + 1), height - dp2px(6), xLinePaint);
                }

            }
        }

        if(data.size()>0){
            canvas.drawCircle(dp2px(28) +(float) (0)*xsqr , height- (float) (data.get(0).ydata)*ysqr, 10, data.get(0).colorType==0?pointLinePaint1:pointLinePaint);
        }
        canvas.drawText("时间", dp2px(35) + width, dp2px(10) + height, titlePaint);
        for(int i=1;i<data.size();i++){

                canvas.drawCircle(dp2px(28) +(float) (data.get(i).xdata)*xsqr , height- (float) (data.get(i).ydata)*ysqr, 10, data.get(i).colorType==0?pointLinePaint1:pointLinePaint);

            canvas.drawLine(dp2px(28) +(float) ((data.get(i-1).xdata)*xsqr),height- (float) (data.get(i-1).ydata)*ysqr,dp2px(28) + (float) (data.get(i).xdata)*xsqr, height- (float) (data.get(i).ydata)*ysqr, chartLinePaint);
        }



    }


    private int dp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    private int sp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (v * value + 0.5f);
    }

    /**
     * 设置点击事件，是否显示数字
     */
    public boolean onTouchEvent(MotionEvent event) {
        int step = (getWidth() - dp2px(30)) / 8;
        int x = (int) event.getX();
        for (int i = 0; i < 12; i++) {
            if (x > (dp2px(15) + step * (i + 1) - dp2px(15))
                    && x < (dp2px(15) + step * (i + 1) + dp2px(15))) {
                text[i] = 1;
                for (int j = 0; j < 12; j++) {
                    if (i != j) {
                        text[j] = 0;
                    }
                }
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    invalidate();
                } else {
                    postInvalidate();
                }
            }
        }
        return super.onTouchEvent(event);
    }


    private class HistogramAnimation extends Animation {
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f && flag == 2) {
                for (int i = 0; i < aniProgress.length; i++) {
                    aniProgress[i] = (int) (progress[i] * interpolatedTime);
                }
            } else {
                for (int i = 0; i < aniProgress.length; i++) {
                    aniProgress[i] = progress[i];
                }
            }
            invalidate();
        }
    }

}