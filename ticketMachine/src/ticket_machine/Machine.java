package ticket_machine;

import java.util.Scanner;

import states.MainMenuState;
import states.RechargeState;
import states.SelectDestState;
import states.SelectTicketNumState;
import states.State;
import states.StatisticsMenuState;

public class Machine {

	public static int refund = 0;
	public static String startStation  = null;
	public static String endStation = null;
	Log log = new Log();
	State mainMenuState;
	State rechargeState;
	State selectDestState;
	State selectTicketNumState;
	State statisticsMenuState;
	State state = mainMenuState;
	Common common;
	boolean exit = false;
	Scanner sc = null;
	
	public Machine(){
		ReadConfig.readConfig();
		if(ReadConfig.db_use){
			log.updateDB();
		}
		common = new Common();
		mainMenuState = new MainMenuState(this);
		rechargeState = new RechargeState(this);
		selectDestState = new SelectDestState(this);
		selectTicketNumState = new SelectTicketNumState(this);
		statisticsMenuState = new StatisticsMenuState(this);
		
	}
	
	public void exit(){
		sc.close();
		exit = true;
	}
	
	public void start(){
		sc = new Scanner(System.in);
		int beginNumber = 0;
		while(true){
			Display.enterStationName();
			startStation = sc.next();
			beginNumber = common.searchStation(startStation);
			if(beginNumber>=0){
				state = mainMenuState;
				break;
			}else{
				Display.noStation();
			}
		}
		state.waitInput();
	}
	
	public void setState(State s){
		this.state = s;
	}
	
	public State getState(){
		return state;
	}
	
	public State getMainMenuState() {
		return mainMenuState;
	}

	public State getRechargeState() {
		return rechargeState;
	}

	public State getSelectDestState() {
		return selectDestState;
	}

	public State getSelectTicketNumState() {
		return selectTicketNumState;
	}

	public State getStatisticsMenuState() {
		return statisticsMenuState;
	}

	public static void main(String[] args) {
		Machine machine = new Machine();
		machine.start();
		while(!machine.exit){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return;
	}

}
