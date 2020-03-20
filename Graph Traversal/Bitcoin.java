import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/** 
 * Bitcoin클래스이다
 * 구조는 BFS와 거의 동일하다.
 * 다른 점이라면 너비우선 탐색이나 깊이우선 탐색이 아닌 레벨 탐색이라는 점이다.
 * 탐색법이 달라졌다고는 하지만 코드상 달라진 부분은 depth라는 필드가 추가된 것과
 * temp라는 링크드리스트에 오름차순으로 저장된 노드들이 queue에 저장되는 조건이 생긴 것 뿐이다.
 * 따라서 전체적인 설명은 앞서 설명한 BFS를 참고하도록 하고 달라진 부분에 대해서만 주석으로 나타내도록 하겠다.
 * 
 *  
 * depth필드는 최대 깊이를 의미할 것이다.
 * @author KNoA
 *
 */
public class Bitcoin {
    private int V; 
    private LinkedList<Object> adj[];
    private int depth;
  
    Bitcoin(int v, String list[]) 
    { 
        V = v; 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) { 
            adj[i] = new LinkedList();
            adj[i].add(list[i]);
        }
        this.depth = 0;
    } 
  
    void addEdge(String v,String w) 
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
     * 레벨 탐색이 이루어지는 메서드의 이름을 편하게 Search라고 변경하겠다.
     * @param s 중심이 되는 노드
     */
	void Search(String s) 
	{ 	
		int k = 0;
		for(int i=0; i<V; ++i) {
    		if(((String)adj[i].element()).equals(s)) {
    			k = i;
    			break;
    		}
    	} 

		boolean visited[] = new boolean[V]; 

		LinkedList<Object> queue = new LinkedList<Object>(); 

        visited[k]=true; 
        queue.add(s); 
        LinkedList<Object> temp = new LinkedList<Object>();
        
		while (queue.size() != 0) 
		{ 
			s = (String) queue.poll();
			int tmp = 0;
			for(int t=0; t<V; t++) {
				if(((String)adj[t].element()).equals(s)) {
					tmp = t;
				}
			}

			Iterator<Object> i = adj[tmp].listIterator(); 
            while (i.hasNext()) 
            { 
                String c = (String) i.next();
                int n = 0;
                for(int j=0; j<V;j++) {
                	if(((String)adj[j].element()).equals(c)) {
                		n=j;
                		break;
                	}
                }
                if (!visited[n]) 
                { 
                    visited[n] = true; 
                    
                    int t=0;
                    Iterator<Object> iterator = temp.listIterator();
                    
                    while(iterator.hasNext()) {
                    	String ch = (String) iterator.next();
                    	
                    	if(ch.length() < c.length()) {
                    		String str = "";
                    		for(int flg=0; flg<ch.length(); flg++) {
                    			str = str + ch.charAt(flg);
                    			if(ch.charAt(flg) < c.charAt(flg)){
                    				t++;
                    				break;
                    			}
                    			else if(str.equals(ch)) {
                    				t++;
                    				break;
                    			}
                    		}
                    	}
                    	else if(ch.length() == c.length()) {
                    		for(int flg=0; flg<ch.length(); flg++) {
                    			if(ch.charAt(flg) < c.charAt(flg)){
                    				t++;
                    				break;
                    			}
                    		}
                    	}
                    	else {
                    		String str = "";
                    		for(int flg=0; flg<c.length(); flg++) {
                    			if(ch.charAt(flg) < c.charAt(flg)){
                    				t++;
                    				break;
                    			}
                    		}
                    	}
                    }
                    temp.add(t, c);
                }
            }
            // temp의 인자들은 바로바로 추가되는 것이 아니라 queue가 모두 비워졌을 때 즉,
            // 같은 깊이에 있는 노드들이 모두 탐색되었을 때 queue에 새로이 추가된다.
            if((queue.size() == 0) && temp.size() != 0) {
            	while(temp.size()!=0) {
                	queue.add(temp.removeFirst());
                }
            	depth++; // 하나의 깊이를 모두 탐색했으므로 depth의 값을 반환한다.
            }
		}
		System.out.println(depth); // 결과 출력
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.nextLine();
		String[] num = n.split(" ");
		
		
		n = sc.nextLine();
		String[] nodeName = n.split(" ");
		
		Bitcoin bitcoin = new Bitcoin(Integer.parseInt(num[0]), nodeName); 

		String[] tmp;
		for(int j=0;j<Integer.parseInt(num[1]);j++) {
			String s = sc.nextLine();
			tmp = s.split(" ");
			
			bitcoin.addEdge(tmp[0], tmp[1]);
		}
		
		n = sc.next();
		
		bitcoin.Search(n);
	}
}
