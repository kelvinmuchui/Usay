package com.example.kelvin.mychat.Utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.kelvin.mychat.Usay.ChatService;

/**
 * Created by kelvin on 3/31/18.
 */

public class ServiceUtil {
    private static ServiceConnection connectionServiceChatForStart = null;
    private static ServiceConnection connectionServiceChatForDestroy = null;

    public static boolean isServiceFriendChatRunning(Context context) {
        Class<?> serviceClass = ChatService.class;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void stopServiceChat(Context context, final boolean kill) {
        if (isServiceFriendChatRunning(context)) {
            Intent intent = new Intent(context, ChatService.class);
            if (connectionServiceChatForDestroy != null) {
                context.unbindService(connectionServiceChatForDestroy);
            }
            connectionServiceChatForDestroy = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName className,
                                               IBinder service) {
                    ChatService.LocalBinder binder = (ChatService.LocalBinder) service;
                    binder.getService().stopSelf();
                }

                @Override
                public void onServiceDisconnected(ComponentName arg0) {
                }
            };
            context.bindService(intent, connectionServiceChatForDestroy, Context.BIND_NOT_FOREGROUND);
        }
    }
}
