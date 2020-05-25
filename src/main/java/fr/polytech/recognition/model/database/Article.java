package fr.polytech.recognition.model.database;

import fr.polytech.recognition.dao.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ListIndexBase;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;

    @Column (unique = true)
    private String name;

    @ElementCollection
    private List <String> vendorLink;

    @Embedded
    private PictureEntity picture;

}
