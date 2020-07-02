
package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.NoSuchReminderException;
import model.Reminder;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageScreenController extends BasicControls implements Initializable {
//contains the controls for the message screen. Used code from https://www.youtube.com/watch?v=4RNhPZJ84P0
    @FXML
    private Label label;

    @FXML
    private TableView<Reminder> tableview;

    @FXML
    private TableColumn<Reminder, String> emailColumn;

    @FXML
    private TableColumn<Reminder, String> messageColumn;

    @FXML
    private TableColumn<Reminder, Integer> sendDateColumn;

    @FXML
    private TableColumn<Reminder, String> senderColumn;

    @FXML
    private TextField filterField;

    @FXML
    private Button writeEmail;

    @FXML
    private Button deleteButton;

    @FXML
    private Button refreshButton;



    private final ObservableList<Reminder> dataList = FXCollections.observableArrayList(
            State.getState().getReminders().returnReminders());




    //Sends user back to default page.
    @FXML
    void writeEmails() {
        goTo(writeEmail, "DefaultPage");
    }

    //deletes email in the selected row.
    @FXML
    void deleteRow() throws NoSuchReminderException {
        ObservableList<Reminder> remindersSelected;
        remindersSelected = tableview.getSelectionModel().getSelectedItems();
        for (Reminder r: remindersSelected) {
            dataList.remove(r);
            State.getState().getReminders().removeReminder(r);
        }

    }

    //refreshes page
    @FXML
    void reloadScreen() {
        goTo(refreshButton, "MessageScreen");
    }



    //initializes page
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableView();
        filterData();
        tableview.setEditable(true);

    }

    //allows each column to take a property of model class classes.
    private void setTableView() {
        emailColumn.setCellValueFactory(new PropertyValueFactory<Reminder, String>("email"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<Reminder, String>("message"));
        sendDateColumn.setCellValueFactory(new PropertyValueFactory<Reminder, Integer>("due"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<Reminder, String>("label"));
        //load data
        tableview.setItems(dataList);
    }

    //allows search bar to filter through messages.
    private void filterData() {
        FilteredList<Reminder> filteredData = new FilteredList<>(dataList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reminder -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (reminder.getMessage().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (reminder.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.

                } else if (String.valueOf(reminder.getDue()).contains(lowerCaseFilter)) {
                    return true;
                } else if (reminder.getLabel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Reminder> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableview.setItems(sortedData);

    }



}
