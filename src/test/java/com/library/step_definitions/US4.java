package com.library.step_definitions;
import com.library.pages.BooksPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.DBUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class US4 {

    BooksPage booksPage = new BooksPage();

    String query = "select full_name,count(*) from users u inner join book_borrow bb on u.id = bb.user_id\n" +
            "group by full_name\n" +
            "order by 2 desc ;";
    @When("I execute a query to find the most popular user")
    public void i_execute_a_query_to_find_the_most_popular_user() {
        Object cellValue = DBUtils.getCellValue(query);

    }
    @Then("verify {string} is the user who reads the most")
    public void verify_is_the_user_who_reads_the_most(String string) {
        Object actual= DBUtils.getCellValue(query);
        Assert.assertEquals(actual,string);
    } }




