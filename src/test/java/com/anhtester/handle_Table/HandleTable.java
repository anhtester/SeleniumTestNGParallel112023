package com.anhtester.handle_Table;

import com.anhtester.common.BaseTest;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.anhtester.keywords.WebUI.*;

public class HandleTable extends BaseTest {
    @Test
    public void testSearchDataInTable(){
        getLoginPage().loginCRM("admin@example.com", "123456");
        getDashboardPage().clickMenuCustomer();
        getCustomerPage().searchAndCheckDataInTable(3, "Anh Tester", "Company");
    }

    @Test
    public void testCheckPaginationOnTable() {
        getLoginPage().loginCRM("admin@example.com", "123456");
        getDashboardPage().clickMenuCustomer();

        //Data search read from Properties
        String searchValue = "An";

        //Get item on One Page
        Select select1 = new Select(WebUI.getWebElement(By.xpath("//select[@name='clients_length']")));
        select1.selectByVisibleText("10");
        sleep(2);
        Select select2 = new Select(WebUI.getWebElement(By.xpath("//select[@name='clients_length']")));
        System.out.println(select2.getFirstSelectedOption().getText());

        int itemTotalOnePage = Integer.parseInt(select2.getFirstSelectedOption().getText());
        System.out.println("Tổng số item / trang: " + itemTotalOnePage);

        //Set Text on Search input
        getCustomerPage().searchDataCustomer(searchValue);

        waitForPageLoaded();
        sleep(2);

        //Get total item
        String strTotal = WebUI.getElementText(By.xpath("//div[@id='clients_info']"));
        ArrayList<String> list = new ArrayList<String>();

        //Tách chuỗi theo khoảng trắng, sau đó cho vào ArrayList
        for (String strItem : strTotal.split("\\s")) {
            list.add(strItem);
        }

        System.out.println(list);

        //Lấy phần tử thứ 6 (trong ArrayList là 5)
        int itemTotal = Integer.parseInt(list.get(5));
        System.out.println("Tổng số item: " + itemTotal);

        int pageTotal = itemTotal / itemTotalOnePage;
        int sodu = itemTotal % itemTotalOnePage;
        System.out.println("Tổng số nguyên: " + pageTotal);
        System.out.println("Tổng số dư: " + sodu);

        if (sodu > 0) {
            pageTotal = pageTotal + 1;
        }

        System.out.println("Tổng số Page: " + pageTotal);

        for (int i = 1; i <= pageTotal; i++) {
            checkDataInTableByColumn_Contains(3, searchValue, "Company");

            //Nhấn nút Next để đến trang tiếp theo
            if (i < pageTotal) {
                clickElement(By.xpath("//a[normalize-space()='Next']"));
                sleep(2);
            }
        }

    }
}
