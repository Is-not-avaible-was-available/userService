package Prototype;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StudentRegistry {

    private Map<Long, Student> studentsMap = new HashMap<>();

    public Student getStudent(Long id){
        return studentsMap.get(id);
    }

    public void addStudent(Long id, Student student){
        studentsMap.put(id, student);
    }
}
