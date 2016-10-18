package com.gewara.a009weight;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

/**
 * Created by taofaqi on 2016/10/12.
 */
public class MyWight extends AppWidgetProvider {
    /**
     * 到达指定时间或者用户第一次创建AppWight时所调用的方法
     * */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
    /**
     * 删除一个AppWight所调用的方法
     * */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }
    /**
     * 接收广播事件
     * */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
    /**
     * 创建第一个AppWight实例所调用的方法
     * */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }
    /**
     * 删除最后一个AppWight所调用的方法
     * */
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
