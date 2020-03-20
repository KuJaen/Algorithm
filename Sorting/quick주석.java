
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
			// j�� ���� pivot���� ū���� ���� �� ���� Ž���ϸ� i�� �ڸ��� �ٲ㳪��.
			// �� ���� pivot���� ū ���� �������� ���ʿ��� pivot���� ������ �����ʿ��� ū���� ��ġ��
			// ���� pivot�� j�� �տ� �Ű���
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
