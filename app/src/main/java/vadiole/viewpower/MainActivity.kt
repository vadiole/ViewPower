package vadiole.viewpower

import android.os.Bundle
import android.view.View
import vadiole.core.ui.HostActivity
import vadiole.core.ui.State
import vadiole.core.ui.components.onClick
import vadiole.core.ui.components.onLongClick
import vadiole.core.ui.theme.AppTheme
import vadiole.core.ui.theme.key_dialogListRipple
import vadiole.core.ui.theme.key_windowBackground

class MainActivity : HostActivity() {
    override val appTheme: State<AppTheme> = State(BlueDark(false))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            View(this).apply {
                val drawable = appTheme.value.getColorDrawable(key_windowBackground)
                val ripple = appTheme.value.getRippleRect(key_dialogListRipple, content = drawable)

                background = ripple
                appTheme.listen2 {
                    drawable.color = getColor(key_windowBackground)
                    ripple.setColor(getColors(key_dialogListRipple))
                }

                var dark = false
                var fullscreen = false
                val applyTheme = {
                    val newTheme = if (dark) BlueDark(fullscreen) else BlueLight(fullscreen)
                    appTheme.update(newTheme)
                }
                onClick = {
                    dark = !dark
                    applyTheme.invoke()
                }

                onLongClick = {
                    fullscreen = !fullscreen
                    applyTheme.invoke()
                    true
                }
            }
        )
    }
}