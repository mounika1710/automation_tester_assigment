package com.we.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.web.pages.AutomationTesterAssigmentPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Fitpeo Login")
@Feature("Login")
public class AutomationTesterAssigmentTest extends BaseAutomationTest {

	private AutomationTesterAssigmentPage automationTesterAssigmentPage = null;
	private WebDriver childDriver = null;
	private static final Logger logger = Logger.getLogger(AutomationTesterAssigmentTest.class.getName());

	@BeforeClass
	@Parameters({ "browser" })
	public void init(String browser) throws Exception {
		logger.info("Starting of initCMSFolderCreation method");

		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.fitpeo.com/");

		this.automationTesterAssigmentPage = new AutomationTesterAssigmentPage(driver);

		logger.info("Ending of initCMSFolderCreation method in AutomationTesterAssigmentTest");
	}

	@Test(priority = 1, description = "Verify Revenue Calculator")
	@Description("Test Description: Verify Revenue Calculator")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify Revenue Calculator")
	public void testRevenueCalculator() {
		logger.info("Starting of testRevenueCalculator method");

		this.automationTesterAssigmentPage.clickOnRevenueCalculator();

		Assert.assertEquals(this.automationTesterAssigmentPage.getTotalGrossRevenuePerYearLabelText(),
				"Total Gross Revenue Per Year");

		Assert.assertEquals(this.automationTesterAssigmentPage.getPatientsShouldBeLabelText(),
				"Patients should be between 0 to 2000");

		this.automationTesterAssigmentPage.setInput("560");
		this.automationTesterAssigmentPage.slide();
		this.automationTesterAssigmentPage.setInput("820");

		logger.info("Ending of testRevenueCalculator method");
	}

	@Test(priority = 2, description = "Verify CPT Codes")
	@Description("Test Description: Verify CPT Codes")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify CPT Codes")
	public void testCPTCodes() {
		logger.info("Starting of testCPTCodes method");

		this.automationTesterAssigmentPage.clickOnCPT99091Checkbox();

		Assert.assertTrue(this.automationTesterAssigmentPage.isCPT99091CheckedCheckboxDisplayed());

		this.automationTesterAssigmentPage.clickOnCPT99453Checkbox();

		Assert.assertTrue(this.automationTesterAssigmentPage.isCPT99453CheckedCheckboxDisplayed());

		this.automationTesterAssigmentPage.clickOnCPT99454Checkbox();

		Assert.assertTrue(this.automationTesterAssigmentPage.isCPT99454CheckedCheckboxDisplayed());

		this.automationTesterAssigmentPage.clickOnCPT99474Checkbox();

		Assert.assertTrue(this.automationTesterAssigmentPage.isCPT99474CheckedCheckboxDisplayed());
	
		logger.info("Ending of testCPTCodes method");
	}
	
	@Test(priority = 3, description = "Verify Recurring Reimbursement")
	@Description("Test Description: Verify Recurring Reimbursement")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify Recurring Reimbursement")
	public void testRecurringReimbursement() {
		logger.info("Starting of testRecurringReimbursement method");
		
		Assert.assertEquals(this.automationTesterAssigmentPage.getTotalGrossRevenuePerYearText(), "$1344135.80");
		
		Assert.assertEquals(this.automationTesterAssigmentPage.getOneTimeReimbursementForAllPatientsAnnuallyText(), "$15735.80");
		
		Assert.assertEquals(this.automationTesterAssigmentPage.getTotalIndividualPatientMonthText(), "820");
		
		Assert.assertEquals(this.automationTesterAssigmentPage.getTotalRecurringReimbursementForAllPatientsPerMonthText(), "$110700");
		
		logger.info("Ending of testRecurringReimbursement method");
	}

	@AfterClass
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		if (childDriver != null) {
			childDriver.quit();
			logger.info("Driver quit successfully");
		}

		logger.info("Ending of quitDriver method");
	}
}