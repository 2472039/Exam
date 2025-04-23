package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {

    private String baseSql="select subject.name, test.subject_cd, test.no, test.point "
    		+ "from test join subject on test.subject_cd = subject.cd where student_no = ?"
    		+ "order by subject.name";
        private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        // リストを初期化
        List<TestListStudent> list = new ArrayList<>();
        try {
            // リザルトセットを全件走査
            while (rSet.next()) {
                // インスタンスを初期化
            	TestListStudent tls = new TestListStudent();
//            	String subject_cd = rSet.getString("subject_cd");

	            tls.setSubjectName(rSet.getString("name"));
	            tls.setSubjectCd(rSet.getString("subject_cd"));
	            tls.setNum(rSet.getInt("no"));
	            tls.setPoint(rSet.getInt("point"));

                // リストに追加
                list.add(tls);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<TestListStudent> filter (Student student) throws Exception {
        // リストを初期化
        List<TestListStudent> list = new ArrayList<>();

        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        try {
        	//	studentを使って科目名、科目コード、回数、点数を取得しTestListStudentに、それをリスト化
        	statement = connection.prepareStatement(baseSql);

            // プリペアドステートメントに入学年度をバインド
        	statement.setString(1, student.getNo());
            rSet = statement.executeQuery();
            list = postFilter(rSet);
        }catch (Exception e) {
        	throw e;
        }finally {
        	if (statement != null) {
        		try {
        		statement.close();
	        	} catch (SQLException sqle) {
	        		throw sqle;
	        	}
	        }
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
}