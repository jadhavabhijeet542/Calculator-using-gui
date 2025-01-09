import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Create a text field
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(textField, BorderLayout.NORTH);

        // Create buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Buttons for calculator
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        // Add buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            panel.add(button);

            // Add action listener for buttons
            button.addActionListener(new ActionListener() {
                private String operator = "";
                private double firstNumber = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    String buttonText = button.getText();

                    if ("0123456789".contains(buttonText)) {
                        textField.setText(textField.getText() + buttonText);
                    } else if ("/*-+".contains(buttonText)) {
                        firstNumber = Double.parseDouble(textField.getText());
                        operator = buttonText;
                        textField.setText("");
                    } else if ("=".equals(buttonText)) {
                        double secondNumber = Double.parseDouble(textField.getText());
                        double result = 0;
                        switch (operator) {
                            case "+": result = firstNumber + secondNumber; break;
                            case "-": result = firstNumber - secondNumber; break;
                            case "*": result = firstNumber * secondNumber; break;
                            case "/": result = secondNumber != 0 ? firstNumber / secondNumber : 0; break;
                        }
                        textField.setText(String.valueOf(result));
                    } else if ("C".equals(buttonText)) {
                        textField.setText("");
                        firstNumber = 0;
                        operator = "";
                    }
                }
            });
        }

        frame.add(panel, BorderLayout.CENTER);

        // Make frame visible
        frame.setVisible(true);
    }
}
