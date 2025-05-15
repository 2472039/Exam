//	入力された値を取得
//	DB更新
//	ページ遷移

package scoremanager.main;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDAO;
import tool.Action;

public class TestRegistExecuteAction extends Action {
	public void execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
		TestDAO tDAO = new TestDAO();
		Teacher teacher = (Teacher) session.getAttribute("user");
		Test test = new Test();
		Student student = new Student();
		Subject subject = new Subject();
		Map<String, String> errors = new HashMap<>();


		int  inputEntYear = (Integer)session.getAttribute("entYear");
		String inputClassNum = (String) session.getAttribute("class_num");
		String inputSubjectCd = (String) session.getAttribute("subject_cd");
		int inputNo = (Integer) session.getAttribute("no");
		String studentNo = (String) request.getSession().getAttribute("studentNo");

		// 入力された値を取得
		String inputPoint = request.getParameter("point");


		if (inputEntYear == 0 || inputSubjectCd == null || inputNo == 0 ||  inputClassNum == null) {
			errors.put("f8", "入学年度とクラスと科目と回数を選択してください");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("test_regist.jsp").forward(request, response);
		return;
		}

	//	String型で取得したIDをint型に変換、できなかったらエラー画面を表示
		int input_Point = 0;
		try {
			input_Point = Integer.parseInt(inputPoint);
		} catch(Exception e) {
			System.out.println(inputPoint);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}


		// 更新を実行
		student.setEnt_year(inputEntYear);
		test.setClass_num(inputClassNum);
		student.setNo(studentNo);
		subject.setCd(inputSubjectCd);
		test.setPoint(input_Point);
		test.setNo(inputNo);
		test.setStudent(student);
		test.setSubject(subject);
		test.setSchool(teacher.getSchool());
		boolean b = tDAO.save(test);

		// 更新が成功したら完了画面、失敗したらエラー画面を表示
		if (b) {
			session.removeAttribute("entYear");
			session.removeAttribute("class_num");
			session.removeAttribute("subject_cd");
			session.removeAttribute("no");
			session.removeAttribute("test");
			request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("test_regist.jsp").forward(request, response);
		}

	}
}