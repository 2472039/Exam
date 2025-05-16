//	test_regist.jspから送られた値を取得
//	その値を使ってフィルター
//	フィルターした結果をリスト化し、sessionに保存
//	test_regist.jspへフォワード


package scoremanager.main;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.Class_numDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;
public class TestRegistAction extends Action {
	@Override
	public void execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {

		HttpSession session=req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		Subject subject= new Subject();
		School school = teacher.getSchool();;
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		Class_numDAO classNumDao = new Class_numDAO();
		SubjectDAO subjectDao = new SubjectDAO();
		TestDAO testDao = new TestDAO();

		String entYearStr = req.getParameter("f1");
		String class_num = req.getParameter("f2");
		String subject_cd = req.getParameter("f3");
		String noStr = req.getParameter("f4");

		subject = subjectDao.get(subject_cd,school);



		int entYear = 0; // デフォルト値
		int no = 0; // デフォルト値

		if(entYearStr != null || class_num != null || subject_cd != null || noStr != null){
			if (!entYearStr.isEmpty()&& !noStr.isEmpty()) {
		    try {
		        entYear = Integer.parseInt(entYearStr);
		        no = Integer.parseInt(noStr);
		    	}catch(Exception e){

		    	}
			}
			List<Test> test = testDao.filter(entYear, class_num, no,subject, school);
			session.setAttribute("entYear", entYear);
			session.setAttribute("class_num", class_num);
			session.setAttribute("subject", subject);
			session.setAttribute("subject_cd", subject_cd);
			session.setAttribute("no", no);
			session.setAttribute("test",test);
		}
//		} else {
//			List<Test> test = testDao.filter();
//		}
		// クラス番号リスト
		List<String> class_num_list = classNumDao.filter(school);
		// 科目リスト
		List<Subject> subject_list = subjectDao.filter(school);
		// 入学年度のリスト
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		// sessionに保存
		session.setAttribute("class_num_set", class_num_list);
		session.setAttribute("subject_list", subject_list);
		session.setAttribute("ent_year_set", entYearSet);
		req.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(req, res);
	}
}




