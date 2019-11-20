package com.gxg.app;

import android.app.Application;

import com.tencent.bugly.Bugly;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //参数解析：
        //
        //参数1：上下文对象
        //
        //参数2：注册时申请的APPID
        //
        //参数3：是否开启debug模式，true表示打开debug模式，false表示关闭调试模式
        Bugly.init(getApplicationContext(), "0498d49627", true);
    }
}
