import java.util.ArrayList;
import java.util.List;

public class ListToDo {
    List<String> list = new ArrayList<>();

    void addCase(String name) {
        list.add(name);
        System.out.println("Добавлено!");
    }

    List<String> getAllCase() {
        return list;
    }

    void deleteCaseIndex(int index) {
        list.remove(index - 1);
        System.out.println("Удалено!");
    }

    void deleteCaseName(String name) {
        list.remove(name);
        System.out.println("Удалено!");
    }

    void deleteKeyWord(String keyWord) {
        for (int i = list.size() - 1; i >= 0; i--){
            if (list.get(i).contains(keyWord)){
                list.remove(i);
            }
        }
        System.out.println("Удалено!");
    }
}
