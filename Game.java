package project_fx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
    private final static int HW = 40 ;
    private int lastGameLevel = 1;
    private final static int mapsizeW = 400;
    private final static int mapsizeH = 400;
    private List<Rectangle> Blocks = new ArrayList<>();
    private Pane root;
    private Rectangle man;
    private List<Rectangle> winRects = new ArrayList<>();
    private List<Rectangle> objRects = new ArrayList<>();
    private int levelN = 0 , moves=0;
    private int[] number_of_moves = {20 , 29 , 30 , 37 , 51 ,52 , 76 , 81 , 83 , 87};
    
    private ArrayList<String> iterations = new ArrayList<>();
    private ArrayList<Rectangle> iterationsO = new ArrayList<>();
    HashMap<Integer, String> durations = new HashMap<Integer, String>();
    private TableView<Statistic> table = new TableView();
    private ObservableList<Statistic> data =
        FXCollections.observableArrayList(
            new Statistic(1, "1 SECOND")
        );
    private int secpass = 0;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            secpass++;
            texttime.setText("Sec : "+secpass);
        }
    };
    
    Stage stage;
    Text text = new Text();
    Text texttime = new Text();
    Text textMaxMoves = new Text();
    Text textMoves = new Text();
    Image imgman = new Image("Images/man.png");
    Image manLeft = new Image("Images/left.png");
    Image manRight = new Image("Images/right.png");
    Image manBehind = new Image("Images/back.png");
    Image imgWall = new Image("Images/redBrick.png");
    Image imgobjof = new Image("Images/boxOff.png");
    Image imgobjon = new Image("Images/boxOn.png");
    Image imgground = new Image("Images/ground.png");

    public static void main(String[] args) {
        launch(args);
    }

    public Parent createContent() {
        root = new Pane();
        root.setPrefSize(600, 600);
        Menu();
        return root;
    }

    public void addRightMenu() {
        text.setTranslateX(425);
        text.setTranslateY(30);
        text.setText("Level " + (levelN));
        text.setFont(Font.font("Verdana", 30));
        text.setFill(Color.BLACK);
        root.getChildren().add(text);
        
        texttime.setTranslateX(425);
        texttime.setTranslateY(80);
        texttime.setText("Sec : 0");
        texttime.setFont(Font.font("Verdana", 30));
        texttime.setFill(Color.BLACK);
        root.getChildren().add(texttime);
        
        textMaxMoves.setTranslateX(20);
        textMaxMoves.setTranslateY(500);
        textMaxMoves.setText("Best Moves : " + number_of_moves[levelN -1]);
        textMaxMoves.setFont(Font.font("Verdana", 20));
        textMaxMoves.setFill(Color.BLACK);
        root.getChildren().add(textMaxMoves);
        
        textMoves.setTranslateX(20);
        textMoves.setTranslateY(530);
        textMoves.setText("Current Moves : 0");
        textMoves.setFont(Font.font("Verdana", 25));
        textMoves.setFill(Color.BLACK);
        root.getChildren().add(textMoves);
        
        Button btn4 = new Button();
        btn4.setTranslateX(600 - 55);
        btn4.setTranslateY(600 - 120);
        btn4.setText("Level 1");
        btn4.setOnAction(e -> {
            levelN = 1;
            root.getChildren().clear();
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn4);
        
        Button btn5 = new Button();
        btn5.setTranslateX(600 - 55);
        btn5.setTranslateY(600 - 150);
        btn5.setText("Level 2");
        btn5.setOnAction(e -> {
            levelN = 2;
            root.getChildren().clear();
            root.setPrefSize(50, 50);
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn5);
        
        Button btn6 = new Button();
        btn6.setTranslateX(600 - 55);
        btn6.setTranslateY(600 - 180);
        btn6.setText("Level 3");
        btn6.setOnAction(e -> {
            levelN = 3;
            root.getChildren().clear();
            root.setPrefSize(50, 50);
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn6);
        
        Button btn7 = new Button();
        btn7.setTranslateX(600 - 55);
        btn7.setTranslateY(600 - 210);
        btn7.setText("Level 4");
        btn7.setOnAction(e -> {
            levelN = 4;
            root.getChildren().clear();
            root.setPrefSize(50, 50);
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn7);
        
        
        Button btn8 = new Button();
        btn8.setTranslateX(600 - 55);
        btn8.setTranslateY(600 - 240);
        btn8.setText("Level 5");
        btn8.setOnAction(e -> {
            levelN = 5;
            root.getChildren().clear();
            root.setPrefSize(50, 50);
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn8);
        
        Button btn9 = new Button();
        btn9.setTranslateX(600 - 55);
        btn9.setTranslateY(600 - 270);
        btn9.setText("Level 6");
        btn9.setOnAction(e -> {
            levelN =6;
            root.getChildren().clear();
            root.setPrefSize(50, 50);
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn9);
        
        
        Button btn10 = new Button();
        btn10.setTranslateX(600 - 55);
        btn10.setTranslateY(600 - 300);
        btn10.setText("Level 7");
        btn10.setOnAction(e -> {
            levelN = 7;
            root.getChildren().clear();
            root.setPrefSize(50, 50);
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn10);
        
        Button btn11 = new Button();
        btn11.setTranslateX(600 - 55);
        btn11.setTranslateY(600 - 330);
        btn11.setText("Level 8");
        btn11.setOnAction(e -> {
            levelN = 8;
            root.getChildren().clear();
            root.setPrefSize(50, 50);
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn11);
        
        
        
        Button btn12 = new Button();
        btn12.setTranslateX(600 - 55);
        btn12.setTranslateY(600 - 360);
        btn12.setText("Level 9");
        btn12.setOnAction(e -> {
            levelN = 9;
            root.getChildren().clear();
            root.setPrefSize(50, 50);
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn12);
        
        Button btn13 = new Button();
        btn13.setTranslateX(600 - 55);
        btn13.setTranslateY(600 - 390);
        btn13.setText("Level 10");
        btn13.setOnAction(e -> {
            levelN = 10;
            root.getChildren().clear();
            root.setPrefSize(50, 50);
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn13);
        
        Button Undo = new Button();
        Undo.setTranslateX(80);
        Undo.setTranslateY(420);
        Undo.setText(" Undo ");
        Undo.setOnAction(e -> {
            undo();
        });
        root.getChildren().add(Undo);
        
        Button btn3 = new Button();
        btn3.setTranslateX(600 - 55);
        btn3.setTranslateY(600 - 90);
        btn3.setText("Menu");
        btn3.setOnAction(e -> {
            levelN = 0;
            root.getChildren().clear();
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn3);

        Button btn2 = new Button();
        btn2.setTranslateX(10);
        btn2.setTranslateY(400 + 20);
        btn2.setText("RETRY");
        btn2.setOnAction(e -> {
            
            root.getChildren().clear();
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn2);

        Button btn1 = new Button();
        btn1.setTranslateX(600-HW);
        btn1.setTranslateY(600 - 30);
        btn1.setText("Exit");
        
        btn1.setOnAction(e -> {
            System.exit(0);
        });
        root.getChildren().add(btn1);
        
        

    }
    public void CreateBackground() {
        Rectangle b1;
        for (int i = 0; i < 600; i += HW) {
            for (int j = 0; j < 600; j += HW) {
                b1 = new Rectangle(HW, HW, new ImagePattern(imgground));
                b1.setTranslateX(i);
                b1.setTranslateY(j);
                root.getChildren().add(b1);
            }
        }
       
        
    }
    public void CreateBorder() {
        Rectangle b1;
        for (int i = 0; i < mapsizeW; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(0);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }

        for (int i = HW; i < mapsizeH; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(mapsizeW - HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for (int i = HW; i < mapsizeH; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(0);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for (int i = HW; i < mapsizeW; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(mapsizeH - HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }

        Line lineh;
        for (int i = 0; i <= mapsizeW; i += HW) {
            lineh = new Line(0, i, mapsizeW, i);
            root.getChildren().add(lineh);
        }
        Line linev;
        for (int i = 0; i <= mapsizeH; i += HW) {
            linev = new Line(i, 0, i, mapsizeH);
            root.getChildren().add(linev);
        }
    }
    public void makeLines(){
        Line lineh;
        for (int i = 0; i <= mapsizeW; i += HW) {
            lineh = new Line(0, i, mapsizeW, i);
            root.getChildren().add(lineh);
        }
        Line linev;
        for (int i = 0; i <= mapsizeH; i += HW) {
            linev = new Line(i, 0, i, mapsizeH);
            root.getChildren().add(linev);
        }
    }

    public void changeLevel() {
        switch (levelN) {
            case 0:
                Menu();
                break;
            case 1:
                System.out.print("YOU ARE IN LEVEL 1");
                level1(); 
                break;
            case 2:
                level2();
                System.out.print("YOU ARE IN LEVEL 2");
                break;
            case 3:
                level3();
                System.out.print("YOU ARE IN LEVEL 3");
                break;
            case 4:
                level4();
                break;
            case 5:
                level5();
                break;
            case 6:
                level6();
                break;
            case 7:
                level7();
                break;
            case 8:
                level8();
                break;
            case 9:
                level9();
                break;
            case 10:
                level10();
                break;
            case 11:
                win();
                break;
            case 12:
                clearToAnotherMap();
                stat();
                break;

        }
    }  
    public void Menu() {
        clearToAnotherMap();
        CreateBackground();
        Rectangle b1;
        for (int i = 0; i < 600; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(0);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for (int i = 0; i < 600; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(0);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        for (int i = 0; i < 600; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(600-HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for (int i = 0; i < 600; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(600-HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        Button btn1 = new Button();
        btn1.setTranslateX(280);
        btn1.setTranslateY(230);
        btn1.setText("Exit");
        btn1.setOnAction(e -> {
            System.exit(0);
        });
        root.getChildren().add(btn1);

        Button btn2 = new Button();
        btn2.setTranslateX(275);
        btn2.setTranslateY(170);
        btn2.setText("Play");
        btn2.setOnAction(e -> {
            clearToAnotherMap();
            levelN = lastGameLevel;
            changeLevel();
        });
        root.getChildren().add(btn2);
        
        
        Button btn3 = new Button();
        btn3.setTranslateX(264);
        btn3.setTranslateY(200);
        btn3.setText(" Statistics ");
        btn3.setOnAction(e -> {
            clearToAnotherMap();
            levelN=12;
            changeLevel();
        });
        root.getChildren().add(btn3);

        text.setTranslateX(205);
        text.setTranslateY(90);
        text.setText("SOKOBAN");
        text.setFont(Font.font("Verdana", 40));
        text.setFill(Color.GREEN);
        root.getChildren().add(text);
    }
    public void level1() {
        moves = 0;
        secpass=0;
        CreateBackground();
        addRightMenu();
        
        man = new Rectangle(HW, HW, new ImagePattern(imgman));
        man.setTranslateY(120);
        man.setTranslateX(80);
        root.getChildren().add(man);

        Rectangle winRec;
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(HW);
        winRec.setTranslateX(120);
        winRects.add(winRec);
        root.getChildren().add(winRec);

        Rectangle objRec;
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(200);
        objRec.setTranslateX(200);
        objRects.add(objRec);
        root.getChildren().add(objRec);

        CreateBorder();
        Rectangle b1;
        for (int i = 120; i < 400; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(160);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        WinObjeColi();
        makeLines();
    }
    public void level2() {
        secpass=0;
        moves = 0;
        CreateBackground();
        addRightMenu();
        CreateBorder();
        man = new Rectangle(HW, HW, new ImagePattern(imgman));
        man.setTranslateY(120);
        man.setTranslateX(80);
        root.getChildren().add(man);

        Rectangle winRec;
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(40);
        winRec.setTranslateX(80);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(120);
        winRec.setTranslateX(40);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        Rectangle objRec;
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(4*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(2*HW);
        objRec.setTranslateX(1*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        Rectangle b1;
        for(int i = 0 ; i < 3*HW ;i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(3*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for(int i = 0 ; i < 3*HW ;i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(3*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for(int i = 3*HW ; i < 6*HW ; i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(2*HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        
        for(int i = 2*HW ; i < 6*HW ;i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(5*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        for(int i = 3*HW ; i < 6*HW ; i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(5*HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for(int i = 0; i < 4*HW ; i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(6*HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        WinObjeColi();
        makeLines();
    }
    public void level3() {
        secpass=0;
        moves = 0;
        CreateBackground();
        addRightMenu();
        CreateBorder();
        
        man = new Rectangle(HW, HW, new ImagePattern(imgman));
        man.setTranslateY(3*HW);
        man.setTranslateX(6*HW);
        root.getChildren().add(man);
        
        Rectangle winRec;
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(2*HW);
        winRec.setTranslateX(3*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(2*HW);
        winRec.setTranslateX(4*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);

        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(2*HW);
        winRec.setTranslateX(6*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(3*HW);
        winRec.setTranslateX(3*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        Rectangle objRec;
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(2*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(2*HW);
        objRec.setTranslateX(5*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(3*HW);
        objRec.setTranslateX(5*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(5*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        Rectangle b1;
        for(int i = 3*HW ; i <= 7*HW ;i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(1*HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        for(int i = 0 ; i < 5*HW ;i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(7*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for(int i = 0 ; i < 8*HW ;i +=HW){
            if(i!= 3*HW && i!=4*HW){
                b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
                b1.setTranslateX(i);
                b1.setTranslateY(4*HW);
                Blocks.add(b1);
                root.getChildren().add(b1);
            }
        }

       for(int i = 4*HW ; i <= 8*HW ;i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(2*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
            
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(5*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
       
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateX(3*HW);
        b1.setTranslateY(7*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
       
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateX(4*HW);
        b1.setTranslateY(7*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        WinObjeColi();
        makeLines(); 
    }
    public void level4() {
        
        secpass=0;
        moves = 0;
        CreateBackground();
        addRightMenu();
        CreateBorder();
        
        man = new Rectangle(HW, HW, new ImagePattern(imgman));
        man.setTranslateY(2*HW);
        man.setTranslateX(6*HW);
        root.getChildren().add(man);
        
        Rectangle winRec;
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(2*HW);
        winRec.setTranslateX(2*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(2*HW);
        winRec.setTranslateX(3*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);

        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(2*HW);
        winRec.setTranslateX(4*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        Rectangle objRec;
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(2*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(2*HW);
        objRec.setTranslateX(4*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(2*HW);
        objRec.setTranslateX(5*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        Rectangle b1;
        for(int i = 0 ; i < 5*HW ;i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(4*HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for(int i = 4*HW ; i < 7*HW ;i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(5*HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        
        for(int i = 0 ; i < 5*HW ;i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(7*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }

        WinObjeColi();
        makeLines(); 
        
    }
    public void level5() {
        secpass=0;
        moves = 0;
        CreateBackground();
        CreateBorder();
        addRightMenu();
        
        man = new Rectangle(HW, HW, new ImagePattern(imgman));
        man.setTranslateY(3*HW);
        man.setTranslateX(3*HW);
        root.getChildren().add(man);

        Rectangle winRec;
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(HW);
        winRec.setTranslateX(4*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(4*HW);
        winRec.setTranslateX(5*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(5*HW);
        winRec.setTranslateX(3*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        Rectangle objRec;
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(3*HW);
        objRec.setTranslateX(2*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(3*HW);
        objRec.setTranslateX(4*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(5*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
       
        
        Rectangle b1;        
        for(int i = 0 ; i < 7*HW ; i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(6*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        for(int i = 0 ; i < 7*HW ; i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(6*HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(2*HW);
        b1.setTranslateX(2*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(2*HW);
        b1.setTranslateX(4*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(4*HW);
        b1.setTranslateX(2*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(4*HW);
        b1.setTranslateX(4*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        WinObjeColi();
        makeLines();

    }
    public void level6() {
         secpass=0;
         moves = 0;
        CreateBackground();
        addRightMenu();
        CreateBorder();
        man = new Rectangle(HW, HW, new ImagePattern(imgman));
        man.setTranslateY(2*HW);
        man.setTranslateX(2*HW);
        root.getChildren().add(man);

        Rectangle winRec;
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(2*HW);
        winRec.setTranslateX(HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(4*HW);
        winRec.setTranslateX(HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(3*HW);
        winRec.setTranslateX(5*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(5*HW);
        winRec.setTranslateX(4*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(6*HW);
        winRec.setTranslateX(6*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(7*HW);
        winRec.setTranslateX(4*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(6*HW);
        winRec.setTranslateX(3*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);

        Rectangle objRec;
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(2*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(3*HW);
        objRec.setTranslateX(4*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(4*HW);
        objRec.setTranslateX(4*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(6*HW);
        objRec.setTranslateX(1*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(6*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(6*HW);
        objRec.setTranslateX(4*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(6*HW);
        objRec.setTranslateX(5*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);

        Rectangle b1;
        for(int i = HW ; i < 3*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(HW);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        for(int i = HW ; i < 3*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(3*HW);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        for(int i = HW ; i < 8*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(8*HW);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        for(int i = 2*HW ; i < 4*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(4*HW);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(5*HW);
        b1.setTranslateX(2*HW);        
        Blocks.add(b1);
        root.getChildren().add(b1);         
        
        for(int i = HW ; i <= 5*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(6*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        
        for(int i = 5*HW ; i <= 8*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(7*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        WinObjeColi();
        makeLines();
        
    }
    public void level7() {
        moves = 0;
        secpass=0;
        CreateBackground();
        addRightMenu();
        CreateBorder();
        
        man = new Rectangle(HW, HW, new ImagePattern(imgman));
        man.setTranslateY(2*HW);
        man.setTranslateX(4*HW);
        root.getChildren().add(man);

        Rectangle winRec;
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(HW);
        winRec.setTranslateX(4*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(3*HW);
        winRec.setTranslateX(1*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(3*HW);
        winRec.setTranslateX(4*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        

        
        Rectangle objRec;
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(3*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(5*HW);
        objRec.setTranslateX(2*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(5*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        
        
        

        Rectangle b1;
        for(int i = 0 ; i < 8*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(5*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        for(int i = 0 ; i <= 6*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(7*HW);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        for(int i = 0 ; i <= 2*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(6*HW);
            Blocks.add(b1);
            root.getChildren().add(b1); 
            
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(2*HW);
            Blocks.add(b1);
            root.getChildren().add(b1); 
            
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(1*HW);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
     
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateX(2*HW);
        b1.setTranslateY(4*HW);
        Blocks.add(b1);
        root.getChildren().add(b1); 
        
        WinObjeColi();
        makeLines();
    }
    public void level8() {
        secpass=0;
        moves = 0;
        
        CreateBackground();
        addRightMenu();
        CreateBorder();
        
        man = new Rectangle(HW, HW, new ImagePattern(imgman));
        man.setTranslateY(6*HW);
        man.setTranslateX(3*HW);
        root.getChildren().add(man);

        Rectangle winRec;
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(4*HW);
        winRec.setTranslateX(2*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(5*HW);
        winRec.setTranslateX(2*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(6*HW);
        winRec.setTranslateX(2*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(6*HW);
        winRec.setTranslateX(1*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(5*HW);
        winRec.setTranslateX(HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        
        

        Rectangle objRec;
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(4*HW);
        objRec.setTranslateX(1*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(3*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(4*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);

        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(4*HW);
        objRec.setTranslateX(5*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(5*HW);
        objRec.setTranslateX(5*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        Rectangle b1;
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateX(2*HW);
        b1.setTranslateY(2*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateX(3*HW);
        b1.setTranslateY(2*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateX(3*HW);
        b1.setTranslateY(5*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateX(4*HW);
        b1.setTranslateY(5*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        for(int i = 0 ; i < 3*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(5*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        for(int i = 5*HW ; i < 8*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(2*HW);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        for(int i =0; i < 7*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(7*HW);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        for(int i =2*HW; i < 6*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(7*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        for(int i =5*HW; i < 8*HW ;i +=HW ){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(6*HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1); 
        }
        
        

        
        WinObjeColi();
        makeLines();
    }
    public void level9() {
        secpass=0;
        moves = 0;
        CreateBackground();
        addRightMenu();
        CreateBorder();
        man = new Rectangle(HW, HW, new ImagePattern(imgman));
        man.setTranslateY(1*HW);
        man.setTranslateX(1*HW);
        root.getChildren().add(man);

        Rectangle winRec;
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(1*HW);
        winRec.setTranslateX(1*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(5*HW);
        winRec.setTranslateX(1*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(6*HW);
        winRec.setTranslateX(1*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        
        
        Rectangle objRec;
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(1*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);

        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(2*HW);
        objRec.setTranslateX(2*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(4*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        Rectangle b1;
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(3*HW);
        b1.setTranslateX(2*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(5*HW);
        b1.setTranslateX(2*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
       
        
        for(int i =0 ; i < 5*HW ; i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateY(i);
            b1.setTranslateX(5*HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        for(int i =4*HW ; i < 8*HW ; i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateY(i);
            b1.setTranslateX(4*HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        for(int i =0; i < 8*HW ; i +=HW){
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateY(7*HW);
            b1.setTranslateX(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        ManOnWinRec();
        WinObjeColi();
        makeLines();
    }
    public void level10() {
        secpass=0;
        moves = 0;
        CreateBackground();
        CreateBorder();
        addRightMenu();
        
        man = new Rectangle(HW, HW, new ImagePattern(imgman));
        man.setTranslateY(4*HW);
        man.setTranslateX(1*HW);
        root.getChildren().add(man);

        Rectangle winRec;
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(5*HW);
        winRec.setTranslateX(5*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(5*HW);
        winRec.setTranslateX(3*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(4*HW);
        winRec.setTranslateX(3*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(3*HW);
        winRec.setTranslateX(4*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        
        winRec = new Rectangle(HW, HW, Color.BLUE);
        winRec.setTranslateY(3*HW);
        winRec.setTranslateX(5*HW);
        winRects.add(winRec);
        root.getChildren().add(winRec);
        

        Rectangle objRec;
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(3*HW);
        objRec.setTranslateX(2*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(4*HW);
        objRec.setTranslateX(2*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(4*HW);
        objRec.setTranslateX(4*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(3*HW);
        objRec.setTranslateX(4*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        objRec = new Rectangle(HW, HW, new ImagePattern(imgobjof));
        objRec.setTranslateY(5*HW);
        objRec.setTranslateX(3*HW);
        objRects.add(objRec);
        root.getChildren().add(objRec);
        
        Rectangle b1;
     
        for(int i =0 ; i < 8*HW ; i +=HW){
                b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
                b1.setTranslateY(HW);
                b1.setTranslateX(i);
                Blocks.add(b1);
                root.getChildren().add(b1);
        }
        for(int i =0 ; i < 8*HW ; i +=HW){
                b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
                b1.setTranslateY(i);
                b1.setTranslateX(7*HW);
                Blocks.add(b1);
                root.getChildren().add(b1);
        }
        
        for(int i =5*HW ; i < 8*HW ; i +=HW){
                b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
                b1.setTranslateY(i);
                b1.setTranslateX(1*HW);
                Blocks.add(b1);
                root.getChildren().add(b1);
        }
        for(int i =6*HW ; i < 8*HW ; i +=HW){
                b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
                b1.setTranslateY(i);
                b1.setTranslateX(2*HW);
                Blocks.add(b1);
                root.getChildren().add(b1);
        }
        for(int i =0 ; i < 7*HW ; i +=HW){
                b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
                b1.setTranslateY(7*HW);
                b1.setTranslateX(i);
                Blocks.add(b1);
                root.getChildren().add(b1);
        }
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(2*HW);
        b1.setTranslateX(1*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(2*HW);
        b1.setTranslateX(6*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(3*HW);
        b1.setTranslateX(6*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
        b1.setTranslateY(6*HW);
        b1.setTranslateX(6*HW);
        Blocks.add(b1);
        root.getChildren().add(b1);
        
        makeLines();
        WinObjeColi();
    }
    public void win(){
        root.getChildren().clear();
        Blocks.clear();
        winRects.clear();
        objRects.clear();
        CreateBackground();
        Rectangle b1;

        for (int i = 0; i < 600; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(0);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for (int i = 0; i < 600; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(0);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        for (int i = 0; i < 600; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(600-HW);
            b1.setTranslateY(i);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        for (int i = 0; i < 600; i += HW) {
            b1 = new Rectangle(HW, HW, new ImagePattern(imgWall));
            b1.setTranslateX(i);
            b1.setTranslateY(600-HW);
            Blocks.add(b1);
            root.getChildren().add(b1);
        }
        
        
        String win = "YOU WIN";
        HBox hBox = new HBox();
        hBox.setTranslateX(170);
        hBox.setTranslateY(200);
        root.getChildren().add(hBox);

        for (int i = 0; i < win.toCharArray().length; i++) {
            char letter = win.charAt(i);

            Text text = new Text(String.valueOf(letter));
            text.setFont(Font.font(61));
            text.setFill(Color.BROWN);
            text.setOpacity(0);

            hBox.getChildren().add(text);

            FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
            ft.setToValue(1);
            ft.setDelay(Duration.seconds(i * 0.15));
            ft.play();
        }
        
        String win1 = "SOKOBAN";
        HBox hBox1 = new HBox();
        hBox1.setTranslateX(170);
        hBox1.setTranslateY(300);
        root.getChildren().add(hBox1);

        for (int i = 0; i < win.toCharArray().length; i++) {
            char letter = win1.charAt(i);

            Text text = new Text(String.valueOf(letter));
            text.setFont(Font.font(61));
            text.setFill(Color.BROWN);
            text.setOpacity(0);

            hBox1.getChildren().add(text);

            FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
            ft.setToValue(1);
            ft.setDelay(Duration.seconds(i * 0.15));
            ft.play();
        }
        
        Button btn3 = new Button();
        btn3.setTranslateX(400);
        btn3.setTranslateY(400);
        btn3.setText("Menu");
        btn3.setOnAction(e -> {
            levelN = 0;
            lastGameLevel=1;
            root.getChildren().clear();
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        });
        root.getChildren().add(btn3);
    
    }
    public void stat(){
        
        
        clearToAnotherMap();
        CreateBackground();
        
        Label label = new Label(" Time ");
        label.setFont(new Font("Arial", 20));
        label.setTranslateX(10);
        label.setTranslateY(10);
        root.getChildren().add(label);
        
        Button btn3 = new Button();
        btn3.setTranslateX(550);
        btn3.setTranslateY(550);
        btn3.setText(" Back ");
        btn3.setOnAction(e -> {
            clearToAnotherMap();
            levelN=0;
            changeLevel();
        });
        root.getChildren().add(btn3);
        
        data.clear();
        for (int name: durations.keySet()){
            data.add(new Statistic(name, durations.get(name).toString()));
        } 
        
        table.getColumns().clear();
        table.setTranslateX(50);
        table.setTranslateY(50);
        table.setEditable(false);
        
        TableColumn firstColumn = new TableColumn("Level");
        firstColumn.setMinWidth(130);
        firstColumn.setCellValueFactory(
               new PropertyValueFactory<Statistic, String>("Level"));
        
        TableColumn secondColumn = new TableColumn("Time");
        secondColumn.setMinWidth(130);
        secondColumn.setCellValueFactory(
                new PropertyValueFactory<Statistic, String>("Time"));
        
        table.setItems(data);
        table.getColumns().addAll(firstColumn, secondColumn);
      
        
        root.getChildren().add(table);
    }
    
    public void ManOnWinRec() {
        Rectangle winRec;
        for (int i = 0; i < winRects.size(); i++) {
            winRec = winRects.get(i);
            if (man.getTranslateX() == winRec.getTranslateX() && man.getTranslateY() == winRec.getTranslateY()) {
                winRec.setFill(Color.RED);
            } else {
                winRec.setFill(Color.BLUE);
            }
        }
    }
    public void objOnWinRec() {
        Rectangle objRec, winRec;
        int check = 0;
        for (int i = 0; i < objRects.size(); i++) {
            objRec = objRects.get(i);
            for (int j = 0; j < winRects.size(); j++) {
                winRec = winRects.get(j);
                if (objRec.getTranslateX() == winRec.getTranslateX() && objRec.getTranslateY() == winRec.getTranslateY()) {
                    check++;
                }
            }
        }
        if (check == objRects.size()) {
            durations.put(levelN , secpass+" Seconds");
            levelN++;
            lastGameLevel = levelN;
            root.getChildren().clear();
            Blocks.clear();
            objRects.clear();
            winRects.clear();
            iterationsO.clear();
            iterations.clear();
            changeLevel();
        }
    }
    public void WinObjeColi(){
        System.out.println("hello Function");
        int check =0;
        Rectangle objRec1 , winRec;
        System.out.println(winRects.size() + " " + objRects.size());
        for(int i = 0 ; i < objRects.size() ; i++){
            objRec1 = objRects.get(i);
            System.out.println("I  ="+i + " "+objRec1.getTranslateX() + " "+objRec1.getTranslateY()  );
            for(int j = 0;j < winRects.size() ; j++){
                winRec = winRects.get(j);
                //System.out.print("  "+ winRec.getTranslateX() + " " + winRec.getTranslateY());
                
                if((objRec1.getTranslateX() == winRec.getTranslateX()) && (winRec.getTranslateY() == objRec1.getTranslateY())){
                    objRec1.setFill(Color.GREEN);
                    //System.out.println("herre");
                    break;
                }
                else{
                    objRec1.setFill(new ImagePattern(imgobjof));
                }
                
            }
            
        }
    }
    
    
    public void moveDown() {
        int check = 0, check1 = 0;
        Rectangle objRec = null;
        for (int i = 0; i < objRects.size(); i++) {
            if (man.getTranslateX() == objRects.get(i).getTranslateX() && (man.getTranslateY() + HW) == objRects.get(i).getTranslateY()) {
                objRec = objRects.get(i);
                check1 = 1;
            }
        }
        if (check1 == 1) {
            for (int i = 0; i < objRects.size(); i++) {
                if (objRec.getTranslateX() == objRects.get(i).getTranslateX() && (objRec.getTranslateY() + HW) == objRects.get(i).getTranslateY()) {
                    check1 = 2;
                }
            }
        }
        if (check1 == 1) {
            if (man.getTranslateX() == objRec.getTranslateX() && objRec.getTranslateY() != mapsizeH - HW && man.getTranslateY() == (objRec.getTranslateY() - HW)) {

                for (int i = 0; i < Blocks.size(); i++) {
                    if (objRec.getTranslateY() + HW == Blocks.get(i).getTranslateY() && objRec.getTranslateX() == Blocks.get(i).getTranslateX()) {
                        check = 1;
                    }
                    
                }
                if (check == 0) { // move the man and the box
                    man.setTranslateY(man.getTranslateY() + HW);
                    objRec.setTranslateY(man.getTranslateY() + HW);
                     moves++;
                     textMoves.setText("Best Moves : " + moves);
                    iterations.add("manDBoxD");
                    iterationsO.add(objRec);
                    System.out.println("1");
                }
            } else if (man.getTranslateX() == objRec.getTranslateX() && man.getTranslateY() != (objRec.getTranslateY() - HW)) {
                man.setTranslateY(man.getTranslateY() + HW);
                 moves++;
                 textMoves.setText("Best Moves : " + moves);
                iterations.add("manD");
                iterationsO.add(null);
                System.out.println("2");
            } else if (man.getTranslateX() != objRec.getTranslateX()) {
                man.setTranslateY(man.getTranslateY() + HW);
                 moves++;
                 textMoves.setText("Best Moves : " + moves);
                iterations.add("manD");
                iterationsO.add(null);
                System.out.println("3");
            }
        } else if (check1 == 0) {
            if (man.getTranslateY() != mapsizeH - HW) {
                man.setTranslateY(man.getTranslateY() + HW);
                 moves++;
                 textMoves.setText("Best Moves : " + moves);
                iterations.add("manD");
                iterationsO.add(null);
            }
        }
    }
    public void moveUp() {
        int check = 0, check1 = 0;
        Rectangle objRec = null;
        for (int i = 0; i < objRects.size(); i++) {
            if (man.getTranslateX() == objRects.get(i).getTranslateX() && (man.getTranslateY() - HW) == objRects.get(i).getTranslateY()) {
                objRec = objRects.get(i);
                check1 = 1;
            }
        }
        if (check1 == 1) {
            for (int i = 0; i < objRects.size(); i++) {
                if (objRec.getTranslateX() == objRects.get(i).getTranslateX() && (objRec.getTranslateY() - HW) == objRects.get(i).getTranslateY()) {
                    check1 = 2;
                }
            }
        }

        if (check1 == 1) {
            if (man.getTranslateX() == objRec.getTranslateX() && objRec.getTranslateY() != 0 && man.getTranslateY() == (objRec.getTranslateY() + HW)) {
                for (int i = 0; i < Blocks.size(); i++) {
                    if (objRec.getTranslateY() - HW == Blocks.get(i).getTranslateY() && objRec.getTranslateX() == Blocks.get(i).getTranslateX()) {
                        check = 1;
                    }
                }
                if (check == 0) {
                    man.setTranslateY(man.getTranslateY() - HW);
                    objRec.setTranslateY(man.getTranslateY() - HW);
                    moves++;
                    textMoves.setText("Best Moves : " + moves);
                    iterations.add("manUBoxU");
                    iterationsO.add(objRec);
                    System.out.println("1");
                }

            } else if (man.getTranslateX() == objRec.getTranslateX() && man.getTranslateY() != (objRec.getTranslateY() + HW)) {
                man.setTranslateY(man.getTranslateY() - HW);
                moves++;
                textMoves.setText("Best Moves : " + moves);
                iterations.add("manU");
                iterationsO.add(null);
               
            } else if (man.getTranslateX() != objRec.getTranslateX()) {
                man.setTranslateY(man.getTranslateY() - HW);
                 moves++;
                 textMoves.setText("Best Moves : " + moves);
                iterations.add("manU");
                iterationsO.add(null);
               
            }
        } else if (check1 == 0) {
            if (man.getTranslateY() != mapsizeH - HW) {
                man.setTranslateY(man.getTranslateY() - HW);
                moves++;
                textMoves.setText("Best Moves : " + moves);
                iterations.add("manU");
                iterationsO.add(null);
            }
        }
    }
    public void moveRight() {
        int check = 0, check1 = 0;
        Rectangle objRec = null;
        for (int i = 0; i < objRects.size(); i++) {
            if (man.getTranslateY() == objRects.get(i).getTranslateY() && (man.getTranslateX() + HW) == objRects.get(i).getTranslateX()) {
                objRec = objRects.get(i);
                check1 = 1;
            }
        }
        if (check1 == 1) {
            for (int i = 0; i < objRects.size(); i++) {
                if (objRec.getTranslateY() == objRects.get(i).getTranslateY() && (objRec.getTranslateX() + HW) == objRects.get(i).getTranslateX()) {
                    check1 = 2;
                }
            }
        }
        if (check1 == 1) {
            if (man.getTranslateY() == objRec.getTranslateY() && objRec.getTranslateX() != mapsizeW - HW && man.getTranslateX() == (objRec.getTranslateX() - HW)) {

                for (int i = 0; i < Blocks.size(); i++) {
                    if (objRec.getTranslateX() + HW == Blocks.get(i).getTranslateX() && objRec.getTranslateY() == Blocks.get(i).getTranslateY()) {
                        check = 1;
                    }
                }
                if (check == 0) {
                    man.setTranslateX(man.getTranslateX() + HW);
                    objRec.setTranslateX(man.getTranslateX() + HW);
                     moves++;
                     textMoves.setText("Best Moves : " + moves);
                    iterations.add("manRBoxR");
                    iterationsO.add(objRec);
                    
                }
            } else if (man.getTranslateY() == objRec.getTranslateY() && man.getTranslateX() != (objRec.getTranslateX() - HW)) {
                man.setTranslateX(man.getTranslateX() + HW);
                 moves++;
                 textMoves.setText("Best Moves : " + moves);
                iterations.add("manR");
                iterationsO.add(null);
              
            } else if (man.getTranslateY() != objRec.getTranslateY()) {
                man.setTranslateX(man.getTranslateX() + HW);
                 moves++;
                 textMoves.setText("Best Moves : " + moves);
                iterations.add("manR");
                iterationsO.add(null);
         
            } else {
                System.out.println(" do nothing ");
            }
        } else if (check1 == 0) {
            if (man.getTranslateX() != mapsizeW - HW) {
                man.setTranslateX(man.getTranslateX() + HW);
                 moves++;
                 textMoves.setText("Best Moves : " + moves);
                iterations.add("manR");
                iterationsO.add(null);
            }
        }
    }
    public void moveLeft() {
        int check = 0, check1 = 0;
        Rectangle objRec = null;
        for (int i = 0; i < objRects.size(); i++) {
            if (man.getTranslateY() == objRects.get(i).getTranslateY() && (man.getTranslateX() - HW) == objRects.get(i).getTranslateX()) {
                objRec = objRects.get(i);
                check1 = 1;
            }
        }
        if (check1 == 1) {
            for (int i = 0; i < objRects.size(); i++) {
                if (objRec.getTranslateY() == objRects.get(i).getTranslateY() && (objRec.getTranslateX() - HW) == objRects.get(i).getTranslateX()) {
                    check1 = 2;
                }
            }
        }
        if (check1 == 1) {
            if (man.getTranslateY() == objRec.getTranslateY() && objRec.getTranslateX() != 0 && man.getTranslateX() == (objRec.getTranslateX() + HW)) {

                for (int i = 0; i < Blocks.size(); i++) {
                    if (objRec.getTranslateX() - HW == Blocks.get(i).getTranslateX() && objRec.getTranslateY() == Blocks.get(i).getTranslateY()) {
                        check = 1;
                    }
                }
                if (check == 0) {
                    man.setTranslateX(man.getTranslateX() - HW);
                    objRec.setTranslateX(man.getTranslateX() - HW);
                     moves++;
                     textMoves.setText("Best Moves : " + moves);
                    iterations.add("manLBoxL");
                    iterationsO.add(objRec);
                }
            } else if (man.getTranslateY() == objRec.getTranslateY() && man.getTranslateX() != (objRec.getTranslateX() + HW)) {
                man.setTranslateX(man.getTranslateX() - HW);
                 moves++;
                 textMoves.setText("Best Moves : " + moves);
                iterations.add("manL");
                iterationsO.add(null);
            } else if (man.getTranslateY() != objRec.getTranslateY()) {
                man.setTranslateX(man.getTranslateX() - HW);
                 moves++;
                 textMoves.setText("Best Moves : " + moves);
                iterations.add("manL");
                iterationsO.add(null);
            } else {
                System.out.println(" do nothing ");
            }
        } else if (check1 == 0) {
            if (man.getTranslateX() != mapsizeW - HW) {
                man.setTranslateX(man.getTranslateX() - HW);
                moves++;
                textMoves.setText("Best Moves : " + moves);
                iterations.add("manL");
                iterationsO.add(null);
            }
        }
    }

    public int manBlockColiUP() {
        int i = 0;
        for (i = 0; i < Blocks.size(); i++) {
            if (man.getTranslateY() == (Blocks.get(i).getTranslateY() + HW) && man.getTranslateX() == Blocks.get(i).getTranslateX()) {
                return 1;
            }
        }
        return 0;
    }
    public int manBlockColiDown() {
        int i = 0;
        for (i = 0; i < Blocks.size(); i++) {
            if (man.getTranslateY() == (Blocks.get(i).getTranslateY() - HW) && man.getTranslateX() == Blocks.get(i).getTranslateX()) {
                return 1;
            }
        }
        return 0;
    }
    public int manBlockColiLeft() {
        int i = 0;
        for (i = 0; i < Blocks.size(); i++) {
            if (man.getTranslateX() == (Blocks.get(i).getTranslateX() + HW) && man.getTranslateY() == Blocks.get(i).getTranslateY()) {
                return 1;
            }
        }
        return 0;
    }
    public int manBlockColiRight() {
        int i = 0;
        for (i = 0; i < Blocks.size(); i++) {
            if (man.getTranslateX() == (Blocks.get(i).getTranslateX() - HW) && man.getTranslateY() == Blocks.get(i).getTranslateY()) {
                return 1;
            }
        }
        return 0;
    }
    
    public void changeManImage(char direction){
        switch(direction){
            case 'w':
                man.setFill(new ImagePattern(imgman));
                break;
            case 'd':
                 man.setFill(new ImagePattern(manRight));
                break;
            case 's':
                 man.setFill(new ImagePattern(manBehind));
                break;
            case 'a':
                 man.setFill(new ImagePattern(manLeft));
                break;
        }
    }
    public void printIte(){
        int i=0;
        System.out.println("00");
        for(i=0;i<iterations.size() ; i++){
            if(iterationsO.get(i)!=null){
                System.out.println(iterations.get(i)+" "+iterationsO.get(i).toString()+" - ");
            }
            else{
                 System.out.println(iterations.get(i)+" null - ");
            }
        }
        
        System.out.println("00");
    }
    public void printsec(){
        Set set = durations.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
        Map.Entry mentry = (Map.Entry)iterator.next();
        System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
        System.out.println(mentry.getValue());
      }
    
    }
    public void undo(){
        Rectangle objrec;
        String direction ;
        if(iterations.size()!=0){
            moves--;
            textMoves.setText("Best Moves : " + moves);
            objrec = iterationsO.get(iterationsO.size()-1);
            direction = iterations.get(iterations.size()-1);
            if( direction.equals("manUBoxU")){
                objrec.setTranslateY(objrec.getTranslateY()+HW);
                man.setTranslateY(man.getTranslateY()+HW);
                iterations.remove(iterations.size()-1);
                iterationsO.remove(iterationsO.size()-1);
            }
            else if(direction.equals("manU")){
                man.setTranslateY(man.getTranslateY()+HW);
                iterations.remove(iterations.size()-1);
                iterationsO.remove(iterationsO.size()-1);
            }
            
            if( direction.equals("manDBoxD")){
                objrec.setTranslateY(objrec.getTranslateY()-HW);
                man.setTranslateY(man.getTranslateY()-HW);
                iterations.remove(iterations.size()-1);
                iterationsO.remove(iterationsO.size()-1);
            }
            else if(direction.equals("manD")){
                man.setTranslateY(man.getTranslateY()-HW);
                iterations.remove(iterations.size()-1);
                iterationsO.remove(iterationsO.size()-1);
            }
            if( direction.equals("manRBoxR")){
                objrec.setTranslateX(objrec.getTranslateX()-HW);
                man.setTranslateX(man.getTranslateX()-HW);
                iterations.remove(iterations.size()-1);
                iterationsO.remove(iterationsO.size()-1);
            }
            else if(direction.equals("manR")){
                man.setTranslateX(man.getTranslateX()-HW);
                iterations.remove(iterations.size()-1);
                iterationsO.remove(iterationsO.size()-1);
            }
            
            if( direction.equals("manLBoxL")){
                objrec.setTranslateX(objrec.getTranslateX()+HW);
                man.setTranslateX(man.getTranslateX()+HW);
                iterations.remove(iterations.size()-1);
                iterationsO.remove(iterationsO.size()-1);
            }
            else if(direction.equals("manL")){
                man.setTranslateX(man.getTranslateX()+HW);
                iterations.remove(iterations.size()-1);
                iterationsO.remove(iterationsO.size()-1);
            }
            
        }
        ManOnWinRec();
     
        WinObjeColi();
    }
    public void clearToAnotherMap(){
        root.getChildren().clear();
        iterations.clear();
        iterationsO.clear();
        objRects.clear();
        winRects.clear();
    }
    public void start(Stage stagee)     {
        timer.scheduleAtFixedRate(task, 1000,1000);
        stage = stagee;
        stage.setScene(new Scene(createContent()));
        stage.getScene().setOnKeyPressed(event -> {
            if (levelN > 0 && levelN <= 10) {
                switch (event.getCode()) {
                    case W: {
                        System.out.println(" i : " + levelN);
                        if ( manBlockColiUP() != 1) {
                            moveUp();
                            ManOnWinRec();
                            objOnWinRec();
                            changeManImage('w');
                            WinObjeColi();
                            //printIte();
                        }
                        System.out.println(number_of_moves[1]);
                    }
                    break;
                    case S:
                        if (manBlockColiDown() != 1) {
                            moveDown();
                            ManOnWinRec();
                            objOnWinRec();
                            changeManImage('s');
                            WinObjeColi();
                            //printIte();
                        }
                        break;
                    case A:
                        if ( manBlockColiLeft() != 1) {
                            moveLeft();
                            ManOnWinRec();
                            objOnWinRec();
                            changeManImage('a');
                            WinObjeColi();
                            //printIte();
                           
                        }

                        break;
                    case D:
                        if (manBlockColiRight() != 1) {
                            moveRight();
                            ManOnWinRec();
                            objOnWinRec();
                            changeManImage('d');
                            WinObjeColi();
                            //printIte();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        stage.setTitle("Game");
        stage.show();
    }
}
