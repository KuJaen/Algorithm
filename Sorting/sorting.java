import java.util.Arrays;
import java.util.Scanner;

public class sorting {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.nextLine();
		String[] list = n.split(" ");
		
		int num = Integer.parseInt(list[0]);
		
		Scanner sc2 = new Scanner(System.in);
		String n2 = sc.nextLine();
		String[] list2 = n2.split(" ");
		String[] list3 = new String[list2.length];
		
		Arrays.sort(list2);
		
		
		if(list[1].equals("A")) {
			for(int i=0;i<num;i++) {
				if(i == num-1)
					System.out.print(list2[i]);
				else
					System.out.print(list2[i] + " ");
			}
		}
		else {
			for(int i=0;i<num;i++) {
				list3[i] = list2[num-1-i];
			}
			
			for(int i=0;i<num;i++) {
				if(i == num-1)
					System.out.print(list3[i]);
				else
					System.out.print(list3[i] + " ");
			}
		}
	}
}
