package ch.hevs.alexpira.util;


public interface OnAsyncEventListener {
    void onSuccess();
    void onFailure(Exception e);
}
