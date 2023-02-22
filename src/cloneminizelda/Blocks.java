package cloneminizelda;

import java.awt.*;

public class Blocks extends Rectangle {

    public Blocks (int x,int y){
        super(x,y,32,32);
    }

    public void render(Graphics g){
        //g.setColor(Color.BLACK);
        //g.fillRect(x,y,32,32);
        g.drawImage(Spritesheet.tileWall,x,y,32,32,null);
    }
}
