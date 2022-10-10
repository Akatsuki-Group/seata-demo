package org.example.jta.mapper2;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.jta.entity.Teacher;

import java.util.List;

public interface TeacherMapper{
    @Select("select * from t_teacher")
    List<Teacher> selectAll();

    @Update("update t_teacher set name = #{name} where id = #{id}")
    int updateById(Teacher teacher);

    @Insert("insert into t_teacher(id,name) values(#{id},#{name})")
    int insert(Teacher teacher);

    @Delete("delete from t_teacher where id = #{id}")
    int deleteById(String id);
}