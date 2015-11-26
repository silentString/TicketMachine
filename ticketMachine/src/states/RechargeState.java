package states;

import java.util.Scanner;

import ticket_machine.Display;
import ticket_machine.Machine;

public class RechargeState implements State{
	
	private Machine machine;
	public RechargeState(Machine machine){
		this.machine = machine;
	}
	@Override
	public void PressWrong() {
		
	}
	@Override
	public void waitInput() {
		Display.addRefund();
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		switch (input) {
		case "1":
		case "2":
		case "5":
		case "10":
			Machine.refund += Integer.parseInt(input);
			machine.setState(machine.getMainMenuState());
			machine.getState().waitInput();
			break;

		default:
			waitInput();
			break;
		}
	}
	@Override
	public void waitInput(int x) {
		
	}
	
}
