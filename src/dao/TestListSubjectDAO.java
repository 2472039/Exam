package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDAO extends DAO {

    private String baseSql="select student.ent_year, student.class_num, student.no, "
    		+ "student.name, group_concat(test.no), group_concat(test.point) from "
    		+ "student join test on student.no = test.student_no where student.ent_year = ? "
    		+ "and student.class_num = ? and test.subject_cd  = ? and test.school_cd = ? "
    		+ "group by student.no";
        private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        // リストを初期化
        List<TestListSubject> list = new ArrayList<>();

        try {
            // リザルトセットを全件走査
            while (rSet.next()) {
                // インスタンスを初期化
            	TestListSubject tls = new TestListSubject();
	            tls.setEntYear(rSet.getInt("ent_year"));
	            tls.setStudentNo(rSet.getString("no"));
	            tls.setStudentName(rSet.getString("name"));
	            tls.setClassNum(rSet.getString("class_num"));
	            String[] testNos = rSet.getString(5).split(",");
	            String[] testPoints = rSet.getString(6).split(",");

	            Map<Integer, Integer> points = new HashMap<>();
	            for (int i = 0; i < testNos.length; i++) {
	                points.put(Integer.parseInt(testNos[i]), Integer.parseInt(testPoints[i]));
	            }
	            tls.setPoints(points);

                // リストに追加
                list.add(tls);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<TestListSubject> filter (int entYear, String classNum, Subject subject, School school) throws Exception {
        // リストを初期化
        List<TestListSubject> list = new ArrayList<>();

        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        try {
        	//   ほしいのは入学年度,クラス, 学生番号, 氏名, 1回目, 2回目の点数
        	statement = connection.prepareStatement(baseSql);

            // プリペアドステートメントに入学年度をバインド
        	statement.setInt(1, entYear);
        	statement.setString(2, classNum);
        	statement.setString(3, subject.getCd());
        	statement.setString(4, school.getCd());
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