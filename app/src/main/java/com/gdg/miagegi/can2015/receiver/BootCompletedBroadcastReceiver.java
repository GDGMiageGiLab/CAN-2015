
package com.gdg.miagegi.can2015.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gdg.miagegi.can2015.service.RefreshService;


public class BootCompletedBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    	
            context.startService(new Intent(context, RefreshService.class));
        
        
    }

}
