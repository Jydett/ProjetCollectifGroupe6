package fr.polytech.recognition.dao;

import java.io.Serializable;

/**
 * interface Identifable
 *
 * marque les objets par un id de n'importe quel type
 *
 * @param <Id> type de l'id
 */
public interface Identifiable<Id extends Serializable> {
    /**
     * Getter de l'id
     * @return Id
     */
    Id getId();

    /**
     * Setter de l'id
     * @param id nouvelle valeur pour l'id
     */
    void setId(Id id);
}
