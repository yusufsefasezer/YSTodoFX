package com.yusufsezer.controller.custom;

import com.yusufsezer.model.Task;
import com.yusufsezer.util.JavaFXUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TaskCellController extends ListCell<Task> {

    @FXML
    private Circle statusCircle;

    @FXML
    private Label statusLabel;

    @FXML
    private Label dateLabel;

    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);
        if (!empty) {
            Node node = JavaFXUtils.<Node>loadCustomFXML("fxml/custom/", "task", this);
            setGraphic(node);
            statusLabel.setText(task.getName());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
            dateLabel.setText(sdf.format(new Date()));
            if (Task.Status.COMPLETED.equals(task.getStatus())) {
                statusCircle.setFill(Color.web("#6CB100"));
                statusLabel.setStyle("-fx-strikethrough: true;");
            }
        } else {
            setGraphic(null);
        }
    }
}
