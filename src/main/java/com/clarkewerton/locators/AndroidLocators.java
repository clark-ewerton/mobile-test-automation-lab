/*
 * MIT License
 *
 * Copyright (c) 2018 Elias Nogueira
  * Modified by Clark Ewerton in 2025
 * This file was adapted to test a calculator app using Appium and TestNG.
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
package com.clarkewerton.locators;

public class AndroidLocators {

    private AndroidLocators() {
        // Construtor privado para evitar instanciação
    }

    // Números
    public static final String DIGIT_0 = "com.google.android.calculator:id/digit_0";
    public static final String DIGIT_1 = "com.google.android.calculator:id/digit_1";
    public static final String DIGIT_2 = "com.google.android.calculator:id/digit_2";
    public static final String DIGIT_3 = "com.google.android.calculator:id/digit_3";
    public static final String DIGIT_4 = "com.google.android.calculator:id/digit_4";
    public static final String DIGIT_5 = "com.google.android.calculator:id/digit_5";
    public static final String DIGIT_6 = "com.google.android.calculator:id/digit_6";
    public static final String DIGIT_7 = "com.google.android.calculator:id/digit_7";
    public static final String DIGIT_8 = "com.google.android.calculator:id/digit_8";
    public static final String DIGIT_9 = "com.google.android.calculator:id/digit_9";

    // Operadores básicos
    public static final String OP_ADD = "com.google.android.calculator:id/op_add";
    public static final String OP_SUB = "com.google.android.calculator:id/op_sub";
    public static final String OP_MUL = "com.google.android.calculator:id/op_mul";
    public static final String OP_DIV = "com.google.android.calculator:id/op_div";
    public static final String OP_EQUALS = "com.google.android.calculator:id/eq";

    // Funções avançadas
    public static final String OP_SQRT = "com.google.android.calculator:id/op_sqrt";
    public static final String OP_POW = "com.google.android.calculator:id/op_pow";
    public static final String OP_PERCENT = "com.google.android.calculator:id/op_pct";

    // Controles
    public static final String CLEAR = "com.google.android.calculator:id/clr";
    public static final String DELETE = "com.google.android.calculator:id/del";
    public static final String DECIMAL = "com.google.android.calculator:id/dec_point";

    // Displays
    public static final String RESULT = "com.google.android.calculator:id/result_final";
    public static final String FORMULA = "com.google.android.calculator:id/formula";
    public static final String RESULT_PREVIEW = "com.google.android.calculator:id/result_preview";
}
