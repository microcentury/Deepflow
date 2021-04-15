package com.flanaganlabs.deepflow.ui.adapter

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageView
import android.widget.TextView
import android.widget.TextView.BufferType
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flanaganlabs.deepflow.R
import com.flanaganlabs.deepflow.data.model.Affirmation
import com.flanaganlabs.deepflow.ui.custom.CustomSpannable
import com.flanaganlabs.deepflow.utils.Constants


/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays [Affirmation] data object.
 */
class ItemAdapter(
    private val context: Context,
    private val dataset: MutableList<Affirmation>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewHead: TextView = view.findViewById(R.id.item_head)
        val textViewBody: TextView = view.findViewById(R.id.item_body)

        //val textViewSource: TextView = view.findViewById(R.id.item_source)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textViewHead.text = item.heading
        holder.textViewBody.text = item.body

        makeTextViewResizable(holder.textViewBody, Constants.NUMBER_OF_CHARACTERS_TO_DISPLAY, "...Read more", true);

        //holder.imageView.setImageResource(item.url)
        Glide.with(context)
            .load(item.url)
            .into(holder.imageView)
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size

    private fun addClickablePartTextViewResizable(
        strSpanned: Spanned, tv: TextView, spanableText: String, viewMore: Boolean
    ): SpannableStringBuilder {

        val str = strSpanned.toString()
        val ssb = SpannableStringBuilder(strSpanned)

        if (str.contains(spanableText)) {
            ssb.setSpan(object : CustomSpannable(false) {
                override fun onClick(widget: View) {
                    //super.onClick(widget)
                    tv.layoutParams = tv.layoutParams
                    tv.setText(tv.tag.toString(), BufferType.SPANNABLE)
                    tv.invalidate()

                    //we will add google analytics here

//                    if (viewMore) {
//                        makeTextViewResizable(tv, -1, "View Less", false)
//                    } else {
//                        makeTextViewResizable(tv, 3, "View More", true)
//                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length, 0)
        }
        return ssb
    }

    fun makeTextViewResizable(tv: TextView, maxLength: Int, expandText: String, viewMore: Boolean) {
        if (tv.tag == null) {
            tv.tag = tv.text
        }
        val vto = tv.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                var truncated: String = ""
                val obs = tv.viewTreeObserver
                obs.removeOnGlobalLayoutListener(this)
                if (maxLength > 0 && tv.text.length >= maxLength) {
                    truncated = tv.text?.substring(0, maxLength) + expandText
                }
                tv.text = truncated
                tv.movementMethod = LinkMovementMethod.getInstance()
                tv.setText(
                    addClickablePartTextViewResizable(
                        SpannableString(tv.text.toString()), tv, expandText,
                        viewMore
                    ), BufferType.SPANNABLE
                )
            }
        })
    }
}
