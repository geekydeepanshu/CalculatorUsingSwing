import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class CalculatorService implements ActionListener {
    CalculatorUI calculatorUI;
    StringBuffer calciDisplayText;
    StringBuffer calciMathematicalExpression;
    JButton sourceButton ;
    String sourceButtonText;
    double expressionResult;
    double answer;
    boolean isOperandFloatType = false;


    CalculatorService(){
        calciDisplayText = new StringBuffer();
        calciMathematicalExpression = new StringBuffer();
    }

    public void actionPerformed(ActionEvent event){
        Object sourceComponent = event.getSource();
        if(!(sourceComponent instanceof JButton))
            throw new RuntimeException("Oops! Something went wrong while parsing the event. Please check the event data and try again. If the issue persists, contact support for assistance.");

        sourceButton = (JButton) sourceComponent;
        sourceButtonText = sourceButton.getText();
        if(isButtonAnOperator() || isButtonNumeric()) {
            calciMathematicalExpression.append(sourceButtonText);
            calciDisplayText.append(sourceButtonText);
//            showExpressionOnDisplay();
        }
        else if (isEqualToButton()) {
                evaluateExpression();
                clearExpressionAndDisplayTextStrings();
                calciMathematicalExpression.append(expressionResult);
                calciDisplayText.append(expressionResult);
                answer = expressionResult;
                updateResultInExpressionAndDisplayTextStrings();
            } else if (isClearButton()) {
                 clearLastCharacterExpressionAndDisplayTextStrings();
            }
             else if(isAllClearButton()){
                 clearExpressionAndDisplayTextStrings();
             }
             else if(isAnswerButton()){
                 calciMathematicalExpression.append(answer);
                 calciDisplayText.append("Ans");

             }

             updateCalciUI();

    }





    public void updateCalciUI(){
        if(calciDisplayText.isEmpty()){
            calculatorUI.setEnableClearButton(false);
            calculatorUI.setEnableOperatorsButtons(false);
            calculatorUI.setEnableEqualsButton(false);
            calculatorUI.setEnableAllClearButton(false);
            calculatorUI.setEnableAllClearButton(false);
            calculatorUI.setEnableAnswerButton(false);
        }
        else if(isOperator(lastElement(calciMathematicalExpression))){
            calculatorUI.setEnableOperatorsButtons(false);
            calculatorUI.setEnableEqualsButton(false);
            calculatorUI.setEnableAllClearButton(false);
            calculatorUI.setEnableAllClearButton(true);
            calculatorUI.setEnableAnswerButton(true);
        }
        else{
            calculatorUI.setEnableClearButton(true);
            calculatorUI.setEnableOperatorsButtons(true);
            calculatorUI.setEnableEqualsButton(true);
            calculatorUI.setEnableAllClearButton(true);
            calculatorUI.setEnableAnswerButton(false);
        }
        updateCalciDisplay();
        System.out.println("Expresssion Result"+expressionResult);
    }


    public void evaluateExpression(){
        if (calciMathematicalExpression.isEmpty())
            throw new RuntimeException("invalid expression");
        if(isOperator(calciMathematicalExpression.charAt(0)) || isOperator(calciMathematicalExpression.charAt(calciMathematicalExpression.length()-1)))
        {
            throw new RuntimeException("invalid expression");
        }
        double operand=0;
        char lastOperator='+';
        double result=0;
        String temp;
        StringTokenizer expressionTokens = new StringTokenizer(String.valueOf(calciMathematicalExpression),"+-*/%",true);
        isAnyOperandFloatType();
        while(expressionTokens.hasMoreElements()){
            temp = expressionTokens.nextToken();
            System.out.print(temp+" ");
            if(isOperator(temp)){

                    lastOperator = temp.charAt(0);
            }
            else{
                operand = Double.parseDouble(temp);
                switch (lastOperator){
                    case '+':
                        result += operand;
                        break;
                    case '-':
                        result -= operand;;
                        break;

                    case '*':
                        result *= operand;
                        break;
                    case '/':
                        result /= operand;
                        break;
                    case '%' :
                        // percentage logic
                        break;
                    default:
                       throw new RuntimeException("Invalid operation on expression!");
                }
            }
        }
        System.out.println("Result"+result);
        expressionResult = result;
        System.out.println("Calculate expression Expression Result: "+expressionResult);
    }

    private void isAnyOperandFloatType() {
            StringTokenizer operandTokens = new StringTokenizer(String.valueOf(calciMathematicalExpression),"+-*/%",false);
            while(operandTokens.hasMoreElements()){
                if(operandTokens.nextToken().contains(".")){
                    isOperandFloatType = true;
                }
            }
            isOperandFloatType = false;
    }

    public void  updateResultInExpressionAndDisplayTextStrings(){
        clearExpressionAndDisplayTextStrings();
        calciDisplayText.append(expressionResult);
        calciMathematicalExpression.append(expressionResult);
    }

    public boolean isButtonAnOperator(){
        return (sourceButtonText.compareTo("+") == 0 || sourceButtonText.compareTo("-") == 0 || sourceButtonText.compareTo("*") == 0 || sourceButtonText.compareTo("/") == 0 || sourceButtonText.compareTo("%") == 0);
    }

    public boolean isButtonNumeric(){
        return (sourceButtonText.compareTo("0") == 0 || sourceButtonText.compareTo("1") == 0 || sourceButtonText.compareTo("2") == 0 || sourceButtonText.compareTo("3") == 0 || sourceButtonText.compareTo("4") == 0 || sourceButtonText.compareTo("5") == 0 || sourceButtonText.compareTo("6") == 0 || sourceButtonText.compareTo("7") == 0 || sourceButtonText.compareTo("8") == 0 || sourceButtonText.compareTo("9") == 0);
    }

    public boolean isEqualToButton(){
        return sourceButtonText.compareTo("=") == 0;
    }

    public boolean isClearButton(){
        return sourceButtonText.compareTo("C") == 0;
    }

    public boolean isAllClearButton(){
        return sourceButtonText.compareTo("AC")==0;
    }

    public boolean isAnswerButton(){
        return sourceButtonText.compareTo("ANS")==0;
    }

    public void  clearExpressionAndDisplayTextStrings(){
        calciMathematicalExpression.delete(0,calciMathematicalExpression.length());
        calciDisplayText.delete(0,calciDisplayText.length());
    }

    public void clearLastCharacterExpressionAndDisplayTextStrings(){
        calciMathematicalExpression.delete(calciMathematicalExpression.length()-1,calciMathematicalExpression.length());
        calciDisplayText.delete(calciDisplayText.length()-1,calciDisplayText.length());
    }


    public void showExpressionOnDisplay(){
        setCalciDisplay(String.valueOf(calciDisplayText));
    }

    public void showResultOnDisplay(){
        if(isOperandFloatType)
            setCalciDisplay(String.valueOf(expressionResult));
        else
            setCalciDisplay(String.valueOf((int)expressionResult));
    }

    public void setCalciDisplay(String displayText){
        calculatorUI.setCalculatorUIResultDisplay(displayText);
    }

    public void updateCalciDisplay(){
        calculatorUI.setCalculatorUIResultDisplay(String.valueOf(calciDisplayText));
    }

    public char lastElement(String str){
        return str.charAt(str.length()-1);
    }


    public char lastElement(StringBuffer strBuffer){
        return strBuffer.charAt(strBuffer.length()-1);
    }



    public boolean isOperator(String str){
        return str.compareTo("+")==0 || str.compareTo("-")==0 || str.compareTo("*")==0 || str.compareTo("/")==0 || str.compareTo("%")==0;
    }


    public boolean isOperator(char ch){
        return ch == '+' ||ch == '-'|| ch == '*' || ch == '/' || ch == '%';
    }

}



