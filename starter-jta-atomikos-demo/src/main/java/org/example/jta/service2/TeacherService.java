package org.example.jta.service2;

import org.example.jta.entity.Teacher;
import org.example.jta.model.QueryTeacherList;
import org.example.jta.utils.Page;

import java.util.List;
import java.util.Map;

public interface TeacherService{
    public List<Teacher> queryTeacherList(Page<QueryTeacherList> page);

    public Map<String,Object> saveOrUpdateTeacher(Teacher teacher);

    void delete(String id);
}
