import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Records extends JFrame {
    JPanel subpannel;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    int y=90;
    static final int screenheight = 500;
    static final int screenwidth = 1000;
    Records() {
        this.setTitle("Ping Pong");
        this.setLayout(null);
        Font font = new Font("Helvetica", Font.BOLD, 15);
        //Center Title
        Font font2=new Font("Helvetica", Font.ITALIC, 30);
        JLabel CenterTitle =new JLabel("Records");
        CenterTitle.setBounds((screenwidth/2)-50,20,300,50);
        CenterTitle.setFont(font2);
        CenterTitle.setForeground(Color.WHITE);

        JPanel parentPanel = new JPanel(new GridLayout(1, 3));
        parentPanel.setBounds(10,70,970,20);

        JLabel label1 = new JLabel("Player Red");
        JLabel label2 = new JLabel("Player Blue");
        JLabel label3 = new JLabel("Winner");

        parentPanel.add(label1);
        parentPanel.add(label2);
        parentPanel.add(label3);

        this.getContentPane().setBackground(Color.BLACK);
        this.setSize(screenwidth, screenheight);
        this.add(CenterTitle);
        this.add(parentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        readingObject();
    }

    void readingObject() {
    Reader game;
    Object o;
        try {
            FileInputStream fs=new FileInputStream("D:\\GUI Projects\\PingPongGame\\score.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fs);

            while (true) {
                try {

                     o=  objectInputStream.readObject();
                    if (o instanceof Reader) {
                        game=(Reader) o;
                        print(game);
                    }

                } catch (EOFException e) {
                    break;
                }

            }

            objectInputStream.close();
            fs.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    void print(Reader game){
        subpannel=new JPanel(new GridLayout(1,3));
        subpannel.setBounds(10,y,970,20);
        subpannel.setBackground(Color.BLACK);
        label1=new JLabel(game.getBluename());
        label1.setForeground(Color.white);
        label2=new JLabel(game.getRedname());
        label2.setForeground(Color.white);
        label3=new JLabel(game.getWinner());
        label3.setForeground(Color.white);
        subpannel.add(label1);
        subpannel.add(label2);
        subpannel.add(label3);
        this.add(subpannel);
        y+=20;
    }
}
