package disuqi.sprites;

import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.image.Image;

public class Fruit extends Sprite {

    private static final String IMAGE_PATH= "catchTheFruit/assets/fruits.png";
    private static int MAX_FRUITS = 4;
    public static int FRUIT_W = 30;
    public static int FRUIT_H = 30;
    public static float STEP_INCREMENT = 0f;

    public Fruit(){
        this((int)(Math.random() * MAX_FRUITS));
    }
    
    public Fruit(int fruitType){
        super(FRUIT_W, FRUIT_H);
        try{
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        }catch(Exception e){
            System.out.println("Couldn't load fruit image");
        }

        spriteX = 0;
        switch(fruitType){
            case 0:
                //gold guy
                spriteX= 0;
                break;
            case 1:
                //green guy
                spriteX = FRUIT_W;
                break;
            case 2:
                //snakke
                spriteY = FRUIT_H * 3;
                break;
            case 4:
                spriteY = FRUIT_H * 4;
                break;
        }
    }

    public void move(){
        this.y += (int) (1 + STEP_INCREMENT);
    }

    public void increaseDifficulty(){
        STEP_INCREMENT += 0.2f;
    }
}
