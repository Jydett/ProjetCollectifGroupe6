package fr.polytech.recognition.dao.context;

/**
 * Un contexte initialisant les types de DAOs qui seront
 * injectés dans l'application
 */
public interface DaoContext {
    /**
     * Initialise et enregistre les DAOs à utiliser
     */
    void init();
}
