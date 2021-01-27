package xubx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.entity
 * 类名称:     Student
 * 类描述:     类功能描述
 * 创建人:     xubx
 * 创建时间:   2021/1/20 14:37
 */
@Data
@AllArgsConstructor
public class Student {
    String idNum;
    String name;
    int age;
    String classNum;
}
