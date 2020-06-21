package com.yusufsezer.ystodofx.controller.dialog;

import com.yusufsezer.ystodofx.contract.DialogControllerBase;
import com.yusufsezer.ystodofx.model.Category;
import com.yusufsezer.ystodofx.util.DialogUtil;
import com.yusufsezer.ystodofx.util.JavaFXUtil;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CategoryDialogController implements
        Initializable, DialogControllerBase<Category> {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private ColorPicker colorColorPicker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public static Optional<Category> createAddCategoryDialog() {
        String title = JavaFXUtil
                .getBundleMessage("dialog.categoryAddTitle");
        String headerText = JavaFXUtil
                .getBundleMessage("dialog.categoryAddHeaderText");
        String fxml = "category";
        DialogControllerBase<Category> cdc = new CategoryDialogController();
        Dialog<Category> dialog = DialogUtil
                .<Category>createCustomDialog(title, headerText, fxml, cdc);
        dialog.setResultConverter(p -> {
            return ButtonType.OK.equals(p) ? cdc.create() : null;
        });
        return dialog.showAndWait();
    }

    public static Optional<Category>
            createEditCategoryDialog(Category category) {
        String title = JavaFXUtil
                .getBundleMessage("dialog.categoryEditTitle");
        String headerText = JavaFXUtil
                .getBundleMessage("dialog.categoryEditHeaderText");
        String fxml = "category";
        DialogControllerBase<Category> cdc = new CategoryDialogController();
        Dialog<Category> dialog = DialogUtil
                .<Category>createCustomDialog(title, headerText, fxml, cdc);
        cdc.set(category);
        ButtonType deleteButtonType = new ButtonType(JavaFXUtil
                .getBundleMessage("dialog.categoryDeleteButton"));
        dialog
                .getDialogPane()
                .getButtonTypes()
                .add(deleteButtonType);
        dialog.setResultConverter(p -> {
            if (ButtonType.OK.equals(p)) {
                return cdc.edit(category);
            } else if (deleteButtonType.equals(p)) {
                category.setDeleted(true);
                return category;
            }
            return null;
        });
        return dialog.showAndWait();
    }

    @Override
    public Category create() {
        return edit(new Category());
    }

    @Override
    public Category edit(Category entity) {
        entity.setName(nameTextField.getText());
        entity.setDescription(descriptionTextArea.getText());
        entity.setColor(colorColorPicker.getValue().toString());
        return entity;
    }

    @Override
    public void set(Category category) {
        nameTextField.setText(category.getName());
        descriptionTextArea.setText(category.getDescription());
        colorColorPicker.setValue(Color.web(category.getColor()));
    }

}
