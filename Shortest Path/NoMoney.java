import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

class Block_{
	private char cost;
	
	public Block_(char cost) {
		this.cost = cost;
	}
	
	public char getCost() {
		return this.cost;
	}
}

class route_{
	private int need;
	private char key;
	
	private int row;
	private int col;
	
	public route_(char key, int need, int row, int col) {
		this.key = key;
		this.need = need;
		this.row = row;
		this.col = col;
	}
	
	public int need() {
		return need;
	}
	public char key() {
		return key;
	}
	
	public int row() {
		return row;
	}
	public int col() {
		return col;
	}
}

public class NoMoney {
	private int N;
	private int M;
	private int money;
	private static int count = 0;
	
	private Block_[][] map;
	
	public NoMoney(int n, int m, int money) {
		this.N = n;
		this.M = m;
		this.money = money;
		
		map = new Block_[N][M];
	}
	
	public void block(String line) {
		for(int i=0;i<line.length();i++) {
			map[count][i] = new Block_(line.charAt(i));
		}
		count++;
	}
	
	public void go() {
		int SIndexR = -1;
		int SIndexC = -1;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j].getCost() == 'S') {
					SIndexR = i;
					SIndexC = j;
					break;
				}
			}
		}
		
		LinkedList<route_> queue = new LinkedList();
		LinkedList<route_> reach = new LinkedList();
		
		boolean visit[][] = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				visit[i][j] = false;
			}
		}
		
		queue.add(new route_(map[SIndexR][SIndexC].getCost(), 0, SIndexR, SIndexC));
		while(queue.size() != 0) {
			route_ tmp = queue.poll();
			
			if(tmp.key() == 'E') {
				Iterator<route_> iter = reach.listIterator();
				
				int index = 0;
				while(iter.hasNext()) {
					if(tmp.need() > iter.next().need())
						index++;
				}
				reach.add(index, tmp);
			}
			
			else if(tmp.key() == 'W' || visit[tmp.row()][tmp.col()]) {}
			
			else if(!visit[tmp.row()][tmp.col()]) {
				visit[tmp.row()][tmp.col()] = true;
				Block_ Maap = map[tmp.row()][tmp.col()];
				
				int index = 0;
				int R = tmp.row()+1;
				int C = tmp.col();
				Block_ tmpMap = map[R][C];
				Iterator<route_> iter1 = queue.listIterator();
				while(iter1.hasNext()) {
					route_ check = iter1.next();
					if(check.need() < tmp.need()+Character.getNumericValue(Maap.getCost())) {
						index++;
					}
				}
				if(tmp.key() == 'S')
					queue.add(index, new route_(tmpMap.getCost(), tmp.need(), R, C));
				else
					queue.add(index, new route_(tmpMap.getCost(), tmp.need()+Character.getNumericValue(Maap.getCost()), R, C));
				
				index = 0;
				R = tmp.row();
				C = tmp.col()+1;
				tmpMap = map[R][C];
				Iterator<route_> iter2 = queue.listIterator();
				while(iter2.hasNext()) {
					route_ check = iter2.next();
					if(check.need() < tmp.need()+Character.getNumericValue(Maap.getCost())) {
						index++;
					}
				}
				if(tmp.key() == 'S')
					queue.add(index, new route_(tmpMap.getCost(), tmp.need(), R, C));
				else
					queue.add(index, new route_(tmpMap.getCost(), tmp.need()+Character.getNumericValue(Maap.getCost()), R, C));
				
				index = 0;
				R = tmp.row()-1;
				C = tmp.col();
				tmpMap = map[R][C];
				Iterator<route_> iter3 = queue.listIterator();
				while(iter3.hasNext()) {
					route_ check = iter3.next();
					if(check.need() < tmp.need()+Character.getNumericValue(Maap.getCost())) {
						index++;
					}
				}
				if(tmp.key() == 'S')
					queue.add(index, new route_(tmpMap.getCost(), tmp.need(), R, C));
				else
					queue.add(index, new route_(tmpMap.getCost(), tmp.need()+Character.getNumericValue(Maap.getCost()), R, C));
				
				index = 0;
				R = tmp.row();
				C = tmp.col()-1;
				tmpMap = map[R][C];
				Iterator<route_> iter4 = queue.listIterator();
				while(iter4.hasNext()) {
					route_ check = iter4.next();
					if(check.need() < tmp.need()+Character.getNumericValue(Maap.getCost())) {
						index++;
					}
				}
				if(tmp.key() == 'S')
					queue.add(index, new route_(tmpMap.getCost(), tmp.need(), R, C));
				else
					queue.add(index, new route_(tmpMap.getCost(), tmp.need()+Character.getNumericValue(Maap.getCost()), R, C));
			}
		}
		
		int money_left = money - reach.element().need();
		if(money_left >= 0) {
			System.out.println(money_left);
		}
		else {
			System.out.println("Not enough money!");
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		NoMoney m;
		
		String status_ = sc.nextLine();
		String status[] = status_.split(" ");
		m = new NoMoney(Integer.parseInt(status[0]),Integer.parseInt(status[1]),Integer.parseInt(status[2]));
		
		for(int i=0;i<Integer.parseInt(status[0]);i++) {
			String line = sc.nextLine();
			m.block(line);
		}
		m.go();
	}
}
