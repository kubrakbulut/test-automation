package screenplay.actions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.environment.ConfiguredEnvironment;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitUntil implements Performable
{
    private boolean present;
    private String target;

    private WaitUntil(String target, boolean present) {
        this.present = present;
        this.target = target;
    }

    public static WaitUntil elementIsPresent(Target target) {
        return new WaitUntil(target.getCssOrXPathSelector(), true);
    }

    public static WaitUntil elementIsPresent(String target) {
        return new WaitUntil(target, true);
    }

    public static WaitUntil elementIsNotPresent(Target target) {
        return new WaitUntil(target.getCssOrXPathSelector(), false);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        long timeout = ConfiguredEnvironment.getConfiguration().getElementTimeoutInSeconds() * 1000;
        driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.MILLISECONDS);

        if (present) {
            boolean elementExist;
            if (target.contains("/"))
                elementExist = !driver.findElements(By.xpath(target)).isEmpty();
            else
                elementExist = !driver.findElements(By.cssSelector(target)).isEmpty();
            Assert.assertTrue("\nBeklenilen element bulunamadi: " + target, elementExist);
        }
        else {
            Date startTime = new Date();
            driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
            boolean elementExist = true;
            for (long waiting = 0; elementExist && waiting < timeout;) {
                if (target.contains("/"))
                    elementExist = !driver.findElements(By.xpath(target)).isEmpty();
                else
                    elementExist = !driver.findElements(By.cssSelector(target)).isEmpty();
                waiting = new Date().getTime() - startTime.getTime();
            }
            Assert.assertFalse("\nBeklenilen element hala sayfada: " + target, elementExist);
        }
        driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.MILLISECONDS);
    }
}
