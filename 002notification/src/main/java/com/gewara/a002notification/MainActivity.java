package com.gewara.a002notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NotificationManager mNotificationManager;
    private final int NORMAL = 0;
    private final int FOLD = 1;
    private final int HANG = 2;
    private final int SELF = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //在Android进行通知处理，首先要获取 通知管理器 NotificationManager--系统的Service
        mNotificationManager = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
    }

    @Override
    @OnClick({R.id.btn_normal, R.id.btn_fold, R.id.btn_hang, R.id.btn_self, R.id.btn_clear})
    public void onClick(View view) {
        //PendingIntent和Intent类似，但不是立即触发。在创建Notification的时候，使用PendingIntent指定在点击通知(下拉条状态)是跳转的Activity。
        //new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/")) 跳转到指定Web页面
        Intent mIntent;
        PendingIntent mPendingIntent;
        Notification.Builder mBuilder;
        int id = view.getId();
        switch (id) {
            //普通的Notification
            case R.id.btn_normal:
                mIntent = new Intent(this, MainActivity.class);
                mPendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
                mBuilder = new Notification.Builder(this);
                //给builder设置各种属性
                mBuilder.setContentIntent(mPendingIntent);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.about_logo));
                mBuilder.setContentTitle("普通通知");
                mBuilder.setContentText("这是普通通知的简介");
                mBuilder.setAutoCancel(false);
                //通过通知管理器来显示Notification
                mNotificationManager.notify(NORMAL, mBuilder.build());
                break;
            //折叠式Notification(不行)
            case R.id.btn_fold:
                mIntent = new Intent(this, MainActivity.class);
                mPendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
                mBuilder = new Notification.Builder(this);
                //设置Notification的属性
                mBuilder.setContentIntent(mPendingIntent);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.about_logo));
                mBuilder.setAutoCancel(true);
                mBuilder.setContentTitle("折叠式");
                mBuilder.setContentText("这是折叠式通知的示例");

                //用RemoteViews来创建自定义Notification视图
                RemoteViews mRemoteViews = new RemoteViews(getPackageName(), R.layout.fold_notification);
                Notification mNotification = mBuilder.build();
                //指定展开时的视图
                mNotification.bigContentView = mRemoteViews;
                mNotificationManager.notify(FOLD, mNotification);

                break;
            //悬挂式的通知(不行)
            case R.id.btn_hang:
                mIntent = new Intent(this, MainActivity.class);
                mPendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder = new Notification.Builder(this);
                mBuilder.setContentIntent(mPendingIntent);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.about_logo));
                mBuilder.setAutoCancel(true);
                mBuilder.setContentTitle("悬挂式");
                mBuilder.setContentText("这是悬挂式通知的示例");
//
//                //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
//                PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setFullScreenIntent(mPendingIntent, true);
                mNotificationManager.notify(HANG, mBuilder.build());
                break;
            case R.id.btn_self:
                mIntent = new Intent(this, MainActivity.class);
                mPendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
                mBuilder = new Notification.Builder(this);
                mBuilder.setContentIntent(mPendingIntent);
                mBuilder.setAutoCancel(false);

                mBuilder.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setTicker("TickerText:您有新短消息，请注意查收！");
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.fold_notification);
                mBuilder.setContent(remoteViews);
                mNotificationManager.notify(SELF, mBuilder.build());
                break;
            case R.id.btn_clear:
                mNotificationManager.cancelAll();
                break;
            default:
                break;
        }
    }
}