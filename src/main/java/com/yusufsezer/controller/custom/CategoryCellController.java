package com.yusufsezer.controller.custom;

import com.yusufsezer.controller.dialog.CategoryDialogController;
import com.yusufsezer.model.Category;
import com.yusufsezer.service.CategoryService;
import com.yusufsezer.util.JPAUtils;
import com.yusufsezer.util.JavaFXUtils;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CategoryCellController extends ListCell<Category> {

    @FXML
    private Label categoryCount;

    @FXML
    private Circle categoryColor;

    @FXML
    private Label categoryName;

    @FXML
    void onMouseEditPress(MouseEvent event) {
        Optional<Category> result = CategoryDialogController
                .createEditCategoryDialog(getItem());
        if (result.isPresent()) {
            CategoryService categoryService = JPAUtils
                    .getCategoryService();
            categoryService
                    .edit(result.get());
            getListView()
                    .getItems()
                    .setAll(categoryService
                            .findAll());
            getListView()
                    .getSelectionModel()
                    .selectFirst();
        }
        event.consume();
    }

    @Override
    protected void updateItem(Category category, boolean empty) {
        super.updateItem(category, empty);
        if (!empty) {
            setGraphic(JavaFXUtils.
                    <Node>loadCustomFXML("fxml/custom/", "category", this));
            categoryName.setText(category.getName());
            categoryColor.setFill(Color.web(category.getColor()));
            categoryCount.setText(String
                    .valueOf(JPAUtils
                            .getCategoryService()
                            .find(category.getId())
                            .getTasks()
                            .size()));
        } else {
            setGraphic(null);
        }
    }

}
