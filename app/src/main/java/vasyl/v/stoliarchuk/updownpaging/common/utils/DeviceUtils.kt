package vasyl.v.stoliarchuk.updownpaging.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.provider.Settings.Secure


class DeviceUtils(private val context: Context) {

    @SuppressLint("HardwareIds")
    fun getSecureDeviceId() = Secure.getString(context.contentResolver, Secure.ANDROID_ID)

    /**
     * @return true if there is any (wifi|mobile) connection is active.
     */
    fun isAnyNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }
}