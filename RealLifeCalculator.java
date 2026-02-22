import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RealLifeCalculator extends JFrame implements ActionListener {

    JTextField display;
    double num1 = 0, num2 = 0, result = 0;
    String operator = "";

    RealLifeCalculator() {
        setTitle("Real Life Calculator");
        setSize(350, 450);
        setLayout(new BorderLayout(5,5));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons
        String buttons[] = {
                "C","DEL","%","/",
                "7","8","9","*",
                "4","5","6","-",
                "1","2","3","+",
                "0",".","=","√",
                "x²","1/x"
        };

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,4,5,5));

        for (String b : buttons) {
            JButton btn = new JButton(b);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            // Numbers and decimal
            if ((cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') || cmd.equals(".")) {
                display.setText(display.getText() + cmd);
            }

            // Clear
            else if (cmd.equals("C")) {
                display.setText("");
                num1 = num2 = result = 0;
                operator = "";
            }

            // Delete last digit
            else if (cmd.equals("DEL")) {
                String s = display.getText();
                if (!s.isEmpty())
                    display.setText(s.substring(0, s.length() - 1));
            }

            // Equals
            else if (cmd.equals("=")) {
                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": result = num1 / num2; break;
                    case "%": result = num1 % num2; break;
                }

                display.setText(String.valueOf(result));
            }

            // Square root
            else if (cmd.equals("√")) {
                num1 = Double.parseDouble(display.getText());
                result = Math.sqrt(num1);
                display.setText(String.valueOf(result));
            }

            // Square
            else if (cmd.equals("x²")) {
                num1 = Double.parseDouble(display.getText());
                result = Math.pow(num1, 2);
                display.setText(String.valueOf(result));
            }

            // Reciprocal
            else if (cmd.equals("1/x")) {
                num1 = Double.parseDouble(display.getText());
                result = 1 / num1;
                display.setText(String.valueOf(result));
            }

            // Operators
            else {
                num1 = Double.parseDouble(display.getText());
                operator = cmd;
                display.setText("");
            }

        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        new RealLifeCalculator();
    }
}