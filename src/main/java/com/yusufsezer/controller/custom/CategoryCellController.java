package com.yusufsezer.controller.custom;

import com.yusufsezer.controller.dialog.CategoryDialogController;
import com.yusufsezer.model.Category;
import com.yusufsezer.model.Task;
import com.yusufsezer.service.CategoryService;
import com.yusufsezer.util.JPAUtils;
import com.yusufsezer.util.JavaFXUtils;
import java.util.List;
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
        Optional<Category> result = CategoryDialogController.createEditCategoryDialog(getItem());
        if (result.isPresent()) {
            CategoryService categoryService = JPAUtils.getCategoryService();
            categoryService.edit(result.get());
            getListView().getItems().setAll(categoryService.findAll());
            getListView().getSelectionModel().selectFirst();
        }
        event.consume();
    }

    @Override
    protected void updateItem(Category category, boolean empty) {
        super.updateItem(category, empty);
        if (!empty) {
            Node node = JavaFXUtils.<Node>loadCustomFXML("fxml/custom/", "category", this);
            setGraphic(node);

            categoryName.setText(category.getName());
            categoryColor.setFill(Color.web(category.getColor()));

            CategoryService categoryService = JPAUtils.getCategoryService();
            Category foundCategory = categoryService.find(category.getId());
            List<Task> categoryTask = foundCategory.getTasks();
            String size = String.valueOf(categoryTask.size());
            categoryCount.setText(size);
        } else {
            setGraphic(null);
        }
    }

}
