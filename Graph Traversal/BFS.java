
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * @author KNoA
 *
 *그래프 클래스
 */
class Graph 
{ 
	String print = ""; // 최종적으로 출력할 내용 즉, BFS 탐사 결과를 저장할 필드이다.
    private int V; // V는 그래프의 노드의 수를 의미한다.
    private LinkedList<Object> adj[]; // 각 노드들이 저장될 링크드 리스트
  
   /**
    * Graph 생성자
    * @param v 총 노드의 갯수를 param값으로 받음
    * @param list 노드 리스트를 입력받는다. 이 리스트 안에는 각 노드들이 들어있다.
    */
    Graph(int v, String list[]) 
    { 
        V = v; // 그래프의 크기지정
        adj = new LinkedList[v]; // adj 객체 생성
        
        /* adj의 각 인자는 다시 링크드리스트를 포함한다. 즉, 2차원 배열을 생각하면 된다.
         * 각 인자 리스트 안에는 첫번째로 각 노드가, 그 뒤로는 해당 노드와 연결되어있는 다른 노드들이 저장된다.
         */
        for (int i=0; i<v; ++i) { 
            adj[i] = new LinkedList(); // 각 인자에 링크드 리스트 생성
            adj[i].add(list[i]); // 각 노드를 각각의 리스트의 첫번째 인자로 저장한다.
        }
    } 
  
