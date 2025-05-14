package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Class_num;
import bean.School;

public class Class_numDAO extends DAO {
// 取得したクラス番号、学校情報をもとにクラス情報を返すメソッドです。
	public Class_num get(String class_num, School school) throws Exception {

	    Class_num classNum = new Class_num();

	    // コネクションの作成
	    Connection connection = getConnection();

	    // プリペアードステートメントの作成
	    PreparedStatement statement = null;

	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement("select * from class_num where class_num = ? and school_cd = ?");
	        // パラメータをプリペアードステートメントにセット
	        statement.setString(1, class_num);
	        statement.setString(2, school.getCd());

	        // SQL文を実行
	        ResultSet rSet = statement.executeQuery();
	        // 1件目のレコードを読み込む
	        if (rSet.next()) {
	            // クラス番号インスタンスを作成
	        	classNum = new Class_num();
	            classNum.setClass_num(rSet.getString("class_num"));
	            classNum.setSchool(school);
	        } else {
	            // レコードが存在しない場合はnullをセット
	            classNum = null;
	        }

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // プリペアードステートメントを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }

	        // コネクションを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return classNum;
	}
	// 学校を指定してクラス番号の一覧を取得するメソッドです。
	public List<String> filter(School school) throws Exception {
	    // リストを用意
	    List<String> list = new ArrayList<>();

	    // データベースのコネクションを作成
	    Connection connection = getConnection();

	    // プリペアードステートメントを用意
	    PreparedStatement statement = null;

	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement(
	            "select class_num from class_num where school_cd=? order by class_num"
	        );

	        // プリペアードステートメントに値をバインド
	        statement.setString(1, school.getCd());

	        // プリペアードステートメントを実行
	        ResultSet set = statement.executeQuery();

	        // レコードを読み込む
	        while (set.next()) {
	            // クラス番号をリストに追加
	            list.add(set.getString("class_num"));
	        }

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // プリペアードステートメントを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }

	        // コネクションを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return list;
	}
	public boolean save(Class_num class_num) throws Exception {
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    int count = 0;

	    try {
	        // 既存のクラス情報を取得（存在確認）
	        Class_num old = get(class_num.getClass_num(), class_num.getSchool());

	        if (old == null) {
	            // 存在しない場合 → INSERT
	            statement = connection.prepareStatement(
	                "insert into class_num(class_num, school_cd) values(?, ?)"
	            );
	            statement.setString(1, class_num.getClass_num());
	            statement.setString(2, class_num.getSchool().getCd());
	        } else {
	            // 存在する場合 → UPDATE（更新内容があるなら）
	            statement = connection.prepareStatement(
	                "update class_num set school_cd=? where class_num=?"
	            );
	            statement.setString(1, class_num.getSchool().getCd());
	            statement.setString(2, class_num.getClass_num());
	        }

	        // SQL実行
	        count = statement.executeUpdate();

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // statementを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }

	        // connectionを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    // 実行件数が1以上 → true、そうでなければfalseを返す
	    return count > 0;
	}
}