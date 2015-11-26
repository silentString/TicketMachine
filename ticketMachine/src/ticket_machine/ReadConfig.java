package ticket_machine;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {

	public static boolean db_use = false;
	public static String[] stationNames = null;
	public static String logPath = null;
	public static String dbName = null;
	public static String tableName = null;
	
	public static void readConfig(){
		Properties prop = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream("src/config.properties"));
			prop.load(in);
			String dbUse = prop.getProperty("db_use");
			String stations = prop.getProperty("stations");
			logPath = prop.getProperty("log_path");
			if(dbUse.equals("Y"))
				db_use = true;
			stationNames = stations.split("\\|");
			dbName = prop.getProperty("db_name");
			tableName = prop.getProperty("table_name");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		ReadConfig.readConfig();
		System.out.println(db_use);
		for(int i=0; i<stationNames.length; i++){
			System.out.println(stationNames[i]);
		}
		System.out.println(logPath);
	}
}
