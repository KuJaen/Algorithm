import java.util.Arrays;
import java.util.Scanner;

public class Anagram {
	void anagram(String a, String b) {
		if(a.length() != b.length())
			System.out.println("False");
		else {
			boolean flag = true;
			
			char[] ac = new char[a.length()];
			char[] bc = new char[b.length()];
			
			for(int i=0; i<a.length();i++) {
				if((a.charAt(i)>=65 && a.charAt(i)<=90)||(a.charAt(i)>=97 && a.charAt(i)<=122)) {
					if(a.charAt(i)>=97 && a.charAt(i)<=122) {
						ac[i] = (char) ((int)a.charAt(i)-32);
					}
					else
						ac[i] = a.charAt(i);
				}
				else {
					ac[i] = a.charAt(i);
				}
				
				if((b.charAt(i)>=65 && b.charAt(i)<=90)||(b.charAt(i)>=97 && b.charAt(i)<=122)) {
					if(b.charAt(i)>=97 && b.charAt(i)<=122) {
						bc[i] = (char) ((int)b.charAt(i)-32);
					}
					else
						bc[i] = b.charAt(i);
				}
				else {
					bc[i] = b.charAt(i);
				}
			}
			
			Arrays.sort(ac);
			Arrays.sort(bc);
			
			for(int j=0; j<ac.length;j++) {
				if(ac[j] != bc[j])
					flag = false;
			}
			
			if(flag)
				System.out.println("True");
			else
				System.out.println("False");
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.nextLine();
		String[] list = n.split(" ");
		
		Anagram ana = new Anagram();
		ana.anagram(list[0], list[1]);
	}
}
