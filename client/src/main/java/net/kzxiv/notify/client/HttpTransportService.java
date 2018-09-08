package net.kzxiv.notify.client;

import android.app.*;
import android.content.*;
import android.util.*;
import java.io.*;
import java.net.*;

public class HttpTransportService extends IntentService
{
	public final static String EXTRA_URL = "net.kzxiv.notifikator.client.http.URL";
	public final static String EXTRA_AUTH = "net.kzxiv.notifikator.client.http.AUTH";
	public final static String EXTRA_USERNAME = "net.kzxiv.notifikator.client.http.USERNAME";
	public final static String EXTRA_PASSWORD = "net.kzxiv.notifikator.client.http.PASSWORD";
	public final static String EXTRA_PAYLOAD_TYPE = "net.kzxiv.notifikator.client.http.PAYLOAD_TYPE";
	public final static String EXTRA_PAYLOAD = "net.kzxiv.notifikator.client.http.PAYLOAD";

	public HttpTransportService()
	{
		super("Notifikator HTTP Transport Service");
	}

	protected void onHandleIntent(Intent intent)
	{
		final String endpointUrl = intent.getStringExtra(EXTRA_URL);
		final boolean endpointAuth = intent.getBooleanExtra(EXTRA_AUTH, false);
		final String endpointUsername = intent.getStringExtra(EXTRA_USERNAME);
		final String endpointPassword = intent.getStringExtra(EXTRA_PASSWORD);
		final String contentType = intent.getStringExtra(EXTRA_PAYLOAD_TYPE);
		final byte[] postData = intent.getByteArrayExtra(EXTRA_PAYLOAD);

		try
		{
			URL url = new URL(endpointUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", contentType);

			if (endpointAuth && endpointUsername != null && endpointPassword != null)
			{
				connection.setRequestProperty("Authorization",
					String.format("Basic %s",
						Base64.encodeToString(
							String.format("%s:%s", endpointUsername, endpointPassword).getBytes(),
							Base64.NO_WRAP)));
			}

			connection.setConnectTimeout(2500);
			connection.setReadTimeout(5000);
			connection.setFixedLengthStreamingMode(postData.length);

			connection.setUseCaches(false);
			connection.setDoInput(false);
			connection.setDoOutput(true);

			try (OutputStream ostrm = connection.getOutputStream())
			{
				ostrm.write(postData);
			}

			connection.getResponseCode();
			connection.disconnect();
		}
		catch (Exception e)
		{
			Log.e("Notifikator", String.format("Failed HTTP POST: %s", e.toString()));
		}
	}
}
