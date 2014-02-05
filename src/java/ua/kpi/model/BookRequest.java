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
public class BookRequest {

    public static final String STATUS_CLOSED = "closed";
    public static final String STATUS_READING_ROOM = "reading-room";
    public static final String STATUS_SUBSCRIPTION = "subscription";
    public static final String STATUS_OPEN = "open";
    public static final String POS_RESPONSE = "positive";
    public static final String NEG_RESPONSE = "negative";
    public static final String UN_RESPONSE = "unchecked";
    private int id;
    private String bookTitle;
    private String userLog;
    private String status;
    private String response;

    public BookRequest() {
    }

    public BookRequest(int id, String bookTitle, String userLog, String status, String response) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.userLog = userLog;
        this.status = status;
        this.response = response;
    }

    public BookRequest(String bookTitle, String userLog, String status, String response) {
        this.bookTitle = bookTitle;
        this.userLog = userLog;
        this.status = status;
        this.response = response;
    }

    public static String getSTATUS_CLOSED() {
        return STATUS_CLOSED;
    }

    public static String getSTATUS_OPEN() {
        return STATUS_OPEN;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getPOS_RESPONSE() {
        return POS_RESPONSE;
    }

    public static String getNEG_RESPONSE() {
        return NEG_RESPONSE;
    }

    public static String getUN_RESPONSE() {
        return UN_RESPONSE;
    }

    public String getResponse() {
        return response;
    }

    public int getId() {
        return id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getUserLog() {
        return userLog;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setUserLog(String userLog) {
        this.userLog = userLog;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
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
        final BookRequest other = (BookRequest) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.bookTitle, other.bookTitle)) {
            return false;
        }
        if (!Objects.equals(this.userLog, other.userLog)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.response, other.response)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BookRequest{" + "id=" + id + ", bookTitle=" + bookTitle + ", userLog=" + userLog + ", status=" + status + ", response=" + response + '}';
    }

   
}
