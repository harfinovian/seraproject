package com.example.harfi.seraproject.exceptions;

import android.support.annotation.NonNull;

/**
 * Created by harfi on 5/18/2017.
 */
public class RxFirebaseDataCastException extends Exception {

    public RxFirebaseDataCastException() {
    }

    public RxFirebaseDataCastException(@NonNull String detailMessage) {
        super(detailMessage);
    }

    public RxFirebaseDataCastException(@NonNull String detailMessage, @NonNull Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RxFirebaseDataCastException(@NonNull Throwable throwable) {
        super(throwable);
    }
}
