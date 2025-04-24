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
import bean.Test;

public class TestDAO extends DAO {

    private String baseSql="select * from student where school_cd=?";
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        // インスタンスを初期化
        Test test = new Test();
        // データベースへのコネクションを確立
        Connection connection = getConnection();
        // プリペアドステートメント
        PreparedStatement statement = null;
        try {
            // プリペアドステートメントにSQLをセット
            statement = connection.prepareStatement("select * from test where student_no=? and subject_cd=? and school_cd=? and no=?");
            // プリペアドステートメントに引数をバインド
            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
            statement.setInt(4,no);
            // プリペアドステートメントを実行
            ResultSet rSet = statement.executeQuery();
            // 学校Daoを初期化
            SchoolDAO sDao = new SchoolDAO();
            StudentDAO stDao = new StudentDAO();
            SubjectDAO sbDao = new SubjectDAO();
            if (rSet.next()) {
	            // インスタンスを作成
	        	test = new Test();
	            test.setStudent(stDao.get(rSet.getString("student_no")));
	            test.setSubject(sbDao.get(rSet.getString("subject_cd"), school));
	            test.setSchool(sDao.get(rSet.getString("school_cd")));
	            test.setNo(rSet.getInt("no"));
	            test.setPoint(rSet.getInt("point"));
	            test.setClass_num(rSet.getString("class_num"));

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
		return test;
	}
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        // リストを初期化
        List<Test> list = new ArrayList<>();
        try {
            // リザルトセットを全件走査
        	SchoolDAO sDao = new SchoolDAO();
            StudentDAO stDao = new StudentDAO();
            SubjectDAO sbDao = new SubjectDAO();
            while (rSet.next()) {
                // インスタンスを初期化
            	Test test = new Test();
	            test.setStudent(stDao.get(rSet.getString("student_no")));
	            test.setSubject(sbDao.get(rSet.getString("subject_cd"), school));
	            test.setSchool(sDao.get(rSet.getString("school_cd")));
	            test.setNo(rSet.getInt("no"));
	            test.setPoint(rSet.getInt("point"));
	            test.setClass_num(rSet.getString("class_num"));

                // リストに追加
                list.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Test> filter (int ent_year, String class_num, int no, Subject subject, School school) throws Exception {
        // リストを初期化
        List<Test> list = new ArrayList<>();

        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        try {
        	//  入学年度、クラス、学生番号、氏名、点数
        	statement = connection.prepareStatement(
        			"select * from student join test on student.no = test.student_no "
        			+ "and student.ent_year = ? and student.class_num = ? and test.no = ? "
        			+ "and test.subject_cd = ? and test.school_cd = ?");

            // プリペアドステートメントにバインド
        	statement.setInt(1, ent_year);
        	statement.setString(2, class_num);
            statement.setInt(3, no);
            statement.setString(4, subject.getCd());
            statement.setString(5, school.getCd());

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
    public boolean save(List<Test> list) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;

        int totalCount = 0;

        try {
            for (Test test : list) {

            	Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());


                if (old == null) {
                    statement = connection.prepareStatement(
                        "insert into test(student_no, subject_cd, school_cd, no, point, class_num) values(?, ?, ?, ?, ?, ?)"
                    );
                    statement.setString(1, test.getStudent().getNo());
                    statement.setString(2, test.getSubject().getCd());
                    statement.setString(3, test.getSchool().getCd());
                    statement.setInt(4, test.getNo());
                    statement.setInt(5, test.getPoint());
                    statement.setString(6, test.getClass_num());
                } else {
                	continue;
                }

                totalCount += statement.executeUpdate();

                if (statement != null) {
                    statement.close(); // 各ループで閉じる（同じstatement再利用してもOK）
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return totalCount > 0;
    }
    public boolean save(Test test) throws Exception {
    	Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;
        try {
        	statement = connection.prepareStatement(
        			"update test set point=? where student_no=? and subject_cd=? and school_cd=? and no=?"
        			);
        	statement.setInt(1, test.getPoint());
        	statement.setString(2, test.getStudent().getNo());
        	statement.setString(3, test.getSubject().getCd());
        	statement.setString(4, test.getSchool().getCd());
        	statement.setInt(5, test.getNo());

        	// プリペアドステートメントを実行
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
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
}
