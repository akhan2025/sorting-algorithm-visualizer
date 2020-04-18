
import java.awt.Color;

public class bar {
    private Color myColor;
    private int height;

    bar(Color c, int h){
        myColor = c;
        height = h;
    }

    public void setColor(Color c){
        myColor = c;
    }

    public Color getColor(){
        return myColor;
    }

    public void setHeight(int h){
        height = h;
    }

    public int getHeight(){
        return height;
    }
}