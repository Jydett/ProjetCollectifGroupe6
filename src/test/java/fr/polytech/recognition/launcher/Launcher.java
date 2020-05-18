package fr.polytech.recognition.launcher;

import fr.polytech.recognition.dao.article.impl.HibernateArticleDao;
import fr.polytech.recognition.dao.articletype.impl.HibernateArticleTypeDao;
import fr.polytech.recognition.model.database.Article;
import fr.polytech.recognition.model.database.ArticleType;
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

public class Launcher {
    public static void main(String[] args) throws IOException {
        //Initialisation des sessions
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        HibernateArticleDao hibernateArticleDao = new HibernateArticleDao(session);
        HibernateArticleTypeDao hibernateArticleTypeDao = new HibernateArticleTypeDao(session);

        //Initialisation de l'image
        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\Tariq\\Desktop\\test.jpg"));
        ImageFormat imageFormat = ImageFormat.valueOf("PNG");

        //Initialisation des images
        PictureEntity pictureEntity = new PictureEntity();

        pictureEntity.setBufferedImage(bufferedImage,imageFormat);

        //Objets articles
        Article article1 = new Article(1L,16.50,"article1",null,"link1",pictureEntity);
        Article article2 = new Article(2L,26.50,"article2",null,"link2",pictureEntity);
        Article article3 = new Article(3L,36.50,"article3",null,"link3",pictureEntity);
        Article article4 = new Article(4L,46.50,"article4",null,"link4",pictureEntity);

        //Listes d'articles
        List <Article> articleList1 = new ArrayList <>();
        articleList1.add(article1);
        articleList1.add(article2);

        List <Article> articleList2 = new ArrayList <>();
        articleList2.add(article3);
        articleList2.add(article4);

        //Objets Type d'article
        ArticleType jeans = new ArticleType(1L,"jeans",articleList1);
        ArticleType chemises = new ArticleType(2L,"chemises",articleList2);


        //Lien entre les articles et les types d'article
        article1.setArticleType(jeans);
        article2.setArticleType(jeans);

        article3.setArticleType(chemises);
        article4.setArticleType(chemises);


        //Ajout dans la BDD
        hibernateArticleTypeDao.saveOrCreate(jeans);
        hibernateArticleTypeDao.saveOrCreate(chemises);

        hibernateArticleDao.saveOrCreate(article1);
        hibernateArticleDao.saveOrCreate(article2);
        hibernateArticleDao.saveOrCreate(article3);
        hibernateArticleDao.saveOrCreate(article4);


        //Récupération d'une liste article en fonction du type d'article "jeans"
        Collection <Article> articleFromTypeJean = hibernateArticleDao.getAllArticlesOfType(jeans);
        List<Article> articlesFromTypeJean = new ArrayList <>(articleFromTypeJean);

        //Création d'une fenetre pour tester l'image
        JFrame fenetre = new JFrame();
        fenetre.setContentPane(new JLabel(new ImageIcon(articlesFromTypeJean.get(0).getPicture().asBufferedImage())));
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.pack();
        fenetre.setLocation(100, 100);
        fenetre.setVisible(true);

        //Affichage des noms des articles
        for (Article art: articlesFromTypeJean) {
            System.out.println(art.getName());
        }


    }
}
