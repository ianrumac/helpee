package ee.help.helpee.models;

/**
 * Created by infinum on 01/05/15.
 */
public class DrawerItemModel{

    String item;

    int drawableResource;

    boolean isPrimary;

    public DrawerItemModel(String item, int drawableResource, boolean isPrimary) {
        this.item = item;
        this.drawableResource = drawableResource;
        this.isPrimary = isPrimary;
    }

    public String getItem() {
        return item;
    }

    public int getDrawableResource() {
        return drawableResource;
    }

    public boolean isPrimary() {
        return isPrimary;
    }
}
