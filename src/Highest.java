import javax.swing.*;
import java.awt.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Highest extends JFrame{
    JPanel subpannel;
    JLabel label1;
    JLabel label2;
    int y=90;
    static final int screenheight = 500;
    static final int screenwidth = 1000;


    Highest(){
        this.setTitle("Ping Pong");
        this.setLayout(null);
        Font font = new Font("Helvetica", Font.BOLD, 15);
        //Center Title
        Font font2=new Font("Helvetica", Font.ITALIC, 30);
        JLabel CenterTitle =new JLabel("Highest");
        CenterTitle.setBounds((screenwidth/2)-50,20,300,50);
        CenterTitle.setFont(font2);
        CenterTitle.setForeground(Color.WHITE);

        JPanel parentPanel = new JPanel(new GridLayout(1, 2));
        parentPanel.setBounds(10,70,970,20);

        JLabel label1 = new JLabel("Name ");
        JLabel label2 = new JLabel("score");

        parentPanel.add(label1);
        parentPanel.add(label2);

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
    void readingObject(){
        String name=null;
        int maxi=0;
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
                        if (game.getScore1()>maxi){
                            maxi=game.getScore1();
                            name=game.getBluename();
                        }

                        else if (game.getScore2()>maxi){
                            maxi=game.getScore2();
                            name=game.getRedname();
                        }
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
        subpannel=new JPanel(new GridLayout(1,2));
        subpannel.setBounds(10,100,970,20);
        label1=new JLabel(name);
        label2=new JLabel(String.valueOf(maxi));
        subpannel.add(label1);
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);
        subpannel.add(label2);
        subpannel.setBackground(Color.black);
        this.add(subpannel);
    }
}
