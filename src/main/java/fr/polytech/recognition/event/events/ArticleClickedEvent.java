package fr.polytech.recognition.event.events;

import fr.polytech.recognition.event.Event;
import fr.polytech.recognition.model.database.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleClickedEvent implements Event {

    private final Article artSelected;

}
