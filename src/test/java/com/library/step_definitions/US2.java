package com.library.step_definitions;
import com.library.pages.*;
import com.library.utilities.BrowserUtils;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.DBUtils;
import com.library.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.testng.asserts.Assertion;

import java.util.List;

public class US2 {
    DashboardPage dashBoardPage = new DashboardPage();
    UsersPage usersPage = new UsersPage();
    BooksPage booksPage = new BooksPage();

    @Given("I am in the homepage of the library app")
    public void i_am_in_the_homepage_of_the_library_app() {
    }
    @When("I take borrowed books number")
    public void i_take_borrowed_books_number() {
        System.out.println("dashBoardPage.borrowedBooks.getAttribute(\"value\") = " + dashBoardPage.borrowedBooks.getText());
    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DBUtils.createConnection();
        String query = "select count(*) from book_borrow where is_returned=0";
        Object cellValue = DBUtils.getCellValue(query);
        Assert.assertEquals(cellValue.toString(),dashBoardPage.borrowedBooks.getText());

    }
}
