package states;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import ticket_machine.Common;
import ticket_machine.Display;
import ticket_machine.Machine;
import ticket_machine.ReadConfig;

public class StatisticsMenuState implements State{
	
	private Machine machine;
	private Common common = new Common();
	
	public StatisticsMenuState(Machine machine){
		this.machine = machine;
	}
	@Override
	public void PressWrong() {
		
	}
	@Override
	public void waitInput() {
		Display.statMenu();
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		switch (input) {
		case "1":
			Display.tenDaysStatTitle(Machine.startStation);
			tenDayStatistics(sc);
			break;

		case "2":	
			oneDayStatistics(sc);
			break;
		case "3":
			machine.setState(machine.getMainMenuState());
			machine.getState().waitInput();
			break;
		default:
			Display.wrongNum();
			waitInput();
			break;
		}
	}
	
	private void oneDayStatistics(Scanner sc) {
		Display.dateOfStat();
		String theDay = sc.next();
		if(!theDay.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")){
			System.out.println("please input in right format!");
			oneDayStatistics(sc);
		}
		theDay += " 00:00:00";	
		Date day = common.string2Date(theDay);
		Date next = common.dayBefore(day, -1);
		String nextDay = common.date2String(next).split(" ")[0];
		String curStation = Machine.startStation;
		int stations = ReadConfig.stationNames.length;
		int[] ticketNum = new int[stations+1];
		
		Display.oneDayStatTitle(curStation, day);
		
		Statement stmt = connectDB();
		String sql = "SELECT dest_name, SUM(ticket_count) AS allTicket FROM "+ReadConfig.tableName
					+" WHERE date_time >='"+theDay+"' AND date_time<'"+nextDay+"' AND station_name='"+curStation
					+"' GROUP BY dest_name";
		int sum = 0;
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String endStation = rs.getString("dest_name");
				int tickets = rs.getInt("allTicket");
				sum += tickets;
				if(endStation.equals("Day pass purchase")){
					ticketNum[stations] = tickets;
					
				}else{
					ticketNum[common.searchStation(endStation)] = tickets;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(0==sum){
			System.out.println("0 tickets saled");
		}else{
			for(int j=0; j<stations; j++){
				if(0==ticketNum[j])
					continue;
				System.out.println("\t"+ReadConfig.stationNames[j] + " "+ ticketNum[j] +" tickets");
			}
			if(ticketNum[stations]>0)
				System.out.println("\t"+"Day pass "+ticketNum[stations]+" tickets");
		}
		System.out.println("<press any key to go back to the menu>");
		sc.next();
		waitInput();
	}
	
	private void tenDayStatistics(Scanner sc) {
		Date today = new Date();
		int stations = ReadConfig.stationNames.length;	
		String curStation = Machine.startStation;
		
		for(int i=0; i<10; i++){
			int[] ticketNum = new int[stations+1];
			Date day = common.dayBefore(today, i);
			Date tomorow = common.dayBefore(day, -1);
			String dayStr = common.date2String(day).split(" ")[0];
			String tomStr = common.date2String(tomorow).split(" ")[0];
			Statement stmt = connectDB();
			String sql = "SELECT dest_name, SUM(ticket_count) AS allTicket FROM "+ReadConfig.tableName
					+" WHERE date_time >='"+dayStr+"' AND date_time<'"+tomStr+"' AND station_name='"+curStation
					+"' GROUP BY dest_name";
			int sum = 0;
			try {
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					String endStation = rs.getString("dest_name");
					int tickets = rs.getInt("allTicket");
					sum += tickets;
					if(endStation.equals("Day pass purchase")){
						ticketNum[stations] = tickets;
						
					}else{
						ticketNum[common.searchStation(endStation)] = tickets;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(0==sum)
				continue;
			System.out.println(dayStr);
			for(int j=0; j<stations; j++){
				if(0==ticketNum[j])
					continue;
				System.out.println("\t"+ReadConfig.stationNames[j] + " "+ ticketNum[j] +" tickets");
			}
			if(ticketNum[stations]>0)
				System.out.println("\t"+"Day pass "+ticketNum[stations]+" tickets");
			
		}
		System.out.println("<press any key to go back to the menu>");
		sc.next();
		waitInput();
	}
	
	private Statement connectDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ ReadConfig.dbName + "?user=root&password=1qaz2wsx");
			return conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void waitInput(int x) {
		
	}

}
