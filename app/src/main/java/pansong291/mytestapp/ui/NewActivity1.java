package pansong291.mytestapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import pansong291.mytestapp.other.MyUpdate;
import android.widget.TextView;
import pansong291.mytestapp.R;
import pansong291.mytestapp.other.MyUpdateDialogListener;
import pansong291.mytestapp.other.MyProclamation;
import android.widget.LinearLayout;

public class NewActivity1 extends Zactivity
{
 static MyUpdate upd;
 static MyUpdateDialogListener lis;
 LinearLayout newll;
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_new1);
  newll=(LinearLayout)findViewById(R.id.new1_ll);
  new MyProclamation(this,"R9tcy9Y",newll);
  lis=new MyUpdateDialogListener(this);
//  upd=new UpDataO("http://t.cn/RPTVoak",lis);
  upd=new MyUpdate(this,"R9tc7zC",lis);
	//false不Toast提示，null指没有进度条
  if(checkQM()&&sp.getBoolean("set_auto_check_new",true))
  //upd.checkNow(this,false,null);
  upd.checkNow(false,null);
 else toast("不检测更新");
 }

 protected boolean checkQM()
 {
  int code2,code=code2=-672009692;
  //防止签名被篡改
  try
  {
   PackageInfo packageInfo=getPackageManager()
  .getPackageInfo(getPackageName(),PackageManager.GET_SIGNATURES);
   Signature[]signs=packageInfo.signatures;
   Signature sign=signs[0];
   code=sign.hashCode();
   //if(code!=0)//为事先计算出来的
    toast(code+"\n"+(code==code2));
//第一次可以让toast显示code值，然后把它替换掉xxxxxx
//这个是算签名的，记得不能用debug的签名，否则别人修改你的安装后，用debug签名，，，，就没用了

  }catch(PackageManager.NameNotFoundException e){}
  return code==code2;
 }
 
 public void loadingDialog(View v)
 {
  LayoutInflater lif=getLayoutInflater();
  View vi=lif.inflate(R.layout.loadingdialog,null);
  View rota=vi.findViewById(R.id.loadingdialogImageView);
  rota.setAnimation(AnimationUtils.loadAnimation(this,R.anim.xuanzhuan));
  
  new AlertDialog.Builder(this)
  .setView(vi)
  .setNegativeButton("取消",null)
  .show();
 }
 
 public void onCrashClick(View c)
 {
  startActivity(new Intent(this,ThrowErrorActivity.class));
 }
 
 public void goToSetting(View x)
 {
  startActivity(new Intent(this,NewActivity2.class));
 }
 
 @Override
 public void onBackPressed()
 {
  super.onBackPressed();
  overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
 }
 
 
 
}
