
public class quick {
	public void quicksort(char[] A, int lo, int hi) {
		if(lo < hi) {
			int p = partition(A, lo, hi);
			quicksort(A, lo, p-1);
			quicksort(A, p+1, hi);
		}
	}
	
	public int partition(char[] A, int lo, int hi) {
		char pivot = A[hi];
		int i = lo;
		
		for(int j=lo;j<hi;j++) {
			// j가 먼저 pivot보다 큰수를 만날 때 까지 탐색하며 i와 자리를 바꿔나감.
			// 즉 만난 pivot보다 큰 수를 기준으로 왼쪽에는 pivot보다 작은수 오른쪽에는 큰수가 배치됨
			// 이후 pivot을 j의 앞에 옮겨줌
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
		return i;
	}
	
	public static void main(String[] args) {
		
	}
}
