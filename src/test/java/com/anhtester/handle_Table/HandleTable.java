package com.anhtester.handle_Table;

import com.anhtester.common.BaseTest;
import com.anhtester.keywords.WebUI;
import org.testng.annotations.Test;

public class HandleTable extends BaseTest {
    @Test
    public void testSearchDataInTable(){
        getLoginPage().loginCRM("admin@example.com", "123456");
        getDashboardPage().clickMenuCustomer();
        getCustomerPage().searchAndCheckDataInTable(3, "Anh Tester", "Company");
    }
}
