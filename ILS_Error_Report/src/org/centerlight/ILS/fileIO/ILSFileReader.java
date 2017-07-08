package org.centerlight.ILS.fileIO;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ILSFileReader {
	private static final Logger logger = LogManager.getLogger(ILSFileReader.class);
	private String iLSFolderPath ;
	private int[] fixedLength;
	private List<String[]> file;
	
	public List<String[]> getFile() {
		return file;
	}
	public void setFile(List<String[]> file) {
		this.file = file;
	}
	public ILSFileReader(){};
	public ILSFileReader(int[] fixedLength, String iLSFolderPath){
		this.setFixedLength(fixedLength);
		this.setILSFolderPath(iLSFolderPath);
	}
	public String getILSFolderPath() {
		return iLSFolderPath;
	}
	public void setILSFolderPath(String iLSFolderPath) {
		this.iLSFolderPath = iLSFolderPath;
	}
	public int[] getFixedLength() {
		return fixedLength;
	}
	public void setFixedLength(int[] fixedLength) {
		this.fixedLength = fixedLength;
	}
	protected File chooseFile(String iLSFolderPath){
		JFileChooser fileChooser = new JFileChooser(iLSFolderPath);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			return fileChooser.getSelectedFile();
		}
		else {
			logger.debug("No file selected\n");
			return null;
		}
	}
	abstract public List<String[]> readFile();
	
	public boolean printFile(){
		logger.debug("printing all data...\n");
		return this.print(this.getFile().size());
	}
	
	public boolean printTop10File(){
		logger.debug("printing top 10 lines of data\n");
		int size = this.getFile().size();
		size = Math.min(size,  10);
		return this.print(size);
	}
	
	private boolean print(int size){
		for (int i = 0; i< size; i++){
			for (int j = 0; j<this.getFixedLength().length; j++){
				logger.debug(this.getFile().get(i)[j]);
			}
			logger.debug("\n");
		}
		return true;
		
	}
}
