/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author FPTSHOP
 */
public class Lecture implements IBaseModel {

    String lecture_id, sesion_name;
    Subject subject;
    Room room;
    Slot slot;
    Date date;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(String lecture_id) {
        this.lecture_id = lecture_id;
    }

    public String getSesion_name() {
        return sesion_name;
    }

    public void setSesion_name(String sesion_name) {
        this.sesion_name = sesion_name;
    }

}
