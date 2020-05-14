package dndlevel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;

public class Controller {

    @FXML private ListView<String> listViewChamberList;
    public ArrayList<Chamber> chamberList;

    public Controller() {
        //listViewChamberList = new ListView<String>();
        //listViewChamberList.getItems().add("Cat");
    }

    public void level() {
        Level level = new Level();
        level.runMe();
        chamberList = level.returnChamberList();
    }
}
