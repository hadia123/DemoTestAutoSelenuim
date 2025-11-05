@Test
public void testCreateAccount() {
    driver.get("https://automationexercise.com/login");

    LoginPage loginPage = new LoginPage(driver);
    loginPage.startSignup("demo123", "testDemo123@gmail.com");

    SignupPage signupPage = new SignupPage(driver);
    signupPage.fillAccountForm(
            "Mr",
            "demo123",
            "testDemo123@gmail.com",
            "123456",
            "10",
            "March",
            "1995",
            true,
            true
    );
    signupPage.submitAccount();

    Assert.assertTrue(driver.getTitle().contains("Account Created"));
}
