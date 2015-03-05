package com.gpshealthsurvey.gpshealthsurvey;

import android.media.Image;

/**
 * Created by Aroon on 2/15/2015.
 */
public class MenuListItem {

    private String name;
    private String description;
    private String ID;
    private Image icon;

    public String getName(){
        return name;
    }

    public String getDescription(){return description;}

    public String getID(){return ID;}

    public Image getIcon(){return icon;}


    public MenuListItem(String aname, String adescription, String aID){
        this.name = aname;
        this.description = adescription;
        this.ID = aID;
        this.icon = null;
    }

    public MenuListItem(String aname, String adescription, String aID, Image aicon){
        this.name = aname;
        this.description = adescription;
        this.ID = aID;
        this.icon = aicon;
    }

}
