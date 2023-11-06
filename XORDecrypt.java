package Decrypt;
import java.io.*;

public class XORDecrypt {

	public static void main(String[] args) {
		
		int i,j,k,l;
		int[] key = new int[3];
		String line = null;
		File file = new File("/home/admin1/workspace/XOR Decryptor/src/Decrypt/cipher1.txt"); // Location of file may change from system to system.
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			line = br.readLine();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.toString());
		} catch (IOException e1) {
			System.out.println("Unable to read File" + file.toString() );
		}

		String[] numbers = line.split(",");
		int[] cipher = new int[numbers.length];
		int[] plain = new int[numbers.length];
		
		for(i=0;i<numbers.length;i++){
			cipher[i] = Integer.parseInt(numbers[i]);
		}
		
		// Scan three letters for "the"
		for(i=97;i<=122;i++){
			for(j=97;j<=122;j++){
				for(k=97;k<=122;k++){
					StringBuilder str = new StringBuilder();
					for(l=0;l<cipher.length-1;l=l+3){
						int letter1 = cipher[l]^i;
						int letter2 = cipher[l+1]^j;
						int letter3 = cipher[l+2]^k;
						
						char c1 = (char)letter1;
						char c2 = (char)letter2;
						char c3 = (char)letter3;
						
						str.append(c1 + "" + c2+ ""+ c3);
					}
					if(str.indexOf(" the ")>-1){
						key[0] = i;
						key[1] = j;
						key[2] = k;
						System.out.println("Key: " + (char)key[0] + (char)key[1] + (char)key[2]);
					}
				}
			}
		}
		
		StringBuilder str1 = new StringBuilder();
		int sumPlainText = 0;
		
		for(i=0;i<cipher.length-1;i+=3){
			for(j=0;j<3;j++){
				plain[i+j] = cipher[i+j]^key[j];
				str1.append((char)plain[i+j]);
				sumPlainText += cipher[i+j]^key[j];
			}
			 
		}
		sumPlainText += cipher[cipher.length-1]^key[(cipher.length%3)-1];
		str1.append((char)(cipher[cipher.length-1]^key[0]));
		System.out.println(str1.toString());
		System.out.println("Sum of Plain Text ASCII values: " + sumPlainText);
	}

}
