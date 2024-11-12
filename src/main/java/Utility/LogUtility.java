package Utility;

import org.apache.logging.log4j.LogManager;

public class LogUtility {
    public static String Log_Path = "test-outputs/Logs";


    public static void Trace(String msg) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).trace(msg);


    }

    public static void Info(String msg) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).info(msg);
    }

    public static void Warning(String msg) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).warn(msg);
    }

    public static void Error(String msg) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).error(msg);
    }

    public static void Fatal(String msg) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString()).fatal(msg);
    }

}
