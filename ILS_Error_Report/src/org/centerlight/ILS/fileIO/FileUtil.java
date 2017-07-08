package org.centerlight.ILS.fileIO;

import java.io.IOException;
import java.io.Reader;

public class FileUtil {

	public static boolean close(Reader reader){
		if(reader != null){
			try {
				reader.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		else return false;
	}
}
