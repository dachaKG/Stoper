package stoper.stoper.chat.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

import stoper.stoper.R;
import stoper.stoper.activities.CommentActivity;
import stoper.stoper.activities.NavigationActivity;
import stoper.stoper.chat.events.PushNotificationEvent;
import stoper.stoper.chat.ui.activity.ChatActivity;
import stoper.stoper.chat.utils.Constants;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    private NotificationManager notificationManager;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            if(remoteMessage.getData().get("type").equals("rezervacija")) {
                Intent notificationIntent = new Intent(this, NavigationActivity.class);
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                final PendingIntent pendingIntent = PendingIntent.getActivity(this,
                        0 /* Request code */, notificationIntent,
                        PendingIntent.FLAG_ONE_SHOT);
                //You should use an actual ID instead
                int notificationId = new Random().nextInt(60000);
                //Bitmap bitmap = getBitmapfromUrl(remoteMessage.getData().get("image-url"));
        /*Intent likeIntent = new Intent(this,LikeService.class);
        likeIntent.putExtra(NOTIFICATION_ID_EXTRA,notificationId);
        likeIntent.putExtra(IMAGE_URL_EXTRA,remoteMessage.getData().get("image-url"));
        PendingIntent likePendingIntent = PendingIntent.getService(this,
                notificationId+1,likeIntent,PendingIntent.FLAG_ONE_SHOT);*/
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    setupChannels();
                }
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(this, "default")
                                //.setLargeIcon(bitmap)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle(remoteMessage.getNotification().getTitle())
                                .setStyle(new NotificationCompat.BigPictureStyle()
                                        .setSummaryText(remoteMessage.getData().get("message")))
                                //.bigPicture(bitmap))/*Notification with Image*/
                                .setContentText(remoteMessage.getNotification().getBody().toString())
                                .setAutoCancel(true)
                                .setSound(defaultSoundUri)
                                .setContentIntent(pendingIntent);
                notificationManager.notify(notificationId, notificationBuilder.build());
                return;
            }else if(remoteMessage.getData().get("type").equals("ocena")){
                Intent notificationIntent = new Intent(this, CommentActivity.class);
                notificationIntent.putExtra("korisnikZaOcenjivanje",remoteMessage.getData().get("mailOcenjivanjog"));
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                final PendingIntent pendingIntent = PendingIntent.getActivity(this,
                        0 /* Request code */, notificationIntent,
                        PendingIntent.FLAG_ONE_SHOT);
                //You should use an actual ID instead
                int notificationId = new Random().nextInt(60000);
                //Bitmap bitmap = getBitmapfromUrl(remoteMessage.getData().get("image-url"));
        /*Intent likeIntent = new Intent(this,LikeService.class);
        likeIntent.putExtra(NOTIFICATION_ID_EXTRA,notificationId);
        likeIntent.putExtra(IMAGE_URL_EXTRA,remoteMessage.getData().get("image-url"));
        PendingIntent likePendingIntent = PendingIntent.getService(this,
                notificationId+1,likeIntent,PendingIntent.FLAG_ONE_SHOT);*/
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    setupChannels();
                }
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(this, "default")
                                //.setLargeIcon(bitmap)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle(remoteMessage.getNotification().getTitle())
                                .setStyle(new NotificationCompat.BigPictureStyle()
                                        .setSummaryText(remoteMessage.getData().get("message")))
                                //.bigPicture(bitmap))/*Notification with Image*/
                                .setContentText(remoteMessage.getNotification().getBody().toString())
                                .setAutoCancel(true)
                                .setSound(defaultSoundUri)
                                .setContentIntent(pendingIntent);
                notificationManager.notify(notificationId, notificationBuilder.build());
                return;
            }

            String title = remoteMessage.getData().get("title");
            String message = remoteMessage.getData().get("text");
            String username = remoteMessage.getData().get("username");
            String uid = remoteMessage.getData().get("uid");
            String fcmToken = remoteMessage.getData().get("fcm_token");

            // Don't show notification if chat activity is open.
            if (!NavigationActivity.isChatActivityOpen()) {
                sendNotification(title,
                        message,
                        username,
                        uid,
                        fcmToken);
            } else {
                EventBus.getDefault().post(new PushNotificationEvent(title,
                        message,
                        username,
                        uid,
                        fcmToken));
            }
        }
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     */
    private void sendNotification(String title,
                                  String message,
                                  String receiver,
                                  String receiverUid,
                                  String firebaseToken) {
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.putExtra(Constants.ARG_RECEIVER, receiver);
        intent.putExtra(Constants.ARG_RECEIVER_UID, receiverUid);
        intent.putExtra(Constants.ARG_FIREBASE_TOKEN, firebaseToken);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_messaging)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels(){
        NotificationChannel channel = new NotificationChannel("default",
                "Channel name",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Channel description");
        notificationManager.createNotificationChannel(channel);
    }
}
