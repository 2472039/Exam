package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDAO extends DAO {

    // 学校コードを指定して学校インスタンスを取得する
    public School get(String code) throws Exception {
        School school = null;

        // コネクションの作成
        Connection connection = getConnection();

        // プリペアードステートメントの作成
        PreparedStatement statement = null;

        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("select * from school where cd = ?");
            // パラメータをプリペアードステートメントにセット
            statement.setString(1, code);
            // SQL文を実行
            ResultSet result = statement.executeQuery();

            // 1件目のレコードを読み込む
            if (result.next()) {
                // 学校インスタンスを作成
                school = new School();
                school.setCd(result.getString("cd"));
                school.setName(result.getString("name"));
            } else {
                // レコードが存在しない場合はnullをセット
                school = null;
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

        return school;
    }
}
