package com.yusufsezer;

import com.yusufsezer.ystodofx.model.Category;
import com.yusufsezer.ystodofx.model.Task;
import com.yusufsezer.ystodofx.util.DummyDataUtil;
import com.yusufsezer.ystodofx.util.JPAUtil;
import com.yusufsezer.ystodofx.util.JavaFXUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javax.persistence.EntityManager;

public class YSTodoFXApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(JavaFXUtil.loadFXML("main").load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
//        EntityManager em = JPAUtil.getEntityManager();
//        em.getTransaction().begin();
//        for (Category category : DummyDataUtil.CATEGORIES) {
//            em.persist(category);
//        }
//        for (Task task : DummyDataUtil.TASKS) {
//            em.persist(task);
//        }
//        em.getTransaction().commit();
//        em.getEntityManagerFactory().close();
//        System.exit(0);
    }

}
