import android.util.Log;

public class Util
{
	public static void log(String src, String msg)
	{
		/*
		 * LOGOPTS for non-android
		 * System.out.println("["+src+"] " + msg);
		 * 
		 **/
		Log.i(src, msg);
		return;
	}
}
