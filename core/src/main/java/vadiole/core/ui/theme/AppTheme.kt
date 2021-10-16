package vadiole.core.ui.theme

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.util.StateSet
import java.util.*

abstract class AppTheme {
    open val fullscreen: Boolean = false
    abstract val dark: Boolean
    protected abstract val colors: HashMap<String, Int>

    fun getColor(key: String): Int {
        return colors[key] ?: Color.RED
    }

    fun getColors(key: String): ColorStateList {
        val color = getColor(key)
        return ColorStateList.valueOf(color)
    }

    fun getColorDrawable(key: String): ColorDrawable {
        val color = getColor(key)
        return ColorDrawable(color)
    }

    fun getColorFilter(key: String): PorterDuffColorFilter {
        val color = getColor(key)
        return PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY)
    }

    fun getRippleRect(key: String, radius: Float = 0f, content: Drawable? = null): RippleDrawable {
        val colorStateList = ColorStateList.valueOf(getColor(key))
        val shape = if (radius == 0f) {
            RectShape()
        } else {
            val corners = FloatArray(8) { radius }
            RoundRectShape(corners, null, null)
        }
        val mask = ShapeDrawable(shape)
        return RippleDrawable(colorStateList, content, mask)
    }

    fun getRippleCircle(key: String, diameter: Float, content: Drawable? = null): RippleDrawable {
        val rippleColor = getColor(key)
        val colorStateList = ColorStateList(
            arrayOf(StateSet.WILD_CARD),
            intArrayOf(rippleColor)
        )
        val mask = ShapeDrawable(OvalShape().also { it.resize(diameter, diameter) })
        StateSet.WILD_CARD
        return RippleDrawable(colorStateList, content, mask)
    }
}

val font_robotoMedium = Typeface.Builder("sans-serif-medium").setWeight(700).build()


const val key_dialogBackground = "dialogBackground"
const val key_dialogTextPrimary = "dialogTextPrimary"
const val key_dialogIcon = "dialogIcon"
const val key_dialogButton = "dialogButton"
const val key_dialogButtonDanger = "dialogButtonDanger"
const val key_dialogListRipple = "dialogListRipple"

const val key_windowBackground = "windowBackground"