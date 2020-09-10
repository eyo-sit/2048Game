package edu.wm.cs.cs301.game2048;
import java.util.Random;
import java.util.function.BooleanSupplier;

public class State implements GameState {
	int tilebin[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public State() {
		// TODO Auto-generated constructor stub
		
		
	}

	public State(State currentState) {
		// TODO Auto-generated constructor stub
		tilebin = currentState.tilebin;
	}

	@Override
	public int getValue(int x, int y) {
		System.out.println("x is " + x);
		System.out.println("y is " + y);
		int index = 0;
		// TODO Auto-generated method stub
		for (int i = 0; i < x; i++) {
			index += 4;
			}
		for (int j = 0; j < y; j++) {
			index += 1;
			}
		System.out.println("x is " + x + " y is " + y + " index is " + index);
		return tilebin[index];
//		return 0;
	}

	@Override
	public boolean addTile() {
		// TODO Auto-generated method stub
		Random rand = new Random();

		// Obtain a number between [0 - 49].
		int randomindex = rand.nextInt(16);
		while (tilebin[randomindex] != 0) {
			randomindex = rand.nextInt(16);
		}
		tilebin[randomindex] = new Random().nextBoolean() ? 2 : 4;
		System.out.println("Random index is " + randomindex);
		return true;
//		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 16; i++) {
			if(tilebin[i] == 0) {
				return false;
			}
		}
//		System.out.println("Finally");
		return true;
	}

	@Override
	public boolean canMerge() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 12; i++) {
			if(tilebin[i] != 3 || tilebin[i] != 7 || tilebin[i] != 11) {	
				if((tilebin[i+1] == tilebin[i])||(tilebin[i+4] == tilebin[i])) {
					return true;
				}
			}else {
				if((tilebin[i+4] == tilebin[i])) {
					return true;
				}
			}
		}
		for(int i = 12; i < 15; i++) {
			if(tilebin[i+1] == tilebin[i]) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean reachedThreshold() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValue(int x, int y, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEmptyBoard() {
		// TODO Auto-generated method stub
		for(int i= 0; i < tilebin.length; i++) {
			tilebin[i] = 0;
		}
	}

	@Override
	public int left() {
		int score = 0;
		// TODO Auto-generated method stub
		for(int i = 4; i < 16; i++) {
			if(tilebin[i] == tilebin[i - 4]) {
			score += tilebin[i]*2;
			tilebin[i-4] = tilebin[i] * 2;
			tilebin[i] = 0;
			}else {
				int currentindex = i;
				while((currentindex - 4) >= 0 && tilebin[currentindex-4] == 0) {
					tilebin[currentindex-4] =  tilebin[currentindex];
					tilebin[currentindex] = 0;
					currentindex -= 4;
				}
				if((currentindex - 4) >= 0 &&tilebin[currentindex] == tilebin[currentindex - 4]) {
					score += tilebin[currentindex]*2;
					tilebin[currentindex-4] = tilebin[currentindex] * 2;
					tilebin[currentindex] = 0;
					System.out.println("yes");
				}
			}
		}
		return score;
	}

	@Override
	public int right() {
		// TODO Auto-generated method stub
		int score = 0;
		// TODO Auto-generated method stub
		for(int i = 0; i < 12; i++) {
			if(tilebin[i] == tilebin[i + 4]) {
			score += tilebin[i]*2;
			tilebin[i+4] = tilebin[i] * 2;
			tilebin[i] = 0;
			}else {
				int currentindex = i;
				while((currentindex + 4) <= 15 && tilebin[currentindex+4] == 0) {
					tilebin[currentindex+4] =  tilebin[currentindex];
					tilebin[currentindex] = 0;
					currentindex += 4;
				}
			}
		}
		return score;
	}

	@Override
	public int down() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int up() {
		// TODO Auto-generated method stub
		return 0;
	}

}
