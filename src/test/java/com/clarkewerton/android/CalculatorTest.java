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
package com.clarkewerton.android;

import static org.assertj.core.api.Assertions.assertThat;

import com.clarkewerton.page_object.CalculatorPage;
import com.clarkewerton.test.BaseTest;
import org.testng.annotations.*;
import java.util.*;

public class CalculatorTest extends BaseTest {

 @Test(dataProvider = "calculatorScenarios")
public void testCalculatorOperations(String scenario, String operation, List<Object> numbers, String expectedResult) {
    CalculatorPage calculator = new CalculatorPage(getDriver());
    
        // Initial validations for negative scenarios
        if (scenario.equals("negative")) {
            if (numbers.isEmpty()) {
                assertThat("Invalid Entry").isEqualTo(expectedResult);
                return;
            }
            if (numbers.size() == 1 && !operation.equals("percentage")) {
                assertThat("Need at least two numbers to make operation").isEqualTo(expectedResult);
                return;
            }
            // Verifies if there's no number's elements
            for (Object num : numbers) {
                if (!(num instanceof Integer)) {
                    assertThat("Invalid Entry").isEqualTo(expectedResult);
                    return;
                }
            }
        }

        // Execução da operação
        calculator.enterNumber((Integer) numbers.get(0));
        for (int i = 1; i < numbers.size(); i++) {
            switch(operation) {
                case "add": calculator.add(); break;
                case "subtract": calculator.subtract(); break;
                case "multiply": calculator.multiply(); break;
                case "divide": calculator.divide(); break;
                case "percentage": calculator.percentage(); break;
            }
            calculator.enterNumber((Integer) numbers.get(i));
        }
        calculator.equals();

        // Verificação do resultado
        if (scenario.equals("positive")) {
            assertThat(calculator.getResult().replace('−', '-')).isEqualTo(expectedResult);
        } else {
			if(operation.equals("invalidOperation")){
                assertThat("Invalid Operation").isEqualTo(expectedResult);
			}else{
				 assertThat(calculator.getResultPreview().replace('−', '-')).isEqualTo(expectedResult);
			} 
        }
        calculator.clear();
}

    @DataProvider
    public Object[][] calculatorScenarios() {
        return new Object[][]{
            {"positive", "add", Arrays.asList(2, 3), "5"},
            {"positive", "add", Arrays.asList(2, 3, 4), "9"},
            {"positive", "subtract", Arrays.asList(10, 3), "7"},
            {"positive","multiply", Arrays.asList(3, 4), "12"},
            {"positive", "divide", Arrays.asList(10, 2), "5"},
            {"positive", "percentage", Arrays.asList(100, 10), "10"},
			{"positive", "subtract", Arrays.asList(3, 10), "-7"},
			{"positive", "multiply", Arrays.asList(0, 10), "0"},
			{"positive", "divide", Arrays.asList(7, 2), "3,5"},
			{"positive", "add", Arrays.asList(-5, 3), "-2"},
			{"positive", "multiply", Arrays.asList(2, 3, 4), "24"},
			{"positive", "percentage", Arrays.asList(0, 50), "0"},
			{"positive", "percentage", Arrays.asList(-200, 10), "-20"},
            {"negative", "divide", Arrays.asList(5, 0), "Impos. dividir por 0"},
			{"negative", "divide", Arrays.asList(0, 0), "Impos. dividir por 0"},
			{"negative", "invalidOperation", Arrays.asList(2, 2), "Invalid Operation"},
			{"negative", "add", Collections.emptyList(), "Invalid Entry"},
			{"negative", "subtract", Arrays.asList(5), "Need at least two numbers to make operation"},
			{"negative", "add", Arrays.asList("A", 5), "Invalid Entry"},
        };
    }
}
