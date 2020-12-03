/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.user;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class UserDTO implements Serializable{
    private String username;
    private String password;
    private String role;
    private String name;
    
    public UserDTO() {
    }

    public UserDTO(String name, String role, String username) {
        this.name = name;
        this.role = role;
        this.username = username;
    }
    
    public UserDTO(String username, String password, String role, String name) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
