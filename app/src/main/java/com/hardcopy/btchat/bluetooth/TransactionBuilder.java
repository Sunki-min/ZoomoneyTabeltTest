package com.hardcopy.btchat.bluetooth;

import android.os.Handler;
import android.util.Log;
/**
 * Created by sunki on 2018-01-23.
 */

public class TransactionBuilder {
    private static final String TAG = "TransactionBuilder";

    private BluetoothManager mBTManager = null;
    private Handler mHandler = null;

    public TransactionBuilder(BluetoothManager bm, Handler errorHandler) {
        mBTManager = bm;
        mHandler = errorHandler;
    }

    public Transaction makeTransaction() {
        return new Transaction();
    }

    public class Transaction {

        public static final int MAX_MESSAGE_LENGTH = 16;

        // Transaction instance status
        private static final int STATE_NONE = 0;		// Instance created
        private static final int STATE_BEGIN = 1;		// Initialize transaction
        private static final int STATE_SETTING_FINISHED = 2;	// End of setting parameters


        // Transaction parameters
        private int mState = STATE_NONE;
        private byte[] mBuffer = null;
        private String mMsg = null;


        public void begin() {
            mState = STATE_BEGIN;
            mMsg = null;
            mBuffer = null;
        }

        public void setMessage(String msg) {
            // TODO: do what you want
            mMsg = msg;
        }

        public void settingFinished() {
            mState = STATE_SETTING_FINISHED;
            mBuffer = mMsg.getBytes();
        }


        public boolean sendTransaction() {
            if(mBuffer == null || mBuffer.length < 1) {
                Log.e(TAG, "##### Ooooooops!! No sending buffer!! Check command!!");
                return false;
            }

            // TODO: For debug. Comment out below lines if you want to see the packets
            return false;
        }

        public byte[] getPacket() {
            if(mState == STATE_SETTING_FINISHED) {
                return mBuffer;
            }
            return null;
        }

    }	// End of class Transaction
}
