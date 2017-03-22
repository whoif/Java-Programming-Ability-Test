import java.util.*;

public class Visit {
    public int countPath(int[][] map, int n, int m) {

		// find A and B
		int ax, ay, bx, by;
		ax = ay = bx = by = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					ax = i;
					ay = j;
				} else if (map[i][j] == 2) {
					bx = i;
					by = j;
				}
			}
		}

		// normalize the matrix
		int hx, hy, lx, ly;
		hx = Math.max(ax, bx);
		hy = Math.max(ay, by);
		lx = Math.min(ax, bx);
		ly = Math.min(ay, by);
		if ((ax - bx) * (ay - by) < 0) {
			// reverse (left-right) the matrix
			for (int i = lx; i <= ly; i++) {
				for (int j = ly; j <= (ly + hy) / 2; j++) {
					int tmp = map[i][j];
					map[i][j] = map[i][ly + hy - j];
					map[i][ly + hy - j] = tmp;
				}
			}
		}

		int cnt[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cnt[i][j] = 0;
			}
		}
		cnt[lx][ly] = 1;

		// fill the 1st row
		for (int i = lx + 1; i <= hx; i++) {
			if (cnt[i][ly] == -1)
				break;
			cnt[i][ly] = 1;
		}

		// fill the 1st column
		for (int j = ly + 1; j <= hy; j++) {
			if (cnt[lx][j] == -1)
				break;
			cnt[lx][j] = 1;
		}

		for (int i = lx + 1; i <= hx; i++) {
			for (int j = ly + 1; j <= hy; j++) {
				if (cnt[i][j] == -1) {
					cnt[i][j] = 0;
					continue;
				}
				cnt[i][j] = cnt[i - 1][j] + cnt[i][j - 1];
			}
		}
		return cnt[hx][hy];
	}
}
