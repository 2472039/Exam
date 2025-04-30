package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteAction extends Action {
	public void execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		SubjectDAO dao = new SubjectDAO();
		Subject subject;

		// 入力された値を取得
		String cd = request.getParameter("no");

		//	入力された値が空だった場合エラー画面を表示
		if (cd == null) {
			//	エラーメッセージをsessionに保存する
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}

		//	詳細データ取得
		subject = dao.get(cd, teacher.getSchool());
		session.setAttribute("subject", subject);

		request.getRequestDispatcher("/scoremanager/main/subject_delete.jsp").forward(request, response);

	}
}