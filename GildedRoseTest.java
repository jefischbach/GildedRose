package gildedrose;

//import org.junit.Test;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {

    private static final String AGEDBRIE = "Aged Brie";
    private static final String CONCERT = "Backstage passes to a TAFKAL80ETC concert";

    private GildedRoseTest(){}

    @Test
    public static void foo() {


        // Fonctionnement standard
        GildedRose appTest = new GildedRose(new Item[]{new Item("Objet standard",10,20)});
        GildedRose appTemoin = new GildedRose(new Item[]{new Item("Objet standard",9,19)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Aged Brie" gagnent en qualité avec le temps
        appTest = new GildedRose(new Item[]{new Item(AGEDBRIE,10,20)});
        appTemoin = new GildedRose(new Item[]{new Item(AGEDBRIE,9,21)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Aged Brie" gagnent en qualité deux fois plus vite si la date de péremption est passée
        appTest = new GildedRose(new Item[]{new Item(AGEDBRIE,0,20)});
        appTemoin = new GildedRose(new Item[]{new Item(AGEDBRIE,-1,22)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Sulfuras, Hand of Ragnaros" ne perdent jamais en qualité et la date de péremption ne bouge pas
        appTest = new GildedRose(new Item[]{new Item("Sulfuras, Hand of Ragnaros",10,80)});
        appTemoin = new GildedRose(new Item[]{new Item("Sulfuras, Hand of Ragnaros",10,80)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Si la qualité d'un item est de 50, elle ne peut pas augmenter au-delà
        appTest = new GildedRose(new Item[]{new Item(AGEDBRIE,10,50)});
        appTemoin = new GildedRose(new Item[]{new Item(AGEDBRIE,9,50)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Backstage passes..." gagnent en qualité avec le temps : un point chaque jour s'il en reste au moins 11 avant péremption
        appTest = new GildedRose(new Item[]{new Item(CONCERT,40,20)});
        appTemoin = new GildedRose(new Item[]{new Item(CONCERT,39,21)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Backstage passes..." gagnent en qualité avec le temps : deux points chaque jour s'il en reste entre 6 et 10 avant péremption
        appTest = new GildedRose(new Item[]{new Item(CONCERT,8,20)});
        appTemoin = new GildedRose(new Item[]{new Item(CONCERT,7,22)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Backstage passes..." gagnent en qualité avec le temps : trois points chaque jour s'il en 5 ou moins avant péremption
        appTest = new GildedRose(new Item[]{new Item(CONCERT,4,20)});
        appTemoin = new GildedRose(new Item[]{new Item(CONCERT,3,23)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Backstage passes..." voient leurs qualités instantanément passer à 0 dès lors que la date de péremption est atteinte
        appTest = new GildedRose(new Item[]{new Item(CONCERT,0,20)});
        appTemoin = new GildedRose(new Item[]{new Item(CONCERT,-1,0)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // La qualité des items "Backstage passes..." est maintenue à 0 après que la date de péremption est passée
        appTest = new GildedRose(new Item[]{new Item(CONCERT,-5,0)});
        appTemoin = new GildedRose(new Item[]{new Item(CONCERT,-6,0)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items dont le nom commence par "Conjured" perdent en qualité deux fois plus vite que les autres
        appTest = new GildedRose(new Item[]{new Item("Conjured Test",40,20)});
        appTemoin = new GildedRose(new Item[]{new Item("Conjured Test",39,18)});
        appTest.updateQuality();
        //assertEquals(appTest,appTemoin);
    }

}
