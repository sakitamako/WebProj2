package com.diworksdev.webproj2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diworksdev.webproj2.util.DBConnector;

public class HelloStrutsDAO {

	//クラス、メソッドの定義
	//boolean型を最後に呼び出し元に渡すので、boolean型を戻り値にしたメソッドを作る
	public boolean select() {
		boolean ret = false;

		//DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		//Connectionは特定のデータベースとの接続
		DBConnector db = new DBConnector();

		//getConnectionの呼び出し（DBと接続する）
		Connection con = db.getConnection();

		//sql文を書く
		//SELECT データを抽出する
		//＊ テーブルに含まれる項目全て
		//FROM 〇〇 〇〇という名前のテーブルからデータを選択する
		String sql = "select * from users";

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			//PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			PreparedStatement ps = con.prepareStatement(sql);

			//executeQuery()/executeUpdate()で実行
			//（select文の場合はexectuteQuery()を使う）
			// select文のSQL文を実行するメソッドで戻り値はResultSet
			ResultSet rs = ps.executeQuery();

			//ここでは2つのことをしている
			//下に1行ずらすこと
			//データが存在していれば戻り値を true で返す・存在しなければ falseで返す
			if (rs.next()) {
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
			con.close();

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//retに入った値を呼び出し元であるActionクラスに渡す
		return ret;
	}

}
