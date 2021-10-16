package vadiole.viewpower

import vadiole.core.ui.theme.*
import java.util.*

class BlueDark(full: Boolean) : AppTheme() {
    override val fullscreen: Boolean = full
    override val dark: Boolean = true

    override val colors: HashMap<String, Int> = hashMapOf(
        key_dialogBackground to 0xFF_212B3Bu.toInt(),
        key_dialogTextPrimary to 0xFF_FFFFFFu.toInt(),
        key_dialogIcon to 0xFF_FFFFFFu.toInt(),
        key_dialogButton to 0xFF_64B5EFu.toInt(),
        key_dialogButtonDanger to 0xFF_EE686Fu.toInt(),
        key_dialogListRipple to 0x14_E6F7FFu.toInt(),

        key_windowBackground to 0xFF_212B3Bu.toInt(),
    )
}