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
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
	public void execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
		StudentDAO sDAO = new StudentDAO();
		Teacher teacher = (Teacher) session.getAttribute("user");
		Student student = new Student();
		Map<String, String> errors = new HashMap<>();

		// 入力された値を取得
		String inputNo = request.getParameter("inputNo");
		String inputName = request.getParameter("inputName");
		String inputEntYear = request.getParameter("inputEntYear");
		String inputClassNum = request.getParameter("inputClassNum");
		request.setAttribute("inputNo", inputNo);
		request.setAttribute("inputName", inputName);
		request.setAttribute("inputEntYear", inputEntYear);
		request.setAttribute("inputClassNum", inputClassNum);

//		System.out.println(inputEntYear);
		if (inputEntYear.equals("0")) {
			errors.put("f3","入学年度を選択してください");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("student_create.jsp").forward(request, response);
			return;
		}
		//	入力された値が空だった場合エラー画面を表示
		if (inputNo == null || inputNo.trim().isEmpty() ||
				inputName == null || inputName.trim().isEmpty() || inputClassNum == null) {

			//	エラーメッセージをsessionに保存する
			request.getRequestDispatcher("student_create.jsp").forward(request, response);
			return;
		}

		//	入力された学生番号が重複していた場合
		//	DBに登録されている学生番号を取得し、ひとつずつ比較する
		Student s = sDAO.get(inputNo);
		if (s != null) {
			//	エラーを表示
			errors.put("f4","学生番号が重複しています");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("student_create.jsp").forward(request, response);
			return;
		}

		//	String型で取得したIDをint型に変換、できなかったらエラー画面を表示
		int inputEnt_Year = 0;
		try {
			inputEnt_Year = Integer.parseInt(inputEntYear);
		} catch(Exception e) {
			request.getRequestDispatcher("student_create.jsp").forward(request, response);
			return;
		}

		// 更新を実行
		student.setNo(inputNo);
		student.setName(inputName);
		student.setEnt_year(inputEnt_Year);
		student.setClass_num(inputClassNum);
		student.setSchool(teacher.getSchool());
		student.setAttend(true);
		boolean b = sDAO.save(student);

		// 更新が成功したら完了画面、失敗したらエラー画面を表示
		if (b) {
			request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("student_create.jsp").forward(request, response);
		}

	}
}