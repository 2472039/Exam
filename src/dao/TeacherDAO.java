package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDAO extends DAO {

    // ログイン機能を追加
    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM teacher WHERE id = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, password);
            ResultSet rSet = statement.executeQuery();

            SchoolDAO schoolDAO = new SchoolDAO();

            if (rSet.next()) {
                teacher = new Teacher();
                teacher.setId(rSet.getString("id"));
                teacher.setPassword(rSet.getString("password"));
                teacher.setName(rSet.getString("name"));
                teacher.setSchool(schoolDAO.get(rSet.getString("school_cd")));
                teacher.setAuthenticated(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            if (connection != null) try { connection.close(); } catch (SQLException sqle) { throw sqle; }
        }

        return teacher;
    }

    public Teacher get(String id) throws Exception {
        Teacher teacher = new Teacher();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM teacher WHERE id = ?");
            statement.setString(1, id);
            ResultSet rSet = statement.executeQuery();

            SchoolDAO schoolDAO = new SchoolDAO();

            if (rSet.next()) {
                teacher.setId(rSet.getString("id"));
                teacher.setPassword(rSet.getString("password"));
                teacher.setName(rSet.getString("name"));
                teacher.setSchool(schoolDAO.get(rSet.getString("school_cd")));
            } else {
                teacher = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            if (connection != null) try { connection.close(); } catch (SQLException sqle) { throw sqle; }
        }
        return teacher;
    }
}
