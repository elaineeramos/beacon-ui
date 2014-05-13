package net.magis.BeaconPH.Controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import net.magis.BeaconPH.Data.Defs;
import net.magis.BeaconPH.Interfaces.WebInterfaceListener;

public class WebInterface
{
	private String lastResponse;
	private WebInterfaceListener listener;
	
	public WebInterface(WebInterfaceListener webIntfListener)
	{
		lastResponse = "";
		listener = webIntfListener;
	}
	
	public int sendRequest(String method, String urlStr, String body)
	{
		URL url;
		HttpURLConnection urlConn;
		
		/* Checks */
		if (!method.equals("GET") && !method.equals("POST"))
		{
			Util.log(this.getClass().getSimpleName(), "Error: Invalid request method type!");
			return Defs.STATUS_FAILED;
		}

		/* Attempt to send the GET request */
		try
		{
			url = new URL(urlStr);
			urlConn = (HttpURLConnection) url.openConnection();
			
			/* Set the connection parameters */
			urlConn.setRequestMethod(method);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setRequestProperty("Accept", "application/json");
			urlConn.setConnectTimeout(60000);
			urlConn.setReadTimeout(10000);
			
			Util.log(this.getClass().getSimpleName(), "GET Request URL: " + url.toString());
			
			/* Send the GET request */
			urlConn.connect();
		}
		catch (SocketTimeoutException e)
		{
			Util.log(this.getClass().getSimpleName(), "Error: Connection Timed Out.");
			return Defs.STATUS_TIMED_OUT;
		}
		catch (IOException e)
		{
			Util.log(this.getClass().getSimpleName(), "Error: IOException occurred!");
			return Defs.STATUS_FAILED;
		}
		
		/* At this point, we can assume that the GET/POST request was successful
		 * and that we have a response waiting. Read that response. */
		try
		{
			/* Check the response code we received first */
			if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
			{
				Util.log(this.getClass().getSimpleName(), "Error: Non-HTTP_OK Response code (" + urlConn.getResponseCode() + ")");
				printErrorResponse(urlConn);
				return Defs.STATUS_FAILED;
			}
			
			/* Get the response */
			Util.log(this.getClass().getSimpleName(), "Info: Passing received response...");
			lastResponse = getResponseString(urlConn);
			
			listener.onResponseAvailable();
			
			return Defs.STATUS_OK;
		}
		catch (IOException e)
		{
			Util.log(this.getClass().getSimpleName(), "Error: IOException occurred!");
			return Defs.STATUS_FAILED;
		}
	}
	
	public String receiveResponse()
	{
		return lastResponse;
	}
	
	private void printErrorResponse(HttpURLConnection conn) throws IOException
	{
		DataInputStream input = new DataInputStream(conn.getErrorStream());
		char c = '\0';
		
		System.out.println("Response: " + conn.getResponseMessage());
		System.out.println("Response code: " + conn.getResponseCode());
		
		System.out.print("> ");
		
		/*
		while (null != ((str = input.readLine()))) {
			System.out.println("> " + str);
		}*/
		
		while ((c = input.readChar()) != -1)
		{
			System.out.print(c);
			if (c == '\n')
			{
				System.out.println();
			}
		}

		input.close();
	}
	
	private String getResponseString(HttpURLConnection conn) throws IOException
	{
		DataInputStream input = new DataInputStream(conn.getInputStream());
		char c = '\0';
		String fullStr = "";

		/*
		while (null != ((str = input.readLine()))) {
			fullStr += str;
		}*/
		
		while ((c = input.readChar()) != -1)
		{
			fullStr += c;
		}
		
		return fullStr;
	}
	
}
