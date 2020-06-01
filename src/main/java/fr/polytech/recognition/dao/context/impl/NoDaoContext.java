package fr.polytech.recognition.dao.context.impl;

import fr.polytech.recognition.dao.context.DaoContext;

/**
 * Un contexte de DAOs n'enregistrant aucun DAOs
 * Utilisé pour développer l'application sans persistance de donnée
 * pour ne pas à avoir en permanance un serveur SQL (ou autre) qui tourne.
 */
public class NoDaoContext implements DaoContext {

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {

    }
}
