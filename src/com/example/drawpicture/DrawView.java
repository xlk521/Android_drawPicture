package com.example.drawpicture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View{

	private int view_width = 0;
	private int view_height = 0;
	private float prex;
	private float prey;
	private Path path;
	public Paint paint = null;
	Bitmap bm = null;
	Canvas canvas1 = null;

	public DrawView(Context context, AttributeSet attrs) {

		super(context, attrs);
		//��Ļ���
		view_width = context.getResources().getDisplayMetrics().widthPixels;
		//��Ļ�߶�
		view_height = context.getResources().getDisplayMetrics().heightPixels;
		//����������
		bm = Bitmap.createBitmap(view_width,view_height,Config.ARGB_8888);
		canvas1 = new Canvas();
		path = new Path();
		canvas1.setBitmap(bm);
		paint = new Paint(Paint.DITHER_FLAG);
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(1);
		paint.setAntiAlias(true);
		paint.setDither(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0xffffffff);
		Paint p = new Paint();
		canvas.drawBitmap(bm, 0,0, p);
		canvas.drawPath(path, paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stu
		//��ȡ�����¼�������λ��
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			prex = x;
			prey = y;
			break;
		case MotionEvent.ACTION_MOVE:
			float dx = Math.abs(x-prex);
			float dy = Math.abs(y-prey);
			if (dx>=5 || dy>5) {
				//�ж��Ƿ�������Χ��
				path.quadTo(prex, prey, (x+prex)/2, (y+prey)/2);
				prex = x;
				prey = y;
			}
			break;
		case MotionEvent.ACTION_UP:
			canvas1.drawPath(path, paint);
			path.reset();
			break;
		default:
			break;
		}
		invalidate();
		return true;
	}

	public void clear(){
		//����ͼ���ص�ʱ�Ĵ���ʽ
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		//���ñʴ��Ŀ��
		paint.setStrokeWidth(50);
	}
	
	public void save(){
		try {
			saveBitmap("myPicture");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveBitmap(String string) throws IOException{
		// TODO Auto-generated method stub
		//�����ļ�����
		File file = new File(Environment.getExternalStorageDirectory().getPath()+string+".png");
		//����һ�����ļ�
		file.createNewFile();
		//����һ���ļ����������
		FileOutputStream fileos = new FileOutputStream(file);
		//��ͼ��ѹ��Ϊpng��ʽ��������������
		bm.compress(Bitmap.CompressFormat.PNG, 100, fileos);
		//���������е�����ȫ��д�����������
		fileos.flush();
		//�ر��ļ����������
		fileos.close();
	}
}
