package fr.polytech.recognition.dao;

import java.io.Serializable;

public interface Identifiable<Id extends Serializable> {
    Id getId();
    void setId(Id id);
}
