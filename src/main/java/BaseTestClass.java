
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseTestClass {


    static ExtentReports report;
    public WebDriver driver;
    public WebDriverWait wait;
    public ExtentReports reporter;
    public ExtentHtmlReporter htmlReporter;
    @BeforeAll
    public void InitiateReport(){
        String reportPath = System.getProperty("user.dir")+"/src/main/resources/Report.html";
        htmlReporter = new ExtentHtmlReporter(reportPath);
        reporter = new ExtentReports();
        reporter.attachReporter(htmlReporter);
    }
    @BeforeEach
    public void SetUp(){
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void AfterEachTest(){

        driver.quit();
    }
    @AfterAll
    public void Flush(){
        reporter.flush();
    }
}
