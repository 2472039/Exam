package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.Class_numDAO;
import dao.SubjectDAO;
import tool.Action;

public class TestListAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {

		// sessionからユーザー情報を取得
		HttpSession session=req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		Class_numDAO cNumDAO = new Class_numDAO();
		SubjectDAO SubDAO = new SubjectDAO();

		// クラス番号リスト
		List<String> class_num_list = cNumDAO.filter(teacher.getSchool());
		// 科目リスト
		List<Subject> subject_list = SubDAO.filter(teacher.getSchool());
		// 入学年度のリスト
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		// sessionに保存
		session.setAttribute("class_num_set", class_num_list);
		session.setAttribute("subject_list", subject_list);
		session.setAttribute("ent_year_set", entYearSet);

		// フォワード
		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}
}