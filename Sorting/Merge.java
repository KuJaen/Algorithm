import java.util.Scanner;

public class Merge {
	public char[] mergeSort(char[] s, int n, int t, String m) {
		if(s.length <= 1) {
			return s;
		}
		int middle = n/2;
		
		char[] left = new char[middle];
		char[] right = new char[n-middle];
		
		for(int i=0;i<middle;i++) {
			left[i] = s[i];
		}
		int tmp = 0;
		for(int i=middle;i<n;i++) {
			right[tmp] = s[i];
			tmp++;
		}
		
		left = mergeSort(left, left.length, t-1, m);
		right = mergeSort(right, right.length, t-1, m);
		
		char[] cs = merge(left, right);
		for(int i=0; i<cs.length; i++) {
			if(i == cs.length-1)
				System.out.println(cs[i]);
			else
				System.out.print(cs[i] + " ");
		}
		
		return cs;
	}
	
	public char[] merge(char[] left, char[] right) {
		char[] result = new char[left.length + right.length];
		int tmp = 0;
		
		try {
			while(true) {
				if(left[tmp] <= right[tmp])
					result[tmp] = left[tmp];
				else
					result[tmp] = right[tmp];
				tmp++;
			}
		}catch(Exception e){}
		
		try {
			while(true) {
				result[tmp] = left[tmp];
				tmp++;
			}
		}catch(Exception e){}
		
		try {
			while(true) {
				result[tmp] = right[tmp];
				tmp++;
			}
		}catch(Exception e){}
		
		
		return result;
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
		
		Merge b = new Merge();
		b.mergeSort(list3, Integer.parseInt(list[0]), Integer.parseInt(list[1]), list[2]);
	}
}
