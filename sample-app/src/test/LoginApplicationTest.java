package test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import application.LoginApplication;
import service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginApplicationTest {

	private LoginApplication loginApplication;
	private LoginService loginService;

	@Before
	public void setUp() {
		loginApplication = new LoginApplication();
		loginService = mock(LoginService.class);
		loginApplication.setLoginService(loginService);
	}

	@Test
	public void testLoginResponse() {

		// Add behavior to the services
		when(loginService.login(TestData.url, TestData.userNameCorrect, TestData.passwordCorrect))
				.thenReturn("Success");
		when(loginService.login(TestData.url, TestData.userNameIncorrect, TestData.passwordIncorrect))
				.thenReturn("Failed");
		when(loginService.loginServiceNotFound(TestData.url, TestData.userNameCorrect, TestData.passwordCorrect))
				.thenReturn("404 Not Found");

		// Test the services
		System.out.println("Start Mock Testing Of Login Services...");

		// Test #1 - Success Login
		System.out.println("\nTest #1 : Login Successfully");
		Assert.assertEquals(loginApplication.login(TestData.url, TestData.userNameCorrect, TestData.passwordCorrect),
				"Success");

		// Test #2 - Failed Login
		System.out.println("\nTest #2 : Login Failed Due To Incorrect Credential(s)");
		Assert.assertEquals(
				loginApplication.login(TestData.url, TestData.userNameIncorrect, TestData.passwordIncorrect), "Failed");

		// Test #3 - Service Not Found / Unavailable
		System.out.println("\nTest #3 : Login Service Not Found - 404");
		Assert.assertEquals(
				loginApplication.loginServiceNotFound(TestData.url, TestData.userNameCorrect, TestData.passwordCorrect),
				"404 Not Found");

		System.out.println("\nTest Complete...");

		// Verify call to login service is made or not
		verify(loginService).login(TestData.url, TestData.userNameCorrect, TestData.passwordCorrect);
		verify(loginService).login(TestData.url, TestData.userNameIncorrect, TestData.passwordIncorrect);
		verify(loginService).loginServiceNotFound(TestData.url, TestData.userNameCorrect, TestData.passwordCorrect);
	}

}
