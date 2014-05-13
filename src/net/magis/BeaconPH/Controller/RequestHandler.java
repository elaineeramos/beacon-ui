package net.magis.BeaconPH.Controller;

import net.magis.BeaconPH.Data.Defs;
import net.magis.BeaconPH.Data.FindLocationRequest;
import net.magis.BeaconPH.Data.FindPersonRequest;
import net.magis.BeaconPH.Data.ReportPersonRequest;
import net.magis.BeaconPH.Data.Request;
import net.magis.BeaconPH.Interfaces.RequestEvent;
import net.magis.BeaconPH.Interfaces.WebInterfaceListener;

public class RequestHandler implements WebInterfaceListener
{
	final String _baseApiUrl = "http://magis.cloudapp.net/api/";
	
	private boolean isSending;
	
	private RequestEvent callbackTarget = null;
	private Thread requestHandlerThread = null;
	private HandleRequestTask handleRequestTask = null;
	private WebInterface webIntf = null;
	
	public RequestHandler(RequestEvent callbackTarget)
	{
		this.callbackTarget = callbackTarget;
		this.isSending = false;
		this.webIntf = new WebInterface(this);
	}
	
	public RequestHandler()
	{
		this(null);
	}
	
	public int pushRequest(Request request)
	{
		if (isSending)
		{
			/* If we are already sending something, block further requests
			 * for now.  */
			return Defs.STATUS_BUSY;
		}
		
		if (handleRequestTask == null) 
		{
			handleRequestTask = new HandleRequestTask(request);
			if (requestHandlerThread == null)
			{
				requestHandlerThread = new Thread(handleRequestTask);
			}
		}
		
		requestHandlerThread.start();
		
		return Defs.STATUS_OK;		
	}
	
	private void processRequest(Request request)
	{
		/* Process according to request type */
		switch (request.getType())
		{
			case Defs.REQUEST_TYPE_FIND_LOCATION:
				webIntf.sendRequest("GET", getRequestUrl((FindLocationRequest)request), "");
				break;
			case Defs.REQUEST_TYPE_FIND_PERSON:
				webIntf.sendRequest("GET", getRequestUrl((FindPersonRequest)request), "");
				break;
			case Defs.REQUEST_TYPE_REPORT_PERSON:
				webIntf.sendRequest("GET", getRequestUrl((ReportPersonRequest)request), "");
				break;
			default:
				Util.log(this.getClass().getSimpleName(), "Error: Unknown request type: " + request.getType());
				break;
		}
		
		return;
	}
	
	private String getRequestUrl(FindLocationRequest request)
	{
		final String locTypeUrlPart[] = { "", "schools/", "churches/", "firestations/",
										  "policestations/", "publicoffices/", "hospitals/" };
	
		int locCode = request.getLocationType();
		if ((locCode < 0) || (locCode >= locTypeUrlPart.length))
		{
			Util.log(this.getClass().getSimpleName(), "Error: Invalid location type query in request!");
			return "";
		}
		
		/** TODO -- other query options shall be added here once server-side api is more complete **/
		
		return (_baseApiUrl + locTypeUrlPart);
	}
	
	private String getRequestUrl(FindPersonRequest request)
	{
		/** TODO -- query options shall be added here once server-side api is more complete **/
		return _baseApiUrl + "publicoffices/11";
	}
	
	private String getRequestUrl(ReportPersonRequest request)
	{
		/** TODO -- query options shall be added here once server-side api is more complete **/
		return _baseApiUrl + "publicoffices/11";
	}
	
	private class HandleRequestTask implements Runnable
	{
		private Request request = null; 
		public HandleRequestTask(Request request)
		{
			this.request = request;
		}
		
		@Override
		public void run()
		{
			/* Load and process the request */
			processRequest(request);
		}
	}

	@Override
	public void onResponseAvailable()
	{
		String respStr = this.webIntf.receiveResponse();
		
		/* Process the response string only if it isn't empty */
		if (!respStr.equals(""))
		{
			/* Inform the callback target that the request was finished */
			callbackTarget.onRequestFinished(new ResponseHandler().getResponse(respStr));
		}
		
		/* Reset the sending state flag */
		if (isSending)
		{
			isSending = false;
		}
	}
	
	/** Testing Method Only **
	public static void main(String args[])
	{
			RequestHandler rh = new RequestHandler(new RequestEvent() {
				@Override
				public void onRequestFinished(Response resp) {
					if (resp != null)
					{
						Util.log("X", "> Response Type: " + resp.type);
						if (resp.type == Defs.RESPONSE_TYPE_LOCATION)
						{
							List<Location> li = ((LocationResponse) resp).getLocationList();
							Util.log("X", li.get(0).toString());
							Util.log("X", li.get(10).toString());
							Util.log("X", li.get(li.size()/2).toString());
							Util.log("X", li.get(li.size()-20).toString());
							Util.log("X", li.get(li.size()-1).toString());
						}
					}
				}
			});
			
			Request req = new FindLocationRequest(Location.TYPE_ANY, false, false);
			
			rh.processRequest(req);
			
	}
	**/
}
