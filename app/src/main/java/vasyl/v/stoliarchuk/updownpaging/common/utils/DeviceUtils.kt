package vasyl.v.stoliarchuk.updownpaging.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings.Secure


class DeviceUtils(private val context: Context) {

    @SuppressLint("HardwareIds")
    fun getSecureDeviceId() = Secure.getString(context.contentResolver, Secure.ANDROID_ID)

}