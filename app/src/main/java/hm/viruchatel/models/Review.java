package hm.viruchatel.models;

import android.content.res.Resources;

import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.ResourceBundle;

import hm.viruchatel.R;

/**
 * Created by Дмитрий on 05.06.2015.
 */
public class Review {
    private static final int RED=1;
    private static final int GREEN=2;
    private static final int YELLOW=3;

    private  int id;
    private String message;
    private String name;
    private String photo;
    private int color;
    public Review(){
        message="Быстро приехал и сделал все, что было нужно. Все нормально получилось, мастер ещеи проконсультировал по некоторым вопросам";
        name="Александра Новикова";
        color= RED;
    }
    public Review( int id,String message, String name, String photo, int color){

        this.id=id;
        this.message=message;
        this.photo=photo;
        this.name=name;
        this.color=color;

    }







    public String getMessage() {
        return message;
    }

    public String getPhoto() {
        return photo;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        int resId=0;
        switch (color){
            case GREEN:
                resId= R.color.text_color_green;
                break;
            case RED:
                resId= R.color.text_color3;
                break;
            case YELLOW:
                resId= R.color.color_yellow;
                break;
        }
        return resId;
    }
    public int getState(){
        return color;
    }

    public String getName() {
        return name;
    }


}