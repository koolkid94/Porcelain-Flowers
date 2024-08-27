package net.kuwulkid.porcelain.client.renderer.item;
import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.item.custom.VineTomeItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class VineTomeRenderer extends GeoItemRenderer<VineTomeItem> {
    public VineTomeRenderer(){
        super(new DefaultedItemGeoModel<>(PorcelainFlowers.id("vinetomeitem")));
    }
}
