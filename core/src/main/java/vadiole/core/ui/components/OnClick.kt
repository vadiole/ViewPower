package vadiole.core.ui.components

import android.view.View
import java.util.concurrent.atomic.AtomicBoolean

class DebouncingOnClick(
    private val intervalMillis: Long = 0,
    private val click: ((View) -> Unit),
) : View.OnClickListener {

    override fun onClick(view: View) {
        if (enabled.getAndSet(false)) {
            view.postDelayed(ENABLE_AGAIN, intervalMillis)
            click.invoke(view)
        }
    }

    companion object {
        @JvmStatic
        private var enabled = AtomicBoolean(true)

        @JvmStatic
        private val ENABLE_AGAIN = { enabled.set(true) }
    }
}

var View.onClick: (View) -> Unit
    get() = throw RuntimeException("There is no getter for onClick")
    set(action) = setOnClickListener(DebouncingOnClick(click = action))
