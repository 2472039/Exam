//	sessionからuser情報を取得
//	入学年度とクラスの一覧を取得し、sessionに保存
//	student_create.jspに遷移

package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class StudentCreateAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {

		HttpSession session=req.getSession();

		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 10; i++) {
			entYearSet.add(i);
		}

		session.setAttribute("ent_year_set", entYearSet);

		req.getRequestDispatcher("/scoremanager/main/student_create.jsp").forward(req, res);
	}
}