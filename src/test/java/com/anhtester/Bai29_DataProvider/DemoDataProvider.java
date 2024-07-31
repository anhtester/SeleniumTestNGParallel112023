package com.anhtester.Bai29_DataProvider;

import com.anhtester.dataproviders.DataProviderFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoDataProvider {

    @Test(dataProvider = "data_provider_01", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider01(String value) {
        System.out.println("Test: " + value);
    }

    @Test(dataProvider = "data_provider_02", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider02(String a, String b, String c) {
        System.out.println("Parameter 1: " + a);
        System.out.println("Parameter 2: " + b);
        System.out.println("Parameter 3: " + c);
    }

    @Test(dataProvider = "data_provider_03", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider03(String name, String phone, int vat, String address) {
        System.out.println("Parameter 1: " + name);
        System.out.println("Parameter 2: " + phone);
        System.out.println("Parameter 3: " + vat);
        System.out.println("Parameter 4: " + address);
    }

}
