package states;

import java.util.Date;
import java.util.Scanner;

import ticket_machine.Common;
import ticket_machine.Display;
import ticket_machine.History;
import ticket_machine.Log;
import ticket_machine.Machine;
import ticket_machine.ReadConfig;

public class SelectTicketNumState implements State{
	
	private Machine machine;
	public SelectTicketNumState(Machine machine){
		this.machine = machine;
	}
	@Override
	public void PressWrong() {
		
	}
	@Override
	public void waitInput() {
		Display.numOfTickets();
		Scanner sc = new Scanner(System.in);
		String num = sc.next();
		
		if(!num.matches("\\d+")){
			waitInput();
		}
		int ticketNum = Integer.parseInt(num);
		Common common = new Common();
		int ticketPrice = common.ticketPrice(common.searchStation(Machine.startStation), 
				common.searchStation(Machine.endStation), ReadConfig.stationNames.length);
		int allPrice = ticketNum*ticketPrice;
		if(Machine.refund<allPrice){
			Display.balanceNotEnough();
			
		}else{//ticket saled
			Log log = new Log();
			History his = new History(new Date(), Machine.startStation, 
					Machine.endStation, ticketNum, allPrice);
			log.writeLog(his);
			Display.ticketSaled(Machine.endStation, allPrice, ticketNum);
			Machine.refund -= allPrice;
		}
		machine.setState(machine.getMainMenuState());
		machine.getState().waitInput();
	}
	
	public void waitInput(int price){
		Display.numOfTickets();
		Scanner sc = new Scanner(System.in);
		String num = sc.next();
		if(!num.matches("\\d+")){
			waitInput(price);
		}
		int ticketNum = Integer.parseInt(num);
		int allPrice = ticketNum*price;
		if(Machine.refund<allPrice){
			Display.balanceNotEnough();
			
		}else{//ticket saled
			Log log = new Log();
			History his = new History(new Date(), "Day pass purchase", 
					"-", ticketNum, allPrice);
			log.writeLog(his);
			Display.dayPassSaled(ticketNum, allPrice);
			Machine.refund -= allPrice;
		}
		machine.setState(machine.getMainMenuState());
		machine.getState().waitInput();
	}

}

