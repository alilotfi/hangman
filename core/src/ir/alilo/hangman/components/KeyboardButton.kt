package ir.alilo.hangman.components

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import ir.alilo.hangman.Resources


class KeyboardButton(
    private val char: Char?,
    private val type: KeyboardButtonType,
    style: TextButtonStyle,
    private val listener: KeyboardListener
) : TextButton(type.label ?: char.toString(), style) {
    init {
        addListener(ButtonListener())
    }

    private inner class ButtonListener : ChangeListener() {
        override fun changed(event: ChangeEvent?, actor: Actor?) {
            listener.onButtonPressed(char, type)
        }
    }

    companion object {
        fun buildStyle(font: BitmapFont): TextButtonStyle {
            val skinButton = Skin()
            val buttonAtlas = TextureAtlas(Resources.Packs.buttons)
            skinButton.addRegions(buttonAtlas)

            return TextButtonStyle().apply {
                this.font = font
                up = skinButton.getDrawable(Resources.Packs.Drawable.buttonsUp)
                down = skinButton.getDrawable(Resources.Packs.Drawable.buttonsDown)
            }
        }
    }
}

enum class KeyboardButtonType(val label: String?) {
    CHARACTER(null),
    DELETE(Resources.Strings.delete),
    SPACE(Resources.Strings.space)
}
