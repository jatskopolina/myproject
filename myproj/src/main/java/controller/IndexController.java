package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CookieUtils;
import service.DBConnector;

@Controller
public class IndexController {
	@Autowired
	private DBConnector connector;
	@Autowired
	private CookieUtils cookieUtils;

	@RequestMapping(value = { "/", "index" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		System.out.println("------ INDEX CONTROLLER ------");
		// if here is an exit parameter it means the user has just logged out
		// so i dont have to authorise him
		try {
			if (request.getSession().getAttribute("exit") != null) {
				return "index";
			}
			String[] array = cookieUtils.getUserCookies(request);
			String userName = array[0];
			String userPass = array[1];
			if (("").equals(userName) || ("").equals(userPass)) {
				System.out.println("No userName or userPass, I cant authorise you");
			} else {
				String dataPass = connector.findPassword(userName);
				if (dataPass.equals(userPass)) {
					System.out.println("I found the data to authorise you!");
					request.getSession().setAttribute("name", userName);
					request.getSession().setAttribute("password", userPass);
					request.getSession().setAttribute("remember", "true");
					return "redirect:login";
				} else {
					System.out.println("Your cookies are incorrect, sorry..");
				}
			}
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}

}