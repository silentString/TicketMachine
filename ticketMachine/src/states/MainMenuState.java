package states;

import java.util.Scanner;

import ticket_machine.Common;
import ticket_machine.Display;
import ticket_machine.Machine;
import ticket_machine.ReadConfig;

public class MainMenuState implements State{
	
	private Machine machine;
	private Common common = new Common();
	public MainMenuState(Machine machine) {
		this.machine = machine;
	}
	
	public void pressRecharge(){
		
	}

	@Override
	public void PressWrong() {
		Display.wrongNum();
	}

	@Override
	public void waitInput() {
		Display.mainMenu(Machine.startStation, Machine.refund);
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		switch (input) {
		case "1":
			machine.setState(machine.getRechargeState());
			machine.getState().waitInput();
			break;
		case "2":
			machine.setState(machine.getSelectDestState());
			machine.getState().waitInput();
			break;
		case "3":
			machine.setState(machine.getSelectTicketNumState());
			machine.getState().waitInput(10);
			break;
		case "97":
			if(!ReadConfig.db_use){
				Display.wrongNum();
				waitInput();
			}else{
				machine.setState(machine.getStatisticsMenuState());
				machine.getState().waitInput();
			}
			break;
		case "98":
			Display.refund(Machine.refund, common.refund(Machine.refund));
			Machine.refund = 0;
			machine.setState(machine.getMainMenuState());
			waitInput();
			break;
		case "99":
			machine.exit();
			break;
		default:
			Display.wrongNum();
			waitInput();
			break;
		}
	}

	@Override
	public void waitInput(int x) {
		
	}
	
}
