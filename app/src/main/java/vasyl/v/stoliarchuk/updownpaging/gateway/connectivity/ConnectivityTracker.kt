package vasyl.v.stoliarchuk.updownpaging.gateway.connectivity

import io.reactivex.Flowable



interface ConnectivityTracker {
    fun listenConnectivityState(): Flowable<Boolean>
}