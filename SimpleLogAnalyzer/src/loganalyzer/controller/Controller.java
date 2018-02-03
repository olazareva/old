package loganalyzer.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import loganalyzer.entity.HttpMethod;
import loganalyzer.service.ILogAnalyzer;
import loganalyzer.entity.LogToken;
import loganalyzer.servicelocator.ServiceLocator;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.ResourceBundle;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Controller implements Initializable {
    @FXML
    private Button openButton;
    @FXML
    private TextField startTimePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private TextField endTimePicker;
    @FXML
    private Button okButton;
    @FXML
    private Label pathLabel;
    @FXML
    private TableView<LogToken> tableView;
    @FXML
    private TableColumn<LogToken, LocalDateTime> time;
    @FXML
    private TableColumn<LogToken, HttpMethod> method;
    @FXML
    private TableColumn<LogToken, String> message;

    private ILogAnalyzer logAnalyzer;
    private String filePath;

    public Controller() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();
        logAnalyzer = serviceLocator.get(ILogAnalyzer.class);
    }


    public void onOpenButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
            pathLabel.setText(filePath);
        }
    }

    public void onOkButtonAction() {
        LocalDateTime timeFrom;
        LocalDateTime timeTo;
        if (isInputParametersValid()) {
            timeFrom = LocalDateTime.of(startDatePicker.getValue(), GetTimePicker(startTimePicker));
            timeTo = LocalDateTime.of(endDatePicker.getValue(), GetTimePicker(endTimePicker));
            if (timeFrom.isAfter(timeTo)) {
                throw new IllegalArgumentException("Start date/time must be earlier End date/time");
            }
            Collection<LogToken> list = logAnalyzer.getTokenFromFile(filePath, timeFrom, timeTo);
            ObservableList<LogToken> usersData = FXCollections.observableArrayList(list);
            tableView.setItems(usersData);
        }
    }


    public void onSetStartTimePicker() {
        validateTime(startTimePicker);
    }

    public void onSetEndTimePicker() {
        validateTime(endTimePicker);
    }


    private LocalTime GetTimePicker(TextField time) {
        return LocalTime.parse(time.getText(), ofPattern("HH:mm:ss"));
    }

    private boolean validateTime(TextField time) {
        String str = time.getText();
        int hh = Integer.parseInt(str.substring(0, 2));
        int mm = Integer.parseInt(str.substring(3, 5));
        int ss = Integer.parseInt(str.substring(6, 8));
        System.out.println(hh + ":" + mm + ":" + ss);
        if ((hh >= 0 && hh < 24) && (mm >= 0 && mm < 60) && (ss >= 0 && ss < 60) && str.length() == 8) {
            return true;
        } else throw new IllegalArgumentException("Illegal time value " + str);
    }

    private boolean isInputParametersValid() {
        if (filePath == null) {
            throw new IllegalArgumentException("Log file is not chosen");
        }
        if ((startDatePicker.getValue() == null) || (endDatePicker.getValue() == null)) {
            throw new IllegalArgumentException("Start/End dates must be filled out");
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        method.setCellValueFactory(new PropertyValueFactory<>("method"));
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
    }
}
