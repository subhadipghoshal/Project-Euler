package Amnesia;

import java.util.Random;

public class SelectiveAmnesia {

	private final static int MAX = 10000000;
	public static void main(String[] args) {
		
		SelectiveAmnesia sa = new SelectiveAmnesia();
		int[] memoryL = new int[5];
		int[] memoryR = new int[5];
		int[] added = new int[11];
		int[] called = new int[11];
		
		int L = 0,R = 0;
		
		double sum = 0;
		boolean found = false;
		
		for(int i=0;i<MAX;i++){
			//System.out.println("Computation: " + i);
			
			for(int j=0;j<50;j++){
				int call = sa.numberCall();
				found = false;
				for(int k=0;k<5;k++){
					if(memoryL[k] == call){
						found = true;
						break;
					}
				}
				if(!found){
					sa.addL(memoryL,added,call);
					added[call] = j;
				}
				else{
					L++;
				}
				found = false;
				for(int k=0;k<5;k++){
					if(memoryR[k] == call){
						found = true;
						called[call] = j;
						break;
					}
				}
				if(!found){
					sa.addR(memoryR,called,call);
					called[call] = j;
				}
				else{
					R++;
				}
				
				//System.out.println("Iteration " + j + ": call = " + call +", L = " + L + ", R = " + R);
			}
			
			sum += Math.abs(L - R);
			//System.out.println("Sum = " + sum);
		}
		
		System.out.println("Expected value: " + sum/MAX);
	}
	
	private void addL(int[] memory,int[] added,int call){
		int i,minI = 0;
		for(i=0;i<5;i++){
			if(added[memory[i]] < added[memory[minI]]){
				minI = i;
			}
		}
		memory[minI] = call;
	}
	
	private void addR(int[] memory,int[] called,int call){
		int i,minI = 0;
		for(i=0;i<5;i++){
			if(called[memory[i]] < called[memory[minI]]){
				minI = i;
			}
		}
		memory[minI] = call;
	}
	
	private int numberCall(){
		return (new Random().nextInt(10) +1);
	}
}
