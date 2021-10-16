package vadiole.core.ui

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import androidx.core.view.WindowCompat.getInsetsController
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import androidx.core.view.WindowInsetsCompat.Type.navigationBars
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
import vadiole.core.ui.theme.AppTheme


//@Suppress("DEPRECATION", "ktNoinlineFunc")
abstract class HostActivity : Activity() {

    abstract val appTheme: State<AppTheme>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appTheme.listen(last = false, ::handleTheme)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (hasFocus) handleTheme(appTheme.value)
    }

    private fun handleTheme(theme: AppTheme) {
        val insetsController = getInsetsController(window, window.decorView)!!

        if (theme.fullscreen) {
            setDecorFitsSystemWindows(window, false)
            insetsController.run {
                hide(statusBars() or navigationBars())
                systemBarsBehavior = BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            setDecorFitsSystemWindows(window, false)
            insetsController.show(statusBars() or navigationBars())

            window.addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }

        insetsController.isAppearanceLightStatusBars = !theme.dark
        insetsController.isAppearanceLightNavigationBars = !theme.dark
    }
}