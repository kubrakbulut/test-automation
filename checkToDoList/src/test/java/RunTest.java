import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty"},
        glue = "steps",
        tags = "@functional")

//RunConfigurations'tan tags,features duzenlenir
public class RunTest {
}