package com.example.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceMapper {
    void submitCleanPhoto(String photoURL,String userid);
    String getURL(int id);
}
