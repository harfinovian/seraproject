package com.example.harfi.seraproject.utils;

import android.support.annotation.NonNull;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import rx.Observable;
import rx.subscriptions.Subscriptions;

/**
 * Created by harfi on 5/18/2017.
 */
public class RxFirebaseAuth {

    @NonNull
    public static Observable<AuthResult> signInWithEmailAndPassword(@NonNull final FirebaseAuth firebaseAuth,
                                                                    @NonNull final String email,
                                                                    @NonNull final String password) {
        return Observable.create(subscriber -> RxHandler.assignOnTask(subscriber, firebaseAuth.signInWithEmailAndPassword(email, password)));
    }

    @NonNull
    public static Observable<FirebaseUser> observeAuthState(@NonNull final FirebaseAuth firebaseAuth) {

        return Observable.create(subscriber -> {
            final FirebaseAuth.AuthStateListener authStateListener = firebaseAuth1 -> {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(firebaseAuth1.getCurrentUser());
                }
            };
            firebaseAuth.addAuthStateListener(authStateListener);

            subscriber.add(Subscriptions.create(() -> firebaseAuth.removeAuthStateListener(authStateListener)));
        });
    }
}
