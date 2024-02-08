package com.yusufsezer.util;

import com.yusufsezer.contract.BaseEntity;
import com.yusufsezer.contract.DialogControllerBase;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class DialogUtils {

    private static final String DIALOG_FOLDER = "fxml/dialog/";

    public static <T extends BaseEntity> Dialog<T> createCustomDialog(String title, String headerText, String fxml, DialogControllerBase<T> controller) {
        Dialog<T> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        Node node = JavaFXUtils.<Node>loadCustomFXML(DIALOG_FOLDER, fxml, controller);
        dialog.getDialogPane().setContent(node);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);
        return dialog;
    }

}
