package hm.viruchatel.models;

/**
 * Created by Дмитрий on 06.06.2015.
 */
public class Friend {private int numSaved;

    private String name;
    private String city;
    private String rating;
    private String top;

    private String photo;
    private String photo50;
    private int photoId=0;


    private int id;

    public Friend(){
        name="Иванов Иван Иванович";
        city="Москва, Россия";
        rating="4.75";
        top="75";

        photo=null;

        id=1;

    }
    public Friend(int id, String name){
        this.name=name;
        city="Москва, Россия";
        rating="4.75";
        top="75";

        photo=null;

        this.id=id;

    }
    public Friend(int id, String name, String lastName,String photo50){
        this.name=name+" "+lastName;
        city="Москва, Россия";
        rating="4.75";
        top="75";
        this.photo50=photo50;
        photo=null;

        this.id=id;

    }
    public Friend(int id, String name, int photoId){
        this.name=name;
        city="Москва, Россия";
        rating="4.75";
        top="75";
        this.photoId=photoId;
        photo=null;

        this.id=id;

    }

    public Friend(String name, String city, String rating, String top, String photo,  int id){
        this.name=name;
        this.city=city;
        this.rating=rating;
        this.top=top;

        this.photo=photo;
        this.photoId=0;
        this.id=id;



    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getRating() {
        return rating;
    }

    public String getTop() {
        return top;
    }





    public String getPhoto() {
        return photo;
    }
    public String getPhoto50() {
        return photo50;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int getId() {
        return id;
    }

    public int getNumSaved() {
        return numSaved;
    }



    public String getNumSavedS(){
        return Integer.toString(numSaved);
    }
}
