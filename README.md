# FirebaseRemoteConfig
Implement Firebase Remote Config in Android app Save the latest version code of app in Remote config panel Fetch that value from Remote Config and check if it is greater then current app version code or not, if it is then prompt the user to update the app. Otherwise, the app is already updated.


Just Create your firebase project in firebase Console and in Under Grow Section in Remote Config just put three Parameters with there default values-
1-update_url=app url
2-version=1.0
3-isUpdate=false

