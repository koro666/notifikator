package net.kzxiv.notify.client;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

final class HttpHelper
{
	private static final char[] HEXCHARS = new char[]
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public final static String generateMultipartSeparator()
	{
		final Random random = new Random();
		final StringBuilder builder = new StringBuilder(40);

		builder.append("------------------------");
		for (int i = 0; i < 16; ++i)
			builder.append(HEXCHARS[random.nextInt(16)]);

		return builder.toString();
	}

	public final static byte[] generateMultipartBody(String separator, Object[] entries, Charset charset) throws IOException
	{
		ByteArrayOutputStream strm = null;
		try
		{
			final Charset ascii = Charset.forName("US-ASCII");
			final byte[] newlineBytes = "\r\n".getBytes(ascii);
			final byte[] binaryTypeBytes = "Content-Type: application/octet-stream\r\nContent-Transfer-Encoding: binary\r\n".getBytes(ascii);
			final byte[] textTypeBytes = "Content-Type: text/plain\r\n".getBytes(ascii);
			final byte[] separatorBytes = String.format("--%s\r\n", separator).getBytes(ascii);

			strm = new ByteArrayOutputStream();

			final int count = entries.length;
			for (int i = 0; i < count; i += 2)
			{
				final String key = (String)entries[i];
				final Object value = entries[i + 1];

				if (value == null)
					continue;

				strm.write(separatorBytes);

				final boolean binary = (value instanceof byte[]);

				strm.write(String.format("Content-Disposition: form-data; name=\"%s\"\r\n", key).getBytes(ascii));
				strm.write(binary ? binaryTypeBytes : textTypeBytes);
				strm.write(newlineBytes);
				strm.write(binary ? (byte[])value : value.toString().getBytes(charset));
				strm.write(newlineBytes);
			}

			strm.write(separatorBytes);

			return strm.toByteArray();
		}
		finally
		{
			if (strm != null)
				strm.close();
		}
	}
}
