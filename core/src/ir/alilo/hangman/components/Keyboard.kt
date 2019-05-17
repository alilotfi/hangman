package ir.alilo.hangman.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import ir.alilo.hangman.Resources


class Keyboard(font: BitmapFont, private val listener: KeyboardListener) : Table() {
    private val letters: List<List<Char>> = listOf(
        Resources.Strings.keyboardFirstRow.toList(),
        Resources.Strings.keyboardSecondRow.toList(),
        Resources.Strings.keyboardThirdRow.toList()
    )

    init {
        addButtons(font).toList()
        setFillParent(true)
        bottom()
    }

    private fun addButtons(font: BitmapFont): MutableList<KeyboardButton> {
        val buttonSize = calculateButtonSize()
        val style = KeyboardButton.buildStyle(font)
        val buttons = mutableListOf<KeyboardButton>()
        letters.forEachIndexed { rowIndex, row ->
            if (rowIndex == LAST_ROW_INDEX) {
                addSpaceButton(buttonSize, style)
            }
            row.forEach { character ->
                val button =
                    KeyboardButton(character, KeyboardButtonType.CHARACTER, style, listener)
                add(button).width(buttonSize).height(buttonSize).spaceLeft(MARGIN)
            }
            if (rowIndex == LAST_ROW_INDEX) {
                addDeleteButton(buttonSize, style)
            }

            row()
            add().height(MARGIN)
            row()

        }
        return buttons
    }

    private fun addSpaceButton(buttonSize: Float, style: TextButton.TextButtonStyle) {
        val space = KeyboardButton(null, KeyboardButtonType.SPACE, style, listener)
        add(space).width(buttonSize).colspan(2).width(2 * buttonSize + MARGIN)
            .height(buttonSize)
            .spaceLeft(MARGIN)
    }

    private fun addDeleteButton(buttonSize: Float, style: TextButton.TextButtonStyle) {
        val delete = KeyboardButton(null, KeyboardButtonType.DELETE, style, listener)
        add(delete).width(buttonSize).colspan(2).width(2 * buttonSize + MARGIN)
            .height(buttonSize)
            .spaceLeft(MARGIN)
    }

    private fun calculateButtonSize() = ((Gdx.graphics.width - MARGIN) / 12f) - MARGIN

    companion object {
        private const val MARGIN = 25f
        private const val LAST_ROW_INDEX = 2
    }
}
