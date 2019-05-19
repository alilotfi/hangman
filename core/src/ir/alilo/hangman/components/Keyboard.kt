package ir.alilo.hangman.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import ir.alilo.hangman.Resources


class Keyboard(
    font: BitmapFont,
    private val listener: KeyboardListener,
    private val hasSpace: Boolean = true,
    private val hasDelete: Boolean = true
) : Table() {
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
            addSpaceIfNeeded(buttonSize, style, rowIndex)
            row.forEach { character ->
                val button =
                    KeyboardButton(character, KeyboardButtonType.CHARACTER, style, listener)
                add(button).width(buttonSize).height(buttonSize).spaceLeft(MARGIN)
            }
            addDeleteIfNeeded(buttonSize, style, rowIndex)

            row()
            add().height(MARGIN)
            row()

        }
        return buttons
    }

    private fun addSpaceIfNeeded(
        buttonSize: Float,
        style: TextButton.TextButtonStyle,
        rowIndex: Int
    ) {
        if (rowIndex != LAST_ROW_INDEX) {
            return
        }

        if (!hasSpace) {
            addEmptyButton(buttonSize)
            return
        }

        val space = KeyboardButton(null, KeyboardButtonType.SPACE, style, listener)
        addLargeButton(space, buttonSize)
    }

    private fun addDeleteIfNeeded(
        buttonSize: Float,
        style: TextButton.TextButtonStyle,
        rowIndex: Int
    ) {
        if (rowIndex != LAST_ROW_INDEX) {
            return
        }

        if (!hasDelete) {
            addEmptyButton(buttonSize)
            return
        }

        val delete = KeyboardButton(null, KeyboardButtonType.DELETE, style, listener)
        addLargeButton(delete, buttonSize)
    }

    private fun addLargeButton(button: KeyboardButton, buttonSize: Float) {
        add(button).width(buttonSize).colspan(2).width(2 * buttonSize + MARGIN)
            .height(buttonSize)
            .spaceLeft(MARGIN)
    }

    private fun addEmptyButton(buttonSize: Float) {
        add().width(buttonSize).colspan(2).width(2 * buttonSize + MARGIN)
            .height(buttonSize)
            .spaceLeft(MARGIN)
    }

    private fun calculateButtonSize() = ((Gdx.graphics.width - MARGIN) / 12f) - MARGIN

    companion object {
        private const val MARGIN = 25f
        private const val LAST_ROW_INDEX = 2
    }
}
