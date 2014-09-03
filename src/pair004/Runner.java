package pair004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Hit & Blow
 * @author t-kitajima, y-sakakibara
 */
public class Runner {

	/**
	 * 答えの桁数
	 */
	private static int MAX_COLUMN_NUMBER = 4;

	/**
	 * ランダムに生成された答えを保持している配列
	 */
	private static List<String> answer = new ArrayList<>();

	private static boolean isDebug = true;
	private static boolean isEnd = false;

	static List<String> allowedInputList;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> list = new ArrayList<>();

		// 答えの生成
		list = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
		allowedInputList = new ArrayList<String>(list);
		Collections.shuffle(list);

		for (int i = 0; i < MAX_COLUMN_NUMBER; i++) {
			answer.add(list.get(i));
		}

		// ゲームのターン数
		int turn = 1;

		while (!isEnd) {

			if (isDebug) {
				System.out.println(answer.toString());
				System.out.println("turn:" + turn);
			}

			// Hitの保持
			int hit = 0;
			// Blowの保持
			int blow = 0;

			String input;
			try {
				input = input();
			} catch (IOException e) {
				System.out.println("入力時にエラーが発生しました。");
				continue;
			}

			if (input.length() != MAX_COLUMN_NUMBER) {
				System.out.println("入力は" + MAX_COLUMN_NUMBER + "桁で入力してください。");
				continue;
			}

			if (!isValidString(input)) {
				System.out.println("入力できる文字は" + allowedInputList + "です");
				continue;
			}

			// 重複チェック　あとでやる
			if (!isDuplicated(input)) {
				System.out.println("同じ文字は入力できません");
				continue;
			}

			// 合っているかチェック
			for (int i = 0; i < input.length(); i++) {
				for (int j = 0; j < answer.size(); j++) {
					if (answer.get(j).equals(String.valueOf(input.charAt(i)))) {
						if (j == i) {
							// Hit
							hit++;
						} else {
							// Blow
							blow++;
						}
					}
				}
			}

			turn++;

			// 結果出力
			String conclusion = hit + "H" + blow + "B";
			if (hit == MAX_COLUMN_NUMBER) {
				// 終了
				System.out.println("正解:" + conclusion + turn + "回目で正解");
				isEnd = true;
			} else {
				// 継続
				System.out.println(conclusion);
			}
		}

	}

	/**
	 * 重複チェック
	 * 
	 * @param input
	 * @return
	 */
	private static boolean isDuplicated(String input) {
		List<String> list = new ArrayList<>();

		for (int i = 0; i < input.length(); i++) {
			String str = String.valueOf(input.charAt(i));
			for (String s : list) {
				if (s.equals(str)) {
					return false;
				}
			}
			list.add(str);
		}
		return true;
	}

	private static boolean isValidString(String input) {

		for (int i = 0; i < input.length(); i++) {
			if (!allowedInputList.contains(String.valueOf(input.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private static String input() throws IOException {
		System.out.print("入力してください:");
		return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

}
