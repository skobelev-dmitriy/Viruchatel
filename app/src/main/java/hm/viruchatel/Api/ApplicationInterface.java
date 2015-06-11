package hm.viruchatel.Api;

/**
 * Created by Дмитрий on 05.06.2015.
 */
public interface ApplicationInterface {

    void  sendAlarm(String caption,String message, int type);
    void  addInFriends();
    void openInviteFriends();
    void  sendMessage();
    void  savesMe();
    void openFriends();
    void acceptFriend();
    void rejectFriend();
    void openGroup(String title,int id);
    void openGroups();
    void openReviews();
    void openFullHelpRequest();
    void orderGPS();
    void openLinkGPS();
    void linkGPS(String number);
    void openUserInfo();
    void takePhoto();
    void openImage();
    void openChangePass();
    void openNewReview();
    void openChat();

    void openProfile(int id);


}
