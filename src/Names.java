import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Names extends JFrame {
    static final int screenheight = 500;
    static final int screenwidth = 1000;
    JTextField Screen1;
    JTextField Screen2;
    private String RedName;
    private String BlueName;
    Names(){
        this.setTitle("Ping Pong");
        this.setLayout(null);
        this.setSize(screenwidth, screenheight);
        JLabel background = new JLabel(new ImageIcon("image 2.png"));
        background.setBounds(0, 0, getWidth(), getHeight());
        Screen1=new JTextField();
        Screen2=new JTextField();
        Font font2=new Font("Helvetica", Font.BOLD, 15);
        JLabel text=new JLabel("Player Blue Name:");
        text.setBounds(10,175,150,15);
        text.setFont(font2);
        text.setForeground(Color.WHITE);
        Screen1.setForeground(Color.white);
        Screen1.setBounds(10, 200, 200, 30);
        Screen1.setBackground(Color.decode("#0F256E"));
        //changes color of the typing blinker
        Screen1.setCaretColor(Color.white);

        JLabel text2=new JLabel("Player Red Name:");
        text2.setBounds(10,250,130,15);
        text2.setFont(font2);
        text2.setForeground(Color.WHITE);
        Screen2.setForeground(Color.white);
        Screen2.setBounds(10, 275, 200, 30);
        Screen2.setBackground(Color.decode("#0F256E"));
        Screen2.setCaretColor(Color.white);
        Screen2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    BlueName=Screen1.getText();
                    RedName=Screen2.getText();
                    try {

                        new Jframe(BlueName,RedName);

                    } catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    dispose();
                }

            }
        });
        this.add(text);
        this.add(Screen1);
        this.add(text2);
        this.add(Screen2);
        this.add(background);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}

