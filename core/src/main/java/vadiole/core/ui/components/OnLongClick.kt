package vadiole.core.ui.components

import android.view.View

var View.onLongClick: (View) -> Boolean
    get() = throw RuntimeException("There is no getter for onClick")
    set(action) = setOnLongClickListener(action)
