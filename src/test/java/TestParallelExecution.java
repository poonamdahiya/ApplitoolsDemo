import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(JUnit4.class)
public class TestParallelExecution {

@Test
public void runAllTest() {
    
    Class<?>[] classes = { BasicDemo.class};
    
    // ParallelComputer(true,true) will run all classes and methods
    // in parallel.  (First arg for classes, second arg for methods)
    JUnitCore.runClasses(new ParallelComputer(true, true), classes);

}
}
