package pair001;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Hit & Blow
 * @author k-koide, k-kunita
 */
public class HitAndBlow {

	private String answer = null;

	public static void main(String[] args) throws Exception {
		new HitAndBlow().execute();
	}

	private void execute() throws Exception {
		int selectCount = 0;
		// 正解を生成する。
		System.out.println("Hit&Blowを始めます。");
		createAnswer();
		System.out.println(answer);

		// 入力
		System.out.println("入力オナシャス");
		String input = prompt();

		// 入力チェック（できたら）

		// HIT & BLOW を生成する。
		System.out.println("あなたの選んだ数字は正解に対して" + getHitAndBlow(input));

	}

	private void createAnswer() {

		Random rdm = new Random();

		while (true) {
			int fourDigitNum = rdm.nextInt(10000);
			// 4桁0埋め
			answer = String.format("%04d", fourDigitNum);

			if (checkDuplicate(answer)) {
				break;
			}
		}
	}

	private String getHitAndBlow(String inputNum) {
		char[] inputNumArray = inputNum.toCharArray();
		char[] answerArray = answer.toCharArray();

		int hitCount = 0;
		int blowCount = 0;

		for (int i = 0; i < inputNumArray.length; i++) {
			char c = inputNumArray[i];
			for (int j = 0; j < answerArray.length; j++) {
				char f = answerArray[j];
				if (c == f) {
					if (i == j) {
						hitCount++;
					} else {
						blowCount++;
					}
				}
			}
		}

		return String.format("%dH%dB", hitCount, blowCount);

	}

	// 重複チェック
	private boolean checkDuplicate(String num) {
		char[] charArray = num.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			char c1 = charArray[i];
			for (int j = i + 1; j < charArray.length; j++) {
				char c2 = charArray[j];
				if (c1 == c2) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 標準入力から文字列を取得する。
	 *
	 * @return 文字列
	 * @throws Exception
	 *             例外
	 */
	private String prompt() throws Exception {

		return new BufferedReader(new InputStreamReader(System.in)).readLine();

	}

}
