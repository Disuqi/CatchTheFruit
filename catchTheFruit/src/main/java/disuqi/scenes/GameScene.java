package disuqi.scenes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import disuqi.App;
import disuqi.sprites.Fruit;
import disuqi.sprites.MainCharacter;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameScene extends GeneralScene {

    private final static String BACKGROUND_IMAGE = "catchTheFruit/assets/forest.png";
    private final static String BACKGROUND_SOUND = "catchTheFruit/assets/autumn-leaves.wav";
    private final static String SOUND_EFFECT = "catchTheFruit/assets/quick-jump.wav";

    private Image background;
    
    private MainCharacter player;
    private Fruit fruit;

    private MediaPlayer mediaPlayerEffects;
    private Media effect;

    public static int points = 0;
    public static int lives = 3;

    public GameScene(){
        super();
        try{
            background = new Image(Files.newInputStream(Paths.get(BACKGROUND_IMAGE)));
            sound = new Media(new File(BACKGROUND_SOUND).toURI().toString());
            mediaPlayer = new MediaPlayer(sound); 
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        }catch(Exception e){
            System.out.println("Error");
        }
        player = new MainCharacter();
        fruit = null;
    }

    @Override
    public void draw() {
        reset();
        mediaPlayer.play();
        activeKeys.clear();
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
                
                gc.drawImage(background, 0, 0, 272, 160, 0, 0, GAME_WIDTH, GAME_HEIGHT);
                player.draw(gc);
                updateHUD();

                if(fruit != null)
                    fruit.draw(gc);
                if(activeKeys.contains(KeyCode.SPACE)){
                    this.stop();
                    App.setScene(App.CREDIT_SCENE);   
                }else if(activeKeys.contains(KeyCode.ESCAPE)){
                    this.stop();
                    App.setScene(App.WELCOME_SCENE);   
                }else if(activeKeys.contains(KeyCode.LEFT) || activeKeys.contains(KeyCode.A)){
                    player.move(MainCharacter.LEFT);
                }else if(activeKeys.contains(KeyCode.RIGHT) || activeKeys.contains(KeyCode.D)){
                    player.move(MainCharacter.RIGHT);
                }

                if(fruit == null){
                    fruit = new Fruit();
                    fruit.moveTo((int)(Math.random() * (GeneralScene.GAME_WIDTH - Fruit.FRUIT_W)), 0);
                }else{
                    fruit.move();
                    if(fruit.collidesWith(player)){
                        points += 10;
                        fruit.increaseDifficulty();
                        playEffect(SOUND_EFFECT);
                        fruit = null;
                    }else if(fruit.getY() > GeneralScene.GAME_HEIGHT){
                        lives--;
                        player.resetPos();
                        fruit = null;
                    }
                }

                if(lives == 0){
                    this.stop();
                    mediaPlayer.stop();
                    App.setScene(App.CREDIT_SCENE);
                }
            }
        };
        timer.start();
    }

    private void playEffect(String path){
        effect = new Media(new File(path).toURI().toString());
        mediaPlayerEffects = new MediaPlayer(effect);
        mediaPlayerEffects.play();
    }
    
    private void reset(){
        player.resetPos();
        lives = 3;
        points = 0;
        Fruit.STEP_INCREMENT = 0f;
    }

    private void updateHUD(){
        Font myFont = Font.font("Arial", FontWeight.BLACK, 18);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        gc.fillText("SCORE: " + points, 20, GeneralScene.GAME_HEIGHT - 15);

        gc.setFill(Color.RED);
        gc.fillText("Lives: " + lives, GeneralScene.GAME_WIDTH - 100, GeneralScene.GAME_HEIGHT - 15);
    }
}
