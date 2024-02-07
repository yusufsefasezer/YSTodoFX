package com.yusufsezer;

import com.yusufsezer.util.JavaFXUtils;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(JavaFXUtils.loadFXML("main").load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
//        EntityManager em = JPAUtils.getEntityManager();
//        em.getTransaction().begin();
//        for (Category category : DummyDataUtils.CATEGORIES) {
//            em.persist(category);
//        }
//        for (Task task : DummyDataUtils.TASKS) {
//            em.persist(task);
//        }
//        em.getTransaction().commit();
//        em.getEntityManagerFactory().close();
//        System.exit(0);
    }

}
