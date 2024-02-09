package com.yusufsezer;

import com.yusufsezer.model.Category;
import com.yusufsezer.model.Task;
import com.yusufsezer.service.CategoryService;
import com.yusufsezer.service.TaskService;
import com.yusufsezer.util.DummyDataUtils;
import com.yusufsezer.util.JPAUtils;
import com.yusufsezer.util.JavaFXUtils;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App {

    public static void main(String[] args) {
        YSTodoFX.main(args);
    }

    public static class YSTodoFX extends Application {

        private static EntityManager entityManager;

        public static void main(String[] args) {
            launch();
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            FXMLLoader loader = JavaFXUtils.loadFXML("main");
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        @Override
        public void init() throws Exception {
            entityManager = JPAUtils.getEntityManager();
            entityManager.getTransaction().begin();

            CategoryService categoryService = JPAUtils.getCategoryService();
            boolean isCategoryEmpty = categoryService.count() < 1;

            if (isCategoryEmpty) {
                for (Category category : DummyDataUtils.CATEGORIES) {
                    entityManager.persist(category);
                }
            }

            TaskService taskService = JPAUtils.getTaskService();
            boolean isTaskEmpty = taskService.count() < 1;
            if (isTaskEmpty) {
                for (Task task : DummyDataUtils.TASKS) {
                    entityManager.persist(task);
                }
            }

            entityManager.getTransaction().commit();
        }

        @Override
        public void stop() throws Exception {
            entityManager.getEntityManagerFactory().close();
        }
    }

}
