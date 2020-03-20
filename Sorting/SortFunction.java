package Sort;

import java.util.Random;

public class SortFunction implements SortInterface {
	private static int MAX_SIZE = 100000; // Array의 최대 크기
	private int[] numberArray; // 난수로 MAX_SIZE만큼 인자가 추가될 Array
	private int[] sortedArray; // Sort가 진행되고 최종적으로 Sort가 끝난 Array가 저장될 메서드
	private Random random; // 난수 대입을 위한 객체 생성
	
	//생성자
	public SortFunction() {
		this.numberArray = new int[MAX_SIZE]; // numberArray 정의
		this.random = new Random(); // random 객체 저의
		
		// numberArray에 0부터 1000사이의 숫자를 랜덤으로 넣는다.
		for(int i = 0; i < this.numberArray.length; i++) {
			numberArray[i] = random.nextInt(1000);
		}
		
		this.sortedArray = new int[MAX_SIZE]; //sortedArray 정의
		// numberArray를 0부터 MAX_SIZE까지 즉, 전체를 sortedArray에 복사한다.
		System.arraycopy(numberArray, 0, sortedArray, 0, MAX_SIZE);
	}
	
	//sortedArray를 초기화 하는 메서드
	public void setSortedArray() {
		//sortedArray를 Sort이전으로 되돌린다.
		System.arraycopy(numberArray, 0, sortedArray, 0, MAX_SIZE);
	}
	
	// Bubble Sort 메서드
	@Override
	public void bubbleSort() {
		int temp; // temp는 Bubble Sort과정에서 인접한 두 인자를 서로 교환하는데 사용할 변수이다.
		
		/* Bubble Sort는 오른쪽에서부터 차례대로 완성되어 간다. 
		 * 따라서 반드시 n번이상의 검사가 이루어질 것이고 이것이 최적의 시간복잡도가  Ω(n)인 이유이다. */
		 for(int i = sortedArray.length; i > 0; i--) {
			 //왼쪽부터 순서대로 인접한 인자끼리 비교한다.
	         for (int j = 0; j < i-1; j++) {
	        	 //만약 인접한 두 인자 중 왼쪽에 있는 인자가 더 크다면 두 인자의 자리를 교체한다.
	             if(sortedArray[j] > sortedArray[j+1]) {
	                 temp = sortedArray[j]; 
	                 sortedArray[j] = sortedArray[j+1];
	                 sortedArray[j+1] = temp;
	             }
	         }
	     }
	}
	
	// Selection Sort 메서드
	@Override
	public void selectionSort() {
		int min; // 정렬되지 않은 인자중 가장 작은 인자의 위치를 나타내는 변수
		int temp; // 인자의 자리를 교환하는데 사용하는 변수
		
		// 정렬되지 않은 인자중 가장 왼쪽부터 초기 min으로 지정한다. 이는 Array의 가장왼쪽부터 차례대로 정렬함을 의미한다.
		for(int i = 0; i < sortedArray.length-1; i++){
            min = i; // 왼쪽부터 순서대로 min으로 지정
            
            // 지정된 min이후의 인자들과 순차적으로 크기를 비교한다.
            for(int j = i+1; j < sortedArray.length; j++){
            	// 만약 min에 해당하는 인자보다 작은 인자가 발견된다면 min 즉, 최솟값을 변경해 준다.
                if(sortedArray[min] > sortedArray[j]){
                    min = j;
                }
            }
            
            /* min에 해당하는 인자와 i에 해당하는 인자의 위치를 서로 바꾸어준다.
             * 이러면 Array의 왼쪽부터 작은 순으로 정렬이 될 것이다.
             */
            temp = sortedArray[min];
            sortedArray[min] = sortedArray[i];
            sortedArray[i] = temp;
        }
	} // 출처: https://hahahoho5915.tistory.com/7
	
