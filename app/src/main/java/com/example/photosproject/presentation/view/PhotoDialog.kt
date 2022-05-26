package com.example.photosproject.presentation.view

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.photosproject.R
import com.example.photosproject.databinding.DialogPhotoBinding
import timber.log.Timber
import java.util.concurrent.Executors

class PhotoDialog : DialogFragment() {

    private val args by navArgs<PhotoDialogArgs>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(createView())
            .create()
            .apply { window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) }
    }

    private fun createView(): View {
        val binding = DialogPhotoBinding.inflate(layoutInflater, null, false)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap?

        executor.execute {
            val imageUrl = args.photo

            try {
                val x = java.net.URL(imageUrl).openStream()
                image = BitmapFactory.decodeStream(x)

                handler.post {
                    binding.photoImageView.setImageBitmap(image)
                }
            } catch (e: Exception) {
                handler.post {
                    binding.photoImageView.setImageResource(R.drawable.ic_baseline_image_24)
                }
                Timber.v("Something went wrong while loading the pic. $e")
            }
        }

        return binding.root
    }
}