import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Override hashCode and equals for caching
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return age == other.age && name.equals(other.name);
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

// Object cache to reduce object creation
class PersonFactory {
    private static final Map<Person, Person> cache = new HashMap<>();

    public static Person getPerson(String name, int age) {
        Person temp = new Person(name, age);
        if (!cache.containsKey(temp)) {
            cache.put(temp, temp); // store and reuse
        }
        return cache.get(temp);
    }
}

public class Main {
    public static void main(String[] args) {
        Person p1 = PersonFactory.getPerson("Alice", 30);
        Person p2 = PersonFactory.getPerson("Alice", 30);

        System.out.println(p1 == p2);  // true — same object
        System.out.println(p1);        // Alice (30)
    }
}
