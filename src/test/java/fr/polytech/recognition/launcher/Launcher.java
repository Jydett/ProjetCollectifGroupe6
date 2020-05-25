package fr.polytech.recognition.launcher;

import fr.polytech.recognition.dao.article.impl.HibernateArticleDao;
import fr.polytech.recognition.model.database.Article;
import fr.polytech.recognition.model.database.PictureEntity;
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
        BufferedImage jean = ImageIO.read(new File("./src/test/resources/images/jeans.jpg"));
        BufferedImage chemise = ImageIO.read(new File("./src/test/resources/images/chemise.jpg"));
        BufferedImage bermuda = ImageIO.read(new File("./src/test/resources/images/bermuda.jpg"));
        BufferedImage casquette = ImageIO.read(new File("./src/test/resources/images/casquette.jpg"));
        BufferedImage costume = ImageIO.read(new File("./src/test/resources/images/costume.jpg"));
        BufferedImage gilet = ImageIO.read(new File("./src/test/resources/images/gilet.jpg"));
        BufferedImage manteau = ImageIO.read(new File("./src/test/resources/images/manteau.jpg"));
        BufferedImage parka = ImageIO.read(new File("./src/test/resources/images/parka.jpg"));
        BufferedImage pull = ImageIO.read(new File("./src/test/resources/images/pull.jpg"));
        BufferedImage veste = ImageIO.read(new File("./src/test/resources/images/veste.jpg"));
        BufferedImage shortType = ImageIO.read(new File("./src/test/resources/images/short.jpg"));
        BufferedImage masque = ImageIO.read(new File("./src/test/resources/images/pull.jpg"));
        BufferedImage polo = ImageIO.read(new File("./src/test/resources/images/polo.jpg"));
        ImageFormat imageFormat = ImageFormat.valueOf("JPG");

        //Initialisation des images
        PictureEntity pictureEntity1 = new PictureEntity();
        pictureEntity1.setBufferedImage(jean,imageFormat);

        PictureEntity pictureEntity2 = new PictureEntity();
        pictureEntity2.setBufferedImage(chemise,imageFormat);

        PictureEntity pictureEntity3 = new PictureEntity();
        pictureEntity3.setBufferedImage(bermuda,imageFormat);

        PictureEntity pictureEntity4 = new PictureEntity();
        pictureEntity4.setBufferedImage(casquette,imageFormat);

        PictureEntity pictureEntity5 = new PictureEntity();
        pictureEntity5.setBufferedImage(costume,imageFormat);

        PictureEntity pictureEntity6 = new PictureEntity();
        pictureEntity6.setBufferedImage(gilet,imageFormat);

        PictureEntity pictureEntity7 = new PictureEntity();
        pictureEntity7.setBufferedImage(manteau,imageFormat);

        PictureEntity pictureEntity8 = new PictureEntity();
        pictureEntity8.setBufferedImage(parka,imageFormat);

        PictureEntity pictureEntity9 = new PictureEntity();
        pictureEntity9.setBufferedImage(pull,imageFormat);

        PictureEntity pictureEntity10 = new PictureEntity();
        pictureEntity10.setBufferedImage(veste,imageFormat);

        PictureEntity pictureEntity11 = new PictureEntity();
        pictureEntity11.setBufferedImage(shortType,imageFormat);

        PictureEntity pictureEntity12 = new PictureEntity();
        pictureEntity12.setBufferedImage(masque,imageFormat);

        PictureEntity pictureEntity13 = new PictureEntity();
        pictureEntity13.setBufferedImage(polo,imageFormat);



        //List de liens de vendeurs
        List<String> vendors1 = new ArrayList <>();
        vendors1.add("link1");
        vendors1.add("link2");

        List<String> vendors2 = new ArrayList <>();
        vendors2.add("link1");
        vendors2.add("link2");

        //Objets articles
        Article article1 = new Article(1L,16.50,"jean",vendors1,pictureEntity1);
        Article article2 = new Article(2L,26.50,"chemise",vendors2,pictureEntity2);
        Article article3 = new Article(3L,16.50,"bermuda",vendors1,pictureEntity1);
        Article article4 = new Article(4L,26.50,"casquette",vendors2,pictureEntity2);
        Article article5 = new Article(5L,16.50,"costume",vendors1,pictureEntity1);
        Article article6 = new Article(6L,26.50,"gilet",vendors2,pictureEntity2);
        Article article7 = new Article(7L,16.50,"manteau",vendors1,pictureEntity1);
        Article article8 = new Article(8L,26.50,"parka",vendors2,pictureEntity2);
        Article article9 = new Article(9L,16.50,"pull",vendors1,pictureEntity1);
        Article article10 = new Article(10L,26.50,"veste",vendors2,pictureEntity2);
        Article article11= new Article(11L,16.50,"shortType",vendors1,pictureEntity1);
        Article article12 = new Article(12L,26.50,"masque",vendors2,pictureEntity2);
        Article article13 = new Article(13L,16.50,"polo",vendors1,pictureEntity1);



        //Ajout dans la BDD
        if(hibernateArticleDao.getAll().isEmpty()){
            hibernateArticleDao.saveOrCreate(article1);
            hibernateArticleDao.saveOrCreate(article2);
            hibernateArticleDao.saveOrCreate(article3);
            hibernateArticleDao.saveOrCreate(article4);
            hibernateArticleDao.saveOrCreate(article5);
            hibernateArticleDao.saveOrCreate(article6);
            hibernateArticleDao.saveOrCreate(article7);
            hibernateArticleDao.saveOrCreate(article8);
            hibernateArticleDao.saveOrCreate(article9);
            hibernateArticleDao.saveOrCreate(article10);
            hibernateArticleDao.saveOrCreate(article11);
            hibernateArticleDao.saveOrCreate(article12);
            hibernateArticleDao.saveOrCreate(article13);
        }



        //Récupération d'une liste article en fonction du type d'article "jeans"
        Optional <Article> articleFromTypeJean = hibernateArticleDao.getArticleOfType("jean");
        Optional <Article> articleFromTypeChemises = hibernateArticleDao.getArticleOfType("chemise");


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
