package com.hardcopy.btchat.network;

/**
 * Created by DELL on 2016-12-01.
 */
public interface CallbackEvent<T> {
    public void onSuccess(T t);
    public void onError();
}
