package org.chzz.refresh.demo;

import android.app.Application;

import org.chzz.refresh.demo.engine.Engine;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/6/21 下午10:13
 * 描述:
 */
public class App extends Application {
    private static App sInstance;
    private Engine mEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mEngine = new Retrofit.Builder()
                .baseUrl("http://7xk9dj.com1.z0.glb.clouddn.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
    }

    public static App getInstance() {
        return sInstance;
    }

    public Engine getEngine() {
        return mEngine;
    }

}