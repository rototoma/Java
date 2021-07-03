
public class Coin {
	private int CurrentCoin;
	private int Add;
	private int Pay;
	
	public Coin(int money) {
		CurrentCoin = money;
		Add = 0;
		Pay = 0;
	}

	public int getCurrentCoin() {
		return CurrentCoin;
	}

	public void setCurrentCoin(int currentCoin) {
		
		CurrentCoin = currentCoin;
		
	}

	public int getAdd() {
		return Add;
	}

	public void setAdd(int up1) {
		Add = up1;
		setCurrentCoin(CurrentCoin+Add);
	}

	public int getPay() {
		return Pay;
	}

	public void setPay(int pay) {
		Pay = pay;
		setCurrentCoin(CurrentCoin-Pay);
	}

	
	
}
