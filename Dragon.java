/////////////////////////////////////////////////////////////////////////////
///////   It is observed that if the pointer at the end of 			  ///////
///////   N elements of the string is at (a,b), then at the			  ///////
///////   end of 2N elements it is at (a+b,b-a)						  ///////
///////   Thus the coordinates can be calculated recursively		  ///////
///////   After we have computed the string up to m such that 	      ///////
///////   m is an odd number and LEN = m * (2^k), k being an integer.  //////
/////////////////////////////////////////////////////////////////////////////


package dragonCurve;

import java.io.*;

public class Dragon {

	static long max = 0L;
	final static long LEN = 1000000000000L;
	static int x = 0, y = 0,dir = 0;
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		String path = generate();
		traverse(path);
		reach();
		
		System.out.println(x + " " + y);
	}

	public static String generate(){
		StringBuffer D0 = new StringBuffer("Fa");
		StringBuffer a = new StringBuffer("aRbFR");
		StringBuffer b = new StringBuffer("LFaLb");
		StringBuffer Di = D0;
		for (int i = 1; i <= 24; i++) { // have to go upto 42
			char[] chArray = Di.toString().toCharArray();
			Di= new StringBuffer("");
			for (int j = 0; j < chArray.length; j++) {
				if (chArray[j] == 'a') {
					Di.append(a);
				}
				else if (chArray[j] == 'b') {
					Di.append(b);
				} 
				else {
					Di.append(chArray[j]);
				}
			}
		}
		return Di.toString();
	}

	public static void traverse(String path) throws FileNotFoundException, UnsupportedEncodingException {
		for(int i=0;i<15258789;i++){
			switch(path.charAt(i)){
			case 'L':
				left();
				break;
			case 'R':
				right();
				break;
			case 'F':
				forward();
			}
		}
	}
	
	public static void reach(){
		int i;
		
		for(i=0;i<4;i++){
			moveDouble();
		}
		
		y--;
		
		for(i=0;i<12;i++){
			moveDouble();
		}
	}
	
	public static void moveDouble(){
		int temp1 = x;
		int temp2 = y;
		x = temp1 + temp2;
		y = temp2 - temp1;
	}
	
	public static void right(){
		switch(dir){
		case 0:
			dir = 1;
			break;
		case 1:
			dir = 2;
			break;
		case 2:
			dir = 3;
			break;
		case 3: 
			dir = 0;
			break;
		}
	}
	
	public static void left(){
		switch(dir){
		case 0:
			dir = 3;
			break;
		case 1:
			dir = 0;
			break;
		case 2:
			dir = 1;
			break;
		case 3: 
			dir = 2;
			break;
		}
	}
	
	public static void forward(){
		switch(dir){
		case 0:
			y++;
			break;
		case 1:
			x++;
			break;
		case 2:
			y--;
			break;
		case 3: 
			x--;
			break;
		}
	}
}

