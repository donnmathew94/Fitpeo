package com.don.fitpeo.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.don.fitpeo.R
import com.don.fitpeo.constants.Constants
import com.don.fitpeo.databinding.ActivityItemDetailBinding
import com.don.fitpeo.models.ResponseItem
import com.squareup.picasso.Picasso


class ItemDetailActivity : AppCompatActivity() {
    private val TAG = "ItemDetailActivity"
    private lateinit var dataBinding: ActivityItemDetailBinding
    private var responseData: ResponseItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail)
        setSupportActionBar( dataBinding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setBindings(dataBinding)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setBindings(dataBinding: ActivityItemDetailBinding?) {
        responseData =
            (intent.getParcelableExtra<Parcelable>(Constants.DATA_KEY) as ResponseItem?)!!
        dataBinding?.item = responseData

    }


    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: View, imageUrl: String?) {
            val image: ImageView = view as ImageView
            Picasso.get().load(imageUrl).into(image);
        }
    }
}