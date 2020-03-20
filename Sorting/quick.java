import java.util.Scanner;

public class quick {
	static char[] list3;
	static int part = 0;
	
	public void quicksort(char[] A, int lo, int hi, int n, String flag) {
		if(lo < hi) {
			int p = partition(A, lo, hi, n, flag);
			quicksort(A, lo, p-1, n, flag);
			quicksort(A, p+1, hi, n, flag);
		}
	}
	
	public int partition(char[] A, int lo, int hi, int n, String flag) {
		if(part < n) {
			char pivot = A[hi];
			int i = lo;
			
			if(flag.equals("A")) {
				for(int j=lo;j<hi;j++) {
					if(A[j] <= pivot) { 
						char tmp = A[j];
						A[j] = A[i];
						A[i] = tmp;
						i++;
					}
				}
				
				char tmp = A[i];
				A[i] = A[hi];
				A[hi] = tmp;
			}
			else {
				for(int j=lo;j<hi;j++) {
					if(A[j] >= pivot) { 
						char tmp = A[j];
						A[j] = A[i];
						A[i] = tmp;
						i++;
					}
				}
				
				char tmp = A[i];
				A[i] = A[hi];
				A[hi] = tmp;
			}
			part++;
			return i;
		}
		else
			return (hi+lo)/2;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.nextLine();
		String[] list = n.split(" ");
		
		int num = Integer.parseInt(list[0]);
		
		Scanner sc2 = new Scanner(System.in);
		String n2 = sc.nextLine();
		String[] list2 = n2.split(" ");
		list3 = new char[list2.length];
		
		for(int i=0; i<list2.length; i++) {
			list3[i] = list2[i].charAt(0);
		}
		
		quick q = new quick();
		q.quicksort(list3, 0, num-1, Integer.parseInt(list[1]), list[2]);
		
		for(int i=0;i<num;i++) {
			if(i == num-1)
				System.out.print(list3[i]);
			else
				System.out.print(list3[i] + " ");
		}
	}
}
