package com.urqa.exceptionhandler;

import com.urqa.Collector.ErrorReport;
import com.urqa.Collector.ErrorReportFactory;
import com.urqa.common.SendErrorProcess;
import com.urqa.common.StateData;
import com.urqa.rank.ErrorRank;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

	Thread.UncaughtExceptionHandler m_DefaultExceptionHandler;

	public ExceptionHandler() {
		m_DefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		ErrorReport report = ErrorReportFactory.CreateErrorReport(ex, "",
				ErrorRank.Unhandle, StateData.AppContext);

		SendErrorProcess errprocess = new SendErrorProcess(report,
				"client/send/exception");
		errprocess.start();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

}
