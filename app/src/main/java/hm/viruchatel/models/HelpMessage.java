package hm.viruchatel.models;

/**
 * Created by Дмитрий on 05.06.2015.
 */
public class HelpMessage {
    private  String adress;
    private String distance;
    private String time;
    private String caption;
    private String message;
    private String name;
    private String photo;
    private String minimap;
    private int userType; //0-никто, 1-гость, 2- друг
    public HelpMessage(){

    }
    public HelpMessage(String adress, String distance,String time,String caption,String message,String name,String photo,String minimap,int userType){
        this.adress=adress;
        this.distance=distance;
        this.time=time;
        this.caption=caption;
        this.message=message;
        this.photo=photo;
        this.minimap=minimap;
        this.name=name;
        this.userType=userType;
    }

    public String getAdress() {
        return adress;
    }

    public String getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }

    public String getCaption() {
        return caption;
    }

    public String getMessage() {
        return message;
    }

    public String getPhoto() {
        return photo;
    }

    public String getMinimap() {
        return minimap;
    }

    public String getName() {
        return name;
    }

    public int getUserType() {
        return userType;
    }
}