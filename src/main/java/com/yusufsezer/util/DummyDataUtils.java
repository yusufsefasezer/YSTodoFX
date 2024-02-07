package com.yusufsezer.util;

import com.yusufsezer.model.Category;
import com.yusufsezer.model.Task;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DummyDataUtils {

    public final static List<Category> CATEGORIES = new ArrayList<>();
    public final static List<Task> TASKS = new ArrayList<>();

    static {
        Category categoryNoCategory = new Category();
        categoryNoCategory.setColor("#CCCCCC");
        categoryNoCategory.setName("No Category");
        categoryNoCategory.setDescription("No Category");

        // No Category
        for (int i = 0; i < 7; i++) {
            Task task = new Task();
            task.setName(categoryNoCategory.getName() + " item #" + i);
            task.setStatus(getRandStatus());
            task.setDescription(getRandText());
            task.setCategory(categoryNoCategory);
            TASKS.add(task);
        }

        Category categoryHome = new Category();
        categoryHome.setColor("#AF3D3D");
        categoryHome.setName("Home");
        categoryHome.setDescription("Home");

        // Home
        for (int i = 0; i < 3; i++) {
            Task task = new Task();
            task.setName(categoryHome.getName() + " item #" + i);
            task.setStatus(getRandStatus());
            task.setDescription(getRandText());
            task.setCategory(categoryHome);
            TASKS.add(task);
        }

        Category categoryWork = new Category();
        categoryWork.setColor("#6144A7");
        categoryWork.setName("Work");
        categoryWork.setDescription("Work");

        // Work
        for (int i = 0; i < 3; i++) {
            Task task = new Task();
            task.setName(categoryWork.getName() + " item #" + i);
            task.setStatus(getRandStatus());
            task.setDescription(getRandText());
            task.setCategory(categoryWork);
            TASKS.add(task);
        }

        Category categoryWork1 = new Category();
        categoryWork1.setColor("#587D38");
        categoryWork1.setName("Work #1");
        categoryWork1.setDescription("Work #1");

        // Work #1
        for (int i = 0; i < 2; i++) {
            Task task = new Task();
            task.setName(categoryWork1.getName() + " item #" + i);
            task.setStatus(getRandStatus());
            task.setDescription(getRandText());
            task.setCategory(categoryWork1);
            TASKS.add(task);
        }

        Category categoryWork2 = new Category();
        categoryWork2.setColor("#8B8697");
        categoryWork2.setName("Work #2");
        categoryWork2.setDescription("Work #2");

        // Work #2
        for (int i = 0; i < 1; i++) {
            Task task = new Task();
            task.setName(categoryWork2.getName() + " item #" + i);
            task.setStatus(getRandStatus());
            task.setDescription(getRandText());
            task.setCategory(categoryWork2);
            TASKS.add(task);
        }

        Category categoryWork3 = new Category();
        categoryWork3.setColor("#CB9516");
        categoryWork3.setName("Work #3");
        categoryWork3.setDescription("Work #3");

        // Work #3
        for (int i = 0; i < 6; i++) {
            Task task = new Task();
            task.setName(categoryWork3.getName() + " item #" + i);
            task.setStatus(getRandStatus());
            task.setDescription(getRandText());
            task.setCategory(categoryWork3);
            TASKS.add(task);
        }

        Category categorySchool = new Category();
        categorySchool.setColor("#EBE421");
        categorySchool.setName("School");
        categorySchool.setDescription("School");

        Category categoryPrivate = new Category();
        categoryPrivate.setColor("#0E44F6");
        categoryPrivate.setName("Private");
        categoryPrivate.setDescription("Private");

        // Private
        for (int i = 0; i < 9; i++) {
            Task task = new Task();
            task.setName(categoryPrivate.getName() + " item #" + i);
            task.setStatus(getRandStatus());
            task.setDescription(getRandText());
            task.setCategory(categoryPrivate);
            TASKS.add(task);
        }

        Category categoryTraining = new Category();
        categoryTraining.setColor("#36E9A6");
        categoryTraining.setName("Training");
        categoryTraining.setDescription("Training");

        // Private
        for (int i = 0; i < 3; i++) {
            Task task = new Task();
            task.setName(categoryTraining.getName() + " item #" + i);
            task.setStatus(getRandStatus());
            task.setDescription(getRandText());
            task.setCategory(categoryTraining);
            TASKS.add(task);
        }

        Collections.addAll(CATEGORIES,
                categoryNoCategory, categoryHome, categoryWork, categoryWork1,
                categoryWork2, categoryWork3, categorySchool,
                categoryPrivate, categoryTraining);
    }

    public static String getRandText() {
        String longString = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.\n"
                + "\n"
                + "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.\n"
                + "\n"
                + "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit.";
        String[] texts = longString.split("\\.");
        Random random = new Random();
        return texts[Math.abs(random.nextInt()) % texts.length] + texts[Math.abs(random.nextInt()) % texts.length] + texts[Math.abs(random.nextInt()) % texts.length];
    }

    public static Task.Status getRandStatus() {
        return Math.random() < 0.5 ? Task.Status.COMPLETED : Task.Status.UNCOMPLETED;
    }

}
