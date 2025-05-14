package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {

		// sessionからユーザー情報を取得
		HttpSession session=req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		School school = teacher.getSchool();
		int ent_year = 0;
		SubjectDAO sDAO = new SubjectDAO();
		TestListSubjectDAO tDAO = new TestListSubjectDAO();
		Map<String, String> errors = new HashMap<>();

		// 入力された値を取得
		String entYearStr = req.getParameter("ent_year");
		String class_num = req.getParameter("class_num");
		String subject_cd = req.getParameter("subject");
		Subject subject = sDAO.get(subject_cd, school);

		if (entYearStr == null || class_num == null || subject == null) {
			errors.put("f5","入学年度とクラスと科目を選択してください");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("test_list.jsp").forward(req, res);
			return;
		}else{
			ent_year = Integer.parseInt(entYearStr);

		}

		List<TestListSubject> list = tDAO.filter(ent_year, class_num, subject, school);

		session.setAttribute("entYearStr", entYearStr);
		session.setAttribute("classNum", class_num);
		session.setAttribute("subjectCd", subject_cd);
		session.setAttribute("subjectName", subject.getName());
		session.setAttribute("testlistsubject", list);

		// フォワード
		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);


	}
}
