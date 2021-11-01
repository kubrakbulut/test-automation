package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.targets.Target;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import screenplay.actions.JSClick;
import screenplay.actions.Start;
import screenplay.view.CommonPage;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CommonSteps {
    @Before
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^(.*), sees empty to do list$")
    public void userSeesEmptyToDoList(final String actorName) {

        //to do list sayfasi acilir ve kontrol edilir
        theActorCalled(actorName).wasAbleTo(Start.withHomePage());
    }

    @Then("^I write (.*) to text box and press enter$")
    public void iWriteToTextBoxAndPressEnter(String buyItem) {
        theActorInTheSpotlight().attemptsTo(
                Enter.keyValues(buyItem + Keys.ENTER).into(CommonPage.textInput));
    }

    @Then("^I should see (.*) item in ToDo list$")
    public void iShouldSeeItemInToDoList(String checkListItem) {
        Target itemXpath =
                Target.the("buttonXpath").locatedBy("//label[contains(text(),'" + checkListItem + "')]");

        Assert.assertTrue(checkListItem.equalsIgnoreCase(itemXpath.resolveFor(theActorInTheSpotlight()).getText()));
    }

    @Then("^I should see (.*) item inserted to ToDo list below (.*) item$")
    public void iShouldSeeItemInsertedToDoListBelowItem(String checkListItem1, String checkListItem2) {
        Target itemXpath1 =
                Target.the("buttonXpath").locatedBy("//label[contains(text(),'" + checkListItem1 + "')]");
        Target itemXpath2 =
                Target.the("buttonXpath").locatedBy("//label[contains(text(),'" + checkListItem2 + "')]");

        checkListItem1.equals(itemXpath1.resolveFor(theActorInTheSpotlight()).getText());
        checkListItem2.equals(itemXpath2.resolveFor(theActorInTheSpotlight()).getText());

        theActorInTheSpotlight().attemptsTo(JSClick.execute(Target.the("deleteItem").locatedBy("(//button[@class=\"destroy\"])[2]")));
    }

    @Given("^(.*), to do list with (.*) item$")
    public void toDoListWithBuySomeMilkItem(final String actorName, String item) {
        theActorCalled(actorName).wasAbleTo(Start.withHomePage());
    }

    @When("^I click on checkbox next to (.*) item$")
    public void iClickOnCheckboxNextToBuySomeMilkItem(String item) {
        theActorInTheSpotlight().attemptsTo(
                Click.on(CommonPage.checkBox));
    }

    @Then("^I should see (.*) item marked as DONE$")
    public void iShouldSeeItemMarkedAsDONE(String item) {
        Assert.assertTrue(CommonPage.completed.resolveFor(theActorInTheSpotlight()).isPresent());
    }

    @When("^I click on checkbox next to item$")
    public void iClickOnCheckboxNextToItem() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(CommonPage.checkBox)
        );
    }

    @Then("^I should see (.*) item marked as UNDONE$")
    public void iShouldSeeItemMarkedAsUNDONE(String item) {
        Assert.assertFalse(CommonPage.completed.resolveFor(theActorInTheSpotlight()).isPresent());

        theActorInTheSpotlight().attemptsTo(JSClick.execute(Target.the("deleteItem").locatedBy("(//button[@class=\"destroy\"])[1]")));

    }

    @When("^I click on delete button next to (.*) item$")
    public void iClickOnDeleteButton(String item) {
        theActorInTheSpotlight().attemptsTo(
                JSClick.execute(CommonPage.clearItem));
    }

    @Then("List should be empty")
    public void listShouldBeEmpty() {
        Assert.assertFalse(CommonPage.todos.resolveFor(theActorInTheSpotlight()).isVisible());
    }

    @Given("^(.*), to do list with (.*) and (.*) item in order$")
    public void iToDoListWithRestForAWhileAndDrinkWaterItemInOrder(final String actorName, String item1, String item2) {
        theActorCalled(actorName).wasAbleTo(Start.withHomePage());
    }

    @Then("^I should see (.*) item in to do list$")
    public void iShouldSeeDrinkWaterItemInToDoList(String item) {
        Target itemXpath = Target.the("item").locatedBy("//label[contains(text(),'"+item+"')]");
        Assert.assertTrue(itemXpath.resolveFor(theActorInTheSpotlight()).isVisible());
    }
}