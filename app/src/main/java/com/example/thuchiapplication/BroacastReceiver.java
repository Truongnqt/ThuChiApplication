package com.example.thuchiapplication;
import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.widget.Toast;

public class BroacastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Check network connectivity status
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            // Network is connected
            Toast.makeText(context, "Network is connected", Toast.LENGTH_SHORT).show();
        } else {
            // Network is disconnected
            Toast.makeText(context, "Network is disconnected", Toast.LENGTH_SHORT).show();
        }
    }
}
