package hm.viruchatel.Api;

import android.graphics.Bitmap;

/**
 * Created by Дмитрий on 04.06.2015.
 */
public interface ApiInterface {

     boolean loginNative(String email, String pass)throws InterruptedException;
    /**
     *вход через соц сети: VK, FB
     */
    void loginSocial();

    /**
     *восстановление пароля
     */
    void restorePassword();

    /**
     *смена пароля
     */
    void changePassword();

    /**
     * регистрация по данным: email, пароль, фамилия, имя, пол, телефон, фото, город, улица, дом
     */
    boolean registerNative(String email, String pass, String fio, int gender,
                               String phone, String photo, String city,String street, String house)throws InterruptedException;
    /**
     *регистрация через VK, FB
     */

    void registerSocial();

    /**
     *получение списка событий о помощи
     */
    void getEventsList();

    /**
     *Подать сигнал тревоги с параметрами: заголовок, текст сообщения, тип (видно всем\видно друзьям)
     */
    void setAlarmMessage(String title, String message, int flag);

    /**
     *Сообщения  - получить список чатов
     */
    void getChatsList();

    /**
     *Сообщения- удалить  чат
     */
    void delChat();

        /**
         *- получить список сообщений в чате
         */
    void getMessages();

    /**
     *Друзья  -  получить список друзей (состоит из каких описанию юзеров)
     */
    void getFriends();
    /**
     * - удалить друга
     */
    void delFriend();
    /**
     *  - принять предложение быть другом
     */
    void confirmFriend();
    /**
     *  - отправить запрос на дружбу
     */
    void sendFriendRequest();
    /**
     *  - отклонить запрос дружбы
     */
    void rejectFriendRequest();

    /**
     * Группы - получить список групп любого юзера
     */
    void getUserGroups();

    /**
     *Группы  - получить список групп по имени группы (может быть пустым)
     */
    void getGroups();

    /**
     *Группы  - получить полное описание группы
     */
    void getGroup();

    /**
     * Профиль юзера
     - получить полное описание профиля любого юзера
     */
    void getUserProfile();
    /**
     * Профиль юзера  - получить список отзывов о юзеру
     */
    void getUserReviewList();

    /**
     *Профиль юзера- добавить  отзыв с параметрами - оценка, отзыв
     */
    void addUserReview();
    /**
     *Профиль юзера-получить список друзей юзера
     */
    void getUsersFriends();

    /**
     *Профиль юзера-получить список групп юзера
     */
    void getUsersGroups();


    /**
     *  О программе
     получить описание - о программе
     */
    void getAbout();
}
