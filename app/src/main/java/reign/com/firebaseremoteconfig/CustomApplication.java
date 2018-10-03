package reign.com.firebaseremoteconfig;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abhishek on 10/3/2018.
 */

public class CustomApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        final FirebaseRemoteConfig remoteConfig=FirebaseRemoteConfig.getInstance();

        Map<String,Object> defaultValue=new HashMap<>();
        defaultValue.put(MyHelper.KEY_UPDATE_ENABLE,false);
        defaultValue.put(MyHelper.KEY_UPDATE_VER,"1.0");
        defaultValue.put(MyHelper.KEY_UPDATE_URL,"app_url");

        remoteConfig.setDefaults(defaultValue);
        remoteConfig.fetch(5)// 5 seconds
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    remoteConfig.activateFetched();
                }
            }
        });

    }
}
