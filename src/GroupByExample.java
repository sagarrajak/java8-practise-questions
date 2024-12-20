import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private Integer empId;
    private String empName;
    private String department;
    private Integer salary;

    // Constructors, Getters, and Setters
    public Employee(Integer empId, String empName, String department, Integer salary) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.salary = salary;
    }

    public Integer getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}


public class GroupByExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40);
        // higest and lowest salery of each department
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "IT", 70000),
                new Employee(2, "Bob", "HR", 50000),
                new Employee(3, "Charlie", "IT", 60000),
                new Employee(4, "David", "HR", 45000),
                new Employee(5, "Eve", "Finance", 80000),
                new Employee(6, "Frank", "Finance", 55000)
        );

//        Map<String, List<Integer>> groupByMinMax = employees
//                .stream()
//                .collect(
//                        Collectors.groupingBy(Employee::getDepartment,
//                                Collectors.collectingAndThen(Collectors.toList(), list -> {
//                                    Integer max = list.stream().map(Employee::getSalary).max(Comparator.naturalOrder()).orElse(0);
//                                    Integer min = list.stream().map(Employee::getSalary).min(Comparator.naturalOrder()).orElse(0);
//                                    return Arrays.asList(max, min);
//                                })));
//
//        Map<String, IntSummaryStatistics> summaryStatisticsMap = employees
//                .stream()
//                .collect(
//                        Collectors.groupingBy(Employee::getDepartment, Collectors.summarizingInt(Employee::getSalary)));
//        // Find the Length of the Longest String in a List
//        List<String> strings = Arrays.asList("apple", "banana", "cherry");
//        Integer collect = strings.stream()
//                .collect(
//                        Collectors.collectingAndThen(
//                                Collectors.maxBy(Comparator.comparingInt(String::length)), s -> s.map(String::length).orElse(0)));
//
//        // Convert a List to an Unmodifiable Set
//        Set<String> collect1 = strings.stream().collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
//        // 3. Find the Smallest Number and Return It as a String
//        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5);
//        String collect2 = numbers.stream().collect(Collectors.collectingAndThen(Collectors.minBy(Integer::compare), opt -> opt.map(Object::toString).orElse("")));

            Map<String, Set<Employee>> setOfMap = employees
                .stream()
                .collect(
                    Collectors.groupingBy(Employee::getDepartment, Collectors.toSet()));

        String collect10 = employees.stream().collect(Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Employee::getSalary)), opt -> opt.map(Object::toString).orElse("")));
        System.out.println(collect10);

    }
}
