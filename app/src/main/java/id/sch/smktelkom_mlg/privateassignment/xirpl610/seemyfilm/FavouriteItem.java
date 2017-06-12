package id.sch.smktelkom_mlg.privateassignment.xirpl610.seemyfilm;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Dini Eka on 14/05/2017.
 */

public class FavouriteItem extends SugarRecord implements Serializable {
    public String judul;
    public String deskripsi;
    public String urlgambar;

    public FavouriteItem() {
    }

    public FavouriteItem(String judul, String deskripsi, String urlgambar) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.urlgambar = urlgambar;
    }
}
