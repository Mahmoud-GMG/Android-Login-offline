# Android-Login-Offline

Simple login form in Java by Mahmoud Gaming.
I wanted to upload this project long time ago.
This project is for experienced modders and programmers only and must be able to search on Google for help.
I will not explain here.
Project based on Lgl team login.
Full offline not php .
Password and Username in jni and they are OBFUSCATED to avoid copy pasters.
Keep in mind that it is mainly for APK modding and not for general app development purposes.

# Owner : Mahmoud Gaming  

https://youtube.com/c/MahmoudGamingChannel 

# Screenshot  

![](https://i.imgur.com/s51xDuz.jpg) 

# How to implement to APK:

Build the project to APK file.

**Build** -> **Build Bundle(s)/APK(s)** -> **Build APK(s)**

If no errors occured, you did everything right and build will succeded. You will be notified that it build successfully

![](https://i.imgur.com/WpSKV1L.png)

Click on **locate** to show you the location of **build.apk**. It is stored at `(your-project)\app\build\outputs\apk\app-debug.apk`

![](https://i.imgur.com/wBTPSLi.png) 

Now decompile your **app-debug.apk**.

Copy all your smali folders to the game's smali folder to the game's decompiled directory `(game name)\smali`. If the game have multidexes, add your smali to the last classes dex if possible to prevent compilation errors such as `Unsigned short value out of range: xxxxx`

Now, you must know the game's launch activity/main activity

You can either use APK Easy Tool to view main activity like screenshot below:

![](https://i.imgur.com/JQdPjyZ.png)

Or decompile your **game APK** using apktool.jar, or any 3rd party CLI or GUI tools, and open `androidmanifest.xml` and search after `<action android:name="android.intent.action.MAIN"/>`
In this case, my game's main activity was `com.unity3d.player.UnityPlayerActivity`

![](https://i.imgur.com/FfOtc1K.png)

On the game's `androidmanifest.xml`, besure that `<uses-permission android:name="android.permission.INTERNET"/>` exist

![](https://i.imgur.com/k0sLVUF.png)

Locate to the game's path of main activity and open the **smali** file.

Example if it was `com.unity3d.player.UnityPlayerActivity`, the path would be `(decompiled game)/com/unity3d/player/UnityPlayerActivity.smali`

If the game have multi dexes, it may be located in smali_classes2... please check all

Open the main acitivity's smali file, search for OnCreate method and paste this code inside (change the package name if you had changed it)
```
invoke-static {p0}, Lyt/mahmoudgaming/login/LoginForm;->Start(Landroid/content/Context;)V
```

![](https://i.imgur.com/yjsAaHD.png)

Save the file

Now compile the game APK

It should launch the login screen and successfully launch the activity after login :)
