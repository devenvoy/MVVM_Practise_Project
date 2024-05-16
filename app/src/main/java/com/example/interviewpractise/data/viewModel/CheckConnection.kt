package com.example.interviewpractise.data.viewModel

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.telephony.TelephonyManager
import android.util.Log
import androidx.lifecycle.LiveData
import javax.inject.Inject


class CheckConnection @Inject constructor(private var connectivityManager: ConnectivityManager) :
    LiveData<Boolean>() {


    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()


//    constructor(application: Application) : this(
//        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    )

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)

            var isWifiConn: Boolean = false
            var isMobileConn: Boolean = false
            connectivityManager.allNetworks.forEach { net ->
                connectivityManager.getNetworkInfo(net)?.apply {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        isWifiConn = isWifiConn or isConnected
                    }
                    if (type == ConnectivityManager.TYPE_MOBILE && subtype != TelephonyManager.NETWORK_TYPE_UNKNOWN) {
                        isMobileConn = isMobileConn or isConnected
                    }
                }
            }
            Log.d("-----", "Wifi connected: $isWifiConn")
            Log.d("-----", "Mobile connected: $isMobileConn")
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

    }


    init {
        postValue(false)
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }


    override fun onActive() {
        super.onActive()
        val request = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(request.build(), networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }


    fun isOnline(): Boolean {
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}