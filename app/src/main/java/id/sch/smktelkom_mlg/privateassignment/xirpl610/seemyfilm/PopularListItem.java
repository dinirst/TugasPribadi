package id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm;

import java.io.Serializable;

/**
 * Created by Dini on 14/05/2017.
 */

public class PopularListItem implements Serializable {
    private String imageUrl;
    private String head;
    private String desc;

    public PopularListItem(String imageUrl, String head, String desc) {
        this.imageUrl = imageUrl;
        this.head = head;
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}
