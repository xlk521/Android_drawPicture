package com.example.drawpicture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater in = new MenuInflater(this);
		in.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//获取自定义的绘图视图
		DrawView dv = (DrawView)findViewById(R.id.draw);
		//取消擦除效果
		dv.paint.setXfermode(null);
		//初始化画笔的宽度
		dv.paint.setStrokeWidth(1);
		switch (item.getItemId()) {
		case R.id.red:
			dv.paint.setColor(Color.RED);
			item.setChecked(true);
			break;
		case R.id.green:
			dv.paint.setColor(Color.GREEN);
			item.setChecked(true);
			break;
		case R.id.blue:
			dv.paint.setColor(Color.BLUE);
			item.setChecked(true);
			break;
		case R.id.width1:
			dv.paint.setStrokeWidth(1);
			item.setChecked(true);
			break;
		case R.id.width2:
			dv.paint.setStrokeWidth(5);
			item.setChecked(true);
			break;
		case R.id.width3:
			dv.paint.setStrokeWidth(10);
			item.setChecked(true);
			break;
		case R.id.clear:
			dv.clear();
			break;
		case R.id.save:
			dv.save();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
