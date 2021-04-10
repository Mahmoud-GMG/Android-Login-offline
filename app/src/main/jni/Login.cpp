#include <jni.h>
#include <string>
#include "Includes/obfuscate.h"
#include "Includes/Logger.h"
#include "Includes/Utils.h"

bool featuresEnabled = false;
bool loginEnabled = false;
bool login2Enabled = false;

extern "C" JNIEXPORT jboolean JNICALL
Java_yt_mahmoudgaming_login_LoginForm_Check(JNIEnv *env, jclass clazz, jstring user,
                                                  jstring pass) {
    const char *userStr = env->GetStringUTFChars(user, 0);
    const char *passStr = env->GetStringUTFChars(pass, 0);

    // Here you can implement server checks

    // This is just an example
    // You should not check username and password locally as it can be easly crackable
    // This implementation is for peoples who have programming skills in Java, C++ and php and can protect/encrypt the lib
    if (strcmp(userStr, OBFUSCATE("Mahmoud Alaa")) == 0 && strcmp(passStr, OBFUSCATE("ModByMahmoudGaming")) == 0) {
        featuresEnabled = true;
        return true;
    }
    return false;
}
extern "C" {
JNIEXPORT jstring
JNICALL
Java_yt_mahmoudgaming_login_LoginForm_Login(JNIEnv *env, jobject thiz) {
    loginEnabled = true;

    //Html is supported
    return env->NewStringUTF(OBFUSCATE("Modded by (Mahmoud Gaming)"));
}
}
extern "C" {
JNIEXPORT jstring
JNICALL
Java_yt_mahmoudgaming_login_LoginForm_Login2(JNIEnv *env, jobject thiz) {
    login2Enabled = true;

    //Html is supported
    return env->NewStringUTF(OBFUSCATE("∆ Please login First to Play Game | Mod Menu By Mahmoud Gaming ∆"));
}
}
