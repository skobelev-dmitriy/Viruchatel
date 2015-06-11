package hm.viruchatel.models;

import java.util.Random;

/**
 * Created by Дмитрий on 06.06.2015.
 */
public class Profile {
    private int numSaved;
    private int numSaves;
    private String name;
    private String city;
    private String rating;
    private String top;
    private int numFriends;
    private int numGroups;
    private int numReviews;
    private String photo;
    private int type; //0-я 1-мои друзья, 2-все остальные.
    private int id;

    public Profile(){
        name="Иванов Иван Петрович";
        city="Москва, Россия";
        rating="4.75";
        top="75";
        numSaved=1;
        numSaves=5;
        numFriends=251;
        numGroups=14;
        numReviews=125;
        photo=null;
        type=0;
        id=132;

    }
    public Profile(int type){
        name="Иванов Иван Иванович";
        city="Москва, Россия";
        rating="4.75";
        top="75";
        numFriends=251;
        numGroups=14;
        numReviews=125;
        photo=null;
        this.type=type;
        id= 25;

    }

    public Profile(String name, String city,String rating, String top, int numFriends, int numGroups, int numReviews,int numSaved,int numSaves, String photo,int type,int id){
        this.name=name;
        this.city=city;
        this.rating=rating;
        this.top=top;
        this.numFriends=numFriends;
        this.numGroups=numGroups;
        this.numReviews=numReviews;
        this.photo=photo;
        this.type=type;
        this.id=id;
        this.numSaved=numSaved;
        this.numSaves=numSaves;

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

    public String getNumFriends() {
        return Integer.toString(numFriends);
    }

    public String getNumGroups() {
        return Integer.toString(numGroups) ;
    }

    public String getNumReviews() {
        return Integer.toString(numReviews);
    }

    public String getPhoto() {
        return photo;
    }
    public int getType(){
        return type;
    }

    public int getId() {
        return id;
    }

    public int getNumSaved() {
        return numSaved;
    }


    public int getNumSavesI() {
        return numSaves;
    }
    public String getNumSavesS(){
        return Integer.toString(numSaves);
    }
    public String getNumSavedS(){
        return Integer.toString(numSaved);
    }
}
