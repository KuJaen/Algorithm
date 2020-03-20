import java.util.Scanner;

public class Kakaodart2 {
	static int[] ilist = new int[3];
	static char[] clist = new char[3];
	static char[] optionlist = {' ', ' ', ' '};
	static boolean flag = false;
	
	
	boolean isAlpha(char c) {
		if((c>=65 && c<=90) || (c>=97 && c<=122))
			return true;
		else
			return false;
	}
	
	boolean isNum(char c) {
		if(c>=48 && c<=57)
			return true;
		else
			return false;
	}
	
	int isSpecial(char c) {
		if(c==42)
			return 1;
		else if(c==35)
			return 0;
		else
			return -1;
	}
	
	void dart(String s, int t) {
		String str = "";
		int tmp = 0;
		for(int i=0;i<s.length();i++) {
			tmp = i;
			if(isAlpha(s.charAt(i)))
				break;
			else
				str = str + s.charAt(i);
		}
		ilist[t] = Integer.parseInt(str);
		clist[t] = s.charAt(tmp);
		
		try {
			if(isSpecial(s.charAt(tmp+1)) == 0) {
				optionlist[t] = '#';
				dart(s.substring(tmp+2, s.length()), t+1);
			}
			else if(isSpecial(s.charAt(tmp+1)) == 1) {
				optionlist[t] = '*';
				dart(s.substring(tmp+2, s.length()), t+1);
			}
			else {
				dart(s.substring(tmp+1, s.length()), t+1);
			}
		}catch(Exception e) {}
	}
	
	int dartGame() {
		for(int i=0;i<ilist.length;i++) {
			if(clist[i] == 'S') {
				
			}
			else if(clist[i] == 'D') {
				ilist[i] = ilist[i]*ilist[i];
			}
			else if(clist[i] == 'T') {
				ilist[i] = ilist[i]*ilist[i]*ilist[i];
			}
			else {
				System.out.println("Error");
				return -1;
			}
			
			if(flag == true) {
				ilist[i] = ilist[i]*2;
				flag = false;
			}
			
			if(optionlist[i] == '*') {
				flag = true;
				ilist[i] = ilist[i]*2;
			}
			else if(optionlist[i] == '#') {
				ilist[i] = ilist[i]*-1;
			}
			else {}
		}
		return ilist[0] + ilist[1]+ ilist[2];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		Kakaodart2 kkd = new Kakaodart2();
		kkd.dart(s, 0);
		System.out.println(kkd.dartGame());
	}
}
