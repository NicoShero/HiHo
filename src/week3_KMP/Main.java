package week3_KMP;
import java.util.Scanner;

public class Main {

	static int[]	fail;

	static void init(String word) {
		fail = new int[word.length()];
		fail[0] = -1;
		for (int i = 0, j = -1, len = word.length(); i < len - 1; ++i, ++j) {
			while (j != -1 && word.charAt(j) != word.charAt(i)) {
				j = fail[j];
			}
			fail[i + 1] = j + 1;
		}
	}

	static int solve(String pattern, String text) {
		init(pattern);
		int res = 0;
		for (int i = 0, j = 0, len = text.length(); i < len; ++i, ++j) {
			while (j != -1 && pattern.charAt(j) != text.charAt(i)) {
				j = fail[j];
			}
			if (j == pattern.length() - 1) {
				res++;
				j = fail[j];
				while (j != -1 && pattern.charAt(j) != text.charAt(i)) {
				    j = fail[j];
			    }
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = Integer.parseInt(in.nextLine());
		for (int caseId = 1; caseId <= cases; ++caseId) {
			String pattern = in.nextLine();
			String text = in.nextLine();
			System.out.println(solve(pattern, text));
		}
		in.close();
	}
}
