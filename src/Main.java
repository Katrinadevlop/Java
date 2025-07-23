import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;


public class Main {

    public static void main(String[] args) {
        TreeSet<Person> candidates = new TreeSet<>(new SpacePersonComparator());
        candidates.add(new Person("Sonya Popova", 35, 15));
        candidates.add(new Person("Dazdraperma Sponzhova", 33, 15));
        candidates.add(new Person("Salavat Netologshvili", 23, 13));
        candidates.add(new Person("Sasha Sun", 31, 15));
        candidates.add(new Person("Svetlana Morkov", 38, 15));
        candidates.add(new Person("Sasha Sosnova", 38, 11));

        Iterator<Person> it = candidates.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
    }
}

class Person {
    private String name;
    private int age;
    private int experience;

    public Person(String name, int age, int experience) {
        this.name = name;
        this.age = age;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return name;
    }
}


class SpacePersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getExperience() == o2.getExperience()) {

            String name1 = o1.getName();
            int count = 0;
            for (int i = 0; i < name1.length(); i++) {
                char c = name1.charAt(i);
                if (c == 's' || c == 'S') {
                    count++;
                }
            }

            String name2 = o2.getName();
            int countTwo = 0;
            for (int i = 0; i < name2.length(); i++) {
                char c = name2.charAt(i);
                if (c == 's' || c == 'S') {
                    countTwo++;
                }
            }

            if (count == countTwo) {
                if (o1.getName().length() > o2.getName().length()) {
                    return 1;
                } else if (o1.getName().length() < o2.getName().length()) {
                    return -1;
                } else {
                    return o1.getName().compareTo(o2.getName());
                }
            } else if (count > countTwo) {
                return -1;
            } else {
                return 1;
            }
        } else if (o1.getExperience() > o2.getExperience()) {
            return -1;
        } else {
            return 1;
        }
    }
}