package ir.alilo.hangman

import ir.alilo.libgdxrtl.ArFont

object Resources {
    private val arFont = ArFont()
    private fun String.asPersianGlyph() = arFont.getText(this)

    object Strings {
        const val keyboardFirstRow = "ضصثقفغعهخحجچ"
        const val keyboardSecondRow = "شسیبلئاتنمکگ"
        const val keyboardThirdRow = "ظطزرذدپو"
        val delete: String = "حذف".asPersianGlyph()
        val space: String = "فاصله".asPersianGlyph()
        val confirm: String = "تایید".asPersianGlyph()
    }

    object Sounds {
        const val congrats = "sounds/congrats.mp3"
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

    object Effects {
        const val flame = "particles/flame.p"

        object Particles {
            const val flameParticle = "particles"
        }
    }
}
