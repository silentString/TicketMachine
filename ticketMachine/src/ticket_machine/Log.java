package ticket_machine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Log {

	private String fileName = "";
	
	public void writeLog(History history){
		String lineLog = history.toString();
		checkFile();
		writeFile(lineLog);
		if(ReadConfig.db_use){
			Statement stmt = connectDB();
			writeDB(history, stmt);
		}
	}
	
	public void updateDB(){
		if(!checkFile())
			return;
		Statement state = connectDB();
		if(null == state)
			return;
		String sql = "SELECT COUNT(*) AS ct FROM sale_history";
		int lines = 0;
		try {
			ResultSet rs = state.executeQuery(sql);
			if(rs.next())
				lines = rs.getInt("ct");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			int curLine = 0;
			while((str=br.readLine()) != null){
				++curLine;
				if(curLine<=lines){
					continue;
				}
				String[] tmp = str.split(",");
				String dateTime = tmp[0];
				String station = tmp[1];
				String dest = tmp[2];
				int ticketNum = Integer.parseInt(tmp[3].split(" ")[0]);
				int price = Integer.parseInt(tmp[4].split(" ")[0]);
				writeDB(dateTime, station, dest, ticketNum, price, state);
			}
			br.close();
			reader.close();
			state.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Statement connectDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306"
					+ "/"+ReadConfig.dbName+"?user="+ReadConfig.dbUserName+"&password="+ReadConfig.dbPassword);
			return conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	private boolean checkFile(){
		String[] tmp = ReadConfig.logPath.split("\\\\");
		String logPath = "";
		for(int i=0; i<tmp.length; i++){
			logPath += tmp[i]+File.separator;
		}
		File filePath = new File(logPath);
		if(!filePath.exists() || !filePath.isDirectory()){
			filePath.mkdir();
		}
		fileName = logPath+"ticket_sales_log.txt";
		File file = new File(fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
				return false;
			} catch (IOException e) {
				System.out.println("create file failed");
				e.printStackTrace();
			}
		}
		return true;
	}

	private void writeDB(History history, Statement stmt) {
		String time = history.toString().split(",")[0];
		writeDB(time, history.getbStation(), history.geteStation(), history.getTicketsNum(), 
				history.getPrice(), stmt);		
	}
	
	private void writeDB(String dateTime, String station, String dest, int num, int price, Statement stmt){
		String sql = "INSERT INTO sale_history(date_time, station_name, dest_name, "
				+ "ticket_count, price)VALUES('"+dateTime+"','"+station+
				"','"+dest+"',"+num+","+price;
		if(null == stmt)
			return;
		try {
			int result = stmt.executeUpdate(sql);
			if(1!=result){
				System.out.println("insert to DB failed");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	private void writeFile(String content) {
		
		try {
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		
	}
}
