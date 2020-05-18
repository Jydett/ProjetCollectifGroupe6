package fr.polytech.recognition.launcher;

import fr.polytech.recognition.dao.article.impl.HibernateArticleDao;
import fr.polytech.recognition.model.Article;
import fr.polytech.recognition.model.PictureEntity;
import fr.polytech.recognition.utils.ImageFormat;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Launcher {
    public static void main(String[] args) throws IOException {
        //Initialisation des sessions
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        HibernateArticleDao hibernateArticleDao = new HibernateArticleDao(session);

        //Initialisation des Buffered Image/ImageFormat
        BufferedImage jean1 = ImageIO.read(new File("./src/test/resources/images/jeans1.jpg"));
        BufferedImage chemise1 = ImageIO.read(new File("./src/test/resources/images/chemise1.jpg"));

        ImageFormat imageFormat = ImageFormat.valueOf("PNG");

        //Initialisation des images
        PictureEntity pictureEntity1 = new PictureEntity();
        pictureEntity1.setBufferedImage(jean1,imageFormat);

        PictureEntity pictureEntity2 = new PictureEntity();
        pictureEntity2.setBufferedImage(chemise1,imageFormat);


        //List de liens de vendeurs
        List<String> vendors1 = new ArrayList <>();
        vendors1.add("link1");
        vendors1.add("link2");

        List<String> vendors2 = new ArrayList <>();
        vendors2.add("link1");
        vendors2.add("link2");

        //Objets articles
        Article article1 = new Article(1L,16.50,"jeans",vendors1,pictureEntity1);
        Article article2 = new Article(2L,26.50,"chemises",vendors2,pictureEntity2);


        //Ajout dans la BDD
        if(hibernateArticleDao.getArticleOfType("jeans")!=null && hibernateArticleDao.getArticleOfType("chemises")!=null){

            hibernateArticleDao.saveOrCreate(article1);
            hibernateArticleDao.saveOrCreate(article2);
        }



        //Récupération d'une liste article en fonction du type d'article "jeans"
        Optional <Article> articleFromTypeJean = hibernateArticleDao.getArticleOfType("jeans");
        Optional <Article> articleFromTypeChemises = hibernateArticleDao.getArticleOfType("chemises");


        //Création d'une fenetre pour tester l'image
        JFrame fenetre1 = new JFrame();
        fenetre1.setContentPane(new JLabel(new ImageIcon(articleFromTypeJean.get().getPicture().asBufferedImage())));
        fenetre1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre1.pack();
        fenetre1.setLocation(100, 100);
        fenetre1.setVisible(true);
        
        JFrame fenetre2 = new JFrame();
        fenetre2.setContentPane(new JLabel(new ImageIcon(articleFromTypeChemises.get().getPicture().asBufferedImage())));
        fenetre2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre2.pack();
        fenetre2.setLocation(200, 200);
        fenetre2.setVisible(true);


        //Affichage des noms des articles
        articleFromTypeJean.get().getName();
        articleFromTypeChemises.get().getName();


    }
}
