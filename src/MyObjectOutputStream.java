import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class MyObjectOutputStream extends ObjectOutputStream {


    MyObjectOutputStream() throws IOException
    {

        super();
    }


    MyObjectOutputStream(OutputStream o) throws IOException
    {
        super(o);
    }


    @Override
    public void writeStreamHeader() throws IOException
    {
        return;
    }
}
 