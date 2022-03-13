package disuqi.sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    protected int width, height;
    protected int x, y;
    protected int spriteX, spriteY;
    protected Image spriteImage;

    public Sprite(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void moveTo(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(spriteImage, spriteX, spriteY, width, height, x, y, width, height);
    }

    public boolean collidesWith(Sprite sp){
        return (x + width / 2 > sp.x && x < sp.x + sp.width/2 && y + height / 2 > sp.y && y < sp.y + sp.height/2);
    }
}
