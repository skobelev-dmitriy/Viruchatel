package hm.viruchatel.Api;

import android.graphics.Bitmap;

/**
 * Created by Дмитрий on 04.06.2015.
 */
public class Api implements ApiInterface{


    public Api(){

    }
/**
Авторизация
- вход по email и паролю
 **/@Override
    public boolean loginNative(String email, String pass) throws InterruptedException {
    String EMAIL="test@test.ru";
    String PASS="test";

    Thread.sleep(2000);
return EMAIL.equals(email)&&PASS.equals(pass);
}
/**
 *вход через соц сети: VK, FB
 */
    public void loginSocial(){}

/**
 *восстановление пароля
 */
    public void restorePassword(){}

/**
 *смена пароля
 */
    public void changePassword(){}

/**
 * регистрация по данным: email, пароль, фамилия, имя, пол, телефон, фото, город, улица, дом
 */
    public boolean registerNative(String email, String pass, String fio, int gender,
                               String phone, String photo, String city,String street, String house)throws InterruptedException{
        Thread.sleep(2000);
        return true;
    }
/**
 *регистрация через VK, FB
 */

    public void registerSocial(){

    }

/**
 *получение списка событий о помощи
 */
    public void getEventsList(){

    }

/**
 *Подать сигнал тревоги с параметрами: заголовок, текст сообщения, тип (видно всем\видно друзьям)
 */
    public void setAlarmMessage(String title, String message, int flag){

    }

/**
 *Сообщения  - получить список чатов
 */
    public void getChatsList(){

    }

/**
 *Сообщения- удалить  чат
 */
    public void delChat(){

    }
/**
 *- получить список сообщений в чате
 */
    public void getMessages(){

}

/**
 *Друзья  -  получить список друзей (состоит из каких описанию юзеров)
 */
    public void getFriends(){

}
/**
 * - удалить друга
 */
public void delFriend(){

}
/**
 *  - принять предложение быть другом
 */
public void confirmFriend(){

}
/**
 *  - отправить запрос на дружбу
  */
public void sendFriendRequest(){

}
/**
 *  - отклонить запрос дружбы
  */
public void rejectFriendRequest(){

}

/**
 * Группы - получить список групп любого юзера
  */
public void getUserGroups(){

}

/**
 *Группы  - получить список групп по имени группы (может быть пустым)
  */
public void getGroups(){

}

/**
 *Группы  - получить полное описание группы
  */
public void getGroup(){

}

/**
 * Профиль юзера
 - получить полное описание профиля любого юзера
 */
public void getUserProfile(){

}
/**
 * Профиль юзера  - получить список отзывов о юзеру
  */
public void getUserReviewList(){

}

/**
 *Профиль юзера- добавить  отзыв с параметрами - оценка, отзыв
 */
public void addUserReview(){

}

/**
 *Профиль юзера-получить список друзей юзера
 */
public void getUsersFriends(){

}

/**
 *Профиль юзера-получить список групп юзера
 */
public void getUsersGroups(){

}


/**
 *  О программе
 получить описание - о программе
 */
public void getAbout(){

}



   /**     данные желательно присылать везде в одинаковом формате

    *    описание отзыва
        - оценка
        - отзыв
        - краткое описание того, кто оставил отзыв

        краткое описание группы
        - картинка
        - заголовок
        - последнее сообщение
        - число людей в группе

        полное описание группы
        - картинка
        - заголовок
        - последнее сообщение
        - число людей в группе
        - краткое описание юзера - админа группы
        - случайных 4 или меньше участников группы (их краткие описания)
        - список сообщений в группе

        сообщение в группе
        - краткое описание юзера, который написал сообщение
        - дата и время
        - сообщение

        данные по чату
        - дата и время
        - последнее сообщение в чате
        - краткие данные по собеседнику

        данные по событию
        - адрес
        - дата и время (timestamp)
        - заголовок
        - описание
        - краткие данные по юзеру, который отправил событие
        - расстояние до места происшествия
        - координаты (широта и долгота)

        краткие данные:
        - фото
        - имя
        - фамилия
        - друг мне или нет
        - гость в городе или нет

        полные данные:
        - фото
        - имя
        - фамилия
        - друг мне или нет
        - гость в городе или нет
        - сколько человек спас
        - сколько ра спасен
        - адрес
        - рейтинг
        - место в топе
        - число друзей
        - число групп
        - число отзывов
        - число непрочитанных сообщений
        - число запросов на добавление в друзья
        - число людей, которые ждут помощи
*/
}