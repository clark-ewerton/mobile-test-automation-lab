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
package com.clarkewerton.page_object;

import com.clarkewerton.locators.AndroidLocators;
import com.clarkewerton.locators.IOSLocators;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration; // Para versões mais recentes do Selenium
import java.lang.Math;

public class CalculatorPage {

    // Elementos numéricos
    @AndroidFindBy(id = AndroidLocators.DIGIT_0)
    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btn0;

    @AndroidFindBy(id = AndroidLocators.DIGIT_1)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btn1;

    @AndroidFindBy(id = AndroidLocators.DIGIT_2)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btn2;

    @AndroidFindBy(id = AndroidLocators.DIGIT_3)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btn3;

    @AndroidFindBy(id = AndroidLocators.DIGIT_4)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btn4;

    @AndroidFindBy(id = AndroidLocators.DIGIT_5)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btn5;

    @AndroidFindBy(id = AndroidLocators.DIGIT_6)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btn6;

    @AndroidFindBy(id = AndroidLocators.DIGIT_7)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btn7;

    @AndroidFindBy(id = AndroidLocators.DIGIT_8)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btn8;

    @AndroidFindBy(id = AndroidLocators.DIGIT_9)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btn9;

    // Operadores básicos
    @AndroidFindBy(id = AndroidLocators.OP_ADD)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btnAdd;

    @AndroidFindBy(id = AndroidLocators.OP_SUB)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btnSubtract;

    @AndroidFindBy(id = AndroidLocators.OP_MUL)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btnMultiply;

    @AndroidFindBy(id = AndroidLocators.OP_DIV)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btnDivide;

    @AndroidFindBy(id = AndroidLocators.OP_EQUALS)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btnEquals;

    // Funções avançadas
    @AndroidFindBy(id = AndroidLocators.OP_SQRT)
    private WebElement btnSquareRoot;

    @AndroidFindBy(id = AndroidLocators.OP_POW)
    private WebElement btnPower;

    @AndroidFindBy(id = AndroidLocators.OP_PERCENT)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement btnPercent;

    // Display e resultados
    @AndroidFindBy(id = AndroidLocators.RESULT)
    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement resultDisplay;

    @AndroidFindBy(id = AndroidLocators.FORMULA)
    private WebElement formulaDisplay;

    @AndroidFindBy(id = AndroidLocators.RESULT_PREVIEW)
	    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
    private WebElement resultPreview;

    // Outros controles
    @AndroidFindBy(id = AndroidLocators.CLEAR)
    private WebElement btnClear;

    @AndroidFindBy(id = AndroidLocators.DELETE)
    private WebElement btnDelete;

    @AndroidFindBy(id = AndroidLocators.DECIMAL)
    private WebElement btnDecimal;
	
		
	private final AppiumDriver driver;

    public CalculatorPage(AppiumDriver driver) {
		this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

public void enterNumber(int number) {
	
	// Verifies if number is negative
    boolean isNegative = number < 0;
    if (isNegative) {
        waitAndClick(btnSubtract);
		number = Math.abs(number); // transform number into positive
    }

    String numberStr = String.valueOf(number);
    for (int i = 0; i < numberStr.length(); i++) {
        char digitChar = numberStr.charAt(i);
        int digit = Character.getNumericValue(digitChar);

        switch (digit) {
            case 0: waitAndClick(btn0); break;
            case 1: waitAndClick(btn1); break;
            case 2: waitAndClick(btn2); break;
            case 3: waitAndClick(btn3); break;
            case 4: waitAndClick(btn4); break;
            case 5: waitAndClick(btn5); break;
            case 6: waitAndClick(btn6); break;
            case 7: waitAndClick(btn7); break;
            case 8: waitAndClick(btn8); break;
            case 9: waitAndClick(btn9); break;
            default: throw new IllegalArgumentException("Invalid digit: " + digit);
        }
    }
}
    public void add() {
		
        waitAndClick(btnAdd);
    }

    public void subtract() {
        waitAndClick(btnSubtract);
    }

    public void multiply() {
        waitAndClick(btnMultiply);
    }

    public void divide() {
        waitAndClick(btnDivide);
    }

    public void equals() {
        waitAndClick(btnEquals);
    }

    public void clear() {
        waitAndClick(btnClear);
    }

    public void delete() {
        waitAndClick(btnDelete);
    }

    public void decimal() {
        waitAndClick(btnDecimal);
    }

    public void squareRoot() {
        waitAndClick(btnSquareRoot);
    }

    public void power() {
        waitAndClick(btnPower);
    }

    public void percentage() {
       waitAndClick( btnPercent);
    }

    public String getResult() {
		waitForTextToBePresent(resultDisplay, resultDisplay.getText(), 10);
        return resultDisplay.getText();
    }

    public String getFormula() {
		waitForTextToBePresent(formulaDisplay, formulaDisplay.getText(), 10);
        return formulaDisplay.getText();
    }

    public String getResultPreview() {
		waitForTextToBePresent(resultPreview, resultPreview.getText(), 10);
        return resultPreview.getText();
    }
	
	// Métodos auxiliares para esperas
private void waitForElementToBeClickable(WebElement locator, int timeoutInSeconds) {
    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
        .until(ExpectedConditions.elementToBeClickable(locator));
}

private void waitAndClick(WebElement locator) {
    waitForElementToBeClickable(locator, 10);
    locator.click();
}

private void waitForTextToBePresent(WebElement locator, String text, int timeoutInSeconds) {
    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
        .until(ExpectedConditions.textToBePresentInElement(locator, text));
}
}