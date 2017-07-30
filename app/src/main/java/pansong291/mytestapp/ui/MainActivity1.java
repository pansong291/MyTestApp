package pansong291.mytestapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import pansong291.mytestapp.R;

public class MainActivity1 extends Zactivity
{
 boolean isFirst=true;
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main1);
  
  if(isFirst)
  {
   start(MainActivity2.class);
  }else start(NewActivity1.class);
 }
 
 private void start(final Class<?>activity)
 {
  new Handler().postDelayed
  ( new Runnable()
   { @Override
	public void run() 
	{ Intent intent=
	  new Intent(MainActivity1.this,activity);//从启动动画ui跳转到主ui
	 startActivity(intent);//闪屏
	 overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
	 //设置切换动画，先进入，后退出，都向左
	 finish();//结束启动界面
	}
   },1000
  );
 }
 
}
