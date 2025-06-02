package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.ToDoList;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class toDoListController implements Initializable {
    public ListView ListViewPanel;
    public TextField txtItems;
    public TableView tblTasks;
    public TableColumn colCompletedTasks;

    private ObservableList<ToDoList>toDoListObservableList = FXCollections.observableArrayList();

    List<ToDoList>  incomepletedTasks =toDoListObservableList.stream()
            .filter(toDoList -> !toDoList.isComplete())
            .collect(Collectors.toList());

    List<ToDoList>  comepletedTasks =toDoListObservableList.stream()
            .filter(toDoList -> toDoList.isComplete())
            .collect(Collectors.toList());


    public void handlingTasks(){

        String userText = txtItems.getText();

        if(!userText.isEmpty()){
            ToDoList newTask =new ToDoList(userText,false);
            toDoListObservableList.add(newTask);

            CheckBox checkBox = new CheckBox(userText);

            checkBox.setOnAction(event -> {
                int  index = ListViewPanel.getItems().indexOf(checkBox);

                if(index>=0 && index<toDoListObservableList.size()){
                    toDoListObservableList.get(index).setComplete(checkBox.isSelected());

                    updateTaskList();

                    updateTaskInDatabase(toDoListObservableList.get(index));
                }
            });

            ListViewPanel.getItems().add(checkBox);
            txtItems.clear();


        }
    }

    public void updateTaskList(){
        incomepletedTasks =toDoListObservableList.stream()
                .filter(toDoList -> !toDoList.isComplete())
                .collect(Collectors.toList());

        comepletedTasks =toDoListObservableList.stream()
                .filter(toDoList -> toDoList.isComplete())
                .collect(Collectors.toList());
    }

    public void updateTaskInDatabase(ToDoList task){

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ToDoLists", "root", "1234");
            PreparedStatement preparedStatement =connection.prepareStatement("UPDATE item SET isCompleted = ? WHERE task_name = ? ");
            preparedStatement.setBoolean(1,task.isComplete());
            preparedStatement.setString(2,task.getTask());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnAddOnClick(ActionEvent event) {
        handlingTasks();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ToDoLists", "root", "1234");
            PreparedStatement preparedStatementAdd = connection.prepareStatement("INSERT INTO item(task_name,isCompleted)VALUES (?,?)");

            for (ToDoList listItems:toDoListObservableList){
                    preparedStatementAdd.setString(1,listItems.getTask());
                    preparedStatementAdd.setBoolean(2,listItems.isComplete());

            }

            if(preparedStatementAdd.executeUpdate()>0){
                Notifications notificationBuilder = Notifications.create()
                        .title("Added Successfully")
                        .text("saved to/home/downloads")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("clicked on notification");
                            }

                        });
                notificationBuilder.showConfirm();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnReloadOnClick(ActionEvent event) {

        updateTaskList();

        System.out.println(comepletedTasks);

        ObservableList<ToDoList> completedTableTasts = FXCollections.observableArrayList(comepletedTasks);

        tblTasks.setItems(completedTableTasts);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCompletedTasks.setCellValueFactory(new PropertyValueFactory<>("task"));

        ListViewPanel.setItems(FXCollections.observableArrayList());
    }

    public void btnDeleteOnClick(ActionEvent event) {

        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you  sure u want to delete this?").showAndWait();

        if(result.get()==ButtonType.OK) {
            updateTaskList();
            deleteItemsFromDatabase();
            toDoListObservableList.removeAll(comepletedTasks);


            ListViewPanel.getItems().removeIf(item ->
                    item instanceof CheckBox && ((CheckBox) item).isSelected()
            );
        }else{
        Notifications notificationBuilder = Notifications.create()
                .title("Deleting Data")
                .text("Data didn't Delete!")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("clicked on notification");
                    }

                });
        notificationBuilder.showConfirm();
    }



    }

    public void deleteItemsFromDatabase(){
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ToDoLists", "root", "1234");
                PreparedStatement preparedStatementDelete = connection.prepareStatement("DELETE FROM item WHERE task_name = ?");

                for (ToDoList completedTasking:comepletedTasks){
                    preparedStatementDelete.setString(1,completedTasking.getTask());
                    preparedStatementDelete.executeUpdate();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }




    }
}
