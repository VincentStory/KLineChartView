package com.vincent.story.app;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;


public class MyApp extends Application {

    public static MyApp instance;
    //临时保存 需要登录才能访问的页面 在登录成功后跳转
    private Intent intent;
    //屏幕宽高
    int screenWidth;
    int screenHeight;
    /**
     * Activity 栈
     */
    public ActivityStack mActivityStack = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //1、通过WindowManager获取
        DisplayMetrics dm = new DisplayMetrics();
        screenWidth = dm.heightPixels; // 屏幕宽（像素，如：480px）
        screenHeight = dm.widthPixels; // 屏幕高（像素)
        //初始化友盟推送  不用友盟换极光
        //UmengHelper.getInstance().initUmeng();
//        JPushInterface.setDebugMode(true);

        mActivityStack = new ActivityStack();


//        String registrationId = JPushInterface.getRegistrationID(this);
//        SharedPreferencesUtil.put(getApplicationContext(), "RegistrationId", registrationId);
//        L.i("RegistrationId", registrationId);
    }


    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public static MyApp getInstance() {
        return instance;
    }

    public Intent getIntent() {
        return intent;
    }

    public void putIntent(Intent intent) {
        this.intent = intent;
    }

    public void jumpToTargetActivity(Context context) {
        context.startActivity(intent);
        this.intent = null;
    }


}
