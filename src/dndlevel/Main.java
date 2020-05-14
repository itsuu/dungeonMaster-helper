package dndlevel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

public class Main extends Application {

    ListView<String> listViewChamberList;
    BorderPane borders;
    Stage window;
    ArrayList<Chamber> chamberList;
    TextArea text;
    ComboBox doorList;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        window.setTitle("Dungeons and Dragons");
        borders = new BorderPane();

        Label nameLabel = new Label("Dungeon Level");
        nameLabel.setFont(new Font(20));
        nameLabel.setPadding(new Insets(20 , 0, 10, 430));
        VBox topVBox = new VBox(nameLabel);
        borders.setTop(topVBox);


        text = new TextArea();
        HBox centerHBox = new HBox(text);
        centerHBox.setPadding(new Insets(10, 20, 50, 20));
        borders.setCenter(centerHBox);

        Label doorListLabel = new Label("Door List");
        doorListLabel.setFont(new Font(20));
        doorListLabel.setPadding(new Insets(10 , 35, 10, 35));
        VBox rightVBox = new VBox();
        doorList = new ComboBox();
        doorList.setPrefWidth(170);
        doorList.setPromptText("Door List");
        rightVBox.getChildren().add(doorListLabel);
        rightVBox.getChildren().add(doorList);
        rightVBox.setPadding(new Insets(50, 30, 50, 50));
        borders.setRight(rightVBox);


        listViewChamberList = new ListView<String>();
        listViewChamberList.getItems().addAll("Chamber 1", "Chamber 2", "Chamber 3", "Chamber 4", "Chamber 5");
        Label chamberListLabel = new Label("Chamber List");
        chamberListLabel.setFont(new Font(20));
        chamberListLabel.setPadding(new Insets(10 , 35, 10, 35));
        VBox leftVBox = new VBox(chamberListLabel, listViewChamberList);
        leftVBox.setPadding(new Insets(50, 50, 50, 30));
        borders.setLeft(leftVBox);
        Scene mainScene = new Scene(borders,900,600);
        window.setScene(mainScene);
        window.show();

        Level level = new Level();
        level.runMe();
        chamberList = level.returnChamberList();

