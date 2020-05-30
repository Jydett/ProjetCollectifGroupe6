package fr.polytech.recognition.context;

import fr.polytech.recognition.view.View;

/**
 * Une fabrique pour instancier des vues
 */
public interface ViewFactory {

    /**
     * Instancie une vue
     * @param viewClass la classe de la vue à instancier
     * @param <V> le type de la vue à instancier
     * @return l'instance de la vue
     * @throws IllegalArgumentException si l'instanciation de cette vue n'est pas supporté par cette fabrique
     */
    <V> V getView(Class<V> viewClass);

    default View viewNotFound(Class<?> viewClass) {
        throw new IllegalArgumentException("Unknown view " + viewClass.getSimpleName());
    }
}
