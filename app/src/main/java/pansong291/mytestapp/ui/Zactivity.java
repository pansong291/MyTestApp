package pansong291.mytestapp.ui;

import android.app.Activity;
import android.os.Bundle;
import pansong291.crash.ASControl;
import android.widget.Toast;
import android.content.SharedPreferences;

public class Zactivity extends Activity
{
 public static final String QZGX="QZGX";
 public SharedPreferences sp;
 @Override
 protected void onResume()
 {
  super.onResume();
  
 }
 
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  ASControl.getASControl().addActivity(this);
  sp=getSharedPreferences(getPackageName()+"_preferences",0);
 }

 @Override
 protected void onDestroy()
 {
  super.onDestroy();
  ASControl.getASControl().removeActivity(this);
 }
 
 public void toast(String s)
 {
  toast(s,0);
 }
 
 public void toast(String s,int i)
 {
  Toast.makeText(this,s,i).show();
 }
 
}
