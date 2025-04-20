/*
 * MIT License
 *
 * Copyright (c) 2018 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.clarkewerton.test;

import com.clarkewerton.driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import java.util.List;
import org.testng.*;

public class BaseTest {

    protected AppiumDriver driver;

 @BeforeClass(alwaysRun = true)
 @Parameters({"platform", "udid", "platformVersion","env"})
    public void preCondition(String platform, String udid, String platformVersion, String env) {
            driver = new DriverFactory().createInstance(platform, udid, platformVersion, env);
    }
	
    @AfterClass(alwaysRun = true)
    public synchronized void tearDown() {
         if (driver != null) {
        driver.quit();
    }
    }
	
		@AfterMethod(alwaysRun = true)
    public void reportTestStatus(ITestResult result) {
        if (driver != null) {
            String status = result.getStatus() == ITestResult.SUCCESS ? "passed" : "failed";
            try {
                driver.executeScript("sauce:job-result=" + status);
            } catch (Exception e) {
                System.err.println("Failed to update Sauce Labs status: " + e.getMessage());
            }
        }
    }
	
	protected AppiumDriver getDriver() {
        return driver;
    }
}
