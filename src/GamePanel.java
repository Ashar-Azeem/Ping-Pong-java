import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, Serializable {
    private static final int screenheight = 500;
    private static final int screenwidth = 1000;
    private static final  int delay = 2;
    private static final int paddleLength = 100;
    private static final int paddleWidth = 30;
    private static final int ballWidth = 30;
    private static int player1Score;
    private static int player2Score;
    private Timer timer;
    private static int ballXDirection;
    private static int ballYDirection;
    private static int paddle1YDirection;
    private static int paddle1XDirection;
    private static int paddle2YDirection;
    private static int paddle2XDirection;
    private boolean running = false;
    private Random random;
    private int x;
    private int y;
    private int storeY1=0;
    private int storeY2=0;
    private boolean check1=false;
    private boolean check2=false;
    private boolean player1specialAbility=false;
    private boolean player2specialAbility=false;
    private static int scoreholder1=0;
    private static int scoreholder2=0;
    private String Bluename;
    private String Redname;
    private String winner;
    private Clip clip;
    private boolean BallChange1=false;
    private boolean BallChange2=false;
    private Image image;

    GamePanel(String blue,String red) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Bluename=blue;
        Redname=red;
        random = new Random();
        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        //Reading Audio
        File Audio=new File("D:\\GUI Projects\\PingPongGame\\PaddleTick.wav");
        AudioInputStream Ao=AudioSystem.getAudioInputStream(Audio);
        clip= AudioSystem.getClip();
        //Audio stored
        clip.open(Ao);
        image=new ImageIcon("D:\\GUI Projects\\PingPongGame\\firingball50x50.gif").getImage();

        startgame();
    }

    public String getWinner() {
        return winner;
    }

    public String getRedname() {
        return Redname;
    }

    public String getBluename() {
        return Bluename;
    }

    public void startgame() {
        //Direction in which ball will move in the start
        BallXDirection();
        BallYDirection();
        //Ball current Middle position;
        newBall();
        newPaddles();
        running = true;
        timer = new Timer(delay, this); //Calls action performed after each timer delay, so action performed method will have change
        timer.start();
    }

    // This method is executed automatically after rebuilt method is executed.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if(running) {
            //Drawing paddle 1.
            //If special move is ready then RGB color paddle will appear.
            if (scoreholder1>=25){
                g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            }
            else {
                g.setColor(Color.blue);
            }
            g.fillRect(paddle1XDirection, paddle1YDirection, paddleWidth, paddleLength);

            //Drawing paddle 2
            if (scoreholder2>=25){
                g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            }
            else {
                g.setColor(Color.red);
            }
            g.fillRect(paddle2XDirection, paddle2YDirection, paddleWidth, paddleLength);
            //Drawing ball
            //Changing ball colors as RGB when special move is performed

            if (BallChange1 || BallChange2) {
                g.drawImage(image, ballXDirection, ballYDirection, this);
            }
            else {
                g.setColor(Color.white);
                g.fillOval(ballXDirection, ballYDirection, ballWidth, ballWidth);
            }

            //score of player 1
            g.setColor(Color.blue);
            g.setFont(new Font("ITALIC", Font.BOLD, 50));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            g.drawString(player1Score + "      ", (screenwidth - metrics1.stringWidth(player1Score + "     ")) / 2 - 10, g.getFont().getSize());
            //score of player 2
            g.setColor(Color.red);
            g.setFont(new Font("ITALIC", Font.BOLD, 50));
            FontMetrics metrics2 = getFontMetrics(g.getFont());
            g.drawString("     " + player2Score, (screenwidth - metrics1.stringWidth("     " + player2Score)) / 2 + 10, g.getFont().getSize());

            //Line between the table
            g.setColor(Color.lightGray);
            g.drawLine(screenwidth / 2, 0, screenwidth / 2, screenheight);
        }

        else{
            gameover(g);

        }


    }
    public void gameover(Graphics g){
        //if players wanted to finish the game
        //winner decision
        if(player1Score>player2Score) {
            g.setColor(Color.blue);
            g.setFont(new Font("ITALIC", Font.BOLD, 50));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            g.drawString("WINNER: Player 1", (screenwidth - metrics1.stringWidth("WINNER: Player 1")) / 2, screenheight / 2);
            winner=Bluename;
        }

        else if(player1Score<player2Score) {
            g.setColor(Color.red);
            g.setFont(new Font("ITALIC", Font.BOLD, 50));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            g.drawString("WINNER: Player 2", (screenwidth - metrics1.stringWidth("WINNER: Player 2")) / 2, screenheight / 2);
            winner=Redname;
        }
        else{
            g.setColor(Color.white);
            g.setFont(new Font("ITALIC", Font.BOLD, 50));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            g.drawString("Game Drawn", (screenwidth - metrics1.stringWidth("Game Drawn")) / 2, screenheight / 2);
            winner="Draw";
        }

        new Reader(Redname,Bluename,winner,player1Score,player2Score);
        timer.stop();
    }

    //Starting bowl location
    public void newBall() {
        ballXDirection = screenwidth / 2;
        ballYDirection = (screenheight / 2) - ballWidth;
    }
    //Starting paddles location
    public void newPaddles() {
        paddle1YDirection = (screenheight / 2) - paddleLength;
        paddle1XDirection = 0;
        paddle2YDirection = (screenheight / 2) - paddleLength;
        paddle2XDirection = (screenwidth) - paddleWidth;
    }
    //When game starts which way will the ball move toward first;
    public void BallXDirection() {

        int RandomX = random.nextInt(2);
        if (RandomX == 0) {
            RandomX--;
        }
        x = 2*RandomX;
    }
    public void BallYDirection(){
        int RandomYBall = random.nextInt(2);
        if(RandomYBall==0)

        {
            RandomYBall--;
        }

        y=2*RandomYBall;
    }
    //Moves ball around and every thing changes with respect to x and y based on collisions with wall or paddle in collision method.
    public void move(){
        ballXDirection+=x;
        ballYDirection+=y;
    }
    public void checkCollision() {
        // For ball collision with the balls:
        if(ballYDirection<=0){
            y=-y;
        }
        else if(ballYDirection>=screenheight-ballWidth){
            y=-y;
        }


        //Checks the collision with paddle
        //if the ball hits anywhere between the paddle ....
        if(ballXDirection==paddleWidth && (ballYDirection>=paddle1YDirection && ballYDirection<=(paddle1YDirection+paddleLength) ) ){

            // I am using -50 with paddle to mark the half of the paddle because two different possibilities are their for the ball move
            //In java graphics Y decreases upward and increase downward so that's why geometry is inverted
            //If the ball hits upper side then ball moves upward
            if(ballXDirection==paddleWidth && (ballYDirection>=paddle1YDirection && ballYDirection<((paddle1YDirection+paddleLength)-50))){
               clip.setMicrosecondPosition(0);
               clip.start();
                x=-x;

                if(y<0){
                    y=y;
                }

                else if(y>0) {
                    y = -y;
                }
            }

            //In java graphics Y decreases upward and increase downward so that's why geometry is inverted
            //If the ball hits lower side then ball moves downward
            else if (ballXDirection==paddleWidth && (ballYDirection>((paddle1YDirection+paddleLength)-50) && ballYDirection<=(paddle1YDirection+paddleLength))){
                clip.setMicrosecondPosition(0);
                clip.start();
                x=-x;

                if(y<0){
                    y=-y;
                }

                else if(y>0) {
                    y = y;
                }

            }

        }
        else if((ballXDirection==(screenwidth-paddleWidth-ballWidth)) && (ballYDirection>=paddle2YDirection && ballYDirection<=(paddle2YDirection+paddleLength) )){
            //In java graphics Y decreases upward and increase downward so that's why geometry is inverted
            //If the ball hits upper side then ball moves upward
            if(ballXDirection==(screenwidth-paddleWidth-ballWidth) && (ballYDirection>=paddle2YDirection && ballYDirection<=(paddle2YDirection+paddleLength)-50)){
                clip.setMicrosecondPosition(0);
                clip.start();
                x=-x;

                if(y<0){
                    y=y;
                }

                else if(y>0) {
                    y = -y;
                }
            }
            //In java graphics Y decreases upward and increase downward so that's why geometry is inverted
            //If the ball hits lower side then ball moves downward
            else if (ballXDirection==(screenwidth-paddleWidth-ballWidth) && (ballYDirection>((paddle2YDirection+paddleLength)-50) && ballYDirection<(paddle2YDirection+paddleLength))){

                clip.setMicrosecondPosition(0);
                clip.start();
                x=-x;
                //if the ball is moving down then invert the y so it moves upward.
                if(y<0){
                    y=-y;
                }
                //if the ball height is increasing, then let it increase.s
                else if(y>0) {
                    y = y;
                }

            }

        }

        // For paddle 1 collison with top  and bottom wall:
        if(paddle1YDirection<0){
            paddle1YDirection=0;
        }
        else if(paddle1YDirection>screenheight-paddleLength){
            paddle1YDirection=screenheight-paddleLength;
        }
        // For paddle 2:
        if(paddle2YDirection<0){
            paddle2YDirection=0;
        }
        else if(paddle2YDirection>screenheight-paddleLength){
            paddle2YDirection=screenheight-paddleLength;
        }
        //part of ball curver move, curve the straight ball after mid-position.
        if (check1){
            if (ballXDirection==screenwidth/2){
                y=storeY1;
                check1=false;
            }
        }
        if (check2){
            if (ballXDirection==screenwidth/2){
                y=storeY2;
                check2=false;
            }
        }
    }

    public void checkPoint() {

        if (ballXDirection<=0){
            player2Score+=5;
            scoreholder2+=5;
            //new random position will be chosen and both ball and the paddle will refresh from the starting position.
            BallXDirection();
            BallYDirection();

            //Ball current Middle position;

            newBall();
            newPaddles();
        }
        else if (ballXDirection>=screenwidth){
            player1Score+=5;
            scoreholder1+=5;
            //Direction in which ball will move in the start
            BallXDirection();
            BallYDirection();
            //Ball current Middle position;
            newBall();
            newPaddles();
        }

    }
    public boolean SpecialMoveCheck(){

        if (((ballXDirection==(paddleWidth)) && ballYDirection>=(paddle1YDirection) && ballYDirection<=(paddle1YDirection+paddleLength)) && (player1specialAbility) && (scoreholder1>=25)){
            return true;
        }
        else if (((ballXDirection==(screenwidth-paddleWidth-ballWidth)) && ballYDirection>=(paddle2YDirection) && ballYDirection<=(paddle2YDirection+paddleLength)) && (player2specialAbility) && (scoreholder2>=25)){
            return true;
        }
        else{
            return false;
        }
    }
    void ballChanging(){
        if (BallChange1){
            if (ballXDirection==screenwidth-paddleWidth-ballWidth){
                BallChange1=false;
            }
        }
        else if (BallChange2){
            if (ballXDirection==paddleWidth){
                BallChange2=false;
            }
        }
    }


    public void curveofball(){

        if (((ballXDirection==(paddleWidth)) && ballYDirection>=(paddle1YDirection) && ballYDirection<=(paddle1YDirection+paddleLength)) && (player1specialAbility) && (scoreholder1>=25)){
            clip.setMicrosecondPosition(0);
            clip.start();
            BallChange1=true;
            storeY1=y;
            x=-x;
            y=0;
            check1=true;
            player1specialAbility=false;
            scoreholder1=0;
        }
        if (((ballXDirection==(screenwidth-paddleWidth-ballWidth)) && ballYDirection>=(paddle2YDirection) && ballYDirection<=(paddle2YDirection+paddleLength)) && (player2specialAbility) && (scoreholder2>=25)){
            clip.setMicrosecondPosition(0);
            clip.start();
            BallChange2=true;
            storeY2=y;
            x=-x;
            y=0;
            scoreholder2=0;
            player2specialAbility=false;
            check2=true;
        }

    }

    //Works like a main method or runner method, which will be executed after each timer delay.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            if (SpecialMoveCheck()){
                curveofball();
            }
            else{
                checkCollision();
            }
            ballChanging();
            checkPoint();
            move();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_W :
                    paddle1YDirection-=30;
                    break;

                case KeyEvent.VK_S:
                    paddle1YDirection+=30;
                    break;
                case KeyEvent.VK_UP:
                    paddle2YDirection-=30;
                    break;
                case KeyEvent.VK_DOWN:
                    paddle2YDirection+=30;
                    break;
                case KeyEvent.VK_ESCAPE:
                    running=false;
                    break;
                case KeyEvent.VK_SHIFT:
                    player1specialAbility=true;
                    break;
                case KeyEvent.VK_ENTER:
                    player2specialAbility=true;
                    break;
            }
        }


    }
}