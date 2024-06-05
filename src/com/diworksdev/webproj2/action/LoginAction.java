package com.diworksdev.webproj2.action;

import com.diworksdev.webproj2.dao.LoginDAO;
import com.opensymphony.xwork2.ActionSupport;

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
public class LoginAction extends ActionSupport {

	//フィールド変数
	//JSPから受け取る値、ここではnameとpassword を定義
	//※必ずJSPでの定義と同じ名前にする
	private String username;
	private String password;

	//メソッド名は「execute」
	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {

		//メソッドの戻り値「ret」 String ret = ERROR; を定義し、初期値としてERRORを代入
		String ret = ERROR;

		//②DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		LoginDAO dao = new LoginDAO();

		//JSPから送られてきたnameとpasswordを引数として、
		//LoginDAOクラスのselectメソッドを呼び出す
		//その後、DAOで取得した結果をLoginDTOに代入する
		boolean b = dao.select(username, password);

		//もしbとtrueが等しい場合SUCCESS
		if (b == true) {
			ret=SUCCESS;

		//そうでない場合はERROR
		} else {
			ret=ERROR;

		}

		//retに入った値を呼び出し元であるActionクラスに渡す
		//戻り値
		return ret;

	}

	//getterは値を引っ張ってくるメソッド
	//メソッド＝オブジェクトの操作を定義したもの
	public String getUsername() {
		return username;

	}

	//setterは値を代入するメソッド
	//メソッド＝オブジェクトの操作を定義したもの
	public void setUsername(String username) {
		this.username = username;
	}

	//getterは値を引っ張ってくるメソッド
	//メソッド＝オブジェクトの操作を定義したもの
	public String getPassword() {
		return password;

	}

	//setterは値を代入するメソッド
	//メソッド＝オブジェクトの操作を定義したもの
	public void setPassword(String password) {
		this.password = password;

	}

}
