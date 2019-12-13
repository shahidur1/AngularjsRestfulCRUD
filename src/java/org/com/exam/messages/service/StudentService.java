package org.com.exam.messages.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.com.exam.messages.database.DatabaseClass;
import org.com.exam.messages.model.Student;

public class StudentService {

    private Map<Integer, Student> studentMap = new HashMap();

    public StudentService() {
        for (Student s : DatabaseClass.doQuery()) {
            studentMap.put(s.getId(), s);
        }
    }

    public Student findStudentById(int id) {
        return studentMap.get(id);
    }

    public List<Student> getAllStudent() {
        return new ArrayList<Student>(studentMap.values());
    }

    public Student addStudent(Student stu) {
        DatabaseClass.doSave(stu);
        return stu;
    }

    public Student updateStudent(Student stu) {
        if (stu.getId() <= 0) {
            return null;
        }
        DatabaseClass.doUpdate(stu);
        return stu;
    }

    public void removeStudent(int id) {
        Student s = new Student();
        s.setId(id);
        DatabaseClass.doDelete(s);
    }

}
