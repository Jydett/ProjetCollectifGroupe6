package fr.polytech.recognition.launcher;

import fr.polytech.recognition.dao.article.impl.HibernateArticleDao;
import fr.polytech.recognition.model.database.Article;
import fr.polytech.recognition.model.database.PictureEntity;
import fr.polytech.recognition.utils.ImageFormat;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class ArticleDaoTest {
    //Init des sessions + dao
    private Session session = new Configuration().configure().buildSessionFactory().openSession();
    private HibernateArticleDao hibernateArticleDao = new HibernateArticleDao(session);
    private List<Article> articles = new ArrayList <>();
    private int taille;



    /*
     *  Méthode pour init les données avant tout les test
     */
    @Before
    public void init() throws IOException {


        //Initialisation des Buffered Image/ImageFormat
        BufferedImage jean = ImageIO.read(new File("./src/test/resources/images/jeans.jpg"));
        BufferedImage chemise = ImageIO.read(new File("./src/test/resources/images/chemise.jpg"));
        BufferedImage bermuda = ImageIO.read(new File("./src/test/resources/images/bermuda.jpg"));
        BufferedImage casquette = ImageIO.read(new File("./src/test/resources/images/casquette.jpg"));
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



        //List de liens de vendeurs
        List <String> vendors1 = new ArrayList <>();
        vendors1.add("link1");
        //vendors1.add("link2");

        List<String> vendors2 = new ArrayList <>();
        vendors2.add("link1");
        //vendors2.add("link2");

        //Objets articles
        Article article1 = new Article(1L,16.50,"jean",vendors1,pictureEntity1);
        Article article2 = new Article(2L,26.50,"chemise",vendors2,pictureEntity2);
        Article article3 = new Article(3L,16.50,"bermuda",vendors1,pictureEntity3);
        Article article4 = new Article(4L,26.50,"casquette",vendors2,pictureEntity4);


        session.getTransaction().begin();
        session.save(article1);
        session.save(article2);
        session.save(article3);
        session.save(article4);
        session.getTransaction().commit();

        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);

        taille = articles.size();

    }

    /*
     *  Test récupération d'un article
     */
    @Test
    public void testGetArticle(){
        Optional <Article> articleFromTypeJean = hibernateArticleDao.getArticleOfType("jean");
        assertEquals("jean",articleFromTypeJean.get().getName());
    }

    /*
     *  Test récupération de tous les articles
     */
    @Test
    public void testGetAllArticle(){
        List <Article> articleList = new ArrayList <>( hibernateArticleDao.getAll());
        assertEquals(taille,articleList.size());
    }

    /*
     *  Test Ajout d'un article
     */
    @Test
    public void testAddArticle() throws IOException {
        //Init buffered Image + Formats
        BufferedImage casquette = ImageIO.read(new File("./src/test/resources/images/casquette.jpg"));
        ImageFormat imageFormat = ImageFormat.valueOf("JPG");

        //List de liens de vendeurs
        List <String> testAjoutVendeurs = new ArrayList <>();
        testAjoutVendeurs.add("link1");

        //PictureEntity
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setBufferedImage(casquette,imageFormat);

        //Init article à ajouter
        Article testAjout = new Article(5L,26.50,"testAjout",testAjoutVendeurs,pictureEntity);

        //Ajout
        hibernateArticleDao.saveOrCreate(testAjout);

        //récupération de la liste après ajout
        ArrayList<Article> articleArrayListAfterAdd = new ArrayList <>(hibernateArticleDao.getAll());

        //Test après ajout
        assertEquals(taille+1, articleArrayListAfterAdd.size());

        session.getTransaction().begin();
        session.remove(testAjout);
        session.getTransaction().commit();
    }


    /*
     *  Test Suppr d'un article
     */
    @Test
    public void testSupprArticle() throws IOException {
        //Init buffered Image + Formats
        BufferedImage casquette = ImageIO.read(new File("./src/test/resources/images/casquette.jpg"));
        ImageFormat imageFormat = ImageFormat.valueOf("JPG");

        //List de liens de vendeurs
        List <String> testAjoutVendeurs = new ArrayList <>();
        testAjoutVendeurs.add("link1");

        //PictureEntity
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setBufferedImage(casquette,imageFormat);

        //Init article à ajouter
        Article testSuppr = new Article(6L,26.50,"testSuppr",testAjoutVendeurs,pictureEntity);

        //Ajout
        session.getTransaction().begin();
        session.save(testSuppr);
        session.getTransaction().commit();
        taille++;
        hibernateArticleDao.remove(testSuppr);

        //récupération de la liste après suppr
        ArrayList<Article> articleArrayListAftersuppr= new ArrayList <>(hibernateArticleDao.getAll());

        //Test après suppr
        assertEquals(taille-1, articleArrayListAftersuppr.size());


    }


    /*
     *  Clean up de la base de données
     */
    @After
    public void cleanOut(){
        if(!articles.isEmpty()){
            session.getTransaction().begin();
            for (Article art: articles
                 ) {
                session.remove(art);
            }
            session.getTransaction().commit();
        }
    }


}
