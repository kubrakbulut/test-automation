package screenplay.actions;

import common.helpers.Util;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import screenplay.view.CommonPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Start implements Task {

    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Open.browserOn().the(new ApplicationHomePage(Util.getURL())),
                Refresh.theBrowserSession(),
                WaitUntil.elementIsPresent(CommonPage.anasayfaLogo)
        );

    }

    public static Start withHomePage() {
        return instrumented(Start.class);
    }
}


