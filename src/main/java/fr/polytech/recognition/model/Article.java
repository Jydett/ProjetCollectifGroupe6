package fr.polytech.recognition.model;

import fr.polytech.recognition.dao.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private String name;

    @ManyToOne
    private ArticleType articleType;

    private String vendorLink;

    @Embedded
    private PictureEntity picture;

}
