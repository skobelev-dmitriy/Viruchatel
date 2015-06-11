package hm.viruchatel.models;

/**
 * Created by Дмитрий on 06.06.2015.
 */
public class UserFriend {

    private String name;

    private String photo;

    private int id;

    public UserFriend(){
        name="Иванов Иван Иванович";

        photo=null;

        id=1;

    }

    public UserFriend(String name, String city, String rating, String top, int numFriends, int numGroups, int numReviews, int numSaved, int numSaves, String photo, boolean me, int id){
        this.name=name;

        this.photo=photo;

        this.id=id;


    }

    public String getName() {
        return name;
    }



    public String getPhoto() {
        return photo;
    }


    public int getId() {
        return id;
    }


}
