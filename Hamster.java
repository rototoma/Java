
public class Hamster implements Runnable{
	private int Hunger;
	
	
	public Hamster(int hunger) {
		setHunger(hunger);
	}

	public int getHunger() {
		return Hunger;
	}


	public void setHunger(int hunger) {
		this.Hunger = hunger;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.Hunger--;
	}

}
