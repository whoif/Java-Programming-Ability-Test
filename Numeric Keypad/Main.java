import java.util.*;

public class Main {
	static ArrayList<ArrayList<Integer>> lookup;
	static int current;
	static int last;
	static int lastat;
	static int lastis;

	public static void main(String[] args) {
		setLookup();
		Scanner in = new Scanner(System.in);
		int t = Integer.parseInt(in.nextLine());
		for (int i = 0; i < t; i++) {
			String str = in.nextLine();
			String result = "";
			if (str.length() == 1) {
				System.out.println(str);
				continue;
			}
			lastat = 0;
			lastis = -1;
			current = 1;
			for (int j = 0; j < str.length(); j++) {

				int now = Integer.parseInt(str.substring(j, j + 1));
				int res = moveTo(current, now);

				// position changed
				if ((res != -1 && res != current) || j == 0) {
					last = current;
					lastat = j;
					lastis = now;
					current = res;
				}
				
				// move to max-less-than ex:37
				if (res != -1 && res < now) {
					result += res;
					int times = str.length() - result.length();
					for (int k = 0; k < times; k++) {
						if (res == 0)
							result += 0;
						if (res != 0)
							result += 9;
					}
					break;
				}
				
				// backtrack
				if (res == -1) {
					result = result.substring(0, lastat);
					result += moveTo(last, current - 1);
					int times = str.length() - result.length();
					for (int k = 0; k < times; k++) {
						result += 9;
					}
					break;
				} else {
					result += res;
				}
			}
			System.out.println(result);
		}
		in.close();
	}

	public static int moveTo(int posi, int now) {
		List<Integer> ls = lookup.get(posi);
		int max = -1;
		for (Integer i : ls) {
			if (i <= now && i > max) {
				max = i;
			}
		}
		return max;
	}

	public static void setLookup() {
		lookup = new ArrayList<ArrayList<Integer>>(10);
		for (int i = 0; i < 10; i++) {
			lookup.add(new ArrayList<Integer>());
		}
		lookup.get(0).add(0);

		lookup.get(1).add(0);
		lookup.get(1).add(1);
		lookup.get(1).add(2);
		lookup.get(1).add(3);
		lookup.get(1).add(4);
		lookup.get(1).add(5);
		lookup.get(1).add(6);
		lookup.get(1).add(7);
		lookup.get(1).add(8);
		lookup.get(1).add(9);

		lookup.get(2).add(2);
		lookup.get(2).add(3);
		lookup.get(2).add(5);
		lookup.get(2).add(6);
		lookup.get(2).add(8);
		lookup.get(2).add(9);
		lookup.get(2).add(0);

		lookup.get(3).add(3);
		lookup.get(3).add(6);
		lookup.get(3).add(9);

		lookup.get(4).add(4);
		lookup.get(4).add(5);
		lookup.get(4).add(6);
		lookup.get(4).add(7);
		lookup.get(4).add(8);
		lookup.get(4).add(9);
		lookup.get(4).add(0);

		lookup.get(5).add(5);
		lookup.get(5).add(6);
		lookup.get(5).add(8);
		lookup.get(5).add(9);
		lookup.get(5).add(0);

		lookup.get(6).add(6);
		lookup.get(6).add(9);

		lookup.get(7).add(7);
		lookup.get(7).add(8);
		lookup.get(7).add(9);
		lookup.get(7).add(0);

		lookup.get(8).add(8);
		lookup.get(8).add(9);
		lookup.get(8).add(0);

		lookup.get(9).add(9);
	}
}
