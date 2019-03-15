package commonUtil;

import org.apache.log4j.Logger;

public class LogManager {
	
	public static void debugMessage(String message){
		Logger logger = Logger.getLogger(LogManager. class );
		logger.debug(message);
	}
	public static void errorMessage(String message){
		Logger logger = Logger.getLogger(LogManager. class );
		logger.error(message);
	}
	public static void infoMessage(String message){
		Logger logger = Logger.getLogger(LogManager. class );
		logger.info(message);
	}
	public static void getError(String message,Throwable e){
		Logger logger = Logger.getLogger(LogManager. class );
		logger.error(message, e);
	}
	
}
