package service;

public interface LoginService {

	public String login(String url, String userName, String password);
	public String loginServiceNotFound(String url, String userName, String password);
}
