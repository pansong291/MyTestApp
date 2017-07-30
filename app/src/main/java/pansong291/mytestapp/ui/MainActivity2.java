package pansong291.mytestapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import pansong291.mytestapp.R;

public class MainActivity2 extends Zactivity
{
  private ViewPager viewPager;
  private View ll,mainButton,view1,view2,view3;//需要滑动的页卡
  private List<View> viewList;//把需要滑动的页卡添加到这个list中
  private boolean isLastView;//判断最后一页
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main2);
   viewPager=(ViewPager)findViewById(R.id.mViewPager1);
   mainButton=findViewById(R.id.mainButton);
   //这个ll用于设置背景
   ll=findViewById(R.id.mb1);
   
   LayoutInflater inflater=getLayoutInflater();
	view1=inflater.inflate(R.layout.viewpage_item1,null);
	view2=inflater.inflate(R.layout.viewpage_item2,null);
	view3=inflater.inflate(R.layout.viewpage_item3,null);

   viewList=new ArrayList<View>();
   //将要分页显示的View装入数组中
   viewList.add(view1);
   viewList.add(view2);
   viewList.add(view3);
   viewPager.setAdapter(new MyViewPagerAdapter(viewList));
 
   viewPager.setOnPageChangeListener(new OnPageChangeListener(){
	 @Override//当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法会一直调用
	 public void onPageScrolled(int p1, float p2, int p3)
	 { //p1:开始点击滑动的页面;p2:当前页面偏移百分比;p3:当前页面偏移像素
	  
	 }
	 @Override//页面跳转完后调用
	 public void onPageSelected(int p)
	 { //p为当前页面
	  if(p==0)
	   {
		ll.setBackgroundResource(R.drawable.feature_bg_1);
	    view1.setBackgroundResource(R.color.color_alpha);
		view2.setBackgroundResource(R.drawable.feature_bg_2);
	   }else if(p==1)
	   {
		ll.setBackgroundResource(R.drawable.feature_bg_2);
		view1.setBackgroundResource(R.drawable.feature_bg_1);
	    view2.setBackgroundResource(R.color.color_alpha);
		view3.setBackgroundResource(R.drawable.feature_bg_3);
		isLastView=false;
		mainButton.setVisibility(8);
	   }else if(p==2)
	   {
		ll.setBackgroundResource(R.drawable.feature_bg_3);
		view2.setBackgroundResource(R.drawable.feature_bg_2);
	    view3.setBackgroundResource(R.color.color_alpha);
		isLastView=true;
	   }
	 }
	 @Override//在状态改变的时候调用
	 public void onPageScrollStateChanged(int p1)
	 { //p1==0:什么都没做;p1==1:正在滑动;p1==2:滑动完毕;
	  if(p1==1)mainButton.setVisibility(8);
	  else if(isLastView)mainButton.setVisibility(0);
	 }
   });
  }
  
 public class MyViewPagerAdapter extends PagerAdapter
 { private List<View> mListViews;
   public MyViewPagerAdapter(List<View> mListViews)
   {
	this.mListViews=mListViews;//构造方法，参数是我们的页卡，这样比较方便。
   }
   @Override
   public void destroyItem(ViewGroup container,int position,Object object)
   {
	container.removeView(mListViews.get(position));//删除页卡
   }
   @Override
   public Object instantiateItem(ViewGroup container,int position)
   { //这个方法用来实例化页卡
   container.addView(mListViews.get(position),0);//添加页卡
   return mListViews.get(position);
   }
   @Override
   public int getCount()
   {
	return mListViews.size();//返回页卡的数量
   }
   @Override
   public boolean isViewFromObject(View arg0,Object arg1)
   {
	return arg0==arg1;//官方提示这样写
   }
 }
 
 public void onMainClick(View v)
 {
  Intent intent=
   new Intent(MainActivity2.this,NewActivity1.class);
  startActivity(intent);
  overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
  //设置切换动画，先进入，后退出，都向左
  finish();//结束界面
 }
 
  
}