    /**
     * 노드에 엣지를 설정하는 메서드
     * @param v 연결할 두 노드의 첫번쨰 노드
     * @param w 연결할 두 노드의 두번째 노드
     */
    void addEdge(String v,String w) 
    {
    	/*
    	 * for문은 v노드를 탐색하기 위해 사용되었다.
    	 * 탐색 결과 찾아낸 v노드의 리스트에 v노드의 다음 인자로 w를 추가한다.
    	 * 이떄 이미 w가 인자로 포함되어 있다면 w를 추가하지 않는다.
    	 */
    	for(int i=0; i<V; ++i) {
    		if(((String)adj[i].element()).equals(v)) {
    			if(!(adj[i].contains(w)))
    				adj[i].add(w);
    		}
    	}
    	// 위 과정을 w노드를 기준에서도 반복한다. 즉, 두 노드를 쌍방으로 연결시켜준다.
    	for(int i=0; i<V; ++i) {
    		if(((String)adj[i].element()).equals(w)) {
    			if(!(adj[i].contains(v)))
    				adj[i].add(v);
    		}
    	} 
    } 
    
    
    /**
     * BFS 메서드
     * 너비 우선으로 탐색하되 같은 깊이에 있는 노드들은 오름차순으로 탐색한다.
     * @param s 탐색의 중심이 될 노드
     */
	void BFS(String s) 
	{ 	
		// 노드s의 위치를 탐색한다. 노드 위치는 k에 저장된다.
		int k = 0;
		for(int i=0; i<V; ++i) {
    		if(((String)adj[i].element()).equals(s)) {
    			k = i;
    			break;
    		}
    	} 
		
		// BFS에서 이미 탐색이 완료된 노드는 visited값이 true가 된다.
		// true가 된 노드는 다시 탐색, 출력이 이루어지지 않는다.
		boolean visited[] = new boolean[V]; 
		
		/* 탐색 된 노드들은 출력되기 전 queue에 먼저 저장된다. queue에는 출력 순서 즉, 탐색 순서대로 저장된다.
		 * 예를 들면 A의 엣지로 B와 C가 있다면 저장 순서는 A - B - C - B의 엣지들 - ... - C의 엣지들... 인 것이다.
		 */
		LinkedList<Object> queue = new LinkedList<Object>(); 

        visited[k]=true; // 노드 s는 방문했으므로 visited값을 true로 바꿔준다.
        queue.add(s);  // 노드 s를 queue에 추가한다.
        // temp는 같은 깊이에 있는 노드들을 오름차순으로 queue에 출력하기 위하여 사용된다.
        // 같은 깊이에 있는 모든 노드들은 temp에 오름차순으로 저장 된 후 queue에 추가된다.
        LinkedList<Object> temp = new LinkedList<Object>();
        
        // queue의 size가 0이 될 때까지 즉, 모든 노드들이 출력될 때 까지 반복한다.
		while (queue.size() != 0) 
		{ 
			s = (String) queue.poll(); // queue에 저장된 노드들을 차례로 꺼내어 탐색한다.
			print = print + s + " "; // 탐색된 노드는 print에 저장되어 한꺼번에 출력된다.
			
			// 노드 s가 저장되어 있는 위치를 탐색하여 tmp에 저장한다.
			int tmp = 0;
			for(int t=0; t<V; t++) {
				if(((String)adj[t].element()).equals(s)) {
					tmp = t;
				}
			}
			
			// 노드 s와 함께 같이 저장되어 있는 엣지들은 이터레이터를 통해 탐색되어 오름차순으로  temp에 저장될 것이다.
			Iterator<Object> i = adj[k].listIterator(); 
            while (i.hasNext()) 
            { 
                String c = (String) i.next(); // 노드 s와 함께 저장되어 있는 엣지들은 하나씩 탐색되어 c에 저장된다.
                // temp에 저장되기 전에 c가 이미 탐색 되었는지를 확인해야 한다. 즉, visited값을 확인해야 한다.
                // 이를 위하여 노드 c의 위치를 먼저 탐색한다. c의 위치는 n에 저장된다.
                int n = 0;
                for(int j=0; j<V;j++) {
                	if(((String)adj[j].element()).equals(c)) {
                		n=j;
                		break;
                	}
                }
                // 노드 c가 아직 탐색되지 않은 노드라면 계속해서 진행한다.
                if (!visited[n]) 
                { 
                	// 노드 c를 이번에 탐색, 방문하였기 때문에 visited값을 true로 바꾼다.
                    visited[n] = true; 
                    
                    // 노드 c는 아래 temp이터레이터와 반목문을 통하여 오름차순으로 temp에 저장될 것이다.
                    int t=0; // t는 노드 c가 저장될 위치 즉, 오름차순으로 적합한 위치를 의미한다.
                    Iterator<Object> iterator = temp.listIterator();
                    
                    while(iterator.hasNext()) {
                    	// ch는 temp의 각 원소들로 c와 오름차순으로 비교가 이루어질 것이다.
                    	String ch = (String) iterator.next();
                    	
                    	// 오름차순으로 정리 할 때 첫글자가 서로 같으면 길이 짧은 글자가 우선적이다.
                    	// 따라서 길이가 같을때, ch가 길때, c가 길때를 부류하여 사전식 정리를 진행한다.
                    	if(ch.length() < c.length()) {
                    		/* str은 길이가 짧은 노드의 각 글자를 차례로 저장한다.
                    		 *  str이 그 노드와 같아졌다면 이는 길이가 긴 노드는 길이가 짧은 노드를 모두 포함하고 있다는 것을 의미한다.
                    		 *  따라서 길이가 짧은 노드가 앞으로 와야하는 것이다. 따라서 예외적으로 t의 값을 추가해 줄 것이다.
                    		 */
                    		String str = ""; 
                    		for(int flg=0; flg<ch.length(); flg++) {
                    			str = str + ch.charAt(flg);
                    			// 만약 추가하려는 노드가 이미 temp에 저장되어 있는 노드보다 사전식으로 뒤에 있다면
                    			// t의 값을 늘려 위치를 설정 해 준다.
                    			if(ch.charAt(flg) < c.charAt(flg)){
                    				t++;
                    				break;
                    			}
                    			// str값이 ch와 같아진다면 c는 ch를 모두 포함하지만 더 긴 글자라는 것을 의미하기에 t의 값을 늘려준다.
                    			else if(str.equals(ch)) {
                    				t++;
                    				break;
                    			}
                    		}
                    	}
                    	// ch와 c의 길이가 같을 때는 str을 사용할 필요가 없다. 그 외의 과정은 동일하다.
                    	else if(ch.length() == c.length()) {
                    		for(int flg=0; flg<ch.length(); flg++) {
                    			if(ch.charAt(flg) < c.charAt(flg)){
                    				t++;
                    				break;
                    			}
                    		}
                    	}
                    	// c의 길이가 ch보다 짧으면 ch가 c를 모두 포함할 때 사전순으로 당연히 앞으로 와야 하므로
                    	// 이례적으로 t의 값을 늘려줄 필요는 없다. 즉 str을 사용한 비교를 생략한다.
                    	else {
                    		for(int flg=0; flg<c.length(); flg++) {
                    			if(ch.charAt(flg) < c.charAt(flg)){
                    				t++;
                    				break;
                    			}
                    		}
                    	}
                    }
                    temp.add(t, c); // t위치에 c를 저장한다.
                }
            }
            // temp의 size가 0이 될 때 까지 즉, temp의 모든 원소를  queue에 저장하며 삭제해 준다.
            while(temp.size()!=0) {
            	queue.add(temp.removeFirst());
            }
		}
		System.out.println(print.trim()); // 결과 출력
	}
} 


public class BFS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.nextLine();
		String[] num = n.split(" ");
		
		
		n = sc.nextLine();
		String[] nodeName = n.split(" ");
		
		Graph g = new Graph(Integer.parseInt(num[0]), nodeName); 

		String[] tmp;
		for(int j=0;j<Integer.parseInt(num[1]);j++) {
			String s = sc.nextLine();
			tmp = s.split(" ");
			
			g.addEdge(tmp[0], tmp[1]);
		}
		
		n = sc.next();
		
		g.BFS(n);
	}
}
