package ir.alilo.hangman.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import ir.alilo.hangman.Resources

class CharacterArea(
    style: TextButtonStyle,
    char: Char,
    index: Int,
    totalCharacters: Int
) : TextButton(char.toString(), style) {
    init {
        val size = calculateSize()
        val position = calculatePosition(size, index, totalCharacters)

        height = size
        width = size
        setPosition(position.first, position.second)
    }

    private fun calculateSize(): Float = ((Gdx.graphics.width - MARGIN) / 20f) - MARGIN

    private fun calculatePosition(
        size: Float,
        index: Int,
        totalCharacters: Int
    ): Pair<Float, Float> {
        val totalWidth = (size + MARGIN) * totalCharacters + MARGIN
        val x =
            (Gdx.graphics.width - totalWidth) / 2 + (size + MARGIN) * (totalCharacters - index - 1)
        val y = Gdx.graphics.height - size - MARGIN
        return x to y
    }

    companion object {
        private const val MARGIN = 35f

        fun buildCharacterStyle(font: BitmapFont): TextButtonStyle {
            val skinButton = Skin()
            val charAreaAtlas = TextureAtlas(Resources.Packs.charArea)
            skinButton.addRegions(charAreaAtlas)

            return TextButtonStyle().apply {
                this.font = font
                up = skinButton.getDrawable(Resources.Packs.Drawable.charAreaCharArea)
            }
        }

        fun buildSpaceStyle(font: BitmapFont): TextButtonStyle {
            return TextButtonStyle().apply {
                this.font = font
            }
        }
    }
}
