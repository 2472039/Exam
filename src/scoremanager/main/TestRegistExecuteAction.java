//	入力された値を取得
//	DB更新
//	ページ遷移

package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.Class_numDAO;
import dao.TestDAO;
import tool.Action;

public class TestRegistExecuteAction extends Action {
	public void execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
		TestDAO tDAO = new TestDAO();
		Class_numDAO cNumDAO = new Class_numDAO();
		Teacher teacher = (Teacher) session.getAttribute("user");
		Test test = new Test();
		Student student = new Student();
		Subject subject = new Subject();

		// 入力された値を取得
		String inputStudentNo = request.getParameter("inputStudentNo");
		String inputSubjectCd = request.getParameter("inputSubjectCd");
		String inputNo = request.getParameter("inputNo");
		String inputPoint = request.getParameter("Point");
		String inputClassNum = request.getParameter("inputClassNum");


		//	入力された値が空だった場合エラー画面を表示
		if (inputStudentNo == null || inputStudentNo.trim().isEmpty() ||
				inputSubjectCd == null || inputSubjectCd.trim().isEmpty() ||
				inputNo == null || inputPoint == null ||inputClassNum == null) {

			//	エラーメッセージをsessionに保存する
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}

		//	入力された学生番号が重複していた場合
		//	DBに登録されている学生番号を取得し、ひとつずつ比較する
		List<String> nos = cNumDAO.filter(teacher.getSchool());
		for (String no  : nos) {
			if (inputStudentNo.equals(no)) {
				//	エラーを表示
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
		}

		//	String型で取得したIDをint型に変換、できなかったらエラー画面を表示
		int input_No = 0;
		try {
			input_No = Integer.parseInt(inputNo);
		} catch(Exception e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		int input_Point = 0;
		try {
			input_Point = Integer.parseInt(inputPoint);
		} catch(Exception e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}

		// 更新を実行
		student.setNo(inputStudentNo);
		subject.setCd(inputSubjectCd);
		test.setStudent(student);
		test.setSubject(subject);
		test.setNo(input_No);
		test.setPoint(input_Point);
		test.setClass_num(inputClassNum);
		test.setSchool(teacher.getSchool());
		List<Test> list = new ArrayList<>();
		list.add(test);
		boolean b = tDAO.save(list);

		// 更新が成功したら完了画面、失敗したらエラー画面を表示
		if (b) {
			request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("test_regist.jsp").forward(request, response);
		}

	}
}
