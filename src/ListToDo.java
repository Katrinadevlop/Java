import java.util.ArrayList;
import java.util.List;

public class ListToDo {
    List<String> toDoList = new ArrayList<>();

    void addTask(String name) {
        toDoList.add(name);
        System.out.println("Добавлено!");
    }

    List<String> getAllTask() {
        return toDoList;
    }

    void deleteTaskIndex(int index) {
        if (index > 0 && index <= toDoList.size()) {
            toDoList.remove(index - 1);
            System.out.println("Удалено!");
        } else {
            System.out.println("Нет дела с таким номером.");
        }
    }

    void deleteTaskName(String name) {
        if (toDoList.contains(name)) {
            toDoList.remove(name);
            System.out.println("Удалено!");
        } else {
            System.out.println("Нет дела с таким названием.");
        }
    }

    void deleteKeyWord(String keyWord) {
        boolean found = false;

        for (int i = toDoList.size() - 1; i >= 0; i--) {
            if (toDoList.get(i).contains(keyWord)) {
                toDoList.remove(i);
                found = true;
            }
        }

        if (found) {
            System.out.println("Удалено!");
        } else {
            System.out.println("Нет совпадений.");
        }
    }
}
