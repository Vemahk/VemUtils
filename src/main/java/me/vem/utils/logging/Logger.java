package me.vem.utils.logging;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Logger {

	private static int LogLevel = 0;
	
	public static void setLogLevel(int logLevel) {
		LogLevel = logLevel;
	}
	
	private enum Severity{
		INFO, WARNING, DEBUG, TRACE, ERROR, FATAL_ERROR;
		
		public String toString() {
			if(this == TRACE)
				return "DEBUG";
			
			return this.name();
		}
	}
	
	public static void addOut(PrintStream stream) { out.add(stream); }
	public static void remOut(PrintStream stream) { out.remove(stream); }
	public static void addErr(PrintStream stream) { err.add(stream); }
	public static void remErr(PrintStream stream) { err.remove(stream); }
	private static CCPrintStream out = new CCPrintStream(System.out);
	private static CCPrintStream err = new CCPrintStream(System.err);
	
	public static void log(Severity sev, Object obj, int logLevel) {
		if(logLevel > Logger.LogLevel)
			return;
		
		StringBuffer toPrint = new StringBuffer(String.format("[%s][%s]> %s%n", sev, timeFormat(), obj));
		
		if(sev == Severity.TRACE)
			toPrint.insert(0, getCaller());
		
		if(sev.compareTo(Severity.ERROR) >= 0) {
			err.print(toPrint);
		}else out.print(toPrint);
	}
	
	public static void log(Severity sev, Object obj) { log(sev, obj, 0); }
	public static void log(int i, Object obj) { log(Severity.values()[i], obj); }
	public static void log(int i, Object obj, int logLevel) { log(Severity.values()[i], obj, logLevel); }
	
	public static void info(Object out) { log(0, out); }
	public static void warning(Object out) { log(1, out); }
	public static void debug(Object out) { log(2, out); }
	public static void trace(Object out) { log(3, out); }
	public static void error(Object out) { log(4, out); }
	public static void fatalError(Object out) { log(5, out); }
	
	public static void info(Object out, int logLevel) { log(0, out, logLevel); }
	public static void warning(Object out, int logLevel) { log(1, out, logLevel); }
	public static void debug(Object out, int logLevel) { log(2, out, logLevel); }
	public static void trace(Object out, int logLevel) { log(3, out, logLevel); }
	public static void error(Object out, int logLevel) { log(4, out, logLevel); }
	public static void fatalError(Object out, int logLevel) { log(5, out, logLevel); }
	
	public static void logf(Severity sev, String f, Object... objs) { log(sev, String.format(f, objs)); }
	public static void logf(int i, String f, Object... objs) { logf(Severity.values()[i], f, objs); }
	public static void infof(String f, Object... objs) { logf(0, f, objs); }
	public static void warningf(String f, Object... objs) { logf(1, f, objs); }
	public static void debugf(String f, Object... objs) { logf(2, f, objs); }
	public static void tracef(String f, Object... objs) { logf(3, f, objs); }
	public static void errorf(String f, Object... objs) { logf(4, f, objs); }
	public static void fatalErrorf(String f, Object... objs) { logf(5, f, objs); }
	
	public static void logf(Severity sev, int logLevel, String f, Object... objs) { log(sev, String.format(f, objs), logLevel); }
	public static void logf(int i, int logLevel, String f, Object... objs) { logf(Severity.values()[i], logLevel, f, objs); }
	public static void infof(int logLevel, String f, Object... objs) { logf(0, logLevel, f, objs); }
	public static void warningf(int logLevel, String f, Object... objs) { logf(1, logLevel, f, objs); }
	public static void debugf(int logLevel, String f, Object... objs) { logf(2, logLevel, f, objs); }
	public static void tracef(int logLevel, String f, Object... objs) { logf(3, logLevel, f, objs); }
	public static void errorf(int logLevel, String f, Object... objs) { logf(4, logLevel, f, objs); }
	public static void fatalErrorf(int logLevel, String f, Object... objs) { logf(5, logLevel, f, objs); }
	
	private static String timeFormat() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	
	private static String getCaller() {
		int i = 1;
		StackTraceElement trace;
		do {
			trace = Thread.currentThread().getStackTrace()[i++];
		}while(trace.getClassName().equals(Logger.class.getName()));
		
		return String.format("(%s:%d)", trace.getFileName(), trace.getLineNumber());
	}
	
	private static class CCPrintStream{

		private LinkedList<PrintStream> cc;
		
		public CCPrintStream() {
			cc = new LinkedList<>();
		}
		
		public CCPrintStream(PrintStream... streams) {
			this();
			for(PrintStream ps : streams) add(ps);
		}
		
		public void add(PrintStream ps) {
			if(!cc.contains(ps))
				cc.add(ps);
		}
		
		public void remove(PrintStream ps) {
			cc.remove(ps);
		}
		
		public void print(char c) {
			for(PrintStream ps : cc)
				ps.print(c);
		}
		
		public void print(String s) {
			for(char c : s.toCharArray())
				print(c);
		}
		
		public void print(Object o) {
			print(o.toString());
		}
	}
}