package com.github.zzwwws.rxzhihudaily.common.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

final class LogImpl {
	private static final String FILE_FORMAT = "yyyyMMdd";
	private static final String MESSAGE_FORMAT = "MM-dd HH:mm:ss.ms";
	private static final String SUFFIX = ".log";
	private static final String SEPARATOR = ".";

    private static String logPath;
	private static int level = Log.DEBUG;

	private static final ThreadLocal<DateFormat> messageFormat = new ThreadLocal<DateFormat>();
	private static final ThreadLocal<DateFormat> fileFormat = new ThreadLocal<DateFormat>();

    private static final Executor logger = Executors.newSingleThreadExecutor();

    private static final DateFormat messageFormat() {
		DateFormat format = messageFormat.get();
		if (format == null) {
			format = new SimpleDateFormat(MESSAGE_FORMAT, Locale.getDefault());
			messageFormat.set(format);
		}
		
		return format;
	}

	private static final DateFormat fileFormat() {
		DateFormat format = fileFormat.get();
		if (format == null) {
			format = new SimpleDateFormat(FILE_FORMAT, Locale.getDefault());
			fileFormat.set(format);
		}
		
		return format;
	}

    public static void init(String logPath, int level) {
        LogImpl.logPath = logPath;
        LogImpl.level = level;
    }

	public static void i(String tag, String msg) {
		i(tag, msg, null);
	}

	public static void i(String tag, String msg, Throwable tr) {
        o(Log.INFO, tag, msg, tr);
	}

	public static void v(String tag, String msg) {
		v(tag, msg, null);
	}

	public static void v(String tag, String msg, Throwable tr) {
        o(Log.VERBOSE, tag, msg, tr);
	}

	public static void e(String tag, String msg) {
		e(tag, msg, null);
	}

	public static void e(String tag, String msg, Throwable tr) {
        o(Log.ERROR, tag, msg, tr);
	}

	public static void d(String tag, String msg) {
		d(tag, msg, null);
	}

	public static void d(String tag, String msg, Throwable tr) {
        o(Log.DEBUG, tag, msg, tr);
	}

	public static void w(String tag, String msg) {
		w(tag, msg, null);
	}

	public static void w(String tag, String msg, Throwable tr) {
        o(Log.WARN, tag, msg, tr);
	}
	
	public static void o(int priority, String tag, String msg) { 
		o(priority, tag, msg, null);
	}
	
	public static void o(final int priority, final String tag, final String msg, final Throwable tr) {
        final String time = messageFormat().format(new Date());
        final long threadId = Thread.currentThread().getId();
        logger.execute(new Runnable() {
            @Override
            public void run() {
                Log.println(priority, tag, threadId + "/" + msg + '\n' + Log.getStackTraceString(tr));

                if (level <= priority) {
                    outMessage(tag, time, msg, tr);
                }
            }
        });
	}
	
	public static void setLevel(int level) {
		LogImpl.level = level;
	}

	private static void outMessage(String tag, String time, String msg, Throwable tr) {
		outMessage("", tag, time, msg, tr);
	}

	private static void outMessage(String cat, String tag, String time, String msg, Throwable tr) {
		if (TextUtils.isEmpty(logPath)) {
			return;
		}

		outputToFile(formatMessage(tag, time, msg, tr), getLogFilePath(cat));
	}
	
	private static String formatMessage(String tag, String time, String msg, Throwable tr) {
		StringBuilder sb = new StringBuilder();
		
		// time
		sb.append(time);
		sb.append(": ");
		
		// tag
		sb.append(tag);
		sb.append(": ");

		// message
		sb.append(msg);
		sb.append("\n");
		
		// Throwable
		if (tr != null) {
			sb.append(Log.getStackTraceString(tr));
			sb.append("\n");
		}
		
		return sb.toString();
	}

	private static boolean outputToFile(String message, String path) {
		if (TextUtils.isEmpty(message)) {
			return false;
		}
		
		if (TextUtils.isEmpty(path)) {
			return false;
		}
		
		boolean written = false;
		try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(path, true));
			fw.write(message);
			fw.flush();
			fw.close();
			
			written = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return written;
	}
	
	/*package*/ static String getLogFileName(String cat) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(fileFormat().format(new Date()));
		
		if (!TextUtils.isEmpty(cat)) {
			sb.append(SEPARATOR);
			sb.append(cat);
		}
		
		sb.append(SUFFIX);
		
		return sb.toString();
	}

    private static String getLogFilePath(String cat) {
        File dir = new File(logPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return logPath + "/" + getLogFileName(cat);
    }
}