package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.Logger;

@Controller
public class LogoutController {
	@Autowired
	Logger logger;

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("------ LOGOUT CONTROLLER ------");
		try {
			HttpSession session = request.getSession();
			logger.logLogout((String) session.getAttribute("name"));
			if (session != null) {
				session.invalidate();
			}
			request.getSession().setAttribute("exit", "true");
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
		// ������������ ���: ���������� ������ ���������?
	}

}