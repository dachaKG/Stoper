package stoper.stoper.fragments;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import stoper.stoper.MainActivity;
import stoper.stoper.R;
import stoper.stoper.activities.NavigationActivity;
import stoper.stoper.util.MyBroadcastReceiver;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button b=view.findViewById(R.id.buttonOffer_id);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (Build.VERSION.SDK_INT > 26) {
                    NotificationManager notificationManager =
                            (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationChannel channel = new NotificationChannel("default",
                            "Channel name",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    channel.setDescription("Channel description");
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getContext(), "default");
                notificationBuilder.setSmallIcon(R.drawable.diavolo);
                notificationBuilder.setContentTitle("Notification Alert, Click Me!");
                notificationBuilder.setContentText("Hi, This is Android Notification Detail!");
                notificationBuilder.setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationManager.IMPORTANCE_LOW);
                notificationBuilder.setAutoCancel(true);

                Intent i = new Intent(getActivity().getApplicationContext(), MyBroadcastReceiver.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pi = PendingIntent.getService(getActivity().getApplicationContext(), 0, i, 0);
                //notificationBuilder.setContentIntent(pi);
                try {
                    pi.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getContext(), NavigationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);
                notificationBuilder.setDeleteIntent(pendingIntent);
                //notificationBuilder.build();
                NotificationManager mNotificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                // notificationID allows you to update the notification later on.
                mNotificationManager.notify(1, notificationBuilder.build());*/
                Intent intent = new Intent(getContext(), MyBroadcastReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getService(getContext(), 0, intent, 0);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    Log.i("sda","asffas");
                    e.printStackTrace();
                }
            }
        });
    }
}