        //loadDoorList(listViewChamberList);
        clickedOnChamberList(listViewChamberList);
    }

    public void clickedOnChamberList(ListView<String> listViewChamberList) {
        listViewChamberList.setOnMouseClicked(e -> displayInfo(listViewChamberList.getSelectionModel().getSelectedItem()));
        listViewChamberList.setOnMouseReleased(e -> displayDoorList(listViewChamberList.getSelectionModel().getSelectedItem()));
    }

    public void displayDoorInfo(String doorName) {
        if(doorName.contains("Chamber 1"))
        {
            if(doorName.contains("Door 1"))
            {
                text.setText(chamberList.get(0).getDoors().get(0).getDescription());

            }
            else if(doorName.contains("Door 2"))
            {
                text.setText(chamberList.get(0).getDoors().get(1).getDescription());

            }
            else if(doorName.contains("Door 3"))
            {
                text.setText(chamberList.get(0).getDoors().get(2).getDescription());

            }
            else if(doorName.contains("Door 4"))
            {
                text.setText(chamberList.get(0).getDoors().get(3).getDescription());

            }
            else if(doorName.contains("Door 5"))
            {
                text.setText(chamberList.get(0).getDoors().get(4).getDescription());
            }

            text.setEditable(false);
        }
        else if(doorName.contains("Chamber 2"))
        {
            ArrayList<Door> doors = chamberList.get(1).getDoors();
            for(int i = 0; i <doors.size(); i++)
            {
                text.setText(chamberList.get(1).getDoors().get(i).getDescription());
            }
            text.setEditable(false);
        }
        else if(doorName.contains("Chamber 3"))
        {
            ArrayList<Door> doors = chamberList.get(2).getDoors();
            for(int i = 0; i <doors.size(); i++)
            {
                text.setText(chamberList.get(2).getDoors().get(i).getDescription());
            }
            text.setEditable(false);
        }
        else if(doorName.contains("Chamber 4"))
        {
            ArrayList<Door> doors = chamberList.get(3).getDoors();
            for(int i = 0; i <doors.size(); i++)
            {
                text.setText(chamberList.get(3).getDoors().get(i).getDescription());
            }
            text.setEditable(false);
        }
        else if(doorName.contains("Chamber 5"))
        {
            ArrayList<Door> doors = chamberList.get(4).getDoors();
            for(int i = 0; i <doors.size(); i++)
            {
                text.setText(chamberList.get(4).getDoors().get(i).getDescription());
            }
            text.setEditable(false);
        }

    }

    public void displayDoorList(String doorName) {
        if(doorName.contains("1"))
        {
            ArrayList<Door> doors = chamberList.get(0).getDoors();
            ArrayList<String> doorString = new ArrayList<>();
            for(int i = 0; i < doors.size(); i++)
            {
                doorString.add("Chamber 1: Door " + (i + 1));
            }
            ObservableList<String> addToList = FXCollections.observableArrayList(doorString);

            doorList.setItems(addToList);
            doorList.setOnAction(e -> displayDoorInfo(doorList.getItems().toString()));
        }
        else if(doorName.contains("2"))
        {
            ArrayList<Door> doors = chamberList.get(1).getDoors();
            ArrayList<String> doorString = new ArrayList<>();
            for(int i = 0; i < doors.size(); i++)
            {
                doorString.add("Chamber 2: Door " + (i + 1));
            }
            ObservableList<String> addToList = FXCollections.observableArrayList(doorString);

            doorList.setItems(addToList);
            doorList.setOnAction(e -> displayDoorInfo(doorList.getItems().toString()));
        }
        else if(doorName.contains("3"))
        {
            ArrayList<Door> doors = chamberList.get(2).getDoors();
            ArrayList<String> doorString = new ArrayList<>();
            for(int i = 0; i < doors.size(); i++)
            {
                doorString.add("Chamber 3: Door " + (i + 1));
            }
            ObservableList<String> addToList = FXCollections.observableArrayList(doorString);

            doorList.setItems(addToList);
            doorList.setOnAction(e -> displayDoorInfo(doorList.getItems().toString()));
        }
        else if(doorName.contains("4")) {
            ArrayList<Door> doors = chamberList.get(3).getDoors();
            ArrayList<String> doorString = new ArrayList<>();
            for(int i = 0; i < doors.size(); i++)
            {
                doorString.add("Chamber 4: Door " + (i + 1));
            }
            ObservableList<String> addToList = FXCollections.observableArrayList(doorString);

            doorList.setItems(addToList);
            doorList.setOnAction(e -> displayDoorInfo(doorList.getItems().toString()));
        }
        else if(doorName.contains("5"))
        {
            ArrayList<Door> doors = chamberList.get(4).getDoors();
            ArrayList<String> doorString = new ArrayList<>();
            for(int i = 0; i < doors.size(); i++)
            {
                doorString.add("Chamber 5: Door " + (i + 1));
            }
            ObservableList<String> addToList = FXCollections.observableArrayList(doorString);

            doorList.setItems(addToList);
            doorList.setOnAction(e -> displayDoorInfo(doorList.getItems().toString()));
        }
    }


    public void displayInfo(String chamberName) {
        if(chamberName.contains("1"))
        {
            setText1();

        }
        else if(chamberName.contains("2"))
        {
            setText2();

        }
        else if(chamberName.contains("3"))
        {
            setText3();

        }
        else if(chamberName.contains("4"))
        {
            setText4();

        }
        else if(chamberName.contains("5"))
        {
            setText5();

        }
    }

    public void setText1() {
        text.setText(chamberList.get(0).getAllChamber());
        text.setEditable(false);
    }
    public void setText2() {
        text.setText(chamberList.get(1).getAllChamber());
        text.setEditable(false);
    }
    public void setText3() {
        text.setText(chamberList.get(2).getAllChamber());
        text.setEditable(false);
    }
    public void setText4() {
        text.setText(chamberList.get(3).getAllChamber());
        text.setEditable(false);
    }
    public void setText5() {
        text.setText(chamberList.get(4).getAllChamber());
        text.setEditable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
