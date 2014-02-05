/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.exceptions;

/**
 *
 * @author lor1an
 */
public class DAOException extends Exception {

    private String message;

    public DAOException() {
        message = "DAO connection failure";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

