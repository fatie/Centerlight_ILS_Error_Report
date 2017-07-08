package org.centerlight.ILS.fileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ILSExtractFileReader extends ILSFileReader  {
	
	private static final Logger logger = LogManager.getLogger(ILSExtractFileReader.class);
	
	public ILSExtractFileReader(int[] fixedLength, String iLSFolderPath) {
		this.setFixedLength(fixedLength);
		this.setILSFolderPath(iLSFolderPath);
		
	}
	
	@Override
	public List<String[]> readFile() {
		logger.info("Loading ADDLOG file\n");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(this.chooseFile(this.getILSFolderPath())));
			List<String[]> logFile = new ArrayList<String[]>();
			String line;
			int counter = 0;
			while ((line = br.readLine()) != null){
				int start = 0;
				String[] lineLog = new String[this.getFixedLength().length];
				for (int i = 0; i < this.getFixedLength().length; i++ ){
					int end = start + this.getFixedLength()[i];
					lineLog[i] = line.substring(start,end);
					start = end;
				}
				logFile.add(lineLog);
				counter++;
			}

			logger.info(counter+" lines read.\n");
			this.setFile(logFile);
			return this.getFile();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			FileUtil.close(br);
		}
	}	
}
	
		
		
		