package org.centerlight.ILS.errorReport;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.centerlight.ILS.db.ConnectionClose;
import org.centerlight.ILS.db.ConnectionFactory;
import org.centerlight.ILS.fileIO.ILSErrorReader;
import org.centerlight.ILS.fileIO.ILSExtractFileReader;
import org.centerlight.ILS.fileIO.ILSFileReader;

public class Caller {
	private static final Logger logger = LogManager.getLogger(Caller.class);
	
	private static final String iLSAuthFolderPath = "C:\\Users\\FGuo\\Documents\\work doucment\\Transition\\AerialExtraction\\deliverable\\";
	private static final int[] fixedLengthLOG = {4,3,3,7,11,2,6,2,14,12,12,12,7,7,7,7,7,7,7,7,7,7,7,7,8,1,1,1,1,1,8,2,2,8,8,1,2,3,3,3,1,1,1,1,8,2,2,2,2,2,2,8,60,10,1};	
	
	private static final String iLSErrorFolderPath = "C:\\Users\\FGuo\\Documents\\work doucment\\Transition\\AerialExtraction\\error_log\\";
	private static final int[] fixedLengthLOGError = {16,8, 12, 15, 10, 47, 10, 18};
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		
		logger.info("loading LOG data extract files.....");
		ILSFileReader logFileReader = new ILSExtractFileReader(fixedLengthLOG, iLSAuthFolderPath);
		logFileReader.readFile();
		logger.info("Loading finished");
//		logger.debug("printing LOG file read....");
//		logFileReader.printTop10File();
//		logger.debug("Printing finished");
		
		logger.info("starting insert LOG file to DB....");
		
		logger.info("DB update finished");
		
		
		logger.info("loading LOG error files.....");
		ILSFileReader logErrorReader = new ILSErrorReader(fixedLengthLOGError, iLSErrorFolderPath);
		logErrorReader.readFile();
		logger.info("Loading finished");
//		logger.info("printing LOG error file read....");
//		logErrorReader.printFile();
//		logger.info("Printing finished");
		
		
		
		
		if (conn != null){
		    (new ConnectionClose(conn)).closeConnection();
		}
	}

}
