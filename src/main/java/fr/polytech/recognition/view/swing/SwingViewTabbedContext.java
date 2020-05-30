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

public class SwingViewTabbedContext implements ViewContext {
    @Getter
    public TabbedFrame frame;
    private List<Controller<?, ?>> controllers;

    public SwingViewTabbedContext() {
        controllers = new ArrayList<>();
        FlatCyanLightIJTheme.install();
    }

    @Override
    public void init(ControllerRegistry registry) {
        frame = new TabbedFrame(this);
        Iterator<? extends Supplier<Controller<?, ?>>> it = registry.iterator();
        while (it.hasNext()) {
            controllers.add(it.next().get());
        }
        controllers.sort(Comparator.comparingInt(c -> ((SwingView) c.getCurrentView()).priority()));
        for (Controller<?, ?> controller : controllers) {
            SwingView view = (SwingView) controller.getCurrentView();
            frame.getTabbedPanel().addTab(view.getTitle(), view.getIcon(), view);
        }
        frame.setVisible(true);
        frame.pack();
    }

    //TODO pas faire ca ici ?
    @Override
    public void switchView(Controller ctrl) {
        //apeller depuis le controller -> on doit
        //dire a la vue de changer
        frame.switchView(controllers.indexOf(ctrl));
    }

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
