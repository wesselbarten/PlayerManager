package nl.wesselbarten.playermanager.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import nl.wesselbarten.playermanager.R

open class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val drawable: Drawable = ContextCompat.getDrawable(context, R.drawable.divider)!!

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0..parent.childCount - 2) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + drawable.intrinsicHeight

            drawable.setBounds(left, top, right, bottom)
            drawable.draw(canvas)
        }
    }
}