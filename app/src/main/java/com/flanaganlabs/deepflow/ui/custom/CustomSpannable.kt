package com.flanaganlabs.deepflow.ui.custom

import android.graphics.Color
import android.text.TextPaint

import android.text.style.ClickableSpan
import android.view.View


open class CustomSpannable(isUnderline: Boolean) : ClickableSpan() {
    private var isUnderline = false

    override fun updateDrawState(ds: TextPaint) {
        ds.isUnderlineText = isUnderline
        ds.color = Color.parseColor("#343434")
    }

    override fun onClick(widget: View) {
    }

    /**
     * Constructor
     */
    init {
        this.isUnderline = isUnderline
    }
}