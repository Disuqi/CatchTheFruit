package disuqi;

import disuqi.scenes.*;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    public static final int MAX_SCENES = 3;
    public static final int WELCOME_SCENE = 0;
    public static final int GAME_SCENE = 1;
    public static final int CREDIT_SCENE = 2;

    public static final GeneralScene[] scenes = new GeneralScene[MAX_SCENES];

    public static Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        scenes[0] =  new WelcomeScene();
        scenes[1] = new GameScene();
        scenes[2] = new CreditsScene();

        stage.setTitle("Bear Fruit Game");
        setScene(WELCOME_SCENE);
        stage.show();
    }

    public static void setScene(int sceneNum) {
        stage.setScene(scenes[sceneNum]);
        scenes[sceneNum].draw();
    }

    public static void exit(){
        stage.hide();
    }

    public static void main(String[] args) {
        launch(args);
    }

}