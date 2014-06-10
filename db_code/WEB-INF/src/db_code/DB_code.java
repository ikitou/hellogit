package db_code;

import java.sql.*;

//filmsテーブルの内容を全て表示する
public class DB_code{
	//JDBCドライバ名
	private static final String DRIVER_NAME = "org.postgresql.Driver";

	//データベース接続URL
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

	//データベース接続先
	private static final String NAME = "postgres";

	//データベース接続パスワード
	private static final String PASS = "postgres";

	//メインメソッド
	public static void main(String[] args) {
		//DB接続先の設定
		Connection conn = null;
		//ステートメントオブジェクト変数(DBへクエリ用)の宣言
		PreparedStatement pstmt = null;
		//検索結果が格納されるオブジェクト変数の宣言
		ResultSet rs = null;

		try{
			/**
			 * JDBCをjavaVM上に読み込む
			 * もし、読み込みが失敗した場合、ClassNotFoundException例外が発生
			 */
			Class.forName(DRIVER_NAME);
			/**
			 * DBサーバへのアクセス定義
			 * アクセスが成功した場合、Connectionクラスのconnメンバ変数にConnectionクラス
			 * のオブジェクトが返される。
			 */
			conn = DriverManager.getConnection(URL,NAME,PASS);
			/**
			 * ConnectionオブジェクトのprepareStatementメソッドにDBへのクエリ文を代入する。
			 * 成功時にはprepareStatement処理内部でPreparedStatementオブジェクトが渡される。
			 */
			pstmt = conn.prepareStatement("select * from films");
			/**
			 * PreparedStatementオブジェクトのexecuteQueryを実行し、結果をResultSetオブジェクト
			 * に渡される。
			 * 結果は、配列で渡され、Row単位で保存される。
			 */
			rs = pstmt.executeQuery();

			//カラム名
			System.out.println("key¥ttest¥tdata");

			//検索結果を取り出す
			while(rs.next()){
				//ResultSetオブジェクトのnextメソッドを実行し、1行取得する
				String s1 = rs.getString(1);
				String s2 = rs.getString(2);
				int s3 = rs.getInt(3);
				System.out.println(s1 + "¥t" + s2 + "¥t" + s3);
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(conn != null){
					conn.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
