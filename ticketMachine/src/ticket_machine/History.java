package ticket_machine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class History {

	private Date date;
	private String bStation;
	private String eStation;
	private int ticketsNum;
	private int price;
	
	public History(){}

	public History(Date date, String bStation, String eStation, int ticketsNum, int price) {
		this.date = date;
		this.bStation = bStation;
		this.eStation = eStation;
		this.ticketsNum = ticketsNum;
		this.price = price;
	}
	
	public String toString(){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(this.date)+","+this.bStation+","+this.eStation+","
				+ this.ticketsNum+" tickets,"+this.price+" CNY";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getbStation() {
		return bStation;
	}

	public void setbStation(String bStation) {
		this.bStation = bStation;
	}

	public String geteStation() {
		return eStation;
	}

	public void seteStation(String eStation) {
		this.eStation = eStation;
	}

	public int getTicketsNum() {
		return ticketsNum;
	}

	public void setTicketsNum(int ticketsNum) {
		this.ticketsNum = ticketsNum;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}

