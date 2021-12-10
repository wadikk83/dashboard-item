package com.example.jira.plugin.mapper;

import java.util.List;

public interface Mapper<T> {

    String toJSON(List<Object> list);

}
