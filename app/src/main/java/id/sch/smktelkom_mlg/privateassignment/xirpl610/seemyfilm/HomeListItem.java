package id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Dini Eka on 10/05/2017.
 */

public class HomeListItem extends SugarRecord implements Serializable {
    private String imageUrl;
    private String head;
    private String desc;

    public HomeListItem(String imageUrl, String head, String desc) {
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
