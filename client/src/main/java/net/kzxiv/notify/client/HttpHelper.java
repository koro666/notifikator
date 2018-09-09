package net.kzxiv.notify.client;

import java.io.*;

final class HttpHelper
{
	public final static String DASHDASH = "--";
	public final static String NEWLINE = "\r\n";
	public final static String SEPARATOR = "********";

	public final static byte[] generateMultipartBody(Object[] entries) throws IOException
	{
		ByteArrayOutputStream strm0 = null;
		DataOutputStream strm1 = null;
		byte[] result;
		try
		{
			strm0 = new ByteArrayOutputStream();
			strm1 = new DataOutputStream(strm0);

			final int count = entries.length;
			for (int i = 0; i < count; i += 2)
			{
				final String key = (String)entries[i];
				final Object value = entries[i + 1];

				if (value == null)
					continue;

				strm1.writeBytes(DASHDASH);
				strm1.writeBytes(SEPARATOR);
				strm1.writeBytes(NEWLINE);

				final boolean binary = (value instanceof byte[]);

				strm1.writeBytes(String.format("Content-Disposition: form-data; name=\"%s\"", key));
				strm1.writeBytes(NEWLINE);

				if (binary)
				{
					strm1.writeBytes("Content-Type: application/octet-stream");
					strm1.writeBytes(NEWLINE);
					strm1.writeBytes("Content-Transfer-Encoding: binary");
					strm1.writeBytes(NEWLINE);
				}
				else
				{
					strm1.writeBytes("Content-Type: text/plain");
					strm1.writeBytes(NEWLINE);
				}

				strm1.writeBytes(NEWLINE);

				if (binary)
					strm1.write((byte[])value);
				else
					strm1.writeBytes(value.toString());

				strm1.writeBytes(NEWLINE);
			}

			strm1.writeBytes(DASHDASH);
			strm1.writeBytes(SEPARATOR);
			strm1.writeBytes(DASHDASH);
			strm1.writeBytes(NEWLINE);

			strm1.flush();
			return strm0.toByteArray();
		}
		finally
		{
			if (strm1 != null)
				strm1.close();

			if (strm0 != null)
				strm0.close();
		}
	}
}
