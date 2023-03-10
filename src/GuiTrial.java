
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiTrial extends JFrame implements ActionListener {
    private JTextField passwordField;
    private JLabel strengthLabel;

    public void GUIButtonsSetting(JButton btn){
        btn.setBackground(Color.darkGray);
        btn.setForeground(Color.WHITE);
        Font fn = new Font("Times New Roman", Font.BOLD, 15);
        btn.setFont(fn);
        Cursor crs = new Cursor(Cursor.HAND_CURSOR);
        btn.setCursor(crs);
    }

    public GuiTrial() {
        Container conn1 = new Container();
        JButton StrengthChecker = new JButton("Strength Checker");
        GUIButtonsSetting(StrengthChecker);
        StrengthChecker.setBounds(300, 310, 220, 70);
        conn1.add(StrengthChecker);
        StrengthChecker.addActionListener(e -> {
                    if (StrengthChecker == e.getSource()) {
                        try {
                            String simplePasswd = JOptionPane.showInputDialog("Enter your Password");
                            if (!simplePasswd.isEmpty()) {
                                JLabel result = new JLabel(checkPasswordStrength(simplePasswd));
                                conn1.add(result);
                            } else JOptionPane.showMessageDialog(conn1, "Please enter password!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(conn1, ex.getMessage(), "EXIT", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public static String checkPasswordStrength(String password) {
        int length = password.length();
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;

        for (int i = 0; i < length; i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                hasSymbol = true;
            }
        }

        if (length < 8 || !hasUppercase || !hasLowercase || !hasDigit || !hasSymbol) {
            return "Weak";
        } else if (length >=10 && hasUppercase && hasLowercase && hasDigit && hasSymbol  ) {
            return "Strong";
        } else {
            return "Medium";
        }
    }

    public static void main(String[] args) {
        GuiTrial checker = new GuiTrial();
    }
}
