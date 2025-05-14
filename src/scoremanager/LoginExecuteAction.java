package scoremanager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginExecuteAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {
		HttpSession session = req.getSession();

		String id = req.getParameter("id");
		String password = req.getParameter("password");
		Teacher teacher = new Teacher();
		TeacherDAO tDAO = new TeacherDAO();
		Map<String, String> errors = new HashMap<>();

		teacher = tDAO.login(id, password);

		if (teacher != null) {
			session.setAttribute("user", teacher);
			req.getRequestDispatcher("main/menu.jsp").forward(req, res);
		} else {
			errors.put("f2", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
			req.setAttribute("errors", errors);
			req.setAttribute("id", id);
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
	}
}