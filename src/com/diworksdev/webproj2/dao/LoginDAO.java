package com.diworksdev.webproj2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diworksdev.webproj2.util.DBConnector;

public class LoginDAO {

	public String username;
	public String password;

	//クラス、メソッドの定義
	//boolean型を最後に呼び出し元に渡すので、boolean型を戻り値にしたメソッドを作る
	public boolean select(String username, String password) {
		boolean ret = false;

		//DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		//Connectionは特定のデータベースとの接続
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		//sql文を書く
		//SELECT データを抽出する
		//＊ テーブルに含まれる項目全て
		//FROM 〇〇 〇〇という名前のテーブルからデータを選択する
		//WHERE ＜条件＞抽出条件を指定
		//usersに入っているデータ、user_name=? password=?に入る2つの条件を満たしたデータがsqlに代入される。
		//?はプレースホルダと言って、その都度違うデータを入れて使用する。
		//［例］user_name="taro" password="123"とした場合は太朗と１２３しかデータを抽出することができなくなる。
		String sql = "select * from users where user_name=? and password=?";

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			//PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			PreparedStatement ps = con.prepareStatement(sql);

			//sql文の?に入れる値をsetする
			ps.setString(1, username);
			ps.setString(2, password);

			//executeQuery()/executeUpdate()で実行
			//（select文の場合はexectuteQuery()を使う）
			// select文のSQL文を実行するメソッドで戻り値はResultSet
			ResultSet rs = ps.executeQuery();

			//ここでは2つのことをしている
			//下に1行ずらすこと
			//データが存在していれば戻り値を true で返す・存在しなければ falseで返す
			if (rs.next()) {

				//結果の処理（select文で取得した値をDTOに格納）
				//select文でDBから取得した情報をString型に変換してDTOクラスに格納
				//LoginDTOクラスのsetName、setPassword（setter）を利用
				this.username = rs.getString("user_name");
				this.password=rs.getString("password");

				ret = true;
			}

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch (SQLException e) {
			e.printStackTrace();

		}

		//try.catchはjavaの例外処理のための構文
		try {

			//⑨con.close()で接続を切る
			//データベースとの接続をクローズ
			//これをしないとデータベースを接続したまま作業が実行されてしまってメモリに負荷がかかる
			con.close();

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch (SQLException e) {
			e.printStackTrace();

		}

		//戻り値
		//処理結果を返す
		return ret;

	}


}
