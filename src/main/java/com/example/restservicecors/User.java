package com.example.restservicecors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private String name;
    private String email;
    private String editor;
    
    public User() {
	this.id     = -1;
	this.name   = "";
	this.email  = "";
	this.editor = "";
    }

    public User(Integer id, String name, String email, String editor) {
	this.id     = id;
	this.name   = name;
        this.email  = email;
	this.editor = editor;
    }

    public Integer getId() {
	return id;
    }
    public void setId(Integer id) {
	this.id = id;
    }
    
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }

    public String getEmail() {
	return email;
    }
    public void setEmail(String email) {
	this.email = email;
    }
    
    public String getEditor() {
	return editor;
    }
    public void setEditor(String editor) {
	this.editor = editor;
    }

}
