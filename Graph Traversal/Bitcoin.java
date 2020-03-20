import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/** 
 * BitcoinŬ�����̴�
 * ������ BFS�� ���� �����ϴ�.
 * �ٸ� ���̶�� �ʺ�켱 Ž���̳� ���̿켱 Ž���� �ƴ� ���� Ž���̶�� ���̴�.
 * Ž������ �޶����ٰ�� ������ �ڵ�� �޶��� �κ��� depth��� �ʵ尡 �߰��� �Ͱ�
 * temp��� ��ũ�帮��Ʈ�� ������������ ����� ������ queue�� ����Ǵ� ������ ���� �� ���̴�.
 * ���� ��ü���� ������ �ռ� ������ BFS�� �����ϵ��� �ϰ� �޶��� �κп� ���ؼ��� �ּ����� ��Ÿ������ �ϰڴ�.
 * 
 *  
 * depth�ʵ�� �ִ� ���̸� �ǹ��� ���̴�.
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
     * ���� Ž���� �̷������ �޼����� �̸��� ���ϰ� Search��� �����ϰڴ�.
     * @param s �߽��� �Ǵ� ���
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
            // temp�� ���ڵ��� �ٷιٷ� �߰��Ǵ� ���� �ƴ϶� queue�� ��� ������� �� ��,
            // ���� ���̿� �ִ� ������ ��� Ž���Ǿ��� �� queue�� ������ �߰��ȴ�.
            if((queue.size() == 0) && temp.size() != 0) {
            	while(temp.size()!=0) {
                	queue.add(temp.removeFirst());
                }
            	depth++; // �ϳ��� ���̸� ��� Ž�������Ƿ� depth�� ���� ��ȯ�Ѵ�.
            }
		}
		System.out.println(depth); // ��� ���
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
