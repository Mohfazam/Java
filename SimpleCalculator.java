import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends Frame implements ActionListener {
    TextField display;
    String operator = "";
    double num1 = 0, num2 = 0;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setLayout(new BorderLayout());

        // Text field at the top
        display = new TextField();
        add(display, BorderLayout.NORTH);

        // Panel with buttons
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "%", "=", "C"
        };

        for (String b : buttons) {
            Button btn = new Button(b);
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);

        setSize(300, 300);
        setVisible(true);

        // Close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("C")) {
            display.setText("");
            operator = "";
            num1 = num2 = 0;
        } else if (cmd.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "%": result = num1 % num2; break;
            }

            display.setText(String.valueOf(result));
            operator = "";
        } else if ("+-*%".contains(cmd)) {
            num1 = Double.parseDouble(display.getText());
            operator = cmd;
            display.setText("");
        } else {
            display.setText(display.getText() + cmd);
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}


