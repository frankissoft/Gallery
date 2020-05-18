package edu.vt.cs.cs5254.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.vt.cs.cs5254.gallery.api.GalleryItem

class PhotoGalleryViewModel : ViewModel() {

    val galleryItemsLiveData: LiveData<List<GalleryItem>> = FlickrFetchr.responseLiveData

    fun loadPhotos() {
        FlickrFetchr.fetchPhotos(false)
    }
}