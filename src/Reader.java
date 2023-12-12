import java.io.*;

public class Reader implements Serializable {
    private String redName;
    private String blueName;
    private String winner;
    private int score1;
    private int score2;
    Reader(String red,String blue,String winner,int s1,int s2){
        score1=s1;
        score2=s2;
        redName=red;
        blueName=blue;
        this.winner=winner;
        Inputvalue();
    }


    //saves the object when the game is complete
    void Inputvalue() {
        try {
            FileOutputStream fos=new FileOutputStream("score.txt", true);
            File fie=new File("D:\\GUI Projects\\PingPongGame\\score.txt");
            if (fie.length()==0) {
                ObjectOutputStream write = new ObjectOutputStream(fos);
                write.writeObject(this);
                write.close();
            }
            else{
                ObjectOutputStream write2=new MyObjectOutputStream(fos);
                write2.writeObject(this);
                write2.close();
            }
            fos.close();

        } catch (IOException e) {
            System.out.println("Can't read the File");
        }
    }
    public String getWinner() {
        return winner;
    }

    public String getRedname() {
        return redName;
    }

    public String getBluename() {
        return blueName;
    }
    public int getScore1(){
        return score1;
    }
    public int getScore2(){
        return score2;
    }

}
