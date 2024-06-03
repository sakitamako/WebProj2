package com.diworksdev.webproj2.action;

import com.diworksdev.webproj2.dao.HelloStrutsDAO;
import com.opensymphony.xwork2.ActionSupport;

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
public class HelloStrutsAction extends ActionSupport {

	//メソッド名は「execute」
	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {

		//メソッドの戻り値「ret」 String ret = ERROR; を定義し、初期値としてERRORを代入
		String ret = ERROR;

		//②DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		//Connectionは特定のデータベースとの接続
		HelloStrutsDAO dao = new HelloStrutsDAO();

		//boolean は true （真）か false （偽）の値のみを取れる論理データ型
		//HelloStrutsDAOクラスのselectメソッドを呼び出す
		boolean b = dao.select();

		//もしbとtrueが等しい時、ret=SUCCESS
		if (b == true) {
			ret = SUCCESS;

		//それ以外の場合、ret=ERROR
		} else {
			ret = ERROR;
		}

		//戻り値
		//処理を終了し、呼び出し元に処理を戻す
		return ret;
	}

}
