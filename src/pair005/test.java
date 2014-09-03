package pair005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Hit & Blow
 * @author n-yusa, r-mitsui
 */
public class test{

	static List<String> RANDOMLIST;
	static List<String> ANSWERLIST;
	static int ANSWERNUM = 0;
	static boolean COMP = false;

	public static void main(String args[]) throws IOException {

		// TODO 入力チェックをしていないです。
		
		// 答えを生成
		createAnswer();

		while (true) {
		
		// 入力する
		String input = input();

		// 判定
		System.out.println(checkAnswer(input));
		
		// 4Hなら終わり
		if (COMP) {
			break;
		}
	}
		
	}
	
	/**
	 * 答えを生成
	 * @return
	 */
	private static List<String> createAnswer(){
		
		Random random = new Random();
		
		RANDOMLIST = new ArrayList<String>();
		
		while (true) {
			
			// ４桁入ったらループを抜ける
			if (RANDOMLIST.size() == 4) {
				break;
			}
			
			// 乱数生成
			 int r = random.nextInt(10);

			 // 既にリストにあったら追加しない
			 if (RANDOMLIST.contains(String.valueOf(r))){
				 continue;
			 }
			RANDOMLIST.add(String.valueOf(r));		
		}
		System.out.println("Answer : " + RANDOMLIST);
		return RANDOMLIST;
	}
	
	/**
	 * 入力する
	 * 
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	private static String input() throws IOException {
		System.out.print("Input : ");
		// 入力チェックは省略
		String input = new BufferedReader(new InputStreamReader(System.in)).readLine();		
		return input;
	}

	private static String checkAnswer(String input){

		String answer = "";
		int hit = 0;
		int blow = 0;
		
		ANSWERLIST = new ArrayList<String>();
		
		for(int i=0;i<4;i++){
			ANSWERLIST.add(input.substring(i, i+1));
		}

		for(int i = 0; i < ANSWERLIST.size() ; i++){
			
			// HIT しているかをチェック
			if (ANSWERLIST.get(i).equals(RANDOMLIST.get(i))){
				hit++;
			}else if(RANDOMLIST.contains(ANSWERLIST.get(i))){
				// HIT していなかったら、BLOWしているかチェック
				blow++;
			}
			// HIT　も　BLOW　も指定なかったら次の入力桁数のチェック（何もしない）			
		}
		// 回答数をカウント
		ANSWERNUM++;
		answer = hit + "H" + blow + "B";
		
		// 4Hなら正解フラグを立てる
		if(hit == 4){
			roopComp();
			answer += "正解！！" + "（入力回数: " + ANSWERNUM + ")";
		}
		
		return answer;
	}
	
	private static void roopComp(){
		COMP = true;
	}
}