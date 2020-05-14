/* 
 * 
 * Author: Muhammad Khairi Norizan
 * Purpose: HW6 COMS 319 TIC TAC TOE
 * 
 * */

package application;
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {
	private char currentPlayer = 'x';
	/* Set number of cell */
	private Cell[][] cell = new Cell[3][3];
	private Label statusMsg = new Label("X must play");
	
	@Override
	public void start(Stage primaryStage) {
		/* SET UP THE PANEL TO DISPLAY CELL */
		GridPane pane = new GridPane();
		
		/* SET CELL AT THE PANE AT FIX POSITION */
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				cell[i][j] = new Cell();
				pane.add(cell[i][j], i, j);
			}
		}
		
		/* CREATE MAIN CONTAINER FOR USER INTERFACE */
		BorderPane borderPane = new BorderPane();
		/* SET PANE TO THE CENTER OF MAIN CONTAINER */
		borderPane.setCenter(pane);
		/* SET STATUS MESSAGE TO THE BOTTOM OF THE MAIN CONTAINER */
		borderPane.setBottom(statusMsg);
		
		/* SET THE SCENE HEIGHT AND WIDTH */
		Scene scene = new Scene(borderPane, 450, 300);
		/* SETTING THE STAGE TITLE */
		primaryStage.setTitle("Tic Tac Toe Game");
		/* PLACING THE SCENE IN THE STAGE */
		primaryStage.setScene(scene);
		/* DISPLAY THE STAGE ON THE SCREEN */
		primaryStage.show();
		
	}
	
	/* CHECK IF THE BOARD IS FULL WITH CHARACTERS */
	public boolean isBoardFull() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; i < 3; j++) {
				if(cell[i][j].getPlayer() == ' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	/* CHECK IF A PLAYER HAS WON THE GAME */
	public boolean hasWon(char player) {
		for(int i = 0; i <  3; i++) {
			if(cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player && cell[i][2].getPlayer() == player) {
				return true;
			}
		}
		for(int i = 0; i <  3; i++) {
			if(cell[0][i].getPlayer() == player && cell[1][i].getPlayer() == player && cell[2][i].getPlayer() == player) {
				return true;
			}
		}
		if(cell[0][0].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer() == player) {
			return true;
		}
		if(cell[0][2].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][0].getPlayer() == player) {
			return true;
		}
		return false;
	}
	
	/* CELL CLASS */
	public class Cell extends Pane{
		private char player = ' ';
		
		public Cell() {
			setStyle("-fx-border-color : black");
			this.setPrefSize(300, 300);
			this.setOnMouseClicked((t) -> handleClick());
			
		}
		
		private void handleClick() {
			if(player == ' ' && currentPlayer != ' ') {
				setPlayer(currentPlayer);
				
				if(hasWon(currentPlayer)) {
					statusMsg.setText(currentPlayer + " won!");
					currentPlayer = ' ';
				}else if(isBoardFull()) {
					statusMsg.setText("No one wins :(");
					currentPlayer = ' ';
				}else {
					currentPlayer = (currentPlayer == 'x') ? 'o' : 'x';
					statusMsg.setText(currentPlayer + " must play");
				}
			}
		}	
		
		public char getPlayer() {
			return player;
		}
		
		public void setPlayer(char c) {
			player = c;
			
			if(player == 'x') {
				/* SET THE IMAGE FOR CHARACTER X */
				FileInputStream inputStream = null;
				try {
					//REPLACE WITH PATH FOR THE FILE
					inputStream = new FileInputStream("C:\\Users\\Khairi\\OneDrive\\Documents\\IOWA STATE UNIVERSITY\\SPRING 2020\\SE 319\\HOMEWORKS\\HW6\\HW6-Files\\HW6-Files\\x.jpg");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Image imageX = new Image(inputStream);
				
				ImageView imageView = new ImageView(imageX);
				
				double setx = this.widthProperty().subtract(110).doubleValue();
				double sety = this.heightProperty().subtract(90).doubleValue();
				
				/* SET POSITION OF X */
				imageView.setX(setx);
				imageView.setY(sety);
				
				/* SET THE SIZE OF THE IMAGE */
				imageView.setFitHeight(80);
				imageView.setFitWidth(80);
								
				getChildren().add(imageView);
				
			}else if(player == 'o'){
				FileInputStream inputStream = null;
				
				//REPLACE WITH PATH FOR THE FILE
				try {
					inputStream = new FileInputStream("C:\\Users\\Khairi\\OneDrive\\Documents\\IOWA STATE UNIVERSITY\\SPRING 2020\\SE 319\\HOMEWORKS\\HW6\\HW6-Files\\HW6-Files\\o.jpg");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/* CREATE IMAGE */
				Image imageO = new Image(inputStream);
				
				ImageView imageView = new ImageView(imageO);
				
				double setx = this.widthProperty().subtract(110).doubleValue();
				double sety = this.heightProperty().subtract(90).doubleValue();
				
				/* SET POSITION OF O */
				imageView.setX(setx);
				imageView.setY(sety);
				
				/* SET THE SIZE OF THE IMAGE */
				imageView.setFitHeight(80);
				imageView.setFitWidth(80);
				
				getChildren().add(imageView);
			}
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
