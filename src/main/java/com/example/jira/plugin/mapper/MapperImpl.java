package com.example.jira.plugin.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Named;
import java.util.List;

@Named
public class MapperImpl<T> implements Mapper<T> {
    @Override
    public String toJSON(List<Object> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
