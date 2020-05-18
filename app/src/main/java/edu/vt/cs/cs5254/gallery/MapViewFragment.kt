package edu.vt.cs.cs5254.gallery

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker

private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
private const val TAG = "MapViewFragment"

open class MapViewFragment : Fragment() {

    protected lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView

    @Suppress("SameParameterValue")
    protected fun onCreateMapView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        mapLayoutResourceId: Int,
        mapViewResourceId: Int
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        val view = inflater.inflate(mapLayoutResourceId, container, false)
        mapView = view.findViewById(mapViewResourceId)
        mapView.onCreate(mapViewBundle)

        return view
    }

    fun onMapViewCreated(
        view: View,
        savedInstanceState: Bundle?,
        block: (GoogleMap) -> Unit
    ) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Setting up map")
        mapView.getMapAsync { map ->
            Log.d(TAG, "Receiving map fragment view")
            googleMap = map
            block.invoke(googleMap)
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    protected fun mapIsInitialized() = this::googleMap.isInitialized

    protected fun setMarkerIcon(
        marker: Marker,
        bitmap: Bitmap,
        dstWidth: Int = 200,
        dstHeight: Int = 200,
        filter: Boolean = false
    ) {
        val scaledBitmap =
            Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, filter)
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(scaledBitmap))
    }
}