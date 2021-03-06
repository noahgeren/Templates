package tech.noahgeren.template.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.noahgeren.template.services.UserService;

@Controller
public class BaseController implements ErrorController {
	
	@Autowired
	private UserService userService;

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "index";
	}
	
	@PostMapping("/register")
	@ResponseBody
	public Object register(HttpServletRequest request) {
		// TODO: Add registering here
		return null;
	}
	
	@RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // get error status
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // TODO: log error details here

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // display specific error page
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errorpages/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errorpages/500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "errorpages/403";
            }
        }

        // display generic error
        return "errorpages/error";
    }

}
