import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentDepartment {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        List<Department> departmentList = new ArrayList<>();

        // Adding sample data
        studentList.add(new Student(1, "Alice", Arrays.asList(1, 2)));
        studentList.add(new Student(2, "Bob", Arrays.asList(2, 3)));
        studentList.add(new Student(3, "Charlie", Arrays.asList(1, 3, 4)));
        studentList.add(new Student(4, "Diana", Arrays.asList(2, 4)));

        departmentList.add(new Department(1, "Science"));
        departmentList.add(new Department(2, "Math"));
        departmentList.add(new Department(3, "Arts"));
        departmentList.add(new Department(4, "Sports"));

        Map<Integer, Long> collect = studentList.stream()
                .flatMap(student -> student.deptIds.stream())
                .collect(Collectors.groupingBy(depId -> depId, Collectors.counting()));

        List<Department> list = departmentList
                .stream()
                .filter(dep -> collect.getOrDefault(dep.departmentId, 0L) >= 2).toList();
        System.out.println(list.toString());
    }

    static class Student {
        Integer id;
        String name;
        List<Integer> deptIds;

        public Student(Integer id, String name, List<Integer> deptIds) {
            this.id = id;
            this.name = name;
            this.deptIds = deptIds;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", deptIds=" + deptIds +
                    '}';
        }
    }

    static class Department {
        Integer departmentId;
        String deptName;

        public Department(Integer departmentId, String deptName) {
            this.departmentId = departmentId;
            this.deptName = deptName;
        }

        @Override
        public String toString() {
            return "Department{" +
                    "departmentId=" + departmentId +
                    ", deptName='" + deptName + '\'' +
                    '}';
        }
    }
}
