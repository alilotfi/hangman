package ir.alilo.hangman

import ir.alilo.libgdxrtl.ArFont

object Resources {
    private val arFont = ArFont()
    private fun String.asPersianGlyph() = arFont.getText(this)

    object Strings {
        val delete: String = "حذف".asPersianGlyph()
        val space: String = "فاصله".asPersianGlyph()
        const val keyboardFirstRow = "ضصثقفغعهخحجچ"
        const val keyboardSecondRow = "شسیبلآاتنمکگ"
        const val keyboardThirdRow = "ظطزرذدپو"
    }

    object Fonts {
        const val yekan = "fonts/yekan.ttf"
    }

    object Textures {
        const val gholve = "gholve.png"
    }

    object Packs {
        const val charArea = "packs/char_area.atlas"
        const val buttons = "packs/buttons.atlas"

        object Drawable {
            const val charAreaCharArea = "char_area"
            const val buttonsUp = "button_up"
            const val buttonsDown = "button_down"
        }
    }
}
