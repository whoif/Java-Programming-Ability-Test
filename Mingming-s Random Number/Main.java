import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int num = in.nextInt();
			SortedSet<Integer> s = new TreeSet<Integer>();
			for (int i = 0; i < num; i++) {
				s.add(in.nextInt());
			}
			for (Integer i : s) {
				System.out.println(i);
			}
		}
		in.close();
	}
}
