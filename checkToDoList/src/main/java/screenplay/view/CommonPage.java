package screenplay.view;

import net.serenitybdd.screenplay.targets.Target;

public class CommonPage {
    public static final Target anasayfaLogo =
            Target.the("anasayfaLogo").locatedBy("body > section > div > header > h1");
    public static final Target textInput =
            Target.the("textInput").locatedBy("//input[@class='new-todo']");
    public static final Target clearItem =
            Target.the("clearItem").locatedBy("//button[@class=\"destroy\"]");
    public static final Target checkBox =
            Target.the("checkBox").locatedBy("//*[@class=\"toggle\"]");
    public static final Target completed =
            Target.the("completed").locatedBy("//li[@class=\"completed\"]");
    public static final Target todos =
            Target.the("todos").locatedBy("body > section > div > section");
}