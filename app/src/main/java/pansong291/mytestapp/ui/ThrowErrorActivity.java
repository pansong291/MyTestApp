package pansong291.mytestapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import pansong291.crash.CrashApplication;
import pansong291.mytestapp.R;

public class ThrowErrorActivity extends Zactivity
{
 private TextView error;
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_throwerror);
  CrashApplication application=(CrashApplication)this.getApplication();
  String packageName=application.getPackageName();
  System.out.println("packageName==="+packageName);
  error=(TextView)findViewById(R.id.error_textview);
 }
 
 public void onExceptionClick(View v)throws Exception
 {
  throw new Exception();
 }
 
 public void onErrorClick(View v)
 {
  throw new Error();
 }
 
}
