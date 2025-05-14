//	入力された学生番号を取得
//	学生の詳細データを取得
//	クラスの一覧を取得
//	update.jspに遷移

package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateAction extends Action {
	public void execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
		StudentDAO dao = new StudentDAO();
		Student student = new Student();
		int ent_year = 0;

		// 入力された値を取得
		String no = request.getParameter("no");


		//	入力された値が空だった場合エラー画面を表示
		if (no == null) {

			//	エラーメッセージをsessionに保存する
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}

		//	詳細データ取得
		student = dao.get(no);
		ent_year = student.getEnt_year();
		session.setAttribute("student", student);
		session.setAttribute("no", no);
		session.setAttribute("ent_year", ent_year);

		request.getRequestDispatcher("/scoremanager/main/student_update.jsp").forward(request, response);

	}
}