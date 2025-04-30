//入力された値を取得
//DB更新
//ページ遷移

package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
public void execute(
		HttpServletRequest request, HttpServletResponse response
) throws Exception {

	HttpSession session = request.getSession();
	StudentDAO sDAO = new StudentDAO();
	Teacher teacher = (Teacher) session.getAttribute("user");
	Student student = new Student();

	//	sessionから値を取得
	String no = (String)session.getAttribute("no");
	int ent_year = (Integer) session.getAttribute("ent_year");


	// 入力された値を取得
	String inputName = request.getParameter("inputName");
	String inputClassNum = request.getParameter("inputClassNum");
	String inputIsAttend = request.getParameter("isAttend");
	boolean isAttend;
	if (inputIsAttend == null) {
		isAttend = false;
	} else {
		isAttend = true;
	}


	//	入力された値が空だった場合エラー画面を表示
	if (inputName == null || inputName.trim().isEmpty() ||
			inputClassNum == null) {

		//	エラーメッセージをsessionに保存する
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	// 更新を実行
	student.setNo(no);
	student.setName(inputName);
	student.setEnt_year(ent_year);
	student.setClass_num(inputClassNum);
	student.setSchool(teacher.getSchool());
	student.setAttend(isAttend);
	boolean b = sDAO.save(student);

	// 更新が成功したら完了画面、失敗したらエラー画面を表示
	if (b) {
		request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
	} else {
		request.getRequestDispatcher("student_update.jsp").forward(request, response);
	}

}
}