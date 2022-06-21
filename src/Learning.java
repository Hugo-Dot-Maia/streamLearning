import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;

public class Learning {

    // ------------------------- Map to Int Test ---------------------------------

    public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {

        return Stream.of(names)
                .filter(e -> (e.length() > 5))
                .mapToInt(String::length)
                .sum();
    }

    public static void mapToIntTest(){
        var totalLetters = getTotalNumberOfLettersOfNamesLongerThanFive("william", "jones", "aaron", "seppe", "frank", "gilliam");
        System.out.println(totalLetters);
    }
    // ------------------------- Map to Int Test ---------------------------------



    // ------------------------- Flat Map Test ---------------------------------
    public static List<String> transform(List<List<String>> collection) {

        return collection
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static void flatMapTest(){
        var collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        System.out.println(transform(collection));
    }
    // ------------------------- Flat Map Test ---------------------------------


    // ------------------------- Max comparator ---------------------------------

    public static Optional<Person> getOldestPerson(List<Person> people) {

        return people
                .stream()
                .max(Comparator.comparing(Person::getAge));
    }

    public static void maxComparatorTest(){
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);
        System.out.println(getOldestPerson(collection));
    }


    // ------------------------- Max comparator ---------------------------------

    // ------------------------- Sum and Reduce ---------------------------------

    public static int calculate(List<Integer> numbers) {
        return numbers
                .stream()
                .reduce(0, Integer::sum);
    }


    public static void sumReduceTest(){
        System.out.println(calculate(asList(1, 2, 3, 4, 5)));
    }



    // ------------------------- Sum and Reduce ---------------------------------

    // ------------------------- Filter ---------------------------------

    public static Set<String> getKidNames(List<Person> people) {

        return people
                .stream()
                .filter(o -> o.getAge() < 18)
                .map(Person::getName)
                .collect(Collectors.toSet());
    }

    public static void filterTest(){
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        Person anna = new Person("Anna", 5);
        List<Person> collection = asList(sara, eva, viktor, anna);

        System.out.println(getKidNames(collection));
    }

    // ------------------------- Filter ---------------------------------


    // ------------------------- Map (GroupByAge) ---------------------------------

    public static Map<Boolean, List<String>> partitionAdults(List<Person> people) {

        return people
                .stream()
                .collect(
                        Collectors.groupingBy(
                                person -> person.getAge() > 18,
                                Collectors.mapping(Person::getName, Collectors.toList())
                        )
                );
    }


    public static void groupByAgeTest(){
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        Person anna = new Person("Anna", 5);
        List<Person> collection = asList(sara, eva, viktor, anna);

        System.out.println(partitionAdults(collection));
    }
    // ------------------------- Map (GroupByAge) ---------------------------------


    // ------------------------- Map (GroupByNationality) ---------------------------------


    public static Map<String, List<String>> groupByNationality(List<Person> people) {
        return people.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getNationality,
                                Collectors.mapping(Person::getName, Collectors.toList())
                        )
                );
    }



    public static void groupByNationalityTest(){
        Person sara = new Person("Sara", 4, "Norwegian");
        Person viktor = new Person("Viktor", 40, "Serbian");
        Person eva = new Person("Eva", 42, "Norwegian");
        Person anna = new Person("Anna", 5, "Brazilian");
        List<Person> collection = asList(sara, eva, viktor, anna);

        System.out.println(groupByNationality(collection));
    }


    // ------------------------- Map (GroupByNationality) ---------------------------------


    public static void main(String[] args) {
        mapToIntTest();
        flatMapTest();
        maxComparatorTest();
        sumReduceTest();
        filterTest();
        groupByAgeTest();
        groupByNationalityTest();
    }
}
