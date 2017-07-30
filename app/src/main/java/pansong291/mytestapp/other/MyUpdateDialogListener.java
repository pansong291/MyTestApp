package pansong291.mytestapp.other;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import pansong291.crash.ASControl;
import pansong291.mytestapp.ui.Zactivity;

public class MyUpdateDialogListener implements DialogInterface.OnClickListener
{
 Activity act;
 MyUpdate upd;
 SharedPreferences sp=null;
 
 public MyUpdateDialogListener(Activity a)
 {
  act=a;
 }
 
 public void setUpDa(MyUpdate u)
 {
  upd=u;
 }
 
 @Override
 public void onClick(DialogInterface p1,int p2)
 {
  switch(p2)
  {
   case -1://积极
//    act.toast("下载地址为"+upd.getDownloadUrl());
    act.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(upd.getDownloadUrl())));
   case -2://消极
    if(sp==null)sp=act.getSharedPreferences(act.getPackageName()+"_preferences",0);
	if(sp.getBoolean(Zactivity.QZGX,false))
	{
	 ASControl.getASControl().finishProgrom();
	}
	break;
//   case -3://中间
//	sp.edit().putBoolean("checkBox3",false).commit();
//	break;
  }
 }
}
