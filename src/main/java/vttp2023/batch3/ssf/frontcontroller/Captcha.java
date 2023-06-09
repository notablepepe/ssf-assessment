package vttp2023.batch3.ssf.frontcontroller;

import java.util.Random;

public class Captcha {

    private int num1;
    private int num2;
    private String operand;

    private static String[] operands = {"+", "-", "/", "*"};

    public Captcha() {
        Random random = new Random();
        this.num1 = random.nextInt(50 - 1) + 1;
        this.num2 = random.nextInt(50 - 1 ) + 1;
        int randomIndex = random.nextInt(3 - 0) + 0;
        this.operand = operands[randomIndex];
    }

    public int getCaptchaResult() {
        String operation = this.operand;
        int result = 0;
        switch(operation) {
            case "+" : 
                result = num1 + num2;
                break;
            case "-" : 
                result = num1 - num2;
                break; 
            case "*" : 
                result = num1 * num2;
                break;
            case "/" : 
                result = num1 / num2;
                break;           
        }
        return result;
    }
}
