//入力された値を取得
//DB更新
//ページ遷移

package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
public void execute(
		HttpServletRequest request, HttpServletResponse response
) throws Exception {

	HttpSession session = request.getSession();
	SubjectDAO sDAO = new SubjectDAO();
	Teacher teacher = (Teacher) session.getAttribute("user");
	Subject subject = new Subject();
	Map<String, String> errors = new HashMap<>();

	//	sessionから値を取得
	Subject oldSubject = (Subject)session.getAttribute("subject");

	// 入力された値を取得
	String inputName = request.getParameter("name");


	//	入力された値が空だった場合エラー画面を表示
	if (inputName == null || inputName.trim().isEmpty()) {

		//	エラーメッセージをsessionに保存する
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	//変更内容をstudentに登録
	subject.setSchool(teacher.getSchool());
	subject.setCd(oldSubject.getCd());
	subject.setName(inputName);
	// 更新を実行
	boolean b = sDAO.save(subject);

	// 更新が成功したら完了画面、失敗したらエラー画面を表示
	if (b) {
		request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
	} else {
		errors.put("f2", "科目が存在していません");
		request.setAttribute("errors", errors);
		request.getRequestDispatcher("subject_update.jsp").forward(request, response);
	}

}
}