	// QuickSort 메서드
	@Override
	/* 매개변수 left, right는 Sort가 이루어질 Array 파티션의 좌우 끝을 각각 의마한다.
	 * 이때 Array 파티션이란 Quick Sort는 하나의 Array를 pivot을 기준으로 두개의 Array로 나누어 진행하는데 그 각각의 분할된 Array를
	 * Array 파티션이라 하겠다. (설명의 편의성을 위해 지정한 명칭이다.)
	 */
	public void quickSort(int left, int right) {
		int low, high; // 검사가 이루어지는 인자중 각각 낮은위치, 높은위치를 나타낸다.
		int pivot; // 기준이 되는 pivot 변수이다.
		int temp; //인자의 위치를 교체하는데 사용할 변수이다.
		
		// 만약  right가 left보다 크다면. 즉, 가장 오른쪽 인자를 나타내는 right가 가장 왼쪽을 나타내는 left보다 오른쪽에 있을 경우를 말한다.
		// left가 right보다 더 크다면 이는 정렬이 끝났음을 의미한다.
		if(left<right)
		{
		   low = left; // low는 left부터 시작한다.
		   high = right; // high는 right부터 시작한다.
		   pivot = sortedArray[left]; // pivot은 정렬하는 Array의 첫번째 인자
		   
		   //분할 과정
		   while(low < high) //high가 low보다 클때까지. 즉, 한번의 정렬이 끝날 때 까지
		   {
			  // pivot에 해당하는 인자보다 작은 sortedArray[high]인자가 나올 때 까지 high인덱스를 하나씩 줄여준다.
		      while(sortedArray[high] > pivot) {
		    	  high--;
		      }
		      
		      // pivot에 해당하는 인자보다 큰 sortedArray[low]인자가 나올 때 까지 low인덱스를 하나씩 올려준다.
		      while(low < high && sortedArray[low] <= pivot) {
		    	  low++;
		      }
		      
		     //pivot보다 크고 작은 두  sortedArray[low], sortedArray[high] 인덱스의 위치를 바꿔준다.
	         temp = sortedArray[low];
	         sortedArray[low] = sortedArray[high];
	         sortedArray[high] = temp;
	       }
		   
		   /* 이 퀵정렬은 정렬하는 Array의 첫번쨰 인자를 pivot으로 사용한다.
		    * 이때 한번 이상 정렬이 이루어져 나누어진 Array 파티션에서도 첫번째 인자를 pivot으로 사용하여야 하기 때문에
		    * 첫 번쨰 인자의 위치에 low위치 인자를 넣어주고 low위치에 pivot을 즉, 천번째 인자의 위치를 넣어준다.
		    * 이는 두 파티션 이 low위치를 기준으로 나누어짐을 의미한다. */
		   sortedArray[left] = sortedArray[low];
		   sortedArray[low] = pivot;
	       
		   // 함수를 재귀적으로 호출하여 두 Array 파티션으로 나누어 다시 정렬을 진행한다.
	       quickSort(left, low-1);
	       quickSort(low+1, right);  
	    }
	 } // 출처: https://cocomo.tistory.com/247 [Cocomo Coding]
	
	// insertionSort 메서드
	@Override
	
	// 인덱스1부터 하나씩 앞에있는 인자들과 비교하여 key에 해당하는 인자보다 큰 인자가 있다면 위치를 바꾼다. 이는 왼쪽에서 오른쪽으로 정렬이 진행된다.
	public void insertionSort() {
		//Array의 총 길이까지 정렬이 진행된다.
		for(int i = 1; i < sortedArray.length; i++) {
			int key = sortedArray[i]; //key는 위치가 바뀔 앞쪽 인덱스에 해당하는 인자이다.
			int k = i - 1; // k는 i인덱스 위치보다 왼쪽 위치에 해당하는 인덱스를 의미한다.
			
			// k가 0보다 크고 key보다 큰 인자가 key바로 이전에 있다면 key보다 작거나 같은 인자가 나올때까지 진행한다.
			while(k >= 0 && key < sortedArray[k]) {
				sortedArray[k+1] = sortedArray[k]; //key보다 큰 인자들을 key위치 까지 한칸씩 Array를 밀어준다.
				
				k--;
			}
			sortedArray[k+1] = key; //Array를 밀어주었다면 밀어준 인자들 가장 앞에 key가 들어간다.
			//그렇지 않은 경우에는 변화가 없다.
		}
	}

	//정렬 이전 Array를 출력하는 메서드이다.
	@Override
	public void printOriginalArray() {
		int temp = 0; // 한줄에 출력할 인자를 제한하기 위한 변수이다.
		
		// 모든 인자들을 출력한다.
		while(temp < numberArray.length) {
			//한줄에 20개씩 출력한다.
			for(int i = temp; i < temp + 20; i++) {
				System.out.print(numberArray[i] + " ");
			}
			// 20개가 모두 출력되었다면 한줄 띄고 다시 20개를 출력한다.
			System.out.println();
			temp = temp + 20;
		}
	}
	
	//정렬 이후 Array를 출력하는 메서드이다.
	@Override
	public void printSortedArray() {
		int temp = 0; // 한줄에 출력할 인자를 제한하기 위한 변수이다.
		
		// 모든 인자를 출력한다.
		while(temp < sortedArray.length) {
			//한줄에 20개씩 출력한다.
			for(int i = temp; i < temp + 20; i++) {
				System.out.print(sortedArray[i] + " ");
			}
			// 20개가 모두 출력되었다면 한줄 띄고 다시 20개를 출력한다.
			System.out.println();
			temp = temp + 20;
		}
	}
}
