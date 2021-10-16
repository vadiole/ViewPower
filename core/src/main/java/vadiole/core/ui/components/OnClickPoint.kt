package vadiole.core.ui.components

import android.annotation.SuppressLint
import android.graphics.PointF
import android.view.MotionEvent.ACTION_DOWN
import android.view.View

var View.onClickPoint: (PointF) -> Unit
    get() = throw RuntimeException("There is no getter for onClickPoint")
    @SuppressLint("ClickableViewAccessibility")
    set(action) {
        val point = PointF()

        setOnTouchListener { _, event ->
            if (event.action == ACTION_DOWN) point.set(event.rawX, event.rawY)
            false
        }

        onClick = {
            action.invoke(point)
        }
    }

