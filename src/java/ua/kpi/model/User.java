/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.model;

import java.util.Objects;

/**
 *
 * @author lor1an
 */
public class User {

    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean isLibrarian;

    public User(int id, String name, String surname, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public User(String name, String surname, String login, String password, boolean isLibrarian) {

        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.isLibrarian = isLibrarian;
    }

    public User() {
    }
    private int numberOfBooks;

    public User(int id, String name, String surname, String login, String password, boolean isLibrarian, int numberOfBooks) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.isLibrarian = isLibrarian;
        this.numberOfBooks = numberOfBooks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsLibrarian() {
        return isLibrarian;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsLibrarian(boolean isLibrarian) {
        this.isLibrarian = isLibrarian;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.isLibrarian != other.isLibrarian) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password=" + password + ", isLibrarian=" + isLibrarian + ", numberOfBooks=" + numberOfBooks + '}';
    }
    
    
}
