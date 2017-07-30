package pansong291.mytestapp.other;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.Color;
import android.preference.Preference;
import android.text.util.Linkify;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import pansong291.network.Update;

public class MyUpdate extends Update
{
 private Activity act;
 private Preference process;
 private AlertDialog altdlg=null;
 private TextView ntxt;
 private MyUpdateDialogListener li;
 private boolean showToast,finished=false,successed=false;
 public boolean isHasNew;
 public String dialogMessage;
 
 public MyUpdate(Activity a,String u,MyUpdateDialogListener li)
 {
  super(a,"t.cn/"+u);
  act=a;
  
  this.li=li;
  li.setUpDa(this);
  isHasNew=true;
 }

 public void checkNow(boolean b,Preference v)
 {
  showToast=b;process=v;finished=false;
  if(process!=null)process.setSummary("正在检测更新中，请稍候...");
  start();
 }
 
// public void start()
// {
//  new Thread(myRnabl).start();
// }
 
 private void createDialog(String s)
 {
  ScrollView slv=new ScrollView(act);
  ntxt=new TextView(act);
  ntxt.setPadding(20,10,20,10);
  ntxt.setAutoLinkMask(Linkify.WEB_URLS);
  ntxt.setTextSize(16);
  ntxt.setTextColor(Color.BLACK);
  slv.addView(ntxt);
  altdlg=new Builder(act)
   .setTitle("发现新版本")
   .setCancelable(false)
   .setIcon(android.R.drawable.ic_dialog_info)
   .setNegativeButton("下次再说",li)
   .setPositiveButton("立即下载",li)
   .setView(slv)
   .create();
  ntxt.setText(s);
 }
 
 public void showDialog()
 {
  if(altdlg==null)
   createDialog(dialogMessage);
  altdlg.show();
 }
 
 @Override
 protected void thenDo(boolean success)
 {
  // TODO: Implement this method
  if(!success&&showToast)
  {
   Toast.makeText(act,"网络延时，请稍候再试",0).show();
  }else if(success&&(isHasNew=getHasNew()))
  {
   dialogMessage=String.format("%1$s\n\n最新版本：%2$s\n当前版本：%3$s\n软件大小：%4$s",
   String.format(getUpdateMessage()),
   getNewVersion(),getOldVersion(),getFileSize());
   showDialog();
  }else if(success&&showToast)
   Toast.makeText(act,"已是最新版本",0).show();
  if(process!=null)process.setSummary("立即检测更新");
  successed=success;
  finished=true;
 }
 
 public boolean isFinished()
 {
  return finished;
 }
 
// public void setFinished(boolean b)
// {
//  finished=b;
// }
 
 public boolean isSuccessed()
 {
  return successed;
 }
 
}
