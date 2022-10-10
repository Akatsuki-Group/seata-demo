package org.example.jta.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.jta.entity.TClass;

import java.util.List;

public interface TClassMapper{
    @Select("select * from t_class")
    List<TClass> selectAll();

    @Update("update t_class set name = #{name} where id = #{id}")
    int updateById(TClass tclass);

    @Insert("insert into t_class(id,name) values(#{id},#{name})")
    int insert(TClass tclass);

    @Delete("delete from t_class where id = #{id}")
    int deleteById(String id);
}