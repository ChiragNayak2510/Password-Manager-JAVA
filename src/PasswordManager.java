import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PasswordManager implements ActionListener {
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
    //Store password class reference
    HashtablePassword data = new HashtablePassword(15,0.5F,0);

    JFrame frame;
    JFrame frame2;
    Container conn1,conn2;
    JLabel lAcc,lPass;
    JTextArea encryptPasswdArea, genePassArea, searchPassArea;
    JButton PassGeneBtn,PassEncryptBtn, PassStoreBtn, PassSearchBtn, AccAddBtn, PassDeleteBtn,StrengthCheckBtn;
    JTextField tAcc,tPass;

    @Override
    public void actionPerformed(ActionEvent e) { }

    //Frame settings
    public static void FrameGUI(JFrame frame){
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
    }

    //container settings
    public static void ContainerGUI(Container conn){
        conn.setVisible(true);
        conn.setBackground(Color.lightGray);
        conn.setLayout(null);
    }


    // buttons settings
    public void GUIButtonsSetting(JButton btn){
        btn.setBackground(Color.darkGray);
        btn.setForeground(Color.WHITE);
        Font fn = new Font("Times New Roman", Font.BOLD, 15);
        btn.setFont(fn);
        Cursor crs = new Cursor(Cursor.HAND_CURSOR);
        btn.setCursor(crs);
    }

    //GUI of Store password
    public void StoringGUI()
    {
        frame2 = new JFrame("Store your passwords");
        frame2.setBounds(1400, 700, 600, 500);
        frame2.setSize(500,400);
        FrameGUI(frame2);
        conn2 = frame2.getContentPane();
        ContainerGUI(conn2);
        Font fn = new Font("Times New Roman", Font.BOLD, 20);

        //Account textFiled and label
        lAcc = new JLabel("Enter Account Name");
        lAcc.setBounds(100, 60, 250, 20);
        lAcc.setFont(fn);
        lAcc.setForeground(Color.darkGray);
        conn2.add(lAcc);

        tAcc = new JTextField();
        tAcc.setBounds(100,90,300,40);
        tAcc.setFont(fn);
        tAcc.setForeground(Color.BLACK);
        conn2.add(tAcc);

        //Account password textField and label
        lPass = new JLabel("Enter Account Password");
        lPass.setBounds(100, 160, 300, 40);
        lPass.setFont(fn);
        lPass.setForeground(Color.darkGray);
        conn2.add(lPass);

        tPass = new JTextField();
        tPass.setBounds(100,200,300,40);
        tPass.setFont(fn);
        tPass.setForeground(Color.RED);
        conn2.add(tPass);

        AccAddBtn = new JButton("STORE");
        AccAddBtn.setBounds(170, 290, 150, 50);
        conn2.add(AccAddBtn);
        GUIButtonsSetting(AccAddBtn);
    }

    //for password generator and encryption
    public void textArea(String Pass,JTextArea TA){
        TA.setText(Pass);
        Font fn = new Font("Times new roman", Font.BOLD, 20);
        TA.setWrapStyleWord(true);
        TA.setLineWrap(true);
        TA.setCaretPosition(0);
        TA.setEditable(false);
        TA.setFont(fn);
    }

    PasswordManager() {
        frame = new JFrame("Password Manager");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));
        frame.setBounds(300, 100, 200, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560,690);
        FrameGUI(frame);

        conn1 = frame.getContentPane();
        ContainerGUI(conn1);

        Container c = frame.getContentPane(); //Gets the content layer
        JLabel label10 = new JLabel(); //JLabel Creation
        label10.setIcon(new ImageIcon("C:\\Users\\HP-PC\\Downloads\\Password-manager.png")); //Sets the image to be displayed as an icon
        Dimension size = label10.getPreferredSize(); //Gets the size of the image
        label10.setBounds(85, 102, size.width, size.height); //Sets the location of the image
        conn1.add(label10);
//        JLabel jLabel3 = new JLabel();
//        jLabel3.setIcon(new javax.swing.ImageIcon("./image.jpg")); // NOI18N
//        jLabel3.setPreferredSize(new java.awt.Dimension(150, 150));
//        conn1.add(jLabel3);

        //Generator buttons settings
        JLabel head = new JLabel("PASSWORD MANAGER");
        head.setBounds(135,7,350,100);
        head.setForeground(Color.DARK_GRAY);
        head.setFont(new Font("Times new roman",Font.BOLD,24));
        conn1.add(head);

        PassGeneBtn = new JButton("GENERATE PASSWORD");
        PassGeneBtn.setBounds(20, 310, 220, 70);
        conn1.add(PassGeneBtn);
        GUIButtonsSetting(PassGeneBtn);

        StrengthCheckBtn = new JButton("PASSWORD STRENGTH CHECKER");
        StrengthCheckBtn.setBounds(300, 500, 220, 70);
        conn1.add(StrengthCheckBtn);
        GUIButtonsSetting(StrengthCheckBtn);
