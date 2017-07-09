package com.longshihan.mymenu;

import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

/**
 * Created by longshihan on 2017/7/9.
 */

public class TestUI extends UiAutomatorTestCase {
    public void testDemo() throws UiObjectNotFoundException {
        UiObject browserObject=new UiObject(new UiSelector().text("浏览器"));
        browserObject.clickAndWaitForNewWindow();
        UiObject editObject=new UiObject(new UiSelector().className("android.wight.edittext"));
        editObject.click();
        UiDevice.getInstance().pressDelete();
        editObject.setText("www.baidu.com");
        UiDevice.getInstance().pressEnter();
        sleep(2000);
    }
}
