package com.example.administrator.surfaceview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Boolean draw=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=(TextView)findViewById(R.id.textView);

        surfaceView=(SurfaceView)findViewById(R.id.sufaceview);
        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Mythread().start();
            }
        });
    }

    public class  Mythread extends Thread{
        @Override
        public void run() {
            super.run();
            synchronized (surfaceHolder){
                int shuzi=100;
                int jiaodu=10;
                Boolean wangfan=true;
                RectF rectF=new RectF(10,10,shuzi+200,shuzi+200);
                RectF rectF1=new RectF(10,10,shuzi+300,shuzi+300);
                while (draw) {
                    long start=System.currentTimeMillis();
                    Canvas canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(getColor(R.color.colorAccent));
                    Paint paint = new Paint();
//                paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(getColor(R.color.colorPrimaryDark));
                    paint.setAntiAlias(true);

//                rectF.right=shuzi;
//                rectF.bottom=shuzi;
//                canvas.drawRect(0, 0, shuzi,shuzi, paint);
                    canvas.drawArc(rectF,-90,jiaodu,true,paint);
                    Bitmap bitmap=((BitmapDrawable)getDrawable(R.drawable.hhh)).getBitmap();
                    canvas.drawBitmap(bitmap,jiaodu,0,paint);
                    paint.setColor(getColor(R.color.zise));
                    canvas.drawArc(rectF1,-90,jiaodu,true,paint);

                    if (jiaodu<360&&wangfan){
                        jiaodu+=10;
                    }else {
                        wangfan=false;
                        jiaodu-=10;
                        if (jiaodu<=0)
                            wangfan=true;

                    }
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    shuzi+=10;
                    long end=System.currentTimeMillis();
                    System.out.println("suoyong shijian:"+(end-start));
                    if ((end-start)<100) {
                        try {
                            Thread.sleep(100-(end-start));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }



        }
    }
}
