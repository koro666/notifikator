package net.kzxiv.notify.client;

import android.app.*;
import android.content.res.*;
import android.os.*;
import android.preference.*;

public class ConfigurationActivity extends PreferenceActivity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		addPreferencesFromResource(R.xml.preferences);
	}

	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference)
	{
		Resources res = getResources();
		if (res.getString(R.string.key_send).equals(preference.getKey()))
		{
			NotificationManager mgr = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
			Notification.Builder nb = new Notification.Builder(this);

			nb.setContentTitle(res.getString(R.string.notification_title));
			nb.setContentText(res.getString(R.string.notification_text));
			// TODO: Set real small icon from app
			nb.setSmallIcon(android.R.drawable.sym_def_app_icon);

			// TODO: Set large icon

			mgr.notify(0, nb.build());
			return false;
		}

		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
}