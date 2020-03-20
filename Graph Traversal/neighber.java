import java.util.Scanner;
/**
 * neigh클래스는 노드를 의미한다.
 * @author KNoA
 *
 */
class neigh{
	String key; // 이 노드의 key값
	String nodeList[]; // 이노드의 이웃 목록
	int neighNum = 0; // 이 노드의 이웃 수
	
	/**
	 * 생성자
	 * @param k 노드의 key값을 입력받는다.
	 * @param n 가질 수 있는 이웃의 수 (최대 수를 의미한다.)
	 */
	public neigh(String k, int n) {
		this.key = k;
		nodeList = new String[n];
	}
	
	/**
	 * 이웃을 추가하는 메서드이다.
	 * @param s 추가할 이웃 노드
	 */
	public void putNeigh(String s) {
		boolean flag = true;
		// 이미 이웃인 노드인 경우는 flag가 false로 바뀌며 새로이 추가되지 않는다.
		for(int i=0; i<neighNum; i++) {
			if(nodeList[i].equals(s)) {
				flag = false;
				return;
			}
		}
		// flag가 true라면 아직 이웃으로 설정되지 않은 노드임을 의미하므로 이웃으로 추가하고 neighNum을 늘린다.
		if(flag == true) {
			nodeList[neighNum] = s;
			neighNum++;
		}
	}
	/**
	 * key값을 반환하는 메서드
	 * @return 반환하려는 key값
	 */
	public String key() {
		return key;
	}
	/**
	 * 이웃의 수를 반환하는 메서드
	 * @return neighNum값을 반환한다.
	 */
	public int num() {
		return neighNum;
	}
}

public class neighber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.nextLine();
		String[] num = n.split(" ");
		
		n = sc.nextLine();
		String[] nodeName = n.split(" ");
		
		neigh[] neighList = new neigh[Integer.parseInt(num[0])];
		for(int i=0; i<Integer.parseInt(num[0]); i++) {
			neighList[i] = new neigh(nodeName[i], Integer.parseInt(num[1]));
		}
		
		String[] tmp;
		for(int j=0;j<Integer.parseInt(num[1]);j++) {
			String s = sc.nextLine();
			tmp = s.split(" ");
			
			for(int k=0;k<neighList.length;k++) {
				if(neighList[k].key().equals(tmp[0])) {
					neighList[k].putNeigh(tmp[1]);
				}
				if(neighList[k].key().equals(tmp[1])) {
					neighList[k].putNeigh(tmp[0]);
				}
			}
		}
		
		n = sc.next();
		
		for(int a=0;a<neighList.length;a++) {
			if(neighList[a].key().equals(n)) {
				System.out.println(neighList[a].num());
			}
		}
	}
}
