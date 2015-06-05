package hm.viruchatel.models;

/**
 * Created by Дмитрий on 05.06.2015.
 */
public class Message {

    private String time;
    private String caption;
    private String message;
    private String name;
    private String photo;
    public Message(){

    }
    public Message( String time,  String message, String name, String photo ){

        this.time=time;

        this.message=message;
        this.photo=photo;

        this.name=name;

    }



    public String getTime() {
        return time;
    }



    public String getMessage() {
        return message;
    }

    public String getPhoto() {
        return photo;
    }



    public String getName() {
        return name;
    }


}