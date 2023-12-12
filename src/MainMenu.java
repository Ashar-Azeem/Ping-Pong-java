import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    static final int screenheight = 500;
    static final int screenwidth = 1000;
    JButton startButton = new JButton(" Start Game");
    JButton exitButton = new JButton("  Exit");
    JButton Record=new JButton("Record");
    JButton Highest=new JButton("Highest");


    MainMenu() {
        this.setTitle("Ping Pong");
        this.setLayout(null);
        Font font = new Font("Helvetica", Font.BOLD, 20);
        //Bottom Credit
        Font font1=new Font("Helvetica", Font.ITALIC, 15);
        JLabel bottomText = new JLabel("Developed by: Ashar Azeem");
        bottomText.setBounds(750,420,250,30);
        bottomText.setFont(font1);
        bottomText.setForeground(Color.lightGray);
        //Center Title
        Font font2=new Font("Helvetica", Font.ITALIC, 30);
        JLabel CenterTitle =new JLabel("Ping Pong");
        CenterTitle.setBounds((screenwidth/2)-40,50,300,50);
        CenterTitle.setFont(font2);
        CenterTitle.setForeground(Color.decode("#FFFFFF"));
        //Start button
        startButton.setBounds(60, (screenheight / 2) - 30, 150, 40);
        startButton.setBackground(Color.decode("#0F256E"));
        startButton.setForeground(Color.WHITE);  // Set the text color of the startButton
        startButton.setFocusable(false);
        startButton.setFont(font);
        //Exit button
        exitButton.setBounds(60, (screenheight / 2 + 90), 150, 40);
        exitButton.setBackground(Color.decode("#0F256E"));
        exitButton.setForeground(Color.WHITE);  // Set the text color of the exitButton
        exitButton.setFocusable(false);
        exitButton.setFont(font);
        //Record button
        Record.setBounds(60, (screenheight / 2 + 30), 150, 40);
        Record.setBackground(Color.decode("#0F256E"));
        Record.setForeground(Color.WHITE);  // Set the text color of the Record
        Record.setFocusable(false);
        Record.setFont(font);
        //Highest
        Highest.setBounds(60, (screenheight / 2 + 150), 150, 40);
        Highest.setBackground(Color.decode("#0F256E"));
        Highest.setForeground(Color.WHITE);  // Set the text color of the Highest
        Highest.setFocusable(false);
        Highest.setFont(font);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Names();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Record.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Records();
            }
        });
        Highest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Highest();
            }
        });

        this.setSize(screenwidth, screenheight);
        JLabel background = new JLabel(new ImageIcon("image 2.png"));
        background.setBounds(0, 0, getWidth(), getHeight());
        this.add(CenterTitle);
        this.add(bottomText);
        this.add(startButton);
        this.add(exitButton);
        this.add(Record);
        this.add(Highest);
        this.add(background);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
