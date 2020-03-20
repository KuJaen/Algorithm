import java.util.Scanner;

public class Bubble {
	public void bubbleSort(char[] s, int n, int t, String m) {
		char temp;
		
		if(m.equals("A")) {
			for(int i = n; i > n-t; i--) {
		         for (int j = 0; j < i-1; j++) {
		             if(s[j] > s[j+1]) {
		                 temp = s[j]; 
		                 s[j] = s[j+1];
		                 s[j+1] = temp;
		             }
		         }
		     }
			
			for(int i=0;i<n;i++) {
				if(i == n-1)
					System.out.print(s[i]);
				else
					System.out.print(s[i] + " ");
			}
		}
		else {
			for(int i = n; i > n-t; i--) {
		         for (int j = 0; j < i-1; j++) {
		             if(s[j] < s[j+1]) {
		                 temp = s[j]; 
		                 s[j] = s[j+1];
		                 s[j+1] = temp;
		             }
		         }
		     }
			
			for(int i=0;i<n;i++) {
				if(i == n-1)
					System.out.print(s[i]);
				else
					System.out.print(s[i] + " ");
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.nextLine();
		String[] list = n.split(" ");
		
		int num = Integer.parseInt(list[0]);
		
		Scanner sc2 = new Scanner(System.in);
		String n2 = sc.nextLine();
		String[] list2 = n2.split(" ");
		char[] list3 = new char[list2.length];
		
		for(int i=0; i<list2.length; i++) {
			list3[i] = list2[i].charAt(0);
		}
		
		Bubble b = new Bubble();
		b.bubbleSort(list3, Integer.parseInt(list[0]), Integer.parseInt(list[1]), list[2]);
	}
}
