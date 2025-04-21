//	入力された値を取得
//	DB更新
//	ページ遷移

package scoremanager.main;

//import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	public void execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
		SubjectDAO sDAO = new SubjectDAO();
		Subject subject = new Subject();

		// 入力された値を取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school_cd = teacher.getSchool();
		String inputCd = request.getParameter("cd");
		String inputName = request.getParameter("name");


		//	入力された値が空だった場合エラー画面を表示
		if (inputCd == null || inputCd.trim().isEmpty() ||
				inputName == null || inputName.trim().isEmpty()) {

			//	エラーメッセージをsessionに保存する
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
		}

		// 更新を実行
		subject.setSchool(school_cd);
		subject.setCd(inputCd);
		subject.setName(inputName);
		boolean b = sDAO.save(subject);

		// 更新が成功したら完了画面、失敗したらエラー画面を表示
		if (b) {
			request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
		}

	}
}