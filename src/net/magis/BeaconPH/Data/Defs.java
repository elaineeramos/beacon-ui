package net.magis.BeaconPH.Data;

public class Defs
{
	public static final int REQUEST_TYPE_UNKNOWN = 1000;
	public static final int REQUEST_TYPE_FIND_PERSON = 1001;
	public static final int REQUEST_TYPE_FIND_LOCATION = 1002;
	public static final int REQUEST_TYPE_REPORT_PERSON = 1003;
	public static final int REQUEST_TYPE_GET_INFO = 1004;
	
	public static final int RESPONSE_TYPE_UNKNOWN = 1100;
	public static final int RESPONSE_TYPE_PERSON = 1101;
	public static final int RESPONSE_TYPE_LOCATION = 1102;

	public static final int STATUS_TIMED_OUT	= -1;
	public static final int STATUS_BUSY 		= -1;
	public static final int STATUS_FAILED 	= 0;
	public static final int STATUS_OK 		= 1;
	
	public static final double LAT_ERROR_OFFSET = -0.094;
	public static final double LON_ERROR_OFFSET = 0.0;
	
}
