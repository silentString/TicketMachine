package ticket_machine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Display {

	public static String star = "**************************************************";
	
	public static void enterStationName(){
		System.out.println(star);
		System.out.println("Enter a station name.");
		System.out.println("Station Name:");
	}
	
	public static void noStation(){
		System.out.println(star);
		System.out.println("ERROR!! The station name doesn't exist.");
		System.out.println("Station Name:");
	}
	
	public static void mainMenu(String stationName, int balance){
		System.out.println(star);
		System.out.println(stationName+" Station | Subway Ticket | Balance:"+balance);
		System.out.println(star);
		System.out.println("1.Payment");
		System.out.println("2.Purchase");
		System.out.println("3.Day pass purchase(10 CNY)");
		if(ReadConfig.db_use)
			System.out.println("97.Statistics display");
		System.out.println("98.Refund");
		System.out.println("99.End");
		System.out.println(star);
		System.out.println("Choose Menu:");
	}
	
	public static void wrongNum(){
		System.out.println(star);
		System.out.println("Note:Wrong input");
		System.out.println("Choose Menu:");
	}
	
	public static void addRefund(){
		System.out.println(star);
		System.out.println("Note:Only 1,2,5 or 10 CNY coins acceptable.");
		System.out.println("Coin:");
	}
	
	public static void arrivalStation(){
		System.out.println(star);
		System.out.println("Arrival Station:");
	}
	
	public static void ticketSaled(String stationName, int price, int count){
		System.out.println(star);
		System.out.println("You have purchased " + count
				+ " ticket(s) to "+stationName+".("+price+"CNY)");
	}
	
	public static void noArrivalStation(){
		System.out.println(star);
		System.out.println("ERROR!! The station name doesn't exist");
		System.out.println("Arrival Station:");
	}
	
	public static void balanceNotEnough(){
		System.out.println(star);
		System.out.println("ERROR!! The balance is not enough.");
		System.out.println(star);
		System.out.println("Choose Menu:");
	}
	
	public static void dayPassSaled(int num, int price){
		System.out.println(star);
		System.out.println("You have purchased "+num+" Day travel pass(is).("+price+"CNY)");
	}
	
	public static void refund(int refund, int[] wons){
		System.out.println(star);
		System.out.println("Total refund amout:"+refund+"CNY");
		if(wons[3]>0)
			System.out.println("10 won:"+wons[3]);
		if(wons[2]>0)
			System.out.println("5 won:"+wons[2]);
		if(wons[1]>0)
			System.out.println("2 won:"+wons[1]);
		if(wons[0]>0)
			System.out.println("1 won:"+wons[0]);
	}
	
	public static void numOfTickets() {
		System.out.println(star);
		System.out.println("Number of tickets:");
	}
	
	
	public static void statMenu(){
		System.out.println(star);
		System.out.println("1.Display statistics for the recent 10 days");
		System.out.println("2.Display statistics for a specific date");
		System.out.println("3.Go back to the menu");
		System.out.println(star);
		System.out.println("Choose Menu:");
	}
	
	public static void tenDaysStatTitle(String station){
		System.out.println(star);
		System.out.println("Ticket Sales info. of "+station+" for the recent 10 days");
		System.out.println(star);
	}
	
	public static void dateOfStat(){
		System.out.println(star);
		System.out.println("Search Date (yyyy-mm-dd):");
	}
	
	public static void oneDayStatTitle(String station, Date date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(star);
		System.out.println("Ticket Sales info. of "+station+" for "+format.format(date));
		System.out.println(star);
	}
	
	public static void main(String[] args) {
	
	}

}
