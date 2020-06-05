package fr.polytech.recognition.view.swing;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import fr.polytech.recognition.controller.Controller;
import fr.polytech.recognition.controller.infra.ControllerRegistry;
import fr.polytech.recognition.view.ViewContext;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/**
 * Décider afficher lequel tabbed panel
 */
public class SwingViewTabbedContext implements ViewContext {
    @Getter
    public TabbedFrame frame;
    private List<Controller<?, ?>> controllers;

    public SwingViewTabbedContext() {
        controllers = new ArrayList<>();
        FlatCyanLightIJTheme.install();
    }

    /**
     * Enregistrer les controlleurs de la vue dans la liste : controllers
     * @param registry controller registry list
     */
    @Override
    public void init(ControllerRegistry registry) {
        frame = new TabbedFrame(this);
        Iterator<? extends Supplier<Controller<?, ?>>> it = registry.iterator();
        while (it.hasNext()) {
            Controller<?, ?> controller = it.next().get();
            if (! controller.isInitiliazed()) {
                controller.init();
            }
            controllers.add(controller);
        }
        controllers.sort(Comparator.comparingInt(c -> ((SwingView) c.getCurrentView()).priority()));
        for (Controller<?, ?> controller : controllers) {
            SwingView view = (SwingView) controller.getCurrentView();
            frame.getTabbedPanel().addTab(view.getTitle(), view.getIcon(), view);
        }
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Choisir la vue pour afficher dans le tabbed panel
     * @param ctrl Contrôleur de la vue
     */
    @Override
    public void switchView(Controller ctrl) {
        //apeller depuis le controller -> on doit
        //dire a la vue de changer
        frame.switchView(controllers.indexOf(ctrl));
    }

    /**
     * Choisir la vue pour afficher dans le tabbed panel
     * @param index le numéro d'ordre du tabbed panel
     * @return succès : true ; échec : false
     */
    public boolean switchView(int index) {
        //apeller depuis la vue, on doit changer de controller
        Controller<?, ?> controller = controllers.get(index);
        if (controller != null) {
            Controller<?, ?> currentController = controllers.get(frame.getTabbedPanel().getSelectedIndex());
            if (currentController == null) {
                throw new IllegalStateException("No controller for the current index !");
            } else {
                if (currentController.canExit()) {
                    currentController.exit();
                    currentController.getRooter().nextController(controller);
                    return true;
                }
                return false;
            }
        } else {
            throw new IllegalArgumentException("No Controller at index " + index);
        }
    }
}
