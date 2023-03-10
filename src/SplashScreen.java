import javax.swing.*;
import java.awt.*;

public class SplashScreen {
    JFrame frame;
    JLabel image = new JLabel(new ImageIcon("img/icon.png"));
    JLabel text = new JLabel("Password Manager");
    JLabel text2 = new JLabel("Mini project");
    JProgressBar progressBar = new JProgressBar();
    JLabel message = new JLabel();
    SplashScreen(){
        createGUI();
        addImage();
        addText();
        addProgressBar();
        addMessage();
        addTitle();
        runningBar();
    }

    public void createGUI(){
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
    }

    public void addImage(){
        image.setSize(600,200);
        frame.add(image);
    }

    public void addText(){
        text.setFont(new Font("times new roman",Font.BOLD,30));
        text.setBounds(130,200,600,60);
        text.setForeground(Color.WHITE);
        frame.add(text);
    }

    public void addTitle(){
        text2.setFont(new Font("times new roman",Font.BOLD,17));
        text2.setBounds(20,370,400,20);
        text2.setForeground(Color.WHITE);
        frame.add(text2);
    }

    public void addMessage(){
        message.setBounds(230,320,200,40);
        message.setFont(new Font("times new roman",Font.BOLD,15));
        message.setForeground(Color.BLACK);
        frame.add(message);
    }

    public void addProgressBar(){
        progressBar.setBounds(100,280,400,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.BLACK);
        progressBar.setForeground(Color.GRAY);
        progressBar.setValue(0);
        frame.add(progressBar);
    }
    public void runningBar(){
        int i=0;
        while(i<=100){
            try{
                Thread.sleep(40);
                progressBar.setValue(i);
                message.setText("Loading...("+(i)+"%)");
                i++;
                if(i==100)
                    frame.dispose();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
//    public static void main(String[] args) {
//        new SplashScreen();
//    }
}
