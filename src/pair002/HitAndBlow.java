package pair002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Hit & Blow
 * @author r-ogata, t-kase
 */
public class HitAndBlow {

	Pattern patten = Pattern.compile("^[0-9]{4}$");

	public static void main(String[] args) throws IOException {
		new HitAndBlow().execute();
	}

	private void execute() throws IOException {
		// ランダムで4桁作る
//		String answer = "1234";
		String answer = createAnswer();
		int c = 0;
		// 正解するまで無限ループ
		while (true) {
			int hc = 0, bc = 0;
			// 入力
			System.out.print("数値を入力してください：");
			String input = input();
			// ?を入力したら答えが見える
			if (input.equals("?")) {
				System.out.println("答え：" + answer);
				continue;
			}
			// 数値チェック
			if (!patten.matcher(input).matches()) {
				System.out.println("ERROR,もう一度入力しなさい");
				continue;
			}
			// 入力規則（重複チェック）
			Set<String> n = new HashSet<String>();
			for (int i = 0; i < input.length(); i++) {
				n.add(String.valueOf(input.charAt(i)));
			}
			if (n.size() != input.length()) {
				System.out.println("ERROR,もう一度入力しなさい");
				continue;
			}
			
			// 入力回数インクリメント
			c++;

			// inputとanswerの比較
			for (int i = 0; i < input.length(); i++) {
				for (int j = 0; j < answer.length(); j++) {
					// 数字が同じ場合
					if (input.charAt(i) == answer.charAt(j)) {
						// 場所が同じ場合HIT
						if (i == j) {
							hc++;
						} else {
							bc++;
						}
						break;
					}
				}
			}
			// 全部Hitだったら
			if (hc == 4) {
				System.out.println(hc + "H" + bc + "B 正解！！　(入力回数" + c + "回)");
				break;
			}
			// 0H0B
			System.out.println(hc + "H" + bc + "B");
			System.out.println("---");
		}
	}

	/**
	 * 答えのランダム4桁を作る
	 * 
	 * @return
	 */
	private String createAnswer() {
		Set<Integer> set = new HashSet<Integer>();
		while (set.size() < 4) {
			// ランダムな値をセットに追加
			set.add(new Random().nextInt(10));
		}
		String ret = "";
		for (Integer i : set) {
			ret += i;
		}
		return ret;
	}

	private String input() throws IOException {
		return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}
}
