package com.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutomationTesterAssigmentPage extends BaseAutomationPage {

	@FindBy(xpath = "//div[contains(text(),'Revenue Calculator')]")
	private WebElement lblRevenueCalculator;

	@FindBy(xpath = "//h5[contains(text(),'Total Gross Revenue Per Year')]")
	private WebElement lblTotalGrossRevenuePerYear;

	@FindBy(xpath = "//span[contains(@class,'MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium')]")
	private WebElement sliderRevenue;

	@FindBy(xpath = "//span[contains(text(),'Patients should be between 0 to 2000')]")
	private WebElement lblPatientsShouldBe;

	@FindBy(xpath = "//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall')]")
	private WebElement txtInputField;

	@FindBy(xpath = ("//p[contains(text(),'560')]"))
	private WebElement lblSliderPoision;

	@FindBy(xpath = "//p[contains(text(),'CPT-99091')]/parent::div/child::label/span/input[@type='checkbox']")
	private WebElement chkCPT99091;

	@FindBy(xpath = "//p[contains(text(),'CPT-99091')]/parent::div/child::label/span[contains(@class,'Mui-checked')]")
	private WebElement chkCPT99091Checked;

	@FindBy(xpath = "//p[contains(text(),'CPT-99453')]/parent::div/child::label/span/input[@type='checkbox']")
	private WebElement chkCPT99453;

	@FindBy(xpath = "//p[contains(text(),'CPT-99453')]/parent::div/child::label/span[contains(@class,'Mui-checked')]")
	private WebElement chkCPT99453Checked;

	@FindBy(xpath = "//p[contains(text(),'CPT-99454')]/parent::div/child::label/span/input[@type='checkbox']")
	private WebElement chkCPT99454;

	@FindBy(xpath = "//p[contains(text(),'CPT-99454')]/parent::div/child::label/span[contains(@class,'Mui-checked')]")
	private WebElement chkCPT99454Checked;

	@FindBy(xpath = "//p[contains(text(),'CPT-99474')]/parent::div/child::label/span/input[@type='checkbox']")
	private WebElement chkCPT99474;

	@FindBy(xpath = "//p[contains(text(),'CPT-99474')]/parent::div/child::label/span[contains(@class,'Mui-checked')]")
	private WebElement chkCPT99474Checked;

	@FindBy(xpath = "//input[@aria-orientation='horizontal']")
	private WebElement siderBar;
	
	@FindBy(xpath = "//p[text()='Total Gross Revenue Per Year:']/p")
	private WebElement lblTotalGrossRevenuePerYearText;
	
	@FindBy(xpath = "//p[text()='One Time Reimbursement for all Patients Annually:']/p")
	private WebElement lblOneTimeReimbursementForAllPatientsAnnuallyText;
	
	@FindBy(xpath = "//p[text()='Total Individual Patient/Month:']/p")
	private WebElement lblTotalIndividualPatientMonthText;
	
	@FindBy(xpath = "//p[text()='Total Recurring Reimbursement for all Patients Per Month:']/p")
	private WebElement lblTotalRecurringReimbursementForAllPatientsPerMonthText;

	private static final Logger logger = Logger.getLogger(AutomationTesterAssigmentPage.class.getName());

	public AutomationTesterAssigmentPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of AutomationTesterAssigmentPage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of AutomationTesterAssigmentPage method");
	}

	public void clickOnRevenueCalculator() {
		logger.info("Starting of clickOnRevenueCalculator method");

		this.lblRevenueCalculator.click();

		logger.info("Ending of clickOnRevenueCalculator method");
	}

	public String getTotalGrossRevenuePerYearLabelText() {
		logger.info("starting of getTotalGrossRevenuePerYearLabelText method");
		logger.info("Ending of getTotalGrossRevenuePerYearLabelText method");

		hardWait(2);
		return lblTotalGrossRevenuePerYear.getText();
	}

	public void scrollToRevenueSlider() {
		logger.info("Starting of scrollToRevenueSlider method");

		scrollIntoView(sliderRevenue);

		logger.info("Ending of scrollToRevenueSlider method");
	}

	public String getPatientsShouldBeLabelText() {
		logger.info("starting of getPatientsShouldBeLabelText method");
		logger.info("Ending of getPatientsShouldBeLabelText method");

		return lblPatientsShouldBe.getText();
	}

	public void setInput(String strInput) {
		logger.info("Starting of setInput method");

		hardWait(4);
		this.txtInputField.click();
		hardWait(2);
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();

		hardWait(2);
		this.txtInputField.sendKeys(strInput);

		logger.info("Ending of setInput method");
	}

	public String getSliderPoisionText() {
		logger.info("starting of getSliderPoisionText method");
		logger.info("Ending of getSliderPoisionText method");

		scrollIntoView(lblSliderPoision);
		return lblSliderPoision.getText();
	}

	public void clickOnCPT99091Checkbox() {
		logger.info("Starting of clickOnCPT99091Checkbox method");

		this.chkCPT99091.click();

		logger.info("Ending of clickOnCPT99091Checkbox method");
	}

	public boolean isCPT99091CheckedCheckboxDisplayed() {
		logger.info("Starting of isCPT99091CheckedCheckboxDisplayed method");
		logger.info("Ending of isCPT99091CheckedCheckboxDisplayed method");

		return chkCPT99091Checked.isDisplayed();
	}

	public void clickOnCPT99453Checkbox() {
		logger.info("Starting of clickOnCPT99453Checkbox method");

		this.chkCPT99453.click();

		logger.info("Ending of clickOnCPT99453Checkbox method");
	}

	public boolean isCPT99453CheckedCheckboxDisplayed() {
		logger.info("Starting of isCPT99453CheckedCheckboxDisplayed method");
		logger.info("Ending of isCPT99453CheckedCheckboxDisplayed method");

		return chkCPT99453Checked.isDisplayed();
	}

	public void clickOnCPT99454Checkbox() {
		logger.info("Starting of clickOnCPT99454Checkbox method");

		this.chkCPT99454.click();

		logger.info("Ending of clickOnCPT99454Checkbox method");
	}

	public boolean isCPT99454CheckedCheckboxDisplayed() {
		logger.info("Starting of isCPT99454CheckedCheckboxDisplayed method");
		logger.info("Ending of isCPT99454CheckedCheckboxDisplayed method");

		return chkCPT99454Checked.isDisplayed();
	}

	public void clickOnCPT99474Checkbox() {
		logger.info("Starting of clickOnCPT99474Checkbox method");

		this.chkCPT99474.click();

		logger.info("Ending of clickOnCPT99474Checkbox method");
	}

	public boolean isCPT99474CheckedCheckboxDisplayed() {
		logger.info("Starting of isCPT99474CheckedCheckboxDisplayed method");
		logger.info("Ending of isCPT99474CheckedCheckboxDisplayed method");

		return chkCPT99474Checked.isDisplayed();
	}

	public void slide() {
		logger.info("Starting of slide method");

		int sliderWidth = siderBar.getSize().getWidth();
		int targetValue = 820;
		int maxSliderValue = 1000;
		int targetPosition = (int) ((targetValue / (double) maxSliderValue) * sliderWidth);
		int currentPosition = siderBar.getLocation().getX();
		int offsetX = targetPosition - currentPosition;

		logger.info("Slider Width: " + sliderWidth);
		logger.info("Target Value: " + targetValue);
		logger.info("Max Slider Value: " + maxSliderValue);
		logger.info("Target Position: " + targetPosition);
		logger.info("Current Position: " + currentPosition);
		logger.info("Calculated OffsetX: " + offsetX);

		if (offsetX > 0) {
			Actions actions = new Actions(driver);
			actions.clickAndHold(siderBar).moveByOffset(offsetX, 0).release().perform();
		}

		logger.info("Ending of slide method");
	}
	
	public String getTotalGrossRevenuePerYearText() {
		logger.info("starting of getTotalGrossRevenuePerYearText method");
		logger.info("Ending of getTotalGrossRevenuePerYearText method");

		scrollDown(20);
		hardWait(3);
		return lblTotalGrossRevenuePerYearText.getText();
	}

	public String getOneTimeReimbursementForAllPatientsAnnuallyText() {
		logger.info("starting of getOneTimeReimbursementForAllPatientsAnnuallyText method");
		logger.info("Ending of getOneTimeReimbursementForAllPatientsAnnuallyText method");

		return lblOneTimeReimbursementForAllPatientsAnnuallyText.getText();
	}
	
	public String getTotalIndividualPatientMonthText() {
		logger.info("starting of getTotalIndividualPatientMonthText method");
		logger.info("Ending of getTotalIndividualPatientMonthText method");

		return lblTotalIndividualPatientMonthText.getText();
	}
	
	public String getTotalRecurringReimbursementForAllPatientsPerMonthText() {
		logger.info("starting of getTotalRecurringReimbursementForAllPatientsPerMonthText method");
		logger.info("Ending of getTotalRecurringReimbursementForAllPatientsPerMonthText method");

		return lblTotalRecurringReimbursementForAllPatientsPerMonthText.getText();
	}

}
