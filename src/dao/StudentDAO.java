package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDAO extends DAO {

    private String baseSql="select * from student where school_cd=?";

    public Student get(String no) throws Exception {
        // 学生インスタンスを初期化
        Student student = new Student();
        // データベースへのコネクションを確立
        Connection connection = getConnection();
        // プリペアドステートメント
        PreparedStatement statement = null;
        try {
            // プリペアドステートメントにSQLをセット
            statement = connection.prepareStatement("select * from student where no=?");
            // プリペアドステートメントに引数をバインド
            statement.setString(1, no);
            // プリペアドステートメントを実行
            ResultSet rSet = statement.executeQuery();
            // 学校Daoを初期化
            SchoolDAO schoolDAO = new SchoolDAO();
            if (rSet.next()) {
                // リザルトセットが存在する場合
                // 学生インスタンスに値をセット
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEnt_year(rSet.getInt("ent_year"));
                student.setClass_num(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
                // 学校コードに基づき学校を取得し学生にセット
                student.setSchool(schoolDAO.get(rSet.getString("school_cd")));
            } else {
                // リザルトセットが存在しない場合
                // 学生インスタンスにnullをセット
                student = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // リソースを解放する
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
        return student;
    }
    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        // リストを初期化
        List<Student> list = new ArrayList<>();
        try {
            // リザルトセットを全件走査
            while (rSet.next()) {
                // 学生インスタンスを初期化
                Student student = new Student();
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEnt_year(rSet.getInt("ent_year"));
                student.setClass_num(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
                student.setSchool(school);

                // リストに追加
                list.add(student);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Student> filter(School school, int ent_year, String class_num, boolean is_attend) throws Exception {
        // リストを初期化
        List<Student> list = new ArrayList<>();

        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;

        // SQL文の条件
        String condition = " and ent_year=? and class_num=?";
        // SQL文のソート
        String order = " order by no asc";

        // SQL文の在学フラグ条件
        String conditionIsAttend = "";
        // 在学フラグがtrueの場合
        if (is_attend) {
            conditionIsAttend = " and is_attend=true";
        }

        try {
            // プリペアドステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            // プリペアドステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアドステートメントに入学年度をバインド
            statement.setInt(2, ent_year);
            // プリペアドステートメントにクラス番号をバインド
            statement.setString(3, class_num);
            // プリペアドステートメントを実行
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
    public List<Student> filter(School school, int ent_year, boolean is_attend) throws Exception {
    	List<Student> list = new ArrayList<>();

        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;

        // SQL文の条件
        String condition = " and ent_year=?";
        // SQL文のソート
        String order = " order by no asc";

        // SQL文の在学フラグ条件
        String conditionIsAttend = "";
        // 在学フラグがtrueの場合
        if (is_attend) {
            conditionIsAttend = " and is_attend=true";
        }
        try {
            // プリペアドステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            // プリペアドステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアドステートメントに入学年度をバインド
            statement.setInt(2, ent_year);
            // プリペアドステートメントを実行
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

    public List<Student> filter(School school, boolean is_attend) throws Exception {
    	List<Student> list = new ArrayList<>();

        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        String order = " order by no asc";
        String conditionIsAttend ="";
        if (is_attend) {
        	conditionIsAttend="and is_attend=true";
        }
        try {
            // プリペアドステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql +  conditionIsAttend + order);
            // プリペアドステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアドステートメントを実行
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

    public boolean save(Student student) throws Exception {

        // コネクションを確立

        Connection connection = getConnection();

        // プリペアードステートメント

        PreparedStatement statement = null;

        // 実行件数

        int count = 0;

        try {

            // データベースから学生を取得

            Student old = get(student.getNo());


            if (old == null) {
            	System.out.println("no");
                // 学生が存在しなかった場合

                // プリペアドステートメントにINSERT文をセット

                statement = connection.prepareStatement(

                    "insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)"
                );
                // プリペアドステートメントに値をバインド
                statement.setString(1, student.getNo());
                statement.setString(2, student.getName());
                statement.setInt(3, student.getEnt_year());
                statement.setString(4, student.getClass_num());
                statement.setBoolean(5, student.isAttend());
                statement.setString(6, student.getSchool().getCd());
            } else {
            	System.out.println("yes");
                // 学生が存在した場合
                // プリペアドステートメントにUPDATE文をセット
                statement = connection.prepareStatement(
                    "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?"
                );
                // プリペアドステートメントに値をバインド
                statement.setString(1, student.getName());
                statement.setInt(2, student.getEnt_year());
                statement.setString(3, student.getClass_num());
                statement.setBoolean(4, student.isAttend());
                statement.setString(5, student.getNo());
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
}
