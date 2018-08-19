package vasyl.v.stoliarchuk.updownpaging.gateway.connectivity

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import com.cantrowitz.rxbroadcast.RxBroadcast
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import vasyl.v.stoliarchuk.updownpaging.common.utils.DeviceUtils


class AndroidConnectivityTracker (private val context: Context,
                                  private val deviceUtils: DeviceUtils): ConnectivityTracker {

    private val intentFilter:IntentFilter = IntentFilter()
    init{
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
    }

    override fun listenConnectivityState(): Flowable<Boolean> {
        return RxBroadcast.fromBroadcast(context, intentFilter)
                .map({deviceUtils.isAnyNetworkAvailable()})
                .toFlowable(BackpressureStrategy.MISSING)
    }
}