package gildedrose;

class GildedRose {

    private static final String CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof GildedRose)) {
            return false;
        }

        GildedRose gr2 = (GildedRose) o;

        int i = 0;
        for(Item item : items)
        {
            if (item.name != gr2.items[i].name || item.sellIn != gr2.items[i].sellIn || item.quality != gr2.items[i].quality)
                return false;
        }
        return true;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals(CONCERT)) {
                if (isNotSpoiled(i)) {
                    items[i].quality = items[i].quality - 1;
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals(CONCERT)) {
                        if ((items[i].sellIn < 11) && (items[i].quality < 50)) {
                            items[i].quality = items[i].quality + 1;
                        }

                        if ((items[i].sellIn < 6) && (items[i].quality < 50)) {
                                items[i].quality = items[i].quality + 1;
                        }
                    }
                }
            }

            if (!items[i].name.equals(SULFURAS)) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals(CONCERT)) {
                        if (isNotSpoiled(i)) {
                            items[i].quality = items[i].quality - 1;
                        }
                    } else {
                        items[i].quality = 0;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }

    private boolean isNotSpoiled(int i){
        return ((items[i].quality > 0) && (!items[i].name.equals(SULFURAS)));
    }
}