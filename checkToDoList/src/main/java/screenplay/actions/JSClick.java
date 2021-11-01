package screenplay.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class JSClick implements Task {

    private Target target;

    public JSClick(Target target) {
        this.target = target;
    }

    public static JSClick execute(Target target) {
        return instrumented(JSClick.class, target);
    }

    public <T extends Actor> void performAs(T actor) {
        WebElement targetElement = this.target.resolveFor(actor);
        BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click();", targetElement);
    }
}
