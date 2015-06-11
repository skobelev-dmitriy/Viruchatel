package hm.viruchatel.models;

/**
 * Created by Дмитрий on 06.06.2015.
 */
public class Group {
    private String title;
    private String description;
    private String photo;
    private String num_users;
    private int id;

    public Group(){
        title="Новая группа";
        description="Мы спасаем бездомных котиков и делаем это абсолютно бесплатно. Нам небезразлична судьба бездамных животных.";
        num_users="125";
        id=32;

    }

    public int getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }

    public String getNum_users() {
        return num_users;
    }

    public String getTitle() {
        return title;
    }
}
