package com.library.step_definitions;
import com.library.pages.BooksPage;
import com.library.pages.DashboardPage;
import com.library.pages.LoginPage;
import com.library.pages.UsersPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.DBUtils;
import com.library.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class US3 {
    DashboardPage dashBoardPage = new DashboardPage();
    UsersPage usersPage = new UsersPage();
    BooksPage booksPage = new BooksPage();

    @When("I execute a query to find the most popular book genre")
    public void i_execute_a_query_to_find_the_most_popular_book_genre() {

        String query = "select bc.name,count(*) from book_borrow bb\n" +
                "                                 inner  join books b on bb.book_id = b.id\n" +
                "                                 inner join book_categories bc on b.book_category_id=bc.id\n" +
                "group by name\n" +
                "order by 2 desc;";
        String actual=""+DBUtils.getCellValue(query);

    }


    @Then("verify that {string} is the most popular book genre")
    public void verifyThatIsTheMostPopularBookGenre(String string) {
        DBUtils.createConnection();
        String query = "select bc.name,count(*) from book_borrow bb\n" +
                "                                 inner  join books b on bb.book_id = b.id\n" +
                "                                 inner join book_categories bc on b.book_category_id=bc.id\n" +
                "group by name\n" +
                "order by 2 desc;";
        String actual=""+DBUtils.getCellValue(query);
        System.out.println("actual = " + actual);
        dashBoardPage.books.click();
        booksPage.mainCategoryElement.click();

        Assert.assertEquals(actual,string);
    }
}
