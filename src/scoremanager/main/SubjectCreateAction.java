package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectCreateAction extends Action {
@Override
public void execute(
	HttpServletRequest req, HttpServletResponse res
) throws Exception {


	req.getRequestDispatcher("/scoremanager/main/subject_create.jsp").forward(req, res);
}
}
