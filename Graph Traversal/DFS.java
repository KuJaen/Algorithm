import java.io.*; 
import java.util.*; 
  
/**
 * DFS의 Graph클래스
 * BFS와 그래프를 생성하는 방법과 엣지를 추가하는 방법이 동일하므로 이에대한 설명은 생략하고 필요한 부분만 설명하겠다
 * @author KNoA
 *
 */
class DFSGraph 
{ 
	String print = "";
    private int V;
    private LinkedList<Object> adj[];
    
    /**
     * DFSGraph 클래스
     * @param v 그래프의 크기
     * @param list 노드들의 리스트
     */
    DFSGraph(int v, String[] list) 
    { 
    	V = v; 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) { 
            adj[i] = new LinkedList();
            adj[i].add(list[i]);
        }
    } 
    
    /**
     * addEdge 클래스. 엣지를 설정하는 클래스
     * @param v 연결할 두 노드 중 첫번째 노드
     * @param w 연결할 두 노드 중 두번째 노드
     */
    void addEdge(String v, String w) 
    { 
    	for(int i=0; i<V; ++i) {
    		if(((String)adj[i].element()).equals(v)) {
    			if(!(adj[i].contains(w)))
    				adj[i].add(w);
    		}
    	}
    	for(int i=0; i<V; ++i) {
    		if(((String)adj[i].element()).equals(w)) {
    			if(!(adj[i].contains(v)))
    				adj[i].add(v);
    		}
    	} 
    } 
  
    /**
     * DFSUtil 클래스
     * 깊이 우선 탐색과정이 담겨있는 클래스
     * @param s 탐색중인 노드
     * @param visited visited값은 BFS와 동일한 의미를 갖고 있다.
     */
    void DFSUtil(String s,boolean visited[]) 
    {  
    	// 노드 s의 위치를 탐색하여 tmp에 저장한다.
    	int tmp = 0;
		for(int t=0; t<V; t++) {
			if(((String)adj[t].element()).equals(s)) {
				tmp = t;
			}
		}
		// 만약 노드 s가 탐색이 이루어진 노드가 아니라면 print에 추가하고 visited갑을 true로 바꾼다.
		if(!visited[tmp]) {
			visited[tmp] = true; 
	        print = print + s + " "; 
		}
		// 노드 s에 연결되어 있는 다른 노드들 또한 탐색한다.
        Iterator<Object> i = adj[tmp].listIterator(); 
        // temp는 BFS와는 반대로 같은 너비에 있을떄 오름차순으로 탐색하기 위해 사용된다.
        LinkedList<Object> temp = new LinkedList<Object>();
        while (i.hasNext()) 
        { 
        	// 아래로는 temp에 오름차순으로 탐색한 노드들을 저장하는 과정으로 BFS와 동일하다.
        	// 다른 부분이 있다면 temp에 있는 노드들을 어떤 순서로 탐색하도록 하느냐이다.
        	// 따라서 설명은 temp에 노드들을 오름차순으로 저장하는 과정 이후, 탐색 과정부터 다시 이어가도록 하겠다.
            s = (String) i.next(); 
            
            for(int k=0; k<V; k++) {
    			if(((String)adj[k].element()).equals(s)) {
    				tmp = k;
    			}
    		}
            if (!visited[tmp]) {
            	Iterator<Object> iterator = temp.listIterator();
                int t = 0;
                while(iterator.hasNext()) {
                	String ch = (String) iterator.next();
                	
                	if(ch.length() < s.length()) {
                		String str = "";
                		for(int flg=0; flg<ch.length(); flg++) {
                			str = str + ch.charAt(flg);
                			if(ch.charAt(flg) < s.charAt(flg)){
                				t++;
                				break;
                			}
                			else if(str.equals(ch)) {
                				t++;
                				break;
                			}
                		}
                	}
                	else if(ch.length() == s.length()) {
                		for(int flg=0; flg<ch.length(); flg++) {
                			if(ch.charAt(flg) < s.charAt(flg)){
                				t++;
                				break;
                			}
                		}
                	}
                	else {
                		String str = "";
                		for(int flg=0; flg<s.length(); flg++) {
                			if(ch.charAt(flg) < s.charAt(flg)){
                				t++;
                				break;
                			}
                		}
                	}
                }
                temp.add(t, s);
            }
        }
        /* temp에 저장된 노드들은 순서대로 탐색되는데 BFS와는 다르다.
         * BFS는 temp에 저장되어 이는 모든 노드를 queue에 저장하여 탐색을 진행했다.
         * 이 과정에서 같은 깊이에 있는 즉, 너비우선 탐색을 진행하도록 한 것이다.
         * 하지만 DFS는 이와는 다르게 오름차순으로 우선적인 노드를 먼저 탐색하되
         * 그 다음노드는 탐색중인 노드의 깊이가 모두 탐색되기 전에는 탐색이 이루어지지 않는다.
         * 즉 깊이 우선 탐색이 이루어지는 것이다.
         */
        Iterator<Object> iterator = temp.listIterator();
        // 반복문 내부의 재귀문으로 인하여 모든 깊이가 탐색되기 전까지 이 반복문은 반복이 이루어지지 않는다.
        // 즉 깊이 우선 탐색이 이루어지는 것이다.
        while(iterator.hasNext()) {
        	
        	String sc = (String)iterator.next();
            DFSUtil(sc, visited);
        }
    } 
    
    /**
     * DFSUtil이 실행되는 DFS클래스이다.
     * @param s 탐색의 중심이 될 노드를 나타낸다.
     */
    void DFS(String s) 
    { 
        boolean visited[] = new boolean[V]; 

        DFSUtil(s, visited); // DFS실행
        System.out.println(print.trim()); // 결과 출력
    } 
}
    
public class DFS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.nextLine();
		String[] num = n.split(" ");
		
		
		n = sc.nextLine();
		String[] nodeName = n.split(" ");
		
		DFSGraph g = new DFSGraph(Integer.parseInt(num[0]), nodeName); 

		String[] tmp;
		for(int j=0;j<Integer.parseInt(num[1]);j++) {
			String s = sc.nextLine();
			tmp = s.split(" ");
			
			g.addEdge(tmp[0], tmp[1]);
		}
		
		n = sc.next();
		
		g.DFS(n);
	}
}
