package com.example.harfi.seraproject.exceptions;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseError;

/**
 * Created by harfi on 5/18/2017.
 */
public class RxFirebaseDataException extends Exception {

    protected DatabaseError error;

    public RxFirebaseDataException(@NonNull DatabaseError error) {
        this.error = error;
    }

    public DatabaseError getError() {
        return error;
    }

    @Override
    public String toString() {
        return "RxFirebaseDataException{" +
                "error=" + error +
                '}';
    }
}
