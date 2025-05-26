package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.Class_numDAO;
import dao.StudentDAO;
import tool.Action;

public class StudentListAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {

		HttpSession session=req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String entYearStr = "";
		String classNum = "";
		String isAttendStr = "";
		int entYear = 0;
		boolean isAttend = false;
		List<Student> students = null;
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		StudentDAO sDAO = new StudentDAO();
		Class_numDAO cNumDAO = new Class_numDAO();
		Map<String, String> errors = new HashMap<>();

//		requestから値を取得
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttendStr = req.getParameter("f3");
		if (isAttendStr != null) {
			isAttend = true;
		}
		System.out.println(isAttendStr);

		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}

		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		List<String> list = cNumDAO.filter(teacher.getSchool());

		//	入力された条件により分岐し検索
		if (entYear != 0 && classNum != null && !"0".equals(classNum)) {
			students = sDAO.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && "0".equals(classNum)) {
			students = sDAO.filter(teacher.getSchool(), entYear, isAttend);
		} else if ((entYear != 0 && classNum == null) || (entYear == 0 && "0".equals(classNum))) {
			students = sDAO.filter(teacher.getSchool(), isAttend);
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度もしてください");
			req.setAttribute("errors", errors);
			students = sDAO.filter(teacher.getSchool(), isAttend);
		}

		//	sessionに値を保存
		session.setAttribute("f1", entYear);
		session.setAttribute("f2", classNum);
		session.setAttribute("f3", isAttend);
		session.setAttribute("students", students);
		session.setAttribute("class_num_set", list);
		session.setAttribute("ent_year_set", entYearSet);

		req.getRequestDispatcher("student_list.jsp").forward(req, res);
	}
}