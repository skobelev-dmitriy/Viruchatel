package hm.viruchatel.models;

import android.database.Observable;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Observer;

import hm.viruchatel.Api.Api;

/**
 * Created by Дмитрий on 04.06.2015.
 */
public class LoginModel {
    private static final String TAG = "LoginModel";
    private boolean mIsWorking;
    private LoginTask loginTask;
    private final LoginObservable mObservable=new LoginObservable();

    public LoginModel(){

    }
    public void login(String email, String pass){
        if (mIsWorking){
            return;
        }
        mObservable.notifyStarted();
        mIsWorking=true;
        loginTask=new LoginTask(email, pass);
        loginTask.execute();
    }

    public void stopLogin(){
        if(mIsWorking){
            loginTask.cancel(true);
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

    private class LoginTask extends AsyncTask<Void, Void, Boolean> {
        private String mEmail;
        private String mPassword;

        public LoginTask(final String email, final String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(final Void... params) {
            final Api communicator = new Api();

            try {
                return communicator.loginNative(mEmail, mPassword);
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
        void onSignInStarted(LoginModel loginModel);

        void onSignInSucceeded(LoginModel loginModel);

        void onSignInFailed(LoginModel loginModel);
    }

    private class LoginObservable extends Observable<Observer> {
        public void notifyStarted() {
            for (final Observer observer : mObservers) {
                observer.onSignInStarted(LoginModel.this);
            }
        }

        public void notifySucceeded() {
            for (final Observer observer : mObservers) {
                observer.onSignInSucceeded(LoginModel.this);
            }
        }

        public void notifyFailed() {
            for (final Observer observer : mObservers) {
                observer.onSignInFailed(LoginModel.this);
            }
        }
    }
}
