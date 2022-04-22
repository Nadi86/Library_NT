package com.library.step_definitions;
import com.library.utilities.DBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.testng.asserts.Assertion;

import java.util.*;

public class US1 {
    String query="";
    List<String> actualIDs = new ArrayList<>();

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DBUtils.createConnection();
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
       // List<String> actualID=new ArrayList<>();

       query = "select id  from books";

        actualIDs=DBUtils.getColumnDataAsList(1);
    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        query = "select id  from books";

        Set<String> expectedIDs = new LinkedHashSet<>();
       actualIDs=DBUtils.getColumnDataAsList(1);
       expectedIDs.addAll(actualIDs);
        System.out.println("actualIDs.toString() = " + actualIDs.toString());
        System.out.println("expectedIDs.toString() = " + expectedIDs.toString());
        Assert.assertEquals(actualIDs.toString(),expectedIDs.toString());
    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
      query = "select *\n" +
                "           from users";

      List<String> actualColumns = DBUtils.getColumnNames(query);
        System.out.println("columnNames.toString() = " + actualColumns.toString());

    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result() {
        query = "select *\n" +
                "           from users";

        List<String> actualColumns = DBUtils.getColumnNames(query);
        System.out.println("columnNames.toString() = " + actualColumns.toString());

        List<String> expectedColumns=new ArrayList<>(Arrays.asList(
                "id",
                "full_name",
                "email",
                "password",
                "user_group_id",
                "image",
                "extra_data",
                "status",
                "is_admin",
                "start_date",
                "end_date",
                "address"));
        Map<String, Object> rowMap = DBUtils.getRowMap(query);
        List<Map<String, Object>> uniqueID = DBUtils.getQueryResultMap(query);
        //System.out.println("uniqueID.toString() = " + uniqueID.toString());

        Assert.assertEquals(expectedColumns,actualColumns);
    }


}
