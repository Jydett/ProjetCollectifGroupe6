package fr.polytech.recognition.dao;

import fr.polytech.recognition.utils.ReflectionUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * classe abstraite HibernateDao
 *
 * Assure la gestion des données présentes dans la BDD
 * pour un type d'id et un type de table donnés
 *
 * @param <Id> type de l'id de la table
 * @param <Type> type de la table (par exemple Article)
 */
public abstract class HibernateDao<Id extends Serializable, Type extends Identifiable<Id>> {

    /**
     * Le nom de classe de la table
     */
    protected final String className;

    /**
     * La session hibernate
     */
    protected final Session hibernateSession;

    /**
     * Le type de classe de la table
     */
    protected final Class<Type> clazz;

    /**
     * Le nom de la table
     */
    protected final String tableName;

    /**
     * Initialise le dao avec la session passé en paramètre
     * @param hibernateSession une session hibernate
     */
    public HibernateDao(Session hibernateSession) {
        this.hibernateSession = hibernateSession;
        this.clazz = ReflectionUtils.getParameterClass(getClass());
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        if (tableAnnotation == null || tableAnnotation.name().isEmpty()) {
            tableName = clazz.getSimpleName().toLowerCase();
        } else {
            tableName = tableAnnotation.name();
        }
        className = clazz.getSimpleName();
    }

    /**
     * Ajoute un objet dans la BdD
     * @param toSave l'objet à ajouter dans la BdD
     */
    public void saveOrCreate(Type toSave) {
        Transaction transaction = hibernateSession.beginTransaction();
        hibernateSession.save(toSave);
        transaction.commit();
    }

    /**
     * Supprime un objet de la BdD
     * @param toRemove l'objet à supprimer de la BdD
     */
    public void remove(Type toRemove) {
        Transaction transaction = hibernateSession.beginTransaction();
        hibernateSession.delete(toRemove);
        transaction.commit();
    }

    /**
     * Récupére une liste d'objet du type du Dao
     * @return une collection d'objets du type du Dao
     */
    public Collection<Type> getAll() {
        return hibernateSession.createQuery("FROM " + className, clazz).list();
    }

    /**
     * Récupére un objet en fonction de l'id passé en paramètre
     *
     * @param playerId l'id de l'objet
     * @return un objet
     */
    public Optional<Type> getById(Id playerId) {
        return Optional.ofNullable(hibernateSession.get(clazz, playerId));
    }
}
