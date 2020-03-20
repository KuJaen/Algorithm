import java.util.Scanner;

public class Palindrome {
	static int result = 0;
	
	void palindrome(String s) {
		int itmp = 1;
		for(int i=0; i<s.length(); i++) {
			String tmp = s.substring(0, i+1);
			boolean flag = true;
			if(tmp.length()%2 == 0) {
				for(int j=0; j<i/2; j++) {
					if(tmp.charAt(j) != tmp.charAt(tmp.length()-1-j))
						flag = false;
				}
				if(flag) {
					if(result <= itmp)
						result = itmp;
				}
			}
			else {
				for(int j=0; j<(i/2)-1; j++) {
					if(tmp.charAt(j) != tmp.charAt(tmp.length()-1-j))
						flag = false;
				}
				if(flag) {
					if(result <= itmp)
						result = itmp;
				}
			}
			itmp++;
		}
		if(s.length() != 1)
			palindrome(s.substring(1,s.length()));
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.nextLine();
		String[] list = n.split(" ");
		
		String s = "";
		for(int i=0;i<list.length;i++)
			s = s+list[i];
		s = s.toLowerCase();
		
		Palindrome p = new Palindrome();
		p.palindrome(s);
		System.out.println(result);
	}
}
