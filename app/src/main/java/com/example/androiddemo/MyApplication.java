package com.example.androiddemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;


/**
 * 全局application ,app入口, 进行一些初始化工作
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";





    @Override
    public void onCreate() {
        super.onCreate();
        initPlatform();
        // 以下用来捕获程序崩溃异常, 因为google内置机制,app崩溃后会自动重启.导致开发过程中看不到崩溃日志
        if (BuildConfig.DEBUG) {
            Thread.setDefaultUncaughtExceptionHandler(restartHandler); // 程序崩溃时触发线程
            registerActivityListener();//activity管理类
        }

    }






    /**
     * 第三方sdk初始化
     */
    private void initPlatform() {
        LogUtils.getConfig().setLogSwitch(AppUtils.isAppDebug());//控制日志开关
        //设置fragment栈视图查看器
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }



    List<Activity> activities = new ArrayList<>();
    public Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            //下面为调试用的代码，发布时可注释
            Writer info = new StringWriter();
            PrintWriter printWriter = new PrintWriter(info);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.flush();
            printWriter.close();
            String result = info.toString();
            Log.i("sss", result);
            for (int i = 0; i < activities.size(); i++) {
                Log.i("sss", activities.get(i).getLocalClassName());
                if (activities.get(i) != null)
                    activities.get(i).finish();
            }
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    };


    private void registerActivityListener() {

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                /**
                 *  监听到 Activity创建事件 将该 Activity 加入list
                 */
                activities.add(activity);

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (null == activities && activities.isEmpty()) {
                    return;
                }
                if (activities.contains(activity)) {
                    /**
                     *  监听到 Activity销毁事件 将该Activity 从list中移除
                     */
                    activities.remove(activity);
                }
            }
        });
    }


}
