package com.urqa.Collector;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.urqa.common.CallStackData;
import com.urqa.common.StateData;
import com.urqa.common.JsonObj.ErrorSendData;
import com.urqa.eventpath.EventPathManager;
import com.urqa.rank.ErrorRank;

public class ErrorReportFactory {
	
	

	public static ErrorReport CreateErrorReport(Throwable e,String tag,ErrorRank rank ,Context context)
	{
		ErrorReport report = new ErrorReport();
		report.ErrorData = CreateSendData(e,tag,rank,context);
		report.LogData	 = LogCollector.getLog(context);
		
		return report;
	}
	
	public static ErrorReport CreateNativeErrorReport(Context context)
	{
		ErrorReport report = new ErrorReport();
		report.ErrorData = CreateNativeSendData(context);
		report.LogData	 = LogCollector.getLog(context);
		
		return report;
	}
	
	private static ErrorSendData CreateNativeSendData(Context context)
	{
		ErrorSendData senddata = new ErrorSendData();
		
	
		PackageManager packagemanager = context.getPackageManager();
		PackageInfo packageinfo = null;
		try {
			packageinfo = packagemanager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		
		senddata.apikey 		= StateData.APIKEY;
		senddata.datetime 		= DateCollector.GetDateYYMMDDHHMMSS(context);
		senddata.device			= android.os.Build.MODEL;
		senddata.country		= DeviceCollector.GetNational(context);
		senddata.appversion		= packageinfo.versionName;
		senddata.osversion		= android.os.Build.VERSION.RELEASE;
		senddata.gpson			= (DeviceCollector.GetGps(context)) ? 1 : 0;
		senddata.wifion			= (DeviceCollector.GetWiFiNetwork(context)) ? 1 : 0;
		senddata.mobileon		= (DeviceCollector.GetMobileNetwork(context)) ? 1 : 0;
		senddata.scrwidth		= DeviceCollector.GetWidthScreenSize(context);
		senddata.scrheight		= DeviceCollector.GetHeightScreenSize(context);
		senddata.batterylevel	= DeviceCollector.GetBatteryLevel(context);
		senddata.availsdcard	= DeviceCollector.BytetoMegaByte(DeviceCollector.getAvailableExternalMemorySize());
		senddata.rooted			= (DeviceCollector.CheckRoot()) ? 1 : 0;
		senddata.appmemtotal	= DeviceCollector.BytetoMegaByte(DeviceCollector.GetTotalMemory());
		senddata.appmemfree		= DeviceCollector.BytetoMegaByte(DeviceCollector.GetFreeMemory());
		senddata.appmemmax		= DeviceCollector.BytetoMegaByte(DeviceCollector.GetMaxMemory());
		senddata.kernelversion	= DeviceCollector.GetLinuxKernelVersion();
		senddata.xdpi			= DeviceCollector.GetXDPI(context);
		senddata.ydpi			= DeviceCollector.GetYDPI(context);
		senddata.scrorientation = DeviceCollector.GetOrientation(context);
		senddata.sysmemlow		= (DeviceCollector.GetSystemLowMemory()) ? 1 : 0;
		senddata.eventpaths		= EventPathManager.GetErrorEventPath();
		senddata.locale			= DeviceCollector.GetLocale(context);
		senddata.rank			= ErrorRank.Native.value();
		
		return senddata;
	}
	
	private static ErrorSendData CreateSendData(Throwable e,String tag,ErrorRank rank ,Context context)
	{
		ErrorSendData senddata = new ErrorSendData();
		
		String CallStack = CallStackCollector.GetCallStack(e);
		CallStackData data = CallStackCollector.ParseStackTrace(e, CallStack);
		
		PackageManager packagemanager = context.getPackageManager();
		PackageInfo packageinfo = null;
		try {
			packageinfo = packagemanager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		
		senddata.apikey 		= StateData.APIKEY;
		senddata.datetime 		= DateCollector.GetDateYYMMDDHHMMSS(context);
		senddata.device			= android.os.Build.MODEL;
		senddata.country		= DeviceCollector.GetNational(context);
		senddata.errorname		= data.ErrorName; 
		senddata.errorclassname	= data.ClassName;
		senddata.linenum		= data.Line;
		senddata.lastactivity	= data.ActivityName;
		senddata.callstack		= CallStack;
		senddata.appversion		= packageinfo.versionName;
		senddata.osversion		= android.os.Build.VERSION.RELEASE;
		senddata.gpson			= (DeviceCollector.GetGps(context)) ? 1 : 0;
		senddata.wifion			= (DeviceCollector.GetWiFiNetwork(context)) ? 1 : 0;
		senddata.mobileon		= (DeviceCollector.GetMobileNetwork(context)) ? 1 : 0;
		senddata.scrwidth		= DeviceCollector.GetWidthScreenSize(context);
		senddata.scrheight		= DeviceCollector.GetHeightScreenSize(context);
		senddata.batterylevel	= DeviceCollector.GetBatteryLevel(context);
		senddata.availsdcard	= DeviceCollector.BytetoMegaByte(DeviceCollector.getAvailableExternalMemorySize());
		senddata.rooted			= (DeviceCollector.CheckRoot()) ? 1 : 0;
		senddata.appmemtotal	= DeviceCollector.BytetoMegaByte(DeviceCollector.GetTotalMemory());
		senddata.appmemfree		= DeviceCollector.BytetoMegaByte(DeviceCollector.GetFreeMemory());
		senddata.appmemmax		= DeviceCollector.BytetoMegaByte(DeviceCollector.GetMaxMemory());
		senddata.kernelversion	= DeviceCollector.GetLinuxKernelVersion();
		senddata.xdpi			= DeviceCollector.GetXDPI(context);
		senddata.ydpi			= DeviceCollector.GetYDPI(context);
		senddata.scrorientation = DeviceCollector.GetOrientation(context);
		senddata.sysmemlow		= (DeviceCollector.GetSystemLowMemory()) ? 1 : 0;
		senddata.tag			= tag;
		senddata.rank			= rank.value();
		senddata.eventpaths		= EventPathManager.GetErrorEventPath();
		senddata.locale			= DeviceCollector.GetLocale(context);
		
		return senddata;
	}
}
