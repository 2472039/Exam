// ユーザー情報を取得して、学校コードを取得
// 学校コードを使って、科目一覧を取得し、sessionに保存
// subject_list.jspへ

package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectListAction extends Action {

	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {

		HttpSession session=req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		SubjectDAO SubDAO = new SubjectDAO();

//		学校コードのリストを取得
		List<Subject> list = SubDAO.filter(teacher.getSchool());

//		sessionに保存
		session.setAttribute("subject_list", list);

		//	subject_list.jspへ
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);

	}
}