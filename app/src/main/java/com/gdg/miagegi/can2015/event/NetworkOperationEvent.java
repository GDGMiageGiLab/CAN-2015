package com.gdg.miagegi.can2015.event;

public class NetworkOperationEvent {

    final public static int HAS_FAILED = -1;
    final public static int HAS_FINISHED_ALL = 10;
    final public static int HAS_FINISHED_ONE = 1;
    final public static int HAS_STARTED = 0;

    private String mMessage;
    private int mStatus;
    public int mPosition;

    public NetworkOperationEvent(int status) {
        this.mStatus = status;
    }
    
    /*public NetworkOperationEvent(int status, int position){
    	this.mStatus=status;
    	this.mPosition=position;
    }*/

    public NetworkOperationEvent(int status, String message) {
        this.mStatus = status;
        this.mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    public boolean hasFailed() {
        return (mStatus == HAS_FAILED);
    }

    public boolean hasFinishedAll() {
        return (mStatus == HAS_FINISHED_ALL);
    }

    public boolean hasFinishedOne() {
        return (mStatus == HAS_FINISHED_ONE);
    }

    public boolean hasStarted() {
        return (mStatus == HAS_STARTED);
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }
}
