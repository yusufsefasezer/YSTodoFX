package com.yusufsezer.util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;

public class JavaFXUtils {

    public final static String BUNDLE_NAME = "bundle/text";
    public final static String FXML_PATH = "fxml/";

    public static FXMLLoader loadFXML(String fxml) {
        return loadFXMLFromDirectory(FXML_PATH, fxml);
    }

    public static FXMLLoader loadFXMLFromDirectory(String dir, String fxml) {
        return loadFXMLFromResource(dir + fxml + ".fxml");
    }

    public static FXMLLoader loadFXMLFromResource(String fxml) {
        URL url = JavaFXUtils.class.getClassLoader().getResource(fxml);
        ResourceBundle rb = ResourceBundle.getBundle(JavaFXUtils.BUNDLE_NAME);
        return new FXMLLoader(url, rb);
    }

    public static String getBundleMessage(String msg) {
        return ResourceBundle.getBundle(JavaFXUtils.BUNDLE_NAME).getString(msg);
    }

    public static <T> T loadCustomFXML(String dir, String fxml, Object controller) {
        try {
            FXMLLoader loader = loadFXMLFromDirectory(dir, fxml);
            loader.setController(controller);
            return loader.<T>load();
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }

}
