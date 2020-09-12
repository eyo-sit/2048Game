package edu.wm.cs.cs301.game2048;
import java.util.Random;
import java.util.function.BooleanSupplier;

public class State implements GameState {
	//Creates one dimensional array representing tiles
	int tilebin[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	public State() {
		// TODO Auto-generated constructor stub
		
		
	}
	//Constructor to inherit previous tile bin
	public State(State currentState) {
		// TODO Auto-generated constructor stub
		tilebin = currentState.tilebin;
	}

	@Override
	public int getValue(int x, int y) {
		int index = 0;
		// Maps x and y coordinates to array indices
		for (int i = 0; i < x; i++) {
			index += 4;
			}
		for (int j = 0; j < y; j++) {
			index += 1;
			}
		return tilebin[index];				// return value at index
//		return 0;
	}

	@Override
	public boolean addTile() {
		Random rand = new Random();
		// Obtain a number between [0 - 15].
		int randomindex = rand.nextInt(16);
		if(!isFull()) {								//Check if there is a empty tile to insert a value
		while (tilebin[randomindex] != 0) {
			randomindex = rand.nextInt(16);			//Locates a random empty tile
		}
		tilebin[randomindex] = new Random().nextBoolean() ? 2 : 4;		// Places 2 or 4 in index
		return true;													//Return True if insertion was successful
		}else {
			return false;												//Return False if insertion was not successful
		}
	}

	@Override
	public boolean isFull() {
		// Determines if there is a tile that is empty(has a value of 0)
		for(int i = 0; i < 16; i++) {
			if(tilebin[i] == 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean canMerge() {
		// Checks if tiles have similar values to tiles under or to the left
		for(int i = 0; i < 12; i++) {
			if(tilebin[i] == tilebin[i+1]|| tilebin[i] == tilebin[i+4]) {
				return true;												//Return true if values match
			}
		}
		return false;														//Return false if no tiles have adjacent tiles that match 
	}
	
	@Override
	public boolean reachedThreshold() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 16; i++) {										//Checks if any tile has a value over 2048
			if(tilebin[i] >= 2048){
				return true;												//Returns true if threshold is surpassed or met
			}
		}
		return false;														//Returns false if threshold has not been met
	}

	@Override
	public void setValue(int x, int y, int value) {
		// Assigns value at index that corresponds with coordinates
		int index = 0;
		// Maps x and y coordinates to index
		for (int i = 0; i < x; i++) {
			index += 4;
			}
		for (int j = 0; j < y; j++) {
			index += 1;
			}
		tilebin[index] = value;										//Assign value
	}

	@Override
	public void setEmptyBoard() {
		// Clears board
		for(int i= 0; i < tilebin.length; i++) {
			tilebin[i] = 0;
		}
	}

	@Override
	public int left() {
		int score = 0;
		// Method merges tiles with tiles to the left that has the same values
		//If tiles to the left have a value of zero the values are swapped
		//For-loop structure traverses each row starting from 4
		for(int j = 4; j <= 7; j++) {
			for(int i = j; i <= 15; i+=4) {
				if(tilebin[i] == tilebin[i - 4]) {								//If the values match they are combined to the left
				score += tilebin[i]*2;											//Increment score by the values of both tiles
				tilebin[i-4] = tilebin[i] * 2;	
				tilebin[i] = 0;
				}else if(tilebin[i - 4]==0){									//While-loop that moves tiles if the tile to the left is zero
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
		return score;														//Return Score
	}

	@Override
	public int right() {
		// Method merges tiles with tiles to the right that has the same values
		//If tiles to the right have a value of zero the values are swapped
		int score = 0;
		//For-loop structure traverses each row starting from 8
		for(int j = 8; j <= 11; j++) {			
			for(int i = j; i >= 0; i-=4) {
				if(tilebin[i] == tilebin[i + 4]) {					//If the values match they are combined to the right
				score += tilebin[i]*2;							 	//Increment score by the values of both tiles
				tilebin[i+4] = tilebin[i] * 2;
				tilebin[i] = 0;
				}else if(tilebin[i + 4]==0){						//While-loop that moves tiles if the tile to the right is zero
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
		return score;												//Return Score
	}

	@Override
	public int down() {
		// Method merges tiles with tiles below that has the same values
		// If tiles below have a value of zero the values are swapped
		int score = 0;
		//For-loop structure traverses each column starting from 14
		for(int j = 14; j >= 0; j-=4) {
			for(int i = j; i%4 != 3 && i >= 0; i--) {
				if(tilebin[i] == tilebin[i + 1]) {							//If the values match they are combined down
				score += tilebin[i]*2;										//Increment score by the values of both tiles
				tilebin[i+1] = tilebin[i] * 2;
				tilebin[i] = 0;
				}else if(tilebin[i + 1]==0){								//While-loop that moves tiles if the tile below is zero
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
		return score;														//Return Score
	}

	@Override
	public int up() {
		// Method merges tiles with tiles above that has the same values
		// If tiles above have a value of zero the values are swapped
		int score = 0;
		//For-loop structure traverses each column starting from 1
		for(int j = 1; j <= 13; j+=4) {
			for(int i = j; i%4 != 0 && i >= 0; i++) {
				if(tilebin[i] == tilebin[i - 1]) {							//If the values match they are combined up
				score += tilebin[i]*2;										//Increment score by the values of both tiles
				tilebin[i-1] = tilebin[i] * 2;
				tilebin[i] = 0;
				}else if(tilebin[i - 1]==0){								//While-loop that moves tiles if the tile above is zero							
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
		return score;														//Return Score
	}
	public boolean equals(State o) {
		for(int i = 0; i < tilebin.length; i++) {							//Determine if the tile bin's of the two objects were equal
			if(this.tilebin[i] != o.tilebin[i]) {
			return false;
			}
		}
		return true;
	}
}
