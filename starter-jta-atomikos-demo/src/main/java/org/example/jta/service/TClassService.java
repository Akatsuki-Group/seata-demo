package org.example.jta.service;

import org.example.jta.utils.Page;
import org.example.jta.entity.TClass;
import org.example.jta.model.QueryTClassList;

import java.util.List;
import java.util.Map;

public interface TClassService{
    public List<TClass> queryTClassList(Page<QueryTClassList> page);

    public Map<String,Object> saveOrUpdateTClass(TClass tclass);

    void delete(String id);
}
