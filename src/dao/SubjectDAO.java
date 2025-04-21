package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
public class SubjectDAO extends DAO {

	public Subject get(String cd, School school) throws Exception {
		Subject subject = new Subject();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
            // プリペアドステートメントにSQLをセット
            statement = connection.prepareStatement("select * from subject where cd=?");
            // プリペアドステートメントに引数をバインド
            statement.setString(1, cd);
//            statement.setString(2, school.getCd());
            // プリペアドステートメントを実行
            ResultSet rSet = statement.executeQuery();
            // 学校Daoを初期化
            SchoolDAO sDao = new SchoolDAO();
            if (rSet.next()) {
	            // 科目番号インスタンスを作成
	        	subject = new Subject();
	            subject.setCd(rSet.getString("cd"));
	            subject.setName(rSet.getString("name"));
	            subject.setSchool(sDao.get(rSet.getString("school_cd")));
	        } else {
	            // レコードが存在しない場合はnullをセット
	            subject = null;
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
	    }
		return subject;
	}

    public List<Subject> filter(School school) throws Exception {
	    // リストを用意
	    List<Subject> list = new ArrayList<>();

	    // データベースのコネクションを作成
	    Connection connection = getConnection();

	    // プリペアードステートメントを用意
	    PreparedStatement statement = null;

	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement(
	            "select * from subject where school_cd=? order by cd"
	        );

	        // プリペアードステートメントに値をバインド
	        statement.setString(1, school.getCd());

	        // プリペアードステートメントを実行
	        ResultSet set = statement.executeQuery();

	        // レコードを読み込む
	        while (set.next()) {
	        	Subject subject = new Subject();
	        	subject.setCd(set.getString("cd"));
	        	subject.setName(set.getString("name"));
	        	subject.setSchool(school);
	            // 科目番号をリストに追加
	            list.add(subject);
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
    public boolean save(Subject subject) throws Exception {

        // コネクションを確立

        Connection connection = getConnection();

        // プリペアードステートメント

        PreparedStatement statement = null;

        // 実行件数

        int count = 0;

        try {

            // データベースから科目を取得

        	Subject old = get(subject.getCd(), subject.getSchool());

            if (old == null) {

                // 学生が存在しなかった場合

                // プリペアドステートメントにINSERT文をセット

                statement = connection.prepareStatement(

                    "insert into subject(school_cd,cd, name) values(?, ?, ?)"
                );
                // プリペアドステートメントに値をバインド
                statement.setString(1, subject.getSchool().getCd());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getName());
            } else {
                // 科目が存在した場合
                // プリペアドステートメントにUPDATE文をセット
                statement = connection.prepareStatement(
                    "update subject set name=? where cd=?"
                );
                // プリペアドステートメントに値をバインド
                statement.setString(1, subject.getName());
                statement.setString(2, subject.getCd());
            }
            // プリペアドステートメントを実行
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアドステートメントを閉じる
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
        if (count > 0) {
            // 実行件数が1件以上ある場合
            return true;
        } else {
            // 実行件数が0件の場合
            return false;
        }
    }
    public boolean delete(Subject subject) throws Exception {

        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // 実行件数
        int count = 0;
        try {
        	statement = connection.prepareStatement(
                "delete from subject where cd = ?");
            // プリペアドステートメントに値をバインド
            statement.setString(1, subject.getCd());
            count = statement.executeUpdate();
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // プリペアドステートメントを閉じる
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
	    if (count > 0) {
	        // 実行件数が1件以上ある場合
	        return true;
	    } else {
	        // 実行件数が0件の場合
	        return false;
	    }}
}