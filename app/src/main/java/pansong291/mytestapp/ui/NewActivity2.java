package pansong291.mytestapp.ui;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.View;
import pansong291.crash.ASControl;
import pansong291.mytestapp.R;
import pansong291.mytestapp.other.MyUpdate;
import pansong291.mytestapp.other.MyUpdateDialogListener;
import android.widget.Toast;

public class NewActivity2 extends PreferenceActivity implements OnPreferenceClickListener
{
 CheckBoxPreference cbp;
 Preference btn_about,btn_help,btn_check_new;
 MyUpdate mUpdate;
 
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  addPreferencesFromResource(R.xml.activity_setting);
  ASControl.getASControl().addActivity(this);

  cbp=(CheckBoxPreference)findPreference("set_auto_check_new");
  btn_about=findPreference("set_about");
  btn_help=findPreference("set_help");
  btn_check_new=findPreference("set_check_new_now");
  btn_check_new.setTitle("版本: "+"VersionName");
  
  btn_about.setOnPreferenceClickListener(this);
  btn_help.setOnPreferenceClickListener(this);
  btn_check_new.setOnPreferenceClickListener(this);

 }

 @Override
 public boolean onPreferenceClick(Preference p1)
 {
  switch(p1.getKey())
  {
   case "set_help":
    new AlertDialog.Builder(this)
     .setTitle("帮助")
     .setMessage("帮助")
     .setPositiveButton("确定",null)
     .show();
    break;
   case "set_about":
    
    break;
   case "set_check_new_now":
    if(mUpdate!=null&&mUpdate.isFinished())
    {
     if(!mUpdate.isSuccessed())
     {
      mUpdate.checkNow(true,p1);
     }else if(mUpdate.getHasNew())
     {
      mUpdate.showDialog();
     }else
     {
      Toast.makeText(this,"已是最新版本",0).show();
     }
    }else if(mUpdate==null)
    {
     mUpdate=new MyUpdate(this,"R9tc7zC",new MyUpdateDialogListener(this));
     mUpdate.checkNow(true,p1);
    }
    break;
  }
  return true;
 }
 
}
