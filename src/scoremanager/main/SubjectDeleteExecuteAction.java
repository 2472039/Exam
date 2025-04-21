package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	public void execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
		SubjectDAO dao = new SubjectDAO();
		Subject subject = (Subject)session.getAttribute("subject");

		boolean set = dao.delete(subject);

		// 更新が成功したら完了画面、失敗したらエラー画面を表示
		if (set) {
			request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}