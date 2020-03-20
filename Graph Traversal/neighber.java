import java.util.Scanner;
/**
 * neighŬ������ ��带 �ǹ��Ѵ�.
 * @author KNoA
 *
 */
class neigh{
	String key; // �� ����� key��
	String nodeList[]; // �̳���� �̿� ���
	int neighNum = 0; // �� ����� �̿� ��
	
	/**
	 * ������
	 * @param k ����� key���� �Է¹޴´�.
	 * @param n ���� �� �ִ� �̿��� �� (�ִ� ���� �ǹ��Ѵ�.)
	 */
	public neigh(String k, int n) {
		this.key = k;
		nodeList = new String[n];
	}
	
	/**
	 * �̿��� �߰��ϴ� �޼����̴�.
	 * @param s �߰��� �̿� ���
	 */
	public void putNeigh(String s) {
		boolean flag = true;
		// �̹� �̿��� ����� ���� flag�� false�� �ٲ�� ������ �߰����� �ʴ´�.
		for(int i=0; i<neighNum; i++) {
			if(nodeList[i].equals(s)) {
				flag = false;
				return;
			}
		}
		// flag�� true��� ���� �̿����� �������� ���� ������� �ǹ��ϹǷ� �̿����� �߰��ϰ� neighNum�� �ø���.
		if(flag == true) {
			nodeList[neighNum] = s;
			neighNum++;
		}
	}
	/**
	 * key���� ��ȯ�ϴ� �޼���
	 * @return ��ȯ�Ϸ��� key��
	 */
	public String key() {
		return key;
	}
	/**
	 * �̿��� ���� ��ȯ�ϴ� �޼���
	 * @return neighNum���� ��ȯ�Ѵ�.
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
