package fr.polytech.recognition.dao;

import fr.polytech.recognition.utils.ReflectionUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * Classe abstraite assurant la gestion des données présentes dans la BDD
 *
 */
public abstract class HibernateDao<Id extends Serializable, Type extends Identifiable<Id>> {

    protected final String className;
    protected final Session hibernateSession;
    protected final Class<Type> clazz;
    protected final String tableName;

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

    public void saveOrCreate(Type toSave) {
        Transaction transaction = hibernateSession.beginTransaction();
        hibernateSession.save(toSave);
        transaction.commit();
    }

    public void remove(Type toRemove) {
        Transaction transaction = hibernateSession.beginTransaction();
        hibernateSession.delete(toRemove);
        transaction.commit();
    }

    public Collection<Type> getAll() {
        return hibernateSession.createQuery("FROM " + className, clazz).list();
    }

    public Optional<Type> getById(Id playerId) {
        return Optional.ofNullable(hibernateSession.get(clazz, playerId));
    }
}
