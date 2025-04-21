package scoremanager.main;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.Class_numDAO;
import dao.SubjectDAO;
import tool.Action;

public class TestRegistAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {

		HttpSession session=req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		School school = teacher.getSchool();
		Class_numDAO classNumDao = new Class_numDAO();
		SubjectDAO subjectDao = new SubjectDAO();

		List<String> classNumSet = classNumDao.filter(school);
		List<Subject> subjectSet = subjectDao.filter(school);

		session.setAttribute("class_num_set", classNumSet);
		session.setAttribute("subject_set", subjectSet);

		req.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(req, res);
	}
}
