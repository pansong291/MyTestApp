package pansong291.mytestapp.ui;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import pansong291.mytestapp.R;
import android.widget.EditText;
import pansong291.crash.CrashApplication;

public class CrashActivity extends Zactivity
{
 EditText txt;
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  // TODO: Implement this method
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_crash);
  txt=(EditText)findViewById(R.id.activityerrorTextView1);
  txt.setText(getIntent().getStringExtra(CrashApplication.ERROR_MESSAGE_TAG));
 }
 
 public void restartClick(View v)
 {
  startActivity(new Intent(this,MainActivity1.class));
 }
 
}
