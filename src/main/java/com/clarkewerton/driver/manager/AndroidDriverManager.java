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
package com.clarkewerton.driver.manager;

import com.clarkewerton.driver.IDriver;
import com.clarkewerton.config.Configuration;
import com.clarkewerton.config.ConfigurationManager;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.io.File; 
import java.util.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.MutableCapabilities;

public class AndroidDriverManager {

    private final Configuration config;
    
    public AndroidDriverManager() {
        this.config = ConfigurationManager.getConfiguration();
    }

    public AppiumDriver createInstance(String deviceName, String platformVersion, String env) {
        try {
			if ("local".equals(env)) {
				return createLocalDriver(deviceName, platformVersion);
			}else{
				return createCloudDriver(deviceName, platformVersion);
			}
        } catch (Exception e) {
            throw new RuntimeException("Failed to create driver for: " + deviceName, e);
        }
    }

    private AppiumDriver createLocalDriver(String udid, String platformVersion) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
            .setUdid(udid)
            .setPlatformVersion(platformVersion)
            .setDeviceName("Android Device")
            .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
            .setAvdReadyTimeout(Duration.ofSeconds(30))
            .setAutoGrantPermissions(true);

        if (config.installApp()) {
            options.setApp(getAbsoluteAppPath(config.androidAppPath()));
        } else {
            options.setAppPackage(config.androidAppPackage())
                   .setAppActivity(config.androidAppActivity());
        }

        return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

 private AppiumDriver createCloudDriver(String deviceName, String platformVersion) throws MalformedURLException {

//code below to be used with Sauce Labs
MutableCapabilities caps = new MutableCapabilities();
caps.setCapability("platformName", "Android");
caps.setCapability("appium:app", "storage:filename=googleCalculator.apk");
caps.setCapability("appium:deviceName", deviceName);
caps.setCapability("appium:platformVersion", "current_major");
caps.setCapability("appium:automationName", "UiAutomator2");
MutableCapabilities sauceOptions = new MutableCapabilities();
sauceOptions.setCapability("username", config.lambdaTestUsername());
sauceOptions.setCapability("accessKey", config.lambdaTestAccessKey());
sauceOptions.setCapability("build", config.lambdaTestBuild());
sauceOptions.setCapability("name", config.lambdaTestProject());
sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
caps.setCapability("sauce:options", sauceOptions);

            return new AndroidDriver(
                    new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"),
                    caps);

}

    private String getAbsoluteAppPath(String relativePath) {
        return new File(relativePath).getAbsolutePath();
    }
}
