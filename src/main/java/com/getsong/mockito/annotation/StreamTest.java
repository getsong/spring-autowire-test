package com.getsong.mockito.annotation;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@TestInfo
public class StreamTest {
  public static void main(String[] args) {

    Student student1 =
        new Student(
            "Jayesh",
            26,
            new Address("1234"),
            Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));

    Student student2 =
        new Student(
            "Khyati",
            20,
            new Address("1235"),
            Arrays.asList(
                new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

    Student student3 =
        new Student(
            "Jason",
            21,
            new Address("1236"),
            Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

    List<Student> students = Arrays.asList(student1, student2, student3);

    // Sort by age
    Collections.sort(students);
    log.info(students.toString());

    students.sort((a, b) -> b.getAge() - a.getAge());
    log.info(students.toString());

    students.sort(
        new Comparator<Student>() {
          @Override
          public int compare(Student o1, Student o2) {
            return o1.getAge() - o2.getAge();
          }
        });
    log.info("sort with anonymous class {}", students.toString());

    Stream.of(student1, student2, student3).map(Student::getName).forEach(log::info);

    IntStream.range(0, 4).mapToObj(i -> "int" + i).forEach(log::info);

    log.info("test parallel stream and execution order");
    students
        .parallelStream()
        .filter(
            s -> {
              log.info(s.getName() + " in filter");
              return true;
            })
        .map(
            s -> {
              log.info(s.getName() + " in map");
              return s;
            })
        .sorted(
            (a, b) -> {
              log.info("comparing {}, {}", a.getName(), b.getName());
              return a.getAge() - b.getAge();
            })
        .map(Student::toString)
        .forEach(log::info);

    log.info("common pool size = " + ForkJoinPool.commonPool().getPoolSize());

    log.info("try flatmap and collect to set, grouping by");
    Supplier<Stream<String>> streamSupplier2 =
        () ->
            students.stream()
                .peek(s -> log.info("{} in supplier", s.getName()))
                .flatMap(s -> Stream.of("a1", "b2", "c3"))
                .peek(log::info);
    log.info("after supplier");
    Stream<String> filteredStream = streamSupplier2.get().filter(s -> !s.startsWith("b"));
    log.info("after filter");
    Set<String> randoms = filteredStream.collect(Collectors.toSet());
    log.info("collected set = {}", randoms);
    Map<Character, List<String>> char2stringList =
        streamSupplier2.get().collect(Collectors.groupingBy(s -> s.charAt(0)));
    log.info("collected map = {}", char2stringList);

    Optional<Student> result =
        students.stream()
            .reduce(
                (a, b) -> {
                  a.setAge(a.getAge() + b.getAge());
                  return a;
                });
    log.info("reduce result = {}", result.map(Student::toString).orElse("No Result"));

    // Find Jayesh
    log.info(
        students.stream()
            .filter(s -> s.getName().equals("Jayesh"))
            .findFirst()
            .map(Student::toString)
            .orElse("No student with name Jayesh"));

    // Find Nemo
    log.info(
        students.stream()
            .filter(s -> s.getName().equals("Nemo"))
            .findFirst()
            .map(Student::toString)
            .orElse("No student with name Nemo"));

    // Convert students to name list
    Supplier<Stream<String>> nameSupplier = () -> students.stream().map(Student::getName);

    // Log name list
    log.info(nameSupplier.get().collect(Collectors.toList()).toString());

    // Sort and change case
    log.info(
        nameSupplier
            .get()
            .sorted()
            .map(String::toUpperCase)
            .collect(Collectors.toList())
            .toString());

    List<Annotation> annotations = Arrays.asList(StreamTest.class.getAnnotations());
    log.info(
        annotations.stream()
            .filter(a -> a instanceof TestInfo)
            .findFirst()
            .map(a -> ((TestInfo) a).seriousLevel())
            .orElse("No Test Info"));
  }
}

@Data
class TempStudent {
  public String name;
  public int age;
  public Address address;
  public List<MobileNumber> mobileNumbers;

  public TempStudent(String name, int age, Address address, List<MobileNumber> mobileNumbers) {
    this.name = name;
    this.age = age;
    this.address = address;
    this.mobileNumbers = mobileNumbers;
  }
}

@Data
class Student implements Comparable<Student> {
  private String name;
  private int age;
  private Address address;
  private List<MobileNumber> mobileNumbers;

  public Student(String name, int age, Address address, List<MobileNumber> mobileNumbers) {
    this.name = name;
    this.age = age;
    this.address = address;
    this.mobileNumbers = mobileNumbers;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Address getAddress() {
    return address;
  }

  public List<MobileNumber> getMobileNumbers() {
    return mobileNumbers;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
    this.mobileNumbers = mobileNumbers;
  }

  @Override
  public String toString() {
    return "Student{"
        + "name='"
        + name
        + '\''
        + ", age="
        + age
        + ", address="
        + address
        + ", mobileNumbers="
        + mobileNumbers
        + '}';
  }

  @Override
  public int compareTo(Student o) {
    return this.age - o.getAge();
  }
}

@Data
class Address {
  private String zipcode;

  public Address(String zipcode) {
    this.zipcode = zipcode;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
}

@Data
class MobileNumber {
  private String number;

  public MobileNumber(String number) {
    this.number = number;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }
}
