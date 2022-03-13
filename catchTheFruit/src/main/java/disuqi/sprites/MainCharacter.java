package disuqi.sprites;

import java.nio.file.Files;
import java.nio.file.Paths;

import disuqi.scenes.GeneralScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MainCharacter extends AnimatedSprite{

    public static final int SPRITE_W = 64;
    public static final int SPRITE_H = 64;
    private static final String IMAGE_PATH = "catchTheFruit/assets/characters.png";
    private static final int STEP = 2;

    public MainCharacter() {
        super(SPRITE_W, SPRITE_H);
        try{
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        }catch(Exception e){
            System.out.println("Couldn't load image for main character");
        }

        spriteXCoordinates[RIGHT] = new int[] {32*13, 32*14, 32*15, 32*16};
        spriteYCoordinates[RIGHT] = new int[] {64, 64, 64, 64};
        spriteXCoordinates[LEFT] = new int[] {32*13, 32*14, 32*15, 32*16};
        spriteYCoordinates[LEFT] = new int[] {64, 64, 64, 64};

        updateSpriteCoordinates();
    }

    public void move(int movement){
        int newX = x;
        if(movement == LEFT){
            newX -= Math.min(STEP, x);
        }else if (movement == RIGHT){
            newX += Math.min(STEP, GeneralScene.GAME_WIDTH - SPRITE_W/2 - x);
        }
        moveTo(newX, y);
        animate(movement);
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.drawImage(spriteImage, spriteX, spriteY, 32d, 32d, x, y, width, height);
    }

    public void resetPos() {
        moveTo(GeneralScene.GAME_WIDTH/2 - SPRITE_W / 2, GeneralScene.GAME_HEIGHT - 30 - SPRITE_H);
    }

}
