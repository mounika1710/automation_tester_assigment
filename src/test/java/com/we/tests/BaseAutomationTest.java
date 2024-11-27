package com.we.tests;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.web.pages.AutomationTesterAssigmentPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseAutomationTest {

	protected static String browserDriverPath = null;
	protected static Properties testDataProp = null;
	protected static Properties expectedAssertionsProp = null;

	protected static Map<String, String> chromeDriverMap = new HashMap<String, String>();

	private static final Logger logger = Logger.getLogger(BaseAutomationTest.class.getName());

	private static Map<WEB_DRIVER, WebDriver> webDriverPool = new Hashtable<WEB_DRIVER, WebDriver>();
	protected static final String BASE_DIR = System.getProperty("user.dir");
	protected static final String SITE_URL = System.getProperty("https://www.fitpeo.com/");

	public enum WEB_DRIVER {

		WebDriversEnum
	}

	private WebDriver childDriver;
	private WebDriver driver;
	private AutomationTesterAssigmentPage automationTesterAssigmentPage = null;

	@BeforeSuite(groups = "sanity")
	@Parameters()
	public void initTestData() {
		/*
		 * if (siteURL != null) { this.loginURL = siteURL; }
		 */

		if (testDataProp == null) {
			FileReader testDataReader = null;
			FileReader assertionsReader = null;
			try {
				testDataReader = new FileReader("src/main/resources/testdata.properties");
				assertionsReader = new FileReader("src/main/resources/expectedassertions.properties");

				testDataProp = new Properties();
				testDataProp.load(testDataReader);

				expectedAssertionsProp = new Properties();
				expectedAssertionsProp.load(assertionsReader);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					testDataReader.close();
					assertionsReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected synchronized WebDriver getDriver(String browser) {
		logger.info("Starting of method getDriver");

		WebDriver driver = null;
		if (browser.equalsIgnoreCase("Chrome")) {
			// WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", BASE_DIR + "src\\main\\resources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		logger.info("***************** Driver Successfully Created **************** " + driver.getTitle());
		
		//driver.get(SITE_URL);
		
		logger.info("End of method getDriver");
		return driver;
	}

	/**
	 * This method is used for get driver
	 * 
	 * @param webDriver
	 * @return
	 */

	protected synchronized WebDriver getWebDriver(String browser) {
		logger.info("Starting of method getWebDriver");

		String osPath = System.getProperty("os.name");
		WebDriver webDriver = null;

		if (osPath.contains("Linux")) {

			if (browser.equalsIgnoreCase("Firefox")) {

				WebDriverManager.firefoxdriver().setup();

				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				options.addArguments("--no-sandbox");

				webDriver = new FirefoxDriver(options);

			} else if (browser.equalsIgnoreCase("Chrome")) {

				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.setHeadless(true);
				options.addArguments("--headless"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.addArguments("--remote-allow-origins=*");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				options.addArguments("--window-size=1920,1080");
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);// del
				options.addArguments("--disable-browser-side-navigation"); // del
				options.addArguments("--disable-dev-shm-usage"); // del
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("auto-open-devtools-for-tabs", "true");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_settings.popups", 0);
				options.setExperimentalOption("prefs", prefs);

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("CHROME");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				
				capabilities.setCapability("applicationCacheEnabled", "true");

				webDriver = new ChromeDriver(options);
			}

		} else if (osPath.contains("Mac OS X")) {

			if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.addArguments("--remote-allow-origins=*");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				webDriver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				webDriver = new FirefoxDriver();

			}

		} else {

			if (browser.equalsIgnoreCase("Chrome")) {

				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.addArguments("enable-automation");
				options.addArguments("--headless");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.addArguments("--remote-allow-origins=*");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				// options.setHeadless(isHeadless());
				// options.addArguments("--no-sandbox");

				webDriver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("Firefox")) {

				WebDriverManager.firefoxdriver().setup();
				webDriver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("Chromium")) {

				WebDriverManager.chromiumdriver().setup();
				webDriver = new EdgeDriver();

			} else if (browser.equalsIgnoreCase("IEDriverServer")) {

				WebDriverManager.iedriver().setup();
				webDriver = new InternetExplorerDriver();
			}
		}

		webDriver.manage().window().maximize();
		webDriver.manage().deleteAllCookies();
		webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		logger.info("**************** Driver Successfully Created *************** " + webDriver.getTitle());
		logger.info("End of method getWebDriver");

		return webDriver;
	}
	
	public void siteLogin(WebDriver driver) {
		logger.info("Starting of siteLogin method");
		try {
			
			this.childDriver = driver;
			this.automationTesterAssigmentPage = new AutomationTesterAssigmentPage(driver);

			driver.get(SITE_URL);
			

		} catch (Exception e) {
			Assert.fail("Exception occured while testing site login : " + e.getMessage());
			logger.error("Error occured while testing site login ", e);
		}

		logger.info("Ending of siteLogin method");
	}

	public String getChromeDriverVersion(String driverInfo) {
		logger.info("Starting of getChromeDriverVersion method ");

		String tVersion = driverInfo.split("is")[2];
		tVersion = tVersion.split("with")[0];
		tVersion = (tVersion.split("\\.")[0]).trim();

		logger.debug("Chrome browser Version : " + tVersion);
		logger.info("Ending of getChromeDriverVersion method ");

		return tVersion;
	}

	protected synchronized WebDriver getDriver(String browser, WebDriver webDriver) {
		logger.info("Starting of method getDriver");

		WebDriver driver = null;
		if (browser.equalsIgnoreCase("Chrome")) {
			// WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", BASE_DIR + "\\src\\main\\resources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		logger.info("***************** Driver Successfully Created **************** " + driver.getTitle());
		logger.info("End of method getDriver");
		return driver;
	}

	public WebDriver getDriver() {
		logger.info("Starting of WebDriver method ");
		logger.info("Ending of WebDriver method ");

		return childDriver;
	}

	public void login(String siteURL) {
		logger.info("Starting of login method");

		this.childDriver = driver;
		this.automationTesterAssigmentPage = new AutomationTesterAssigmentPage(driver);

		driver.get(siteURL);

		logger.info("Ending of login method");
	}

	public void fluentWaitForElement(WebDriver childDriver, String xPath) {

		try {

			// Reference : https://www.guru99.com/implicit-explicit-waits-selenium.html
			Wait<WebDriver> wait = new FluentWait<WebDriver>(childDriver).withTimeout(Duration.ofSeconds(3))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);

			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(xPath));
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public List<String> getPropertyList(String name) {
		List<String> list = Arrays.asList(name.toString().split("\\,"));
		System.out.println(list);
		return list;
	}

}