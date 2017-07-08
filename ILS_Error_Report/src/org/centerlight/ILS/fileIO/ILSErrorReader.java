package org.centerlight.ILS.fileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ILSErrorReader extends ILSFileReader {
	
private static final Logger logger = LogManager.getLogger(ILSErrorReader.class);
	
	public ILSErrorReader(int[] fixedLength, String iLSFolderPath) {
		this.setFixedLength(fixedLength);
		this.setILSFolderPath(iLSFolderPath);
		
	}
	
	private String getSubstring(int start, int end, String string){
		String resultString = string.substring(start, end).trim();
		int position = resultString.indexOf('\\');
		if(position > 0){
			resultString = resultString.substring(0, position);
		}
		return resultString;
	}

	@Override
	public List<String[]> readFile() {
		logger.info("Loading LOGError file\n");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(this.chooseFile(this.getILSFolderPath())));
			List<String[]> logErrorFile = new ArrayList<String[]>();
			String line;
			int counter = 0;
			while (((line = br.readLine())) != null){
				if (Character.isDigit(line.charAt(0))){
					int lineLength = line.length();
					int start = 0;
					String[] lineLogError = new String[this.getFixedLength().length];
					for (int i = 0; i < this.getFixedLength().length; i++ ){
						int end = start + this.getFixedLength()[i];
						if(end <= lineLength){
							lineLogError[i] = this.getSubstring(start, end, line);
							start = end;
						} else {
							lineLogError[i] = this.getSubstring(start, lineLength, line);
							start = lineLength;
						}
					}
					logErrorFile.add(lineLogError);
					counter++;
				}	
			}

			logger.info(counter+" lines read.\n");
			this.setFile(logErrorFile);
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
