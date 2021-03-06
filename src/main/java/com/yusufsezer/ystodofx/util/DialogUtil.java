package com.yusufsezer.ystodofx.util;

import com.yusufsezer.ystodofx.contract.BaseEntity;
import com.yusufsezer.ystodofx.contract.DialogControllerBase;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class DialogUtil {

    private static final String DIALOG_FOLDER = "fxml/dialog/";

    public static <T extends BaseEntity> Dialog<T> createCustomDialog(
            String title, String headerText, String fxml,
            DialogControllerBase<T> controller) {
        Dialog<T> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog
                .getDialogPane()
                .setContent(JavaFXUtil.
                        <Node>loadCustomFXML(DIALOG_FOLDER, fxml, controller));
        dialog
                .getDialogPane()
                .getButtonTypes()
                .addAll(ButtonType.OK, ButtonType.CLOSE);

        return dialog;
    }

}
