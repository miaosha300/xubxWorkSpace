package xubx.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.java8
 * 类名称:     Filter
 * 类描述:     java8 filter 过滤器
 * 创建人:     xubx
 * 创建时间:   2020/4/1 16:12
 */
public class Filter {
    public static void main(String[] args) {
        //测试过滤后再循环
        Employee e1 = new Employee(1, 23, "M", "Rick", "Beethovan");
        Employee e2 = new Employee(2, 13, "F", "Martina", "Hengis");
        Employee e3 = new Employee(3, 43, "M", "Ricky", "Martin");
        Employee e4 = new Employee(4, 26, "M", "Jon", "Lowman");
        Employee e5 = new Employee(5, 19, "F", "Cristine", "Maria");
        Employee e6 = new Employee(6, 15, "M", "David", "Feezor");
        Employee e7 = new Employee(7, 68, "F", "Melissa", "Roy");
        Employee e8 = new Employee(8, 79, "M", "Alex", "Gussin");
        Employee e9 = new Employee(9, 15, "F", "Neetu", "Singh");
        Employee e10 = new Employee(10, 45, "M", "Naveen", "Jain");
        List<Employee> employees = new ArrayList<>();
        employees.addAll(Arrays.asList(new Employee[] {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10}));
        employees.stream().filter(e -> e.getAge() > 20).forEach(System.out::println);

        List<Persona> personalList = Arrays.asList(
                new Persona("zhangSan", 18),
                new Persona("liSi", 28),
                new Persona("wangWu", 38)
        );

        Persona persona = personalList.stream()
                .filter((p) -> "zhangSan".equals(p.getName()) && 18 == p.getAge()).findAny().orElse(null);
        System.out.println(persona);

        int name = personalList.stream().filter(p -> "liSi".equals(p.getName())).map(Persona::getAge).findAny().orElse(0);
        System.out.println(name);

        List<Persona> list = personalList.stream().filter(e -> e.getAge() > 18).collect(Collectors.toList());

    }
}

@Data
@AllArgsConstructor
class Employee {
    private Integer id;
    private Integer age;
    private String gender;
    private String firstName;
    private String lastName;
}

@Data
@AllArgsConstructor
class Persona {
    private String name;
    private int age;
}

