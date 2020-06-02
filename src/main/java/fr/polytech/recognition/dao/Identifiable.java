package fr.polytech.recognition.dao;

import java.io.Serializable;

/**
 * Interface marquant les objets par un id de n'importe quel type
 *
 */
public interface Identifiable<Id extends Serializable> {
    Id getId();
    void setId(Id id);
}
