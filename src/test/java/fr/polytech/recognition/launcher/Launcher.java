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
import java.util.List;
import java.util.Optional;

public class Launcher {
    public static void main(String[] args) throws IOException {
        //Initialisation des sessions
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        HibernateArticleDao hibernateArticleDao = new HibernateArticleDao(session);

        //Initialisation des Buffered Image/ImageFormat
        BufferedImage sweatshirt = ImageIO.read(new File("./src/test/resources/images/sweatshirt.jpg"));
        BufferedImage miniskirt = ImageIO.read(new File("./src/test/resources/images/miniskirt.jpg"));
        BufferedImage sunglass = ImageIO.read(new File("./src/test/resources/images/sunglass.jpg"));
        BufferedImage furcoat = ImageIO.read(new File("./src/test/resources/images/furcoat.jpg"));
        BufferedImage pajama = ImageIO.read(new File("./src/test/resources/images/pajama.jpg"));
        BufferedImage runningshoe = ImageIO.read(new File("./src/test/resources/images/runningshoe.jpg"));
        BufferedImage sandal = ImageIO.read(new File("./src/test/resources/images/sandal.jpg"));
        BufferedImage jean = ImageIO.read(new File("./src/test/resources/images/jeans.jpg"));
        BufferedImage cloak = ImageIO.read(new File("./src/test/resources/images/cloak.png"));
        BufferedImage apron = ImageIO.read(new File("./src/test/resources/images/apron.jpg"));
        BufferedImage poncho = ImageIO.read(new File("./src/test/resources/images/poncho.jpg"));
        BufferedImage cowboyboot = ImageIO.read(new File("./src/test/resources/images/cowboyboot.jpg"));
        BufferedImage sombrero = ImageIO.read(new File("./src/test/resources/images/sombrero.jpg"));
        BufferedImage labcoat = ImageIO.read(new File("./src/test/resources/images/labcoat.jpg"));
        BufferedImage overskirt = ImageIO.read(new File("./src/test/resources/images/overskirt.jpg"));
        BufferedImage tshirt = ImageIO.read(new File("./src/test/resources/images/tshirt.jpg"));
        BufferedImage tanktop = ImageIO.read(new File("./src/test/resources/images/tanktop.jpg"));
        BufferedImage loafer = ImageIO.read(new File("./src/test/resources/images/loafer.jpg"));
        BufferedImage bikini = ImageIO.read(new File("./src/test/resources/images/bikini.jpg"));
        BufferedImage sock = ImageIO.read(new File("./src/test/resources/images/sock.jpg"));
        ImageFormat imageFormat = ImageFormat.valueOf("JPG");
        ImageFormat imageFormat1 = ImageFormat.valueOf("PNG");

        //Initialisation des images
        PictureEntity sweatshirtPE = new PictureEntity();
        sweatshirtPE.setBufferedImage(sweatshirt,imageFormat);

        PictureEntity miniskirtPE = new PictureEntity();
        miniskirtPE.setBufferedImage(miniskirt,imageFormat);

        PictureEntity sunglassPE = new PictureEntity();
        sunglassPE.setBufferedImage(sunglass,imageFormat);

        PictureEntity furcoatPE = new PictureEntity();
        furcoatPE.setBufferedImage(furcoat,imageFormat);

        PictureEntity pajamaPE = new PictureEntity();
        pajamaPE.setBufferedImage(pajama,imageFormat);

        PictureEntity runningshoePE = new PictureEntity();
        runningshoePE.setBufferedImage(runningshoe,imageFormat);

        PictureEntity sandalPE = new PictureEntity();
        sandalPE.setBufferedImage(sandal,imageFormat);

        PictureEntity jeanPE = new PictureEntity();
        jeanPE.setBufferedImage(jean,imageFormat);

        PictureEntity cloakPE = new PictureEntity();
        cloakPE.setBufferedImage(cloak,imageFormat1);

        PictureEntity apronPE = new PictureEntity();
        apronPE.setBufferedImage(apron,imageFormat);

        PictureEntity ponchoPE = new PictureEntity();
        ponchoPE.setBufferedImage(poncho,imageFormat);

        PictureEntity cowboybootPE = new PictureEntity();
        cowboybootPE.setBufferedImage(cowboyboot,imageFormat);

        PictureEntity labcoatPE = new PictureEntity();
        labcoatPE.setBufferedImage(labcoat,imageFormat);

        PictureEntity sombreroPE = new PictureEntity();
        sombreroPE.setBufferedImage(sombrero,imageFormat);

        PictureEntity overskirtPE = new PictureEntity();
        overskirtPE.setBufferedImage(overskirt,imageFormat);

        PictureEntity tshirtPE = new PictureEntity();
        tshirtPE.setBufferedImage(tshirt,imageFormat);

        PictureEntity tanktopPE = new PictureEntity();
        tanktopPE.setBufferedImage(tanktop,imageFormat);

        PictureEntity loaferPE = new PictureEntity();
        loaferPE.setBufferedImage(loafer,imageFormat);

        PictureEntity bikiniPE = new PictureEntity();
        bikiniPE.setBufferedImage(bikini,imageFormat);

        PictureEntity sockPE = new PictureEntity();
        sockPE.setBufferedImage(sock,imageFormat);



        //List de liens de vendeurs
        List<String> sweatshirtWS = new ArrayList <>();
        sweatshirtWS.add("https://www.zalando.fr/lacoste-sh8546-sweatshirt-la222s02o-a11.html");
        sweatshirtWS.add("https://www.zalando.fr/lacoste-sweatshirt-farine-la222s031-a11.html");

        List<String> miniskirtWS = new ArrayList <>();
        miniskirtWS.add("https://www.missguidedfr.fr/vetements/jupes/mini-jupes");


        List<String> sunglassWS = new ArrayList <>();
        sunglassWS.add("https://www.sunglasses-shop.co.uk/ray-ban/aviator/black-l2821/12329?tmsb=tay13mz&tmrl=dbzzt&tmsl=4-product-carousel&tmty=w&tmcv=40&tmcs=1jaz5z5");

        List<String> furcoatWS = new ArrayList <>();
        furcoatWS.add("https://www.ssense.com/en-de/women/product/gucci/tan-faux-fur-coat/4649101?clickref=1011l8p9uCWR&utm_source=PH_1011l2075&utm_medium=affiliate&utm_content=1100l24757&utm_term=https%3A%2F%2Fwww.lyst.fr%2F&utm_campaign=");

        List<String> pajamaWS = new ArrayList <>();
        pajamaWS.add("https://www.victoriassecret.com/vs/sleepwear/pajamas");
        List<String> runningshoeWS = new ArrayList <>();
        runningshoeWS.add("https://www.brooksrunning.com/fr_fr/chaussure-de-running-sur-route-pour-femme-adrenaline-gts-20/1202961B406.090.html?gclid=CjwKCAjw8df2BRA3EiwAvfZWaHPeifEeN6ooLLXqPQxhEHm4NUwEUAWdmxPn0yHRJJMW37WX1KEUiBoCmXUQAvD_BwE&gclsrc=aw.ds");

        List<String> sandalWS = new ArrayList <>();
        sandalWS.add("https://www.zalando.fr/sandales-nu-pieds-femme/");

        List<String> jeanWS = new ArrayList <>();
        jeanWS.add("https://www.jules.com/fr-fr/p/719212.html?cidHC=MDA_Google_SmartShopping_CSS_BAS&gclid=CjwKCAjw8df2BRA3EiwAvfZWaPhOxzMMrM5xoAQwNzmL33m6jy7yxVjleeYVTZH6MEdaN7CVOaYORBoCLTgQAvD_BwE&gclsrc=aw.ds");

        List<String> cloakWS = new ArrayList <>();
        cloakWS.add("https://www.amazon.com/s?k=cloak&rh=n%3A668145011&ref=nb_sb_noss");
        List<String> apronWS = new ArrayList <>();
        apronWS.add("https://www.amazon.com/Aprons/b?ie=UTF8&node=668145011");
        List<String> ponchoWS = new ArrayList <>();
        ponchoWS.add("https://nohowstyle.com/products/sioux-poncho-in-black-and-orange?gclid=CjwKCAjw8df2BRA3EiwAvfZWaGfw040iUcvZbgS442A7WPFl1nJ09NESfxciPdpkmNbHL_g58afIQBoCIioQAvD_BwE");
        List<String> cowboybootWS = new ArrayList <>();
        cowboybootWS.add("https://www.cavenders.com/mens-western-boots-shoes/");

        List<String> sombreroWS = new ArrayList <>();
        sombreroWS.add("https://www.deguisetoi.fr/p-236771-sombrero-mexicain-bordure-rose-et-bleu-adulte.html?type=product&gclid=CjwKCAjw8df2BRA3EiwAvfZWaLTGs2KUvfwIiCBoIlIoDjOFiiZ-d8QxMn65phU8eiQdAzce_pnz4RoCg0cQAvD_BwE#ectrans=1");


        List<String> labcoatWS = new ArrayList <>();
        labcoatWS.add("https://fr.scrub-supply.com/product-page/charles-lab-coat?gclid=CjwKCAjw8df2BRA3EiwAvfZWaFtceaskekLE3Btd35xrqqzKO4y4oCTh8VpnucbG9j_YUObwybq-CBoCI4wQAvD_BwE");


        List<String> overskirtWS = new ArrayList <>();
        overskirtWS.add("https://www.seraphine.fr/surjupe-de-mariee-en-tulle.html?gclid=CjwKCAjw8df2BRA3EiwAvfZWaL-7xjHY639Vs23VqqwsWf-5EVL7xJFGhWEqF1aR1ZOOf9nRpdVeqBoCX4EQAvD_BwE");

        List<String> tshirtWS = new ArrayList <>();
        tshirtWS.add("https://www.g-star.com/fr_fr/shop/basiques/d07205-124-110?gclid=CjwKCAjw8df2BRA3EiwAvfZWaCipcrSJWQoSbm06pmKKR42UgbC1VSZDr1Xq9cTrxn3AlPCaFlWrwBoCT1IQAvD_BwE");
        tshirtWS.add("https://www.wordans.fr/fruit-of-the-loom-ss048-t-shirt-a-col-rond-6599/c23-blanc/s38525-l?gclid=CjwKCAjw8df2BRA3EiwAvfZWaAQP0zj7FLGpkh9DRylLPR7fYBnECZ77EgPf8IWvUxVpTyar76jJNxoCJ0UQAvD_BwE");
        List<String> tanktopWS = new ArrayList <>();
        tanktopWS.add("https://www.g-star.com/fr_fr/shop/hommes/basiques/d07206-124-110?gclid=CjwKCAjw8df2BRA3EiwAvfZWaBHjGrmzMe_Z-vkjw3PRPcThMfhVLCRfVzBSt0jtxNkKJuzCiTZghBoC_BEQAvD_BwE");
        tanktopWS.add("https://fr.gymshark.com/products/gymshark-critical-drop-armhole-tank-grey-marl?gclid=CjwKCAjw8df2BRA3EiwAvfZWaKIAQiiQG6YHkD5iag3PdluJcWlx5kafmIpm0jFqHaz3K19s2DgEjBoCQQ4QAvD_BwE");

        List<String> loaferWS = new ArrayList <>();
        loaferWS.add("https://www.zalando.fr/mocassins-loafers-homme/");
        List<String> bikiniWS = new ArrayList <>();
        bikiniWS.add("https://www.oysho.com/fr/bain-et-beachwear/bikini/voir-tout-c1010277574.html");


        List<String> sockWS = new ArrayList <>();
        sockWS.add("https://www.zalando.fr/homme/?q=sock");




        //Objets articles
        Article article1 = new Article(1L,16.50,"sweatshirt",sweatshirtWS,sweatshirtPE);
        Article article2 = new Article(2L,26.50,"miniskirt",miniskirtWS,miniskirtPE);
        Article article3 = new Article(3L,16.50,"sunglass",sunglassWS,sunglassPE);
        Article article4 = new Article(4L,26.50,"fur coat",furcoatWS,furcoatPE);
        Article article5 = new Article(5L,16.50,"pajama",pajamaWS,pajamaPE);
        Article article6 = new Article(6L,26.50,"running shoe",runningshoeWS,runningshoePE);
        Article article7 = new Article(7L,16.50,"sandal",sandalWS,sandalPE);
        Article article8 = new Article(8L,26.50,"jean",jeanWS,jeanPE);
        Article article9 = new Article(9L,16.50,"cloak",cloakWS,cloakPE);
        Article article10 = new Article(10L,26.50,"apron",apronWS,apronPE);
        Article article11= new Article(11L,16.50,"poncho",ponchoWS,ponchoPE);
        Article article12 = new Article(12L,26.50,"cowboy boot",cowboybootWS,cowboybootPE);
        Article article13 = new Article(13L,16.50,"sombrero",sombreroWS,sombreroPE);
        Article article14 = new Article(14L,16.50,"lab coat",labcoatWS,labcoatPE);
        Article article16 = new Article(16L,16.50,"overskirt",overskirtWS,overskirtPE);
        Article article17 = new Article(17L,16.50,"tshirt",tshirtWS,tshirtPE);
        Article article18 = new Article(18L,16.50,"tanktop",tanktopWS,tanktopPE);
        Article article19 = new Article(19L,16.50,"loafer",loaferWS,loaferPE);
        Article article20 = new Article(20L,16.50,"bikini",bikiniWS,bikiniPE);
        Article article21 = new Article(15L,16.50,"sock",sockWS,sockPE);



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
            hibernateArticleDao.saveOrCreate(article14);
            hibernateArticleDao.saveOrCreate(article16);
            hibernateArticleDao.saveOrCreate(article17);
            hibernateArticleDao.saveOrCreate(article18);
            hibernateArticleDao.saveOrCreate(article19);
            hibernateArticleDao.saveOrCreate(article20);
            hibernateArticleDao.saveOrCreate(article21);
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
