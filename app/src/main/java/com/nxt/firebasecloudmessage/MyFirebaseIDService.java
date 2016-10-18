package com.nxt.firebasecloudmessage;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by NXT on 14/10/2016.
 */

public class MyFirebaseIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token= FirebaseInstanceId.getInstance().getToken();
        Log.d("tokennnnnn",token);

        luuTokenVaoCSDL(token);
    }

    private void luuTokenVaoCSDL(String token){
        new FireBaseIDTask().execute(token);
    }
}
