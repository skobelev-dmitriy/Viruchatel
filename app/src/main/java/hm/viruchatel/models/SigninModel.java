package hm.viruchatel.models;

import android.database.Observable;
import android.os.AsyncTask;
import android.util.Log;

import hm.viruchatel.Api.Api;

/**
 * Created by Дмитрий on 04.06.2015.
 */
public class SigninModel {
    private static final String TAG = "LoginModel";
    private boolean mIsWorking;
    private SigninTask signinTask;
    private final LoginObservable mObservable=new LoginObservable();

    public SigninModel(){

    }
    public void signin(String email, String pass, String fio, int gender, String phone){
        if (mIsWorking){
            return;
        }
        mObservable.notifyStarted();
        mIsWorking=true;
        signinTask=new SigninTask(email, pass, fio, gender, phone,null,null,null,null);
        signinTask.execute();
    }

    public void stopSignin(){
        if(mIsWorking){
            signinTask.cancel(true);
            mIsWorking=false;
        }

    }

    public void registerObserver(final Observer observer){
        mObservable.registerObserver(observer);
        if (mIsWorking){
            observer.onSignInStarted(this);
        }
    }

    public void unregisterObserver(final Observer observer){}

    private class SigninTask extends AsyncTask<Void, Void, Boolean> {
        private String mEmail;
        private String mPassword;
        private String mFio;
        private int mGender;
        private String mPhone;
        private String mPhoto;
        private String mCity;
        private String mStreet;
        private String mHouse;

        public SigninTask(String email, String pass, String fio, int gender,
                           String phone, String photo, String city,String street, String house) {
            mEmail = email;
            mPassword = pass;
            mFio=fio;
            mGender=gender;
            mPhone=phone;
            mPhoto=photo;
            mCity=city;
            mStreet=street;
            mHouse=house;

        }

        @Override
        protected Boolean doInBackground(final Void... params) {
            final Api communicator = new Api();

            try {
                return communicator.registerNative(mEmail, mPassword, mFio,mGender, mPhone, mPhoto, mCity,mStreet, mHouse);
            } catch (InterruptedException e) {
                Log.d(TAG, "Sign in interrupted");
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mIsWorking = false;

            if (success) {
                mObservable.notifySucceeded();
            } else {
                mObservable.notifyFailed();
            }
        }
    }

    public interface Observer {
        void onSignInStarted(SigninModel mSigninModel);

        void onSignInSucceeded(SigninModel mSigninModel);

        void onSignInFailed(SigninModel mSigninModel);
    }

    private class LoginObservable extends Observable<Observer> {
        public void notifyStarted() {
            for (final Observer observer : mObservers) {
                observer.onSignInStarted(SigninModel.this);
            }
        }

        public void notifySucceeded() {
            for (final Observer observer : mObservers) {
                observer.onSignInSucceeded(SigninModel.this);
            }
        }

        public void notifyFailed() {
            for (final Observer observer : mObservers) {
                observer.onSignInFailed(SigninModel.this);
            }
        }
    }
}
