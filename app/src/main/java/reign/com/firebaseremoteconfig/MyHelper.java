package reign.com.firebaseremoteconfig;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/**
 * Created by Abhishek on 10/3/2018.
 */

public class MyHelper {


    public interface onUpdateChecListner {

        void onUpdateCheckListner(String updateUlr);
    }


    /*Keys which we have put in firebase remote onfig*/
    public static final String KEY_UPDATE_ENABLE = "isUpdate";
    public static final String KEY_UPDATE_VER = "version";
    public static final String KEY_UPDATE_URL = "update_url";

    private onUpdateChecListner onUpdateChecListner;
    private Context context;


    public MyHelper(MyHelper.onUpdateChecListner onUpdateChecListner, Context context) {
        this.onUpdateChecListner = onUpdateChecListner;
        this.context = context;
    }


    public void check() {
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        if (remoteConfig.getBoolean(KEY_UPDATE_ENABLE)) {
            String currentVersion = remoteConfig.getString(KEY_UPDATE_VER);
            String appVersion = getAppVersion(context);
            String appUrl = remoteConfig.getString(KEY_UPDATE_URL);

            if (!TextUtils.equals(currentVersion, appVersion) && onUpdateChecListner != null) {
                onUpdateChecListner.onUpdateCheckListner(appUrl);

            }
        }
    }




    public static Builder with(Context context) {
        return new Builder(context);
    }




    private String getAppVersion(Context context) {

        String result = "";
        try {
            result = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            result = result.replaceAll("[a-zA-Z]|-", "");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }






    public static class Builder {


        private Context context;

        private onUpdateChecListner onUpdateChecListner;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder onUpdateCheck(onUpdateChecListner onUpdateChecListner) {

            this.onUpdateChecListner = onUpdateChecListner;
            return this;
        }


        public MyHelper build() {
            return new MyHelper(onUpdateChecListner, context);
        }


        public MyHelper check() {

            MyHelper myHelper = build();
            myHelper.check();
            return myHelper;
        }

    }

}
