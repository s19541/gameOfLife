import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;

public class Gui {
    static Button[][] buttons;
    static int x=25;
    static int y=25;
    public static void createGui() {
        JFXPanel panel = new JFXPanel();
        JFrame frame = new JFrame("Game of life");
        Platform.runLater(()->{

            Pane root = new FlowPane();
            boolean[][] board = new boolean[x][y];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j <board[i].length ; j++) {
                    board[i][j]=false;
                }
            }
            buttons = new Button[x][y];
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    buttons[i][j]=new Button();
                    buttons[i][j].setPrefSize(30,5);
                    int tmp=i,tmp2=j;
                    buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            buttons[tmp][tmp2].setStyle("-fx-background-color: MediumSeaGreen");
                            board[tmp][tmp2]=true;
                        }
                    });
                    root.getChildren().add(buttons[i][j]);
                }
            }
            Button startButton=new Button("Rozpocznij symulacje");
            startButton.setPrefSize(754,5);
            startButton.setStyle("-fx-background-color: Green");
            startButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (int i = 0; i < buttons.length; i++) {
                        for (int j = 0; j < buttons[i].length; j++) {
                            buttons[i][j].setOnAction(null);
                        }
                    }
                    startButton.setOnAction(null);
                    startButton.setStyle("-fx-background-color: Red");
                    new GameOfLife(board);
                }
            });
            root.getChildren().add(startButton);
            Scene scene = new Scene(root);
            panel.setScene(scene);
            frame.add(panel);
            frame.setDefaultCloseOperation(3);
            frame.setPreferredSize(new Dimension(757,840));
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        });
    }
    static void refreshButtons(boolean[][]board){
        System.out.println("xd");
        System.out.println(buttons.length);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j])
                    buttons[i][j].setStyle("-fx-background-color: MediumSeaGreen");
                else
                    buttons[i][j].setStyle("-fx-background-color: White");
            }
        }

    }
}
