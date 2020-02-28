package application;

import service.LoginService;
import test.TestData;

public class LoginApplication {

	private LoginService loginService;
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public String login(String url, String userName, String password) {
		
		if (url == TestData.url) {
			if (userName == TestData.userNameCorrect) {
				if (password == TestData.passwordCorrect) {
					System.out.println("Response : Successful Login!");
				} else {
					System.out.println("Response : Incorrect Credential [Username / Password]");
				}
			} else {
				System.out.println("Response : Incorrect Credential [Username / Password]");
			}
		} else {
			System.out.println("Response : Incorrect Credential [Username / Password]");
		}
		
		return loginService.login(url, userName, password);
	}
	
	public String loginServiceNotFound(String url, String userName, String password) {
		System.out.println("Response : HTTP 404 NOT FOUND!");
		return loginService.loginServiceNotFound(url, userName, password);
	}
	
}
