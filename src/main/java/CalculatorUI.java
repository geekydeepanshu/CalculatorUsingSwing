import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class CalculatorUI {
    JFrame  mainFrame;
    JPanel resultDisplayPanel;
    JPanel buttonsPanel;
    JTextArea resultDisplayTextArea;
    JButton[] numericButtons;
    JButton addButton;
    JButton subtractButton;
    JButton multiplyButton;
    JButton divideButton;
    JButton clearButton;
    JButton dotButton;
    JButton allClearButton;
    JButton percentageButton;
    JButton equalsButton;
    JButton answerButton;

    CalculatorUI(ActionListener actionListener){

        // customs colors for Ui

        Color customDarkGrayColor = new Color(28, 28, 28, 150);
        Color customButtonBackgroundColor = new Color(20, 20, 20, 150);
        Color customLightWhiteTextColor = new Color(235, 235, 235,150);


        mainFrame = new JFrame();
        mainFrame.setSize(200,350);
        mainFrame.setResizable(false);
//        mainFrame.getContentPane().setBackground(customDarkGrayColor);

        resultDisplayTextArea = new JTextArea();
        resultDisplayTextArea.setPreferredSize(new Dimension(190,72));
        resultDisplayTextArea.setMargin(new Insets(5,5,5,5));
////        resultDisplayTextArea.setBackground(customDarkGrayColor);
//        resultDisplayTextArea.setForeground(customLightWhiteTextColor);



        resultDisplayPanel = new JPanel();
//        resultDisplayPanel.setSize(196,50);
////        resultDisplayPanel.setBackground(customDarkGrayColor);
        resultDisplayPanel.add(resultDisplayTextArea);


        buttonsPanel = new JPanel(new GridLayout(5,4,1,1));
        buttonsPanel.setPreferredSize(new Dimension(190,236));
//        buttonsPanel.setBackground(customDarkGrayColor);

        numericButtons = new JButton[10];
        for(int i=0;i<10;i++){
            numericButtons[i] = new JButton(String.valueOf(i));
            numericButtons[i].setPreferredSize(new Dimension(12,12));
//            numericButtons[i].setForeground(customLightWhiteTextColor);
//            numericButtons[i].setBackground(customButtonBackgroundColor);
            numericButtons[i].setOpaque(true);
            numericButtons[i].addActionListener(actionListener);
//            numericButtons[i].setBorderPainted(false);
        }
        addButton = new JButton("+");
        addButton.addActionListener(actionListener);
//        addButton.setSize(12,12);
//        addButton.setBackground(customButtonBackgroundColor);
//        addButton.setForeground(customLightWhiteTextColor);


        subtractButton = new JButton("-");
        subtractButton.addActionListener(actionListener);
//        subtractButton.setSize(12,12);
//        subtractButton.setBackground(customButtonBackgroundColor);
//        subtractButton.setForeground(customLightWhiteTextColor);


        multiplyButton = new JButton("*");
        multiplyButton.addActionListener(actionListener);
//        multiplyButton.setSize(12,12);
//        multiplyButton.setBackground(customButtonBackgroundColor);
//        multiplyButton.setForeground(customLightWhiteTextColor);


        divideButton = new JButton("/");
        divideButton.addActionListener(actionListener);
//        divideButton.setSize(12,12);
//        divideButton.setBackground(customButtonBackgroundColor);
//        divideButton.setForeground(customLightWhiteTextColor);


        clearButton = new JButton("C");
        clearButton.addActionListener(actionListener);
//        clearButton.setSize(12,12);
//        clearButton.setBackground(customButtonBackgroundColor);
//        clearButton.setForeground(customLightWhiteTextColor);


        dotButton = new JButton(".");
        dotButton.addActionListener(actionListener);
//        dotButton.setSize(12,12);
//        dotButton.setBackground(customButtonBackgroundColor);
//        dotButton.setForeground(customLightWhiteTextColor);
        allClearButton = new JButton("AC");
        allClearButton.addActionListener(actionListener);

        percentageButton = new JButton("%");
        percentageButton.addActionListener(actionListener);

        answerButton = new JButton("ANS");
        answerButton.addActionListener(actionListener);

        equalsButton = new JButton("=");
        equalsButton.addActionListener(actionListener);

        buttonsPanel.add(clearButton);
        buttonsPanel.add(allClearButton);
        buttonsPanel.add(percentageButton);
        buttonsPanel.add(divideButton);
        buttonsPanel.add(numericButtons[7]);
        buttonsPanel.add(numericButtons[8]);
        buttonsPanel.add(numericButtons[9]);
        buttonsPanel.add(multiplyButton);
        buttonsPanel.add(numericButtons[4]);
        buttonsPanel.add(numericButtons[5]);
        buttonsPanel.add(numericButtons[6]);
        buttonsPanel.add(subtractButton);
        buttonsPanel.add(numericButtons[1]);
        buttonsPanel.add(numericButtons[2]);
        buttonsPanel.add(numericButtons[3]);
        buttonsPanel.add(addButton);
        buttonsPanel.add(answerButton);
        buttonsPanel.add(numericButtons[0]);
        buttonsPanel.add(dotButton);
        buttonsPanel.add(equalsButton);
        




        mainFrame.add(resultDisplayPanel,BorderLayout.NORTH);
        mainFrame.add(buttonsPanel,BorderLayout.SOUTH);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // calci initial ui
        setEnableOperatorsButtons(false);
        setEnableEqualsButton(false);
        setEnableClearButton(false);
        setEnableAllClearButton(false);
        setEnableAnswerButton(false);
        setEnableDotButton(false);


        mainFrame.setVisible(true);

    }

    public void setCalculatorUIResultDisplay(String displayText){
        resultDisplayTextArea.setText(displayText);
    }


    public void setEnableOperatorsButtons(boolean isEnable){
        addButton.setEnabled(isEnable);
        subtractButton.setEnabled(isEnable);
        multiplyButton.setEnabled(isEnable);
        divideButton.setEnabled(isEnable);
        percentageButton.setEnabled(isEnable);
    }

    public void setEnableNumericButtons(boolean isEnable){
        for(int i=0;i<numericButtons.length;i++)
            numericButtons[i].setEnabled(isEnable);
    }

    public void setEnableClearButton(boolean isEnable){
        clearButton.setEnabled(isEnable);
    }

    public void setEnableEqualsButton(boolean isEnable){
        equalsButton.setEnabled(isEnable);
    }

    public void setEnableAllClearButton(boolean isEnable){
        allClearButton.setEnabled(isEnable);
    }

    public void setEnableAnswerButton(boolean isEnable){
        answerButton.setEnabled(isEnable);
    }

    public void setEnableDotButton(boolean isEnable){
        dotButton.setEnabled(isEnable);
    }

}
