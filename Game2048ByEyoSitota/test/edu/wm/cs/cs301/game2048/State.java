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
		int index = 0;
		// TODO Auto-generated method stub
		for (int i = 0; i < x; i++) {
			index += 4;
			}
		for (int j = 0; j < y; j++) {
			index += 1;
			}
		return tilebin[index];
//		return 0;
	}

	@Override
	public boolean addTile() {
		// TODO Auto-generated method stub
		Random rand = new Random();

		// Obtain a number between [0 - 49].
		int randomindex = rand.nextInt(16);
//		if(!isFull()) {
		while (tilebin[randomindex] != 0) {
			randomindex = rand.nextInt(16);
		}
		tilebin[randomindex] = new Random().nextBoolean() ? 2 : 4;
		return true;
//		}else {
//			return false;
//		}
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 16; i++) {
			if(tilebin[i] == 0) {
				return false;
			}
		}
		System.out.println("Finally");
		return true;
	}

	@Override
	public boolean canMerge() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 12; i++) {
			if(tilebin[i] == tilebin[i+1]|| tilebin[i] == tilebin[i+4]) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean reachedThreshold() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 16; i++) {
			if(tilebin[i] >= 2048){
				return true;
			}
		}
		return false;
	}

	@Override
	public void setValue(int x, int y, int value) {
		// TODO Auto-generated method stub
		int index = 0;
		// TODO Auto-generated method stub
		for (int i = 0; i < x; i++) {
			index += 4;
			}
		for (int j = 0; j < y; j++) {
			index += 1;
			}
		tilebin[index] = value;
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
		for(int j = 4; j <= 7; j++) {
			for(int i = j; i <= 15; i+=4) {
				if(tilebin[i] == tilebin[i - 4]) {
				score += tilebin[i]*2;
				tilebin[i-4] = tilebin[i] * 2;
				tilebin[i] = 0;
				}else if(tilebin[i - 4]==0){
					int currentindex = i;
					while(currentindex - 4 >=0 && tilebin[currentindex-4] == 0) {
						tilebin[currentindex-4] =  tilebin[currentindex];
						tilebin[currentindex] = 0;
						currentindex -= 4;
					}
					if(currentindex - 4>=0 && tilebin[currentindex]==tilebin[currentindex - 4]) {
						tilebin[currentindex] = 0;
						tilebin[currentindex - 4] *= 2;
						score += tilebin[currentindex - 4];
					}
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
		for(int j = 8; j <= 11; j++) {
			for(int i = j; i >= 0; i-=4) {
				if(tilebin[i] == tilebin[i + 4]) {
				score += tilebin[i]*2;
				tilebin[i+4] = tilebin[i] * 2;
				tilebin[i] = 0;
				}else if(tilebin[i + 4]==0){
					int currentindex = i;
					while(currentindex + 4 <16 && tilebin[currentindex+4] == 0) {
						tilebin[currentindex+4] =  tilebin[currentindex];
						tilebin[currentindex] = 0;
						currentindex += 4;
					}
					if(currentindex + 4<16 && tilebin[currentindex]==tilebin[currentindex + 4]) {
						tilebin[currentindex] = 0;
						tilebin[currentindex + 4] *= 2;
						score += tilebin[currentindex + 4];
					}
				}
			}
		}
		return score;
	}

	@Override
	public int down() {
		// TODO Auto-generated method stub
		int score = 0;
		// TODO Auto-generated method stub
		for(int j = 14; j >= 0; j-=4) {
			for(int i = j; i%4 != 3 && i >= 0; i--) {
				if(tilebin[i] == tilebin[i + 1]) {
				score += tilebin[i]*2;
				tilebin[i+1] = tilebin[i] * 2;
				tilebin[i] = 0;
				}else if(tilebin[i + 1]==0){
					int currentindex = i;
					while((currentindex + 1)%4!= 0 && currentindex + 1 < 16 &&tilebin[currentindex+1] == 0) {
						tilebin[currentindex+1] =  tilebin[currentindex];
						tilebin[currentindex] = 0;
						currentindex += 1;
					}
					if((currentindex + 1)%4!=0 && currentindex + 1 < 16 && tilebin[currentindex]==tilebin[currentindex + 1]) {
						tilebin[currentindex] = 0;
						tilebin[currentindex + 1] *= 2;
						score += tilebin[currentindex + 1];
					}
				}
			}
		}
		return score;
	}

	@Override
	public int up() {
		// TODO Auto-generated method stub
		int score = 0;
		// TODO Auto-generated method stub
		for(int j = 1; j <= 13; j+=4) {
			for(int i = j; i%4 != 0 && i >= 0; i++) {
				if(tilebin[i] == tilebin[i - 1]) {
				score += tilebin[i]*2;
				tilebin[i-1] = tilebin[i] * 2;
				tilebin[i] = 0;
				}else if(tilebin[i - 1]==0){
					int currentindex = i;
					while((currentindex - 1)%4!= 3 && currentindex - 1 >= 0 &&tilebin[currentindex-1] == 0) {
						tilebin[currentindex-1] =  tilebin[currentindex];
						tilebin[currentindex] = 0;
						currentindex -= 1;
					}
					if((currentindex - 1)%4!= 3 && currentindex - 1 >= 0 && tilebin[currentindex]==tilebin[currentindex - 1]) {
						tilebin[currentindex] = 0;
						tilebin[currentindex - 1] *= 2;
						score += tilebin[currentindex - 1];
					}
				}
			}
		}
		return score;
	}

}
