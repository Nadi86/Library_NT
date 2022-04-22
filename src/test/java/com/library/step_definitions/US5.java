package com.library.step_definitions;

import com.library.pages.*;
import com.library.utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class US5 {
    DashboardPage dashBoardPage = new DashboardPage();
    UsersPage usersPage = new UsersPage();
    BooksPage booksPage = new BooksPage();


    @Given("I am on the login page")
    public void i_am_on_the_login_page() {

    }
    @Given("I login to application as a librarian")
    public void i_login_to_application_as_a_librarian() {

    }
    @Given("I navigate to {string} page")
    public void i_navigate_to_page(String page){
dashBoardPage.books.click();

    }

    @When("I open book {string}")
    public void i_open_book(String string) {
usersPage.search.sendKeys(string);
booksPage.editBook(string).click();
        BrowserUtils.waitFor(5);
    }

    @Then("book information must match the database for {string}")
    public void book_information_must_match_the_database_for(String bookName) {
        String actualName = booksPage.bookName.getAttribute("value");
        String actualAuthor = booksPage.author.getAttribute("value");
        String actualISBN = booksPage.isbn.getAttribute("value");
        String actualYear = booksPage.year.getAttribute("value");
        String actualDesc = booksPage.description.getAttribute("value");
        System.out.println("actualName = " + actualName);
        System.out.println("actualAuthor = " + actualAuthor);

        String query = "select name, isbn,year,author,description\n" +
                "from books where name='"+bookName+"'";
        Map<String, Object> dbData = DBUtils.getRowMap(query);

        System.out.println(dbData.toString());

        String expectedName = dbData.get("name").toString();
        String expectedISBN = dbData.get("isbn").toString();
        String expectedYear = dbData.get("year").toString();
        String expectedAuthor = dbData.get("author").toString();
        String expectedDesc = dbData.get("description").toString();

        //compare UI vs Database
        Assert.assertEquals(expectedName,actualName);
        Assert.assertEquals(expectedAuthor,actualAuthor);
        Assert.assertEquals(expectedYear,actualYear);
        Assert.assertEquals(expectedISBN,actualISBN);
        Assert.assertEquals(expectedDesc,actualDesc);

        //real life you dont save those info into variables
       Assert.assertEquals(booksPage.bookName.getAttribute("value"),dbData.get("name").toString());

    }

}
