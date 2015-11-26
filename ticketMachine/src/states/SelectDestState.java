package states;

import java.util.Scanner;

import ticket_machine.Common;
import ticket_machine.Display;
import ticket_machine.Machine;

public class SelectDestState implements State{
	private Machine machine;
	public SelectDestState(Machine machine){
		this.machine = machine;
	}
	@Override
	public void PressWrong() {
		
	}
	@Override
	public void waitInput() {
		Display.arrivalStation();
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		Common common = new Common();
		int destNum = common.searchStation(input);
		if(destNum>=0){
			Machine.endStation = input;
			machine.setState(machine.getSelectTicketNumState());
			machine.getState().waitInput();
		}else{
			sc.close();
			waitInput();
		}
		
	}
	@Override
	public void waitInput(int x) {
		
	}

}
