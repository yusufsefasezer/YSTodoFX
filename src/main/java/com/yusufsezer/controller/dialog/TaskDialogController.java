package com.yusufsezer.controller.dialog;

import com.yusufsezer.contract.DialogControllerBase;
import com.yusufsezer.model.Category;
import com.yusufsezer.model.Task;
import com.yusufsezer.util.DialogUtils;
import com.yusufsezer.util.JPAUtils;
import com.yusufsezer.util.JavaFXUtils;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

public class TaskDialogController implements Initializable, DialogControllerBase<Task> {

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<Category> categoryComboBox;

    @FXML
    private HTMLEditor descriptionHTMLEditor;

    @FXML
    private ComboBox<Task.Status> statusComboBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryComboBox
                .getItems()
                .setAll(JPAUtils
                        .getCategoryService()
                        .findAll());
        statusComboBox
                .getItems()
                .addAll(Task.Status.values());
    }

    public static Optional<Task> createAddTaskDialog(String name) {
        String title = JavaFXUtils.getBundleMessage("dialog.taskAddTitle");
        String headerText = JavaFXUtils.getBundleMessage("dialog.taskAddHeaderText");
        String fxml = "task";
        DialogControllerBase<Task> tdc = new TaskDialogController();
        Dialog<Task> dialog = DialogUtils.<Task>createCustomDialog(title, headerText, fxml, tdc);
        Task task = new Task();
        task.setName(name);
        tdc.set(task);
        dialog.setResultConverter(p -> {
            return ButtonType.OK.equals(p) ? tdc.create() : null;
        });
        return dialog.showAndWait();
    }

    public static Optional<Task> createEditTaskDialog(Task task) {
        String title = JavaFXUtils.getBundleMessage("dialog.taskEditTitle");
        String headerText = JavaFXUtils.getBundleMessage("dialog.taskEditHeaderText");
        String fxml = "task";
        DialogControllerBase<Task> tdc = new TaskDialogController();
        Dialog<Task> dialog = DialogUtils.<Task>createCustomDialog(title, headerText, fxml, tdc);
        tdc.set(task);
        ButtonType deleteButtonType = new ButtonType(JavaFXUtils.getBundleMessage("dialog.taskDeleteButton"));
        dialog
                .getDialogPane()
                .getButtonTypes()
                .add(deleteButtonType);
        dialog.setResultConverter(p -> {
            if (ButtonType.OK.equals(p)) {
                return tdc.edit(task);
            } else if (deleteButtonType.equals(p)) {
                task.setDeleted(true);
                return task;
            }
            return null;
        });
        return dialog.showAndWait();
    }

    @Override
    public Task create() {
        return edit(new Task());
    }

    @Override
    public Task edit(Task entity) {
        entity.setName(nameTextField.getText());
        entity.setDescription(descriptionHTMLEditor.getHtmlText());
        entity.setCategory(categoryComboBox.getValue());
        entity.setStatus(statusComboBox.getValue());
        return entity;
    }

    @Override
    public void set(Task task) {
        nameTextField.setText(task.getName());
        descriptionHTMLEditor.setHtmlText(task.getDescription());
        categoryComboBox.setValue(task.getCategory());
        statusComboBox.setValue(task.getStatus());
    }

}
