package disuqi.sprites;

public class AnimatedSprite extends Sprite{
    


    public static final int TOTAL_MOVEMENTS = 2;
    public static final int RIGHT = 1;
    public static final int LEFT = 0;
    public static final byte SPRITE_CHANGE = 10;

    protected int currentDirection;
    protected byte currentSprite;
    protected byte currentSpriteChange;

    protected int[][] spriteXCoordinates = new int[TOTAL_MOVEMENTS][];
    protected int[][] spriteYCoordinates = new int[TOTAL_MOVEMENTS][];


    public AnimatedSprite(int width, int height) {
        super(width, height);
        currentDirection = RIGHT;
        currentSprite = 0;   
        currentSpriteChange = 0;
    }

    public void animate(int movement){
        if(movement != currentDirection){
            currentDirection = movement;
            currentSprite = 0;
            currentSpriteChange = 0;
        }else{
            currentSpriteChange++;
            if(currentSpriteChange >= SPRITE_CHANGE){
                    currentSpriteChange = 0;
                    currentSprite = (byte) ((currentSprite + 1) % spriteXCoordinates[currentDirection].length);
                }
        }
        updateSpriteCoordinates();
    }

    protected void updateSpriteCoordinates() {
        spriteX = spriteXCoordinates[currentDirection][currentSprite];
        spriteY = spriteYCoordinates[currentDirection][currentSprite];
    }
    
}
