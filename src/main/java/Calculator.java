public class Calculator {
    public static void main(String[] args) {
        CalculatorService calculatorService = new CalculatorService();
        Thread calculatorUIThread = new Thread(){
            public void run (){
                calculatorService.calculatorUI =  new CalculatorUI(calculatorService);
            }
        };
        calculatorUIThread.start();
    }
}
