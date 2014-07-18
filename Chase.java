import java.util.*;

public class Chase {

	private final static double MAX = 100000;
	private final static int NUM_PLAYERS = 100;
	public static void main(String[] args) {
		
		Chase c = new Chase();
		double sum = 0;

		for(int i=0;i<MAX;i++){
			sum += c.game();
			System.out.println("Sum " + i + " = " + sum);
		}

		System.out.println("Expected value is: " + sum/MAX);
	}

	public double game(){

		int die1 = 0;
		int die2 = NUM_PLAYERS/2;
		boolean gameOver = false;
		double count = 0.0;

		while(!gameOver){
			int dieVal1 = roll();
			int dieVal2 = roll();
			count += 1;;

			switch(dieVal1){
				case 1:
				die1 ++;
				die1 = (die1 + NUM_PLAYERS) % NUM_PLAYERS;
				break;

				case 6:
				die1 --;
				die1 = (die1 + NUM_PLAYERS) % NUM_PLAYERS;
				break;

				default:
				break;
			}

			switch(dieVal2){
				case 1:
				die2 ++;
				die2 = (die2 + NUM_PLAYERS) % NUM_PLAYERS;
				break;

				case 6:
				die2 ++;
				die2 = (die2 + NUM_PLAYERS) % NUM_PLAYERS;
				break;

				default:
				break;
			}

			// System.out.println("Game on: " + count);

			if(die1 == die2){
				gameOver = true;
			}
		}
		return count;
	}

	public int roll(){
		return (new Random().nextInt(6) +1);
	}
}
