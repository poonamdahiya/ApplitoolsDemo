import com.applitools.eyes.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

import static org.testng.util.Strings.isNullOrEmpty;


public class BasicDemo {
    
    private EyesRunner runner;
    private Eyes eyes;
    private static BatchInfo batch;
    private WebDriver driver;
    
    @BeforeClass
    public static void setBatch() {
        batch = new BatchInfo("UI Validation");
        batch.setId("MyNewBatchId");
    }
    
    @Before
    public void beforeEach() throws Exception {
    
        runner = new ClassicRunner();
        runner.setDontCloseBatches(true);
        
        // Initialize the eyes SDK
        eyes = new Eyes(runner);
    
        // Raise an error if no API Key has been found.
        if(isNullOrEmpty(System.getenv("APPLITOOLS_API_KEY"))) {
            throw new RuntimeException("No API Key found; Please set environment variable 'APPLITOOLS_API_KEY'.");
        }
    
        // Set your personal Applitols API Key from your environment variables.
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
    
        // set batch name
        eyes.setBatch(batch);
        
        // Hide scroll bar
        eyes.setHideScrollbars(true);
        
        //Take a full page screenshot
        eyes.setForceFullPageScreenshot(true);
        
        //Stitch pages together and remove floating headers and footers...
        eyes.setStitchMode(StitchMode.CSS);
        
        //Set match level to Layout2 for dynamic content sites.
        eyes.setMatchLevel(MatchLevel.STRICT);
    
        eyes.setBranchName("Feature-1");

        eyes.setParentBranchName("UI/UX-Master");
    
        //set new baseline images. Use this when your site has changed without having to do in the dashboard.
        //eyes.setSaveFailedTests(true);
        
        // Enable Eyes Verbose Logs
//        eyes.setLogHandler(new FileLogger("build/reports/logs/applitools/debug.log",true,true));
        eyes.setLogHandler(new StdoutLogHandler(true));
        
        // Use Chrome browser
        driver = new ChromeDriver();
    }

    @Test
    public void basicUITest () throws Exception {

        eyes.open(driver, "UIValidation", "Validate Dashboard", new RectangleSize(1280, 768));

        // Navigate the browser to the "ACME" demo app.
        driver.get("file:////Users/poonamdahiya/Manulife-Training/ApplitoolsDemo/src/App/AnimaSamplePackage/desktop" +
                "-dashboard-new.html");

        // Visual checkpoint #1 - Check the login page.
        eyes.checkWindow("Dashboard");

        // Close Eyes Session
        eyes.closeAsync();
    }
    
    @Test
    public void basicUITestProfileComponent () throws Exception {
        
        eyes.open(driver, "UIValidation", "Validate Profile Menu", new RectangleSize(1280, 768));
        
        // Navigate the browser to the "ACME" demo app.
        driver.get("file:////Users/poonamdahiya/Manulife-Training/ApplitoolsDemo/src/App/AnimaSamplePackage/desktop-dashboard.html");
    
        driver.findElement(By.cssSelector(".avatar-eizpxM")).click();
    
        eyes.check("Profile", Target.region(By.cssSelector(".profile-menu-cGnJcY")).matchLevel(MatchLevel.LAYOUT));
        
        // Close Eyes Session
        eyes.closeAsync();
    }
    
    @Test
    public void basicUITestVideoComponent () throws Exception {
        // Create instance of Javascript executor
        JavascriptExecutor je = (JavascriptExecutor) driver;
        
        eyes.open(driver, "UIValidation", "Validate Video Card", new RectangleSize(1280, 768));
        
        // Navigate the browser to the "ACME" demo app.
        driver.get("file:////Users/poonamdahiya/Manulife-Training/ApplitoolsDemo/src/App/AnimaSamplePackage/desktop-dashboard.html");
        
    
        // Click on More classes to see video frame
        driver.findElement(By.cssSelector("div.more-classes-EjykXH.castoro-normal-black-16px")).click();
        
        // Capture video frame
        eyes.check("Video Component", Target.region(By.cssSelector(".video-card-desktop-a4ImOR")).matchLevel(MatchLevel.LAYOUT));
    
    
        // Close Eyes Session
        eyes.closeAsync();
    }
    
    @After
    public void afterEach() throws Exception {
        driver.quit();
        
        // Wait and collect all test results
        TestResultsSummary allTestResults = runner.getAllTestResults();
    
        // Print results
        System.out.println(allTestResults);
    }
}