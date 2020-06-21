package com.yusufsezer.ystodofx.controller;

import com.yusufsezer.ystodofx.controller.custom.CategoryCellController;
import com.yusufsezer.ystodofx.controller.custom.TaskCellController;
import com.yusufsezer.ystodofx.controller.dialog.CategoryDialogController;
import com.yusufsezer.ystodofx.controller.dialog.TaskDialogController;
import com.yusufsezer.ystodofx.model.Category;
import com.yusufsezer.ystodofx.model.Task;
import com.yusufsezer.ystodofx.util.DummyDataUtil;
import com.yusufsezer.ystodofx.util.JPAUtil;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import org.kordamp.ikonli.javafx.FontIcon;

public class MainController implements Initializable {

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<Category> categoryListView;

    @FXML
    private ListView<Task> taskListView;

    @FXML
    private Label titleLabel;

    @FXML
    private FontIcon taskEditIcon;

    @FXML
    private WebView contentWebView;

    @FXML
    private TextField quickAddTextField;

    ObservableList<Category> observableList = FXCollections
            .observableList(DummyDataUtil.CATEGORIES);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Category
        categoryListView
                .setCellFactory(p -> new CategoryCellController());
        categoryListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((ov, oldValue, newValue) -> {
                    if (newValue != null) {
                        showCategoryTasks(newValue);
                    }
                    taskListView
                            .getSelectionModel()
                            .selectFirst();
                });
        loadCategories();
        categoryListView
                .getSelectionModel()
                .selectFirst();

        // Task
        taskListView
                .setCellFactory(p -> new TaskCellController());
        taskListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((ov, oldValue, newValue) -> {
                    if (newValue != null) {
                        showTaskContent(newValue);
                    }
                });
        taskListView
                .getSelectionModel()
                .selectFirst();
    }

    @FXML
    void onAddNewCategory(MouseEvent event) {
        Optional<Category> result = CategoryDialogController
                .createAddCategoryDialog();
        if (result.isPresent()) {
            JPAUtil
                    .getCategoryService()
                    .create(result.get());
            loadCategories();
        }
    }

    @FXML
    void onKeyTaskReleased(KeyEvent event) {
        boolean isEnterPressed = event.getCode() == KeyCode.ENTER;
        if (isEnterPressed) {
            Optional<Task> result = TaskDialogController
                    .createAddTaskDialog(quickAddTextField.getText());
            if (result.isPresent()) {
                JPAUtil
                        .getTaskService()
                        .create(result.get());
            }
            quickAddTextField.clear();
        }
    }

    @FXML
    void onKeySearchReleased(KeyEvent event) {
        if (event.getSource() instanceof TextField) {
            TextField sourceTextField = (TextField) event.getSource();
            String searchString = sourceTextField.getText();
            observableList.filtered(s -> {
                return searchString.isEmpty()
                        ? true
                        : s.getName().toLowerCase()
                                .contains(searchString.toLowerCase());
            });
        }
    }

    @FXML
    void onMouseEditPressed(MouseEvent event) {
        Task selectedTask = taskListView
                .getSelectionModel()
                .getSelectedItem();
        if (selectedTask != null) {
            Optional<Task> result = TaskDialogController
                    .createEditTaskDialog(selectedTask);
            if (result.isPresent()) {
                JPAUtil
                        .getTaskService()
                        .edit(result.get());
            }
        }
    }

    @FXML
    void onMouseRemovePressed(MouseEvent event) {
        Task selectedTask = taskListView.getSelectionModel()
                .getSelectedItem();
        if (selectedTask != null) {
            selectedTask.setDeleted(true);
            JPAUtil
                    .getTaskService()
                    .edit(selectedTask);
            taskListView
                    .getItems()
                    .remove(selectedTask);
            taskListView
                    .getSelectionModel()
                    .selectFirst();
        }
    }

    @FXML
    void onShowAbout(MouseEvent event) {
        Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
        aboutDialog.setTitle("www.yusufsezer.com");
        aboutDialog.setHeaderText("YSTodoFX");
        aboutDialog.setContentText("Created by Yusuf SEZER");
        aboutDialog.show();
    }

    void showCategoryTasks(Category category) {
        taskListView
                .getItems()
                .setAll(JPAUtil
                        .getCategoryService()
                        .find(category.getId())
                        .getTasks());
    }

    void showTaskContent(Task selectedTask) {
        titleLabel.setText(selectedTask.getName());
        WebEngine engine = contentWebView
                .getEngine();
        engine
                .setUserStyleSheetLocation("data:,body { font-size: 18px; }");
        engine
                .loadContent(selectedTask.getDescription());
    }

    void loadCategories() {
        categoryListView
                .getItems()
                .setAll(JPAUtil
                        .getCategoryService()
                        .findAll());
    }

}
