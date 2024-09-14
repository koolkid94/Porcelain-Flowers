package net.kuwulkid.porcelain.client.renderer.item;


import net.kuwulkid.porcelain.PorcelainFlowers;
import net.kuwulkid.porcelain.item.custom.unused.DeleterCubeItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DeleterCubeRenderer extends GeoItemRenderer<DeleterCubeItem> {
    public DeleterCubeRenderer(){
        super(new DefaultedItemGeoModel<>(PorcelainFlowers.id("deleter_cube")));
    }
}
