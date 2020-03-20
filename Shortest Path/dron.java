import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

class Node{
	private String key;
	private int wait;
	private boolean visited;
	
	public Node(String key, int wait) {
		this.key = key;
		this.wait = wait;
		this.visited = false;
	}
	
	public String key_() {
		return key;
	}
	public int wait_() {
		return wait;
	}
	public void visited(boolean v) {
		this.visited = v;
	}
	
	public boolean getVisit() {
		return visited;
	}
}

class Path {
	private String des;
	private int dis;
	
	public Path(String des, int dis) {
		this.des = des;
		this.dis = dis;
	}
	
	public String des() {
		return des;
	}
	public int dis() {
		return dis;
	}
}

class Status {
	private String node;
	private int dis;
	private String course;
	
	public Status(String node, int dis, String course) {
		this.node = node;
		this.dis = dis;
		this.course = course + node + " ";
	}
	
	public String node() {
		return node;
	}
	public int dis() {
		return dis;
	}
	public String course() {
		return course;
	}
}

public class dron {
	private int N;
	private int M;
	private static int count = 0;
	
	private Node adj[];
	private LinkedList<Path>[] path;
	
	public dron(int N, int M) {
		this.N = N;
		this.M = M;
		
		adj = new Node[N];
		path = new LinkedList[N];
	}
	
	public void node(String key, int wait) {
		if(N>count) {
			adj[count] = new Node(key, wait);
			path[count] = new LinkedList();
			path[count].add(new Path(key, 0));
			count++;
		}
	}
	
	public void setEdge(String start, String des, int dis) {	
		boolean flag = true;
		for(int i=0; i<N; i++) {
			if(path[i].element().des().equals(start)){
				Iterator<Path> iter = path[i].listIterator();
				while(iter.hasNext()) {
					if(iter.next().des().equals(des)) {
						flag = false;
						break;
					}
				}
				
				if(flag)
					path[i].add(new Path(des, dis));
			}
		}
		
		flag = true;
		for(int i=0; i<N; i++) {
			if(path[i].element().des().equals(des)){
				Iterator<Path> iter = path[i].listIterator();
				while(iter.hasNext()) {
					if(iter.next().des().equals(start)) {
						flag = false;
						break;
					}
				}
				
				if(flag)
					path[i].add(new Path(start, dis));
			}
		}
	}
	
	public void search(String start, String destination) {
		LinkedList<Status> desList = new LinkedList();
		int index=0;
		for(int i=0; i<N; i++) {
			if(path[i].element().des().equals(start)) {
				index = i;
				break;
			}
		}
		
		adj[index].visited(true);
		
		LinkedList<Status> queue = new LinkedList();
		queue.add(new Status(path[index].element().des(), 0, ""));
		while(queue.size() != 0) {
			Status tmp = queue.poll();
			
			if(tmp.node().equals(destination)) {
				Iterator<Status> iter = desList.listIterator();
				int i = 0;
				while(iter.hasNext()) {
					if(tmp.dis() > iter.next().dis())
						i++;
				}
				desList.add(i, tmp);
			}
			else {
				for(int i=0;i<N;i++) {
					if(tmp.node().equals(adj[i].key_())) {
						adj[i].visited(true);
						break;
					}
				}
				
				int tmpIndex = -1;
				for(int i=0;i<N;i++) {
					if(path[i].element().des().equals(tmp.node())) {
						tmpIndex = i;
						break;
					}
				}
				
				Iterator<Path> iter = path[tmpIndex].listIterator();
				while(iter.hasNext()) {
					Path p = iter.next();
					int pIndex = -1;
					for(int i=0;i<N;i++) {
						if(p.des().equals(adj[i].key_())) {
							pIndex = i;
							break;
						}
					}
					
					if(!adj[pIndex].getVisit()) {
						int queueIndex = 0;
						Iterator<Status> i = queue.listIterator();
						while(i.hasNext()) {
							Status queueElement = i.next();
							
							if(tmp.node().equals(start)) {
								if(p.dis()+tmp.dis() >= queueElement.dis())
									queueIndex++;
							}
							else {
								if(p.dis()+tmp.dis() + adj[tmpIndex].wait_() >= queueElement.dis())
									queueIndex++;
							}
						}
						if(tmp.node().equals(start)) {
							queue.add(queueIndex,new Status(p.des(), p.dis()+tmp.dis(), tmp.course()));
						}
						else {
							queue.add(queueIndex,new Status(p.des(), p.dis()+tmp.dis() + adj[tmpIndex].wait_(), tmp.course()));
						} 
					}
				}
			}
		}
		System.out.println(desList.element().course().trim());
		System.out.println(desList.element().dis());
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		dron d;
		
		String nm_ = sc.nextLine();
		String[] nm__ = nm_.split(" ");
		int[] nm = new int[2];
		for(int i=0; i<2; i++) {
			nm[i] = Integer.parseInt(nm__[i]);
		}
		d = new dron(nm[0], nm[1]);
		
		String nodeList_ = sc.nextLine();
		String[] nodeList = nodeList_.split(" ");
		
		for(int i=0; i<nm[0]; i++) {
			String node_ = sc.nextLine();
			String[] node = node_.split(" ");
			
			d.node(node[0], Integer.parseInt(node[1]));
		}
		
		for(int i=0; i<nm[1]; i++) {
			String edge_ = sc.nextLine();
			String[] edge = edge_.split(" ");
			
			d.setEdge(edge[0], edge[1], Integer.parseInt(edge[2]));;
		}
		
		String start = sc.nextLine();
		String end = sc.nextLine();
		
		d.search(start, end);
	}
}
