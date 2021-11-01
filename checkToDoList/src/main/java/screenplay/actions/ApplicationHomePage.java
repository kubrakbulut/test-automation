package screenplay.actions;

import net.serenitybdd.core.pages.PageObject;

public class ApplicationHomePage extends PageObject {

    @Override
    public void setDefaultBaseUrl(String defaultBaseUrl) {
        super.setDefaultBaseUrl(defaultBaseUrl);
    }

    public ApplicationHomePage(String defaultBaseUrl) {
        super.setDefaultBaseUrl(defaultBaseUrl);
    }
}