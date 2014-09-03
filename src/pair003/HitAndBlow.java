package pair003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hit & Blow
 * @author s-shingo, s-nagatomo
 */
public class HitAndBlow {

	public static void main(String args[]){
		/**
		 * 1.4桁のランダムな数字を生成する
		 * 2.重複チェック
		 * 3.コンソール入力
		 * 4.重複チェック
		 * 5.答と入力値を精査する
		 * 6.結果表示→外れなら3．
		 * 　　　　　→当たりなら入力回数を表示
		 */

		List<Integer> numList = new ArrayList<>();
		List<Integer> ansList = new ArrayList<>();
		List<Integer> inputList = new ArrayList<Integer>();
		Random rnd = new Random();
		for (int j = 0; j < 10; j++) {
			numList.add(j);
		}
		int elementNum = 10;
//		int[] numList = {0,1,2,3,4,5,6,7,8,9};
		for (int i = 0; i <= 3; i++ ) {
			int rndNum = rnd.nextInt(elementNum);
			int num = numList.get(rndNum);
			numList.remove(rndNum);
			elementNum--;
			ansList.add(num);
		}
		int index;
		int trun = 1;

		boolean isEnd = false;

		do {
			// 入力データ取得
			index = getInputIndex();
			inputList =checkInputNum(index);
			// 重複チェック、四桁チェック
			int count = 0;
			int hit = 0;
			int blow = 0;
			for (int num : ansList) {
				if(num == inputList.get(count)){
					hit++;
				} else if (inputList.contains(num)) {
					blow++;
				}
				count++;

			}
			System.out.println("[" +hit +", H, "+ blow + ", B]");
			if(hit > 3){
				System.out.println(trun +"回目で成功しました。");
				System.out.println("生成された回答 : " + ansList);
				isEnd =  true;
			}
			trun++;
		} while (!isEnd);

	}
	/**
	 * 	入力された数値のチェック
	 *
	 * */
	private static List<Integer> checkInputNum(int inputNum){
		String inputNumStr = Integer.toString(inputNum);
		if (inputNumStr.length() == 3) {
			inputNumStr = "0" + inputNumStr;
		}
		List<Integer> returnArray = new ArrayList<>();

		if(inputNumStr.length() != 4){
			System.out.println("4桁の数値で入力してください");
			return null;
		}
		String[] inputNumStrArray =inputNumStr.split("");

		for(String str : inputNumStrArray){
			if(str.length() > 0){
				int index = Integer.parseInt(str);
				for(Integer checkIndex : returnArray){
					if(checkIndex == index){
						System.out.println(index + "は、重複した数値です");
						return null;
					}
				}
				returnArray.add(index);
			}
		}

		System.out.println(returnArray);
		return returnArray;
	}
	/**
	 * 入力した数値を取得
	 */
	private static int getInputIndex() {

		System.out.println("4桁の数値を入力してください。");

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String buf = "";
		int index;
		try {
			buf = br.readLine();
			index = Integer.parseInt(buf);
		} catch (Exception e) {
			System.out.println("入力エラー");
			return -1;
		}
		return index;
	}

}
