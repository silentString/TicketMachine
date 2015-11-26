package states;

public interface State {

	public void PressWrong();
	
	public void waitInput();
	
	public void waitInput(int x );
}

