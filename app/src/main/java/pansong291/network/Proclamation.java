package pansong291.network;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import pansong291.network.MyRunnable;

public abstract class Proclamation
{
 private String strPackageName,
 strHttpT,strUrl,
 strProclamationMessage;
 private Long lonShowTime;
 private Context contxt;
 private boolean bolForceUpdate;
 private Handler handler=new Handler()
 {
  @Override
  public void handleMessage(Message msg)
  {
   if(msg.what==MyRunnable.TAG_HTTP_FINISHED)
   {
	thenDo(getAllProclamation(msg.obj));
   }
  }
 };

 public Proclamation(Context c,String u)
 {
  contxt=c;
  strPackageName=contxt.getPackageName();
  strHttpT="http";strUrl=strHttpT+"://"+u;
  bolForceUpdate=false;
 }

 private boolean getAllProclamation(Object obj)
 {
  if(obj==null)return false;
  String s=obj.toString();
  //如果包名被修改，这里会抛出异常，相当于做了个包名验证
  s=s.substring(
   s.indexOf(strPackageName+"{")+strPackageName.length(),
   s.indexOf("}"+strPackageName)+1);
  try
  {
   JSONObject josnObj=new JSONObject(s);
   bolForceUpdate=josnObj.optInt("ForceUpdate")>getCurrentVersion(contxt);
   lonShowTime=josnObj.optLong("ShowTime");
   strProclamationMessage=josnObj.optString("ProclamationMessage");
  }catch(JSONException e)
  {
   Log.e("JSONObject","Exception",e);
   return false;
  }
  return true;
 }
 
 private int getCurrentVersion(Context ac)
 {
  int intOldVersionCode=99999999;
  try
  {
   // ---get the package info---
   PackageInfo pi=ac.getPackageManager()
    .getPackageInfo(ac.getPackageName(),0);
   intOldVersionCode=pi.versionCode;
   //strOldVersion=pi.versionName;
  }catch(Exception e)
  {
   Log.e("VersionInfo","Exception",e);
  }
  return intOldVersionCode;
 }
 
 public void start()
 {
  new Thread(new MyRunnable(strUrl,handler,MyRunnable.HTTP_MODEL_GET_RESOURCE_CODE)).start();
 }
 
 protected abstract void thenDo(boolean success);
 
 public String getProclamationMessage()
 {
  return strProclamationMessage;
 }

 public Long getShowTime()
 {
  return lonShowTime;
 }

 public boolean getForceUpdate()
 {
  return bolForceUpdate;
 }
 
}
