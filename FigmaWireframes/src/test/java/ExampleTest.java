
import junit.framework.JUnit4TestAdapter;
import org.junit.*;

public class ExampleTest {
    
    String APIKey= System.getenv("APPLITOOLS_API_KEY");
 

    @Test
    public void takeBaseline() {
      String destination = "FigmaWireframes/UIValidation";
      
        try{
            Assert.assertTrue("Error while taking baseline", EyesManager.takeBaseline(destination,APIKey));
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(new JUnit4TestAdapter(ExampleTest.class));
    }
}