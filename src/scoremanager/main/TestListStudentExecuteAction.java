package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDAO;
import dao.TestListStudentDAO;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {

		// sessionからユーザー情報を取得
		HttpSession session=req.getSession();

		StudentDAO sDAO = new StudentDAO();
		TestListStudentDAO tDAO = new TestListStudentDAO();

		// 入力された値を取得
		String studentNo = req.getParameter("student_no");
		Student student = sDAO.get(studentNo);

		List<TestListStudent> list = tDAO.filter(student);

		session.setAttribute("student", student);
		session.setAttribute("testliststudent", list);

		// フォワード
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);


	}
}