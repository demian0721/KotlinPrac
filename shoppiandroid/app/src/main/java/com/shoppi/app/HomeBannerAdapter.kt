package com.shoppi.app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.DecimalFormat
import kotlin.math.roundToInt

class HomeBannerAdapter :
    ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(BannerDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_banner, parent, false)
        return HomeBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeBannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val bannerImageView = view.findViewById<ImageView>(R.id.iv_banner_image)
        private val bannerBadgeTextView = view.findViewById<TextView>(R.id.tv_banner_badge)
        private val bannerTitleTextView = view.findViewById<TextView>(R.id.tv_banner_title)
        private val bannerDetailBrandLabelTextView =
            view.findViewById<TextView>(R.id.tv_banner_detail_brand_label)
        private val bannerDetailThumbnailImageView =
            view.findViewById<ImageView>(R.id.iv_banner_detail_thumbnail)
        private val bannerDetailProductLabelTextView =
            view.findViewById<TextView>(R.id.tv_banner_detail_product_label)
        private val bannerDetailProductDiscountRateTextView =
            view.findViewById<TextView>(R.id.tv_banner_detail_product_discount_rate)
        private val bannerDetailProductDiscountPriceTextView =
            view.findViewById<TextView>(R.id.tv_banner_detail_product_discount_price)
        private val bannerDetailPriceTextView =
            view.findViewById<TextView>(R.id.tv_banner_detail_product_price)

        fun bind(banner: Banner) {
            loadImage(banner.backgroundImageUrl, bannerImageView)
            bannerBadgeTextView.text = banner.badge.label
            bannerBadgeTextView.background =
                ColorDrawable(Color.parseColor(banner.badge.backGroundColor))
            bannerTitleTextView.text = banner.label
            loadImage(banner.productDetail.thumbnailImageUrl, bannerDetailThumbnailImageView)
            bannerDetailBrandLabelTextView.text = banner.productDetail.brandName
            bannerDetailProductLabelTextView.text = banner.productDetail.label
            bannerDetailProductDiscountRateTextView.text = "${banner.productDetail.discountRate}%"
            val decimalFormat = DecimalFormat("#,###")
            val discountPrice =
                (banner.productDetail.price * (banner.productDetail.discountRate * 0.01)).roundToInt()
            bannerDetailProductDiscountPriceTextView.text =
                "${decimalFormat.format(banner.productDetail.price - discountPrice)}원"
            bannerDetailPriceTextView.text = "${decimalFormat.format(banner.productDetail.price)}원"
        }

        private fun loadImage(urlString: String, imageView: ImageView) {
            Glide.with(itemView)
                .load(urlString)
                .into(imageView)
        }
    }
}

class BannerDiffCallback : DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.productDetail.productId == newItem.productDetail.productId
    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }

}