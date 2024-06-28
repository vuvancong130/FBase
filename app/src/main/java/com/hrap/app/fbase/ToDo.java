package com.hrap.app.fbase;

import java.util.HashMap;
import java.util.Objects;

public class ToDo {
    private String id,title,content;
    public HashMap<String, Object> convertoHasMap(){
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("content",content);
        return map;
    }

    public ToDo(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public ToDo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