//----------------------------------------------------------------------------------------------------------------------
        //generating password
        PassGeneBtn.addActionListener(e -> {
                    if(PassGeneBtn ==e.getSource())
                    {
                        try{
                                //  password generator class reference
                                PasswordGenerator pass = new PasswordGenerator();
                                String passwd = pass.generatePassword(12);
                                genePassArea = new JTextArea(5,4);
                                textArea(passwd,genePassArea);
                                JOptionPane.showMessageDialog(conn1,new JScrollPane(genePassArea),"Copy your password",JOptionPane.INFORMATION_MESSAGE);
                        }
                        catch(Exception ex){JOptionPane.showMessageDialog(conn1,ex.getMessage(),"EXIT!",JOptionPane.ERROR_MESSAGE);}
                    }
                }
        );
        //----------------------------------------------------------------------------------------------------------------------
        //Encryption Button
        PassEncryptBtn = new JButton("ENCRYPT PASSWORD");
        GUIButtonsSetting(PassEncryptBtn);
        PassEncryptBtn.setBounds(300, 310, 220, 70);
        conn1.add(PassEncryptBtn);
        PassEncryptBtn.addActionListener(e -> {
                    if (PassEncryptBtn == e.getSource()) {
                        try {
                            String simplePasswd = JOptionPane.showInputDialog("Enter your Password");
                            if (!simplePasswd.isEmpty()) {
                                byte[] salt = passwordEncryption.getSalt();
                                String encPass = passwordEncryption.getSecurePassword(simplePasswd, salt);
                                //txtArea adding in the panel
                                encryptPasswdArea = new JTextArea(7, 4);
                                textArea(encPass, encryptPasswdArea);
                                JOptionPane.showMessageDialog(conn1, new JScrollPane(encryptPasswdArea), "Copy your Encrypted password", JOptionPane.INFORMATION_MESSAGE);
                            } else JOptionPane.showMessageDialog(conn1, "Please enter password!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(conn1, ex.getMessage(), "EXIT", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );

//----------------------------------------------------------------------------------------------------------------------
        //storing password using hashtable
        PassStoreBtn = new JButton("STORE PASSWORD");
        PassStoreBtn.setBounds(20, 410, 220, 70);
        conn1.add(PassStoreBtn);
        GUIButtonsSetting(PassStoreBtn);
        //Store password action
        PassStoreBtn.addActionListener(e -> {
                    if(PassStoreBtn ==e.getSource())
                    {
                        try{
                            StoringGUI();
                            // action on the Store btn
                            AccAddBtn.addActionListener(e4 -> {
                                        if (AccAddBtn == e4.getSource()) {
                                            String account_name = tAcc.getText();
                                            String acc_pass = tPass.getText();
                                            if (account_name.isEmpty() && acc_pass.isEmpty()) {
                                                JOptionPane.showMessageDialog(conn2,"unable to store your password!","ERROR",JOptionPane.ERROR_MESSAGE);
                                            }
                                            else{
                                                //calling put method of the hashtablePassword class
                                                data.addAccount(account_name,acc_pass);
                                                JOptionPane.showMessageDialog(conn2, "Account added Successfully !");
                                                tAcc.setText(null);
                                                tPass.setText(null);
                                            }
                                        }
                                    }
                            );
                        }
                        catch(Exception ex) {JOptionPane.showMessageDialog(conn2,ex.getMessage(),"EXIT",JOptionPane.ERROR_MESSAGE);}
                    }
                }
        );

//----------------------------------------------------------------------------------------------------------------------
        //searching password
        PassSearchBtn = new JButton("SEARCH PASSWORD");
        GUIButtonsSetting(PassSearchBtn);
        PassSearchBtn.setBounds(300, 410, 220, 70);
        conn1.add(PassSearchBtn);
        PassSearchBtn.addActionListener(e ->{
                    if (PassSearchBtn ==e.getSource()){
                        try{
                            String acc_name = JOptionPane.showInputDialog("Enter your Account Name");
                            if (!acc_name.isEmpty()) {
                                Object pass = data.getAccount(acc_name.toLowerCase());
                                if(pass!=null) {
                                    searchPassArea = new JTextArea(4,5);
                                    textArea(String.valueOf(pass), searchPassArea);
                                    JOptionPane.showMessageDialog(conn1, new JScrollPane(searchPassArea), "Copy your password", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else JOptionPane.showMessageDialog(conn1, "Account not Found!");
                            }
                        }
                        catch (Exception ex){
                            JOptionPane.showMessageDialog(conn1,ex.getMessage(),"EXIT",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );
//----------------------------------------------------------------------------------------------------------------------
        // deleting password
        PassDeleteBtn = new JButton("DELETE PASSWORD");
        GUIButtonsSetting(PassDeleteBtn);
        PassDeleteBtn.setBounds(20, 500, 220, 70);
        conn1.add(PassDeleteBtn);
        PassDeleteBtn.addActionListener(e -> {
                    if (PassDeleteBtn == e.getSource()) {
                        try {
                            String acc_name = JOptionPane.showInputDialog("Enter the Account Name");
                            if (!acc_name.isEmpty()) {
                                data.removeAccount(acc_name.toLowerCase());
                                JOptionPane.showMessageDialog(conn1, "Delete successfully!");
                            }
                            else JOptionPane.showMessageDialog(conn1, "Account not found!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(conn1, ex.getMessage(), "EXIT", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }
        );

        //Strength Checker
        StrengthCheckBtn.addActionListener(e -> {
                    if (StrengthCheckBtn == e.getSource()) {
                        try {
                            String simplePasswd = JOptionPane.showInputDialog("Enter your Password");
                            if (!simplePasswd.isEmpty()) {
                                JOptionPane.showMessageDialog(conn1, checkPasswordStrength(simplePasswd));
                            } else JOptionPane.showMessageDialog(conn1, "Please enter password!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(conn1, ex.getMessage(), "EXIT", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );
    }
    //----------------------------------------------------------------------------------------------------------------------------
    // main method
    public static void main(String[] args) {
        //loading screen class
        //new SplashScreen();
        try {
            //UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
            new PasswordManager();
        }catch (Exception ex) { ex.printStackTrace(); }
    }
}
