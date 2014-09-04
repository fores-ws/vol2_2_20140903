package pair006;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Hit & Blow
 * @author r-yamanaka, h-takemoto
 */
public class Main {

	private static String resultAnswer;
	private static int inputCount = 0;

	public static void main(String args[]) {
		String inputParam = "";
		// 解答を作成
		resultAnswer = createAnswer();
		System.out.println("resultAnswer:" + resultAnswer);
		boolean continueFlg = true;
		do {
			System.out.print("input integer: ");
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			try {
				String buf = br.readLine();
				inputParam = buf;
			} catch (Exception e) {
				inputParam = "0";
			}
			System.out.println("Input Number = " + inputParam);

			// 入力回数をカウント
			inputCount++;

			// hit数の取得
			int hitResult = getHitResult(inputParam);
			System.out.println("hitResult:" + hitResult);

			// brow数の取得
			int browResult = 0;
			if (hitResult != 4) {
				browResult = getBrowResult(inputParam, hitResult);
				System.out.println("browResult:" + browResult);
			}

			// 正解判定
			if (hitResult == 4) {
				continueFlg = false;
			}

			// 結果の出力
			System.out.println("結果：" + hitResult + "H" + browResult + "B");

		} while (continueFlg);
		System.out.println("入力回数：" + inputCount);
	}

	public static String createAnswer() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		// リストの内容をシャッフルします。（１回目）
		Collections.shuffle(list);
		int count = 0;
		String strAns = "";
		for (Integer num : list) {
			count++;
			strAns += num.toString();
			if (count == 4) {
				break;
			}
		}
		return strAns;
	}

	public static int getHitResult(String inputParam) {
		int hitResult = 0;
		for (int i = 0; i < 4; i++) {
			if (resultAnswer.substring(i, i + 1).equals(
					inputParam.substring(i, i + 1))) {
				hitResult++;
			}
		}
		return hitResult;
	}

	public static int getBrowResult(String inputParam, int hitResult) {
		int browResult = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (resultAnswer.substring(j, j + 1).equals(
						inputParam.substring(i, i + 1))) {
					browResult++;
				}
			}
		}
		return browResult - hitResult;
	}

}
