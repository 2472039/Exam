package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {

    private String baseSql="select * from student where school_cd=?";
        private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        // リストを初期化
        List<TestListStudent> list = new ArrayList<>();
        try {
            // リザルトセットを全件走査
        	SchoolDAO sDao = new SchoolDAO();
            StudentDAO stDao = new StudentDAO();
            SubjectDAO sbDao = new SubjectDAO();
            while (rSet.next()) {
                // インスタンスを初期化
            	Subject subject = sbDao.get(rSet.getString("subject_cd"), school)
            	TestListStudent tls = new TestListStudent();
	            tls.setSubjectName(subject.getName());
	            tls.setSubjectCd(subject.getCd());
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
    public List<Test> filter (int ent_year, String class_num, int num, Subject subject, School school) throws Exception {
        // リストを初期化
        List<Test> list = new ArrayList<>();

        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        String order = " order by no asc";
        try {
        	statement = connection.prepareStatement(baseSql +  order);

            // プリペアドステートメントに入学年度をバインド
            statement.setInt(2, ent_year);
            // プリペアドステートメントにクラス番号をバインド
            statement.setString(3, class_num);
            statement.setInt(4, num);
            statement.setString(5, subject.getCd());
         // プリペアドステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery();
            list = postFilter(rSet, school);
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