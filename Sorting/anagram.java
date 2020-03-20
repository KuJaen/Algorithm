import java.util.Arrays;
import java.util.Scanner;

public class anagram {
	public String sortStr(String s) {
		char[] c = new char[s.length()];
		
		for(int i=0;i<s.length();i++) {
			c[i] = s.charAt(i);
		}
		Arrays.sort(c);
		
		String rs ="";
		for(int j=0;j<c.length;j++) {
			rs = rs + c[j];
		}
		return rs;
	}
	
	public String toLower(String s) {	
		String rs = "";
		for(int i=0;i<s.length();i++) {
			rs = rs + String.valueOf(s.charAt(i)).toLowerCase();
		}
		return rs;
	}
	
	public static void main(String[] args) {
		anagram a = new anagram();
		
		Scanner sc = new Scanner(System.in);
		String n = sc.nextLine();
		String[] list = n.split(" ");
		String[] printList = list;
		
		String[] result = new String[list.length];
		
		for(int i=0; i< list.length; i++) {
			list[i] = a.toLower(list[i]);
		}
		
		Arrays.sort(list);
		
		for(int i=0; i< list.length; i++) {
			result[i] = a.sortStr(list[i]);
		}
		
		for(int j=0; j< result.length; j++) {
			if(!(result[j].equals(" "))) {
				System.out.print(printList[j]+" ");
				for(int k=j+1; k< result.length; k++) {
					if(result[j].equals(result[k])) {
						System.out.print(printList[k]+" ");
						result[k]=" ";
					}
				}
				System.out.println();
			}
		}
	}
}
