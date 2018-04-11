package net.kzxiv.notify.client;

import android.service.notification.*;
import android.util.*;

public class NotificationService extends NotificationListenerService
{
	public void onCreate()
	{
		super.onCreate();
		Log.d("Notifikator", "Notification service created.");
	}

	public void onDestroy()
	{
		Log.d("Notifikator", "Notification service destroyed.");
		super.onDestroy();
	}

	public void onNotificationPosted(StatusBarNotification sbn)
	{
		// TODO:
	}

	public void onNotificationRemoved(StatusBarNotification sbn)
	{
	}
}
