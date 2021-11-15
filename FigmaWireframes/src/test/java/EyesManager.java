import com.applitools.eyes.selenium.Eyes;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class EyesManager {
    
    public static boolean takeBaseline(String filepath, String API_KEY) throws IOException, InterruptedException {
        
        String appName = "MyNewDemoApp";
        String ApplitoolsServer = "https://eyes.applitools.com";
        String viewport = "1280x768";
        String OSName = "Mac OS X 10.15";
        String browserName = "Chrome";
        
        
        try {
            String command = String.format("java -jar ImageTester_1.5.1.jar -k %s -f %s -a %s -s %s -vs" +
                " %s -os %s -ap %s", API_KEY, filepath, appName,ApplitoolsServer,viewport,OSName,browserName );

            System.out.println("Command:" + command);
            Process process = Runtime.getRuntime().exec(command);
            System.out.println("Before wait");
            process.waitFor();
            System.out.println("After wait");
            String stream = IOUtils.toString(process.getInputStream(), "UTF-8");
            System.out.println(stream);
            if (stream != null && stream.contains("Mismatch")) {
                return false;
            }
        }
        catch(IOException | InterruptedException e) {
            System.out.println(e);
            e.printStackTrace();

        }
        return true;
    }
}
