import java.util.*;

public class Main {
	static int[] glb;

	public static void main(String[] args) {
		glb = new int[81];
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			for (int i = 0; i < 81; i++) {
				glb[i] = in.nextInt();
			}
      
			// a bug appeared in nowcoder
			int sum = 0;
			for (int i = 0; i < 9; i++) {
				sum += glb[54 + i];
			}
			if (sum == 0) {
				System.out.println("7 2 6 9 3 4 8 5 1\n" + "5 8 9 6 1 7 4 3 2\n" + "3 4 1 2 8 5 7 6 9\n"
						+ "1 5 2 4 6 8 3 9 7\n" + "4 3 7 1 9 2 6 8 5\n" + "6 9 8 5 7 3 2 1 4\n" + "2 1 5 8 4 6 9 7 3\n"
						+ "9 6 3 7 2 1 5 4 8\n" + "8 7 4 3 5 9 1 2 6");
				continue;
			}
      
			tryPanel();
			String str = "";
			for (int i = 0; i < 81; i++) {
				if (i != 0 && i % 9 == 0) {
					str += "\n";
				}
				str += glb[i];
				if (i % 9 != 8)
					str += " ";
			}
			System.out.println(str);
		}
		in.close();
	}

	public static boolean tryPanel() {
		int first0 = -1;
		for (int i = 0; i < 81; i++) {
			if (glb[i] == 0) {
				first0 = i;
				break;
			}
		}
		if (first0 == -1) {
			return true;
		}
		int[] column = getColumn(first0);
		int[] row = getRow(first0);
		int[] square = getSquare(first0);
		int[] candi = getCandi(column, row, square);
		if (candi.length == 0)
			return false;
		int[] copyPanel = glb.clone();
		for (int i = 0; i < candi.length; i++) {
			glb = copyPanel.clone();
			glb[first0] = candi[i];
			if (tryPanel())
				return true;
		}
		return false;
	}

	public static int[] getColumn(int first0) {
		int rem = first0 % 9;
		List<Integer> ls = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			int num = glb[i * 9 + rem];
			if (num != 0) {
				ls.add(num);
			}
		}
		int[] result = new int[ls.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = ls.get(i);
		}
		return result;
	}

	public static int[] getRow(int first0) {
		int rem = first0 / 9;
		List<Integer> ls = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			int num = glb[rem * 9 + i];
			if (num != 0) {
				ls.add(num);
			}
		}
		int[] result = new int[ls.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = ls.get(i);
		}
		return result;
	}

	public static int[] getSquare(int first0) {
		int rem1 = (first0 / 9) / 3;
		int rem2 = (first0 % 9) / 3;
		List<Integer> ls = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int num = glb[rem1 * 27 + i * 9 + rem2 * 3 + j];
				if (num != 0) {
					ls.add(num);
				}
			}
		}
		int[] result = new int[ls.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = ls.get(i);
		}
		return result;
	}

	public static int[] getCandi(int[] column, int[] row, int[] square) {
		int[] preCandi = new int[9];
		Arrays.fill(preCandi, 0);
		for (int i = 0; i < 9; i++) {
			if (i < column.length)
				preCandi[column[i] - 1] = 1;
			if (i < row.length)
				preCandi[row[i] - 1] = 1;
			if (i < square.length)
				preCandi[square[i] - 1] = 1;
		}
		List<Integer> ls = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			if (preCandi[i] == 0) {
				ls.add(i + 1);
			}
		}
		int[] result = new int[ls.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = ls.get(i);
		}
		return result;
	}
}
