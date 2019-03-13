package fr.l3miage.amphinote.model;

import android.util.Base64;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    private String username;
    private String lastname;
    private Integer age;
    private String email;
    private String password;
    private Integer id;
    private String photo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAuth(){
        String base = email + ":" +password;
        return "Basic "+ Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
    }
    public String getDenomination(){
        return this.lastname+" "+this.getUsername();
    }
}
