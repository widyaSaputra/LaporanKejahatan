package com.example.laporan.laporankejahatan;

import java.util.Date;

/**
 * Created by HP on 02/10/2017.
 */

public class SaveLoc {
    public String latitude;
    public String longitude;
    public String tgl;

    public SaveLoc(String latitude, String longitude, String tgl) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.tgl = tgl;
    }
}
