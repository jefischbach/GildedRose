package gildedrose;

import static org.junit.Assert.*;
import org.junit.Test;

public class GildedRoseTest {

    @Test
    public static void foo() {
        GildedRose appTest = new GildedRose(null);
        GildedRose appTemoin = new GildedRose(null);

        // Fonctionnement standard
        appTest = new GildedRose(new Item[]{new Item("Objet standard",10,20)});
        appTemoin = new GildedRose(new Item[]{new Item("Objet standard",9,19)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Aged Brie" gagnent en qualité avec le temps
        appTest = new GildedRose(new Item[]{new Item("Aged Brie",10,20)});
        appTemoin = new GildedRose(new Item[]{new Item("Aged Brie",9,21)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Aged Brie" gagnent en qualité deux fois plus vite si la date de péremption est passée
        appTest = new GildedRose(new Item[]{new Item("Aged Brie",0,20)});
        appTemoin = new GildedRose(new Item[]{new Item("Aged Brie",-1,22)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Sulfuras, Hand of Ragnaros" ne perdent jamais en qualité et la date de péremption ne bouge pas
        appTest = new GildedRose(new Item[]{new Item("Sulfuras, Hand of Ragnaros",10,80)});
        appTemoin = new GildedRose(new Item[]{new Item("Sulfuras, Hand of Ragnaros",10,80)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Si la qualité d'un item est de 50, elle ne peut pas augmenter au-delà
        appTest = new GildedRose(new Item[]{new Item("Aged Brie",10,50)});
        appTemoin = new GildedRose(new Item[]{new Item("Aged Brie",9,50)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Backstage passes..." gagnent en qualité avec le temps : un point chaque jour s'il en reste au moins 11 avant péremption
        appTest = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",40,20)});
        appTemoin = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",39,21)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Backstage passes..." gagnent en qualité avec le temps : deux points chaque jour s'il en reste entre 6 et 10 avant péremption
        appTest = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",8,20)});
        appTemoin = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",7,22)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Backstage passes..." gagnent en qualité avec le temps : trois points chaque jour s'il en 5 ou moins avant péremption
        appTest = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",4,20)});
        appTemoin = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",3,23)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items "Backstage passes..." voient leurs qualités instantanément passer à 0 dès lors que la date de péremption est atteinte
        appTest = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",0,20)});
        appTemoin = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",-1,0)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // La qualité des items "Backstage passes..." est maintenue à 0 après que la date de péremption est passée
        appTest = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",-5,0)});
        appTemoin = new GildedRose(new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",-6,0)});
        appTest.updateQuality();
        assertEquals(appTest,appTemoin);

        // Les items dont le nom commence par "Conjured" perdent en qualité deux fois plus vite que les autres
        appTest = new GildedRose(new Item[]{new Item("Conjured Test",40,20)});
        appTemoin = new GildedRose(new Item[]{new Item("Conjured Test",39,18)});
        appTest.updateQuality();
        //assertEquals(appTest,appTemoin);
    }

}