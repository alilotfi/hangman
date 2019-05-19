package ir.alilo.hangman.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import ir.alilo.hangman.Resources


class Keyboard(
    private val font: BitmapFont,
    listener: KeyboardListener,
    private val hasSpace: Boolean = true,
    private val hasDelete: Boolean = true,
    private val willButtonsDisappear: Boolean = false
) : Table() {
    private val buttons: List<List<ButtonVisibility>>
    private val visibilityControllerListener = VisibilityControllerListener(listener)

    init {
        val letters: List<List<Char>> = listOf(
            Resources.Strings.keyboardFirstRow.toList(),
            Resources.Strings.keyboardSecondRow.toList(),
            Resources.Strings.keyboardThirdRow.toList()
        )
        buttons = letters.map { row ->
            row.map { char ->
                ButtonVisibility(char, true)
            }
        }

        addButtons()
        setFillParent(true)
        bottom()
    }

    private fun addButtons(): MutableList<KeyboardButton> {
        clear()
        val buttonSize = calculateButtonSize()
        val style = KeyboardButton.buildStyle(font)
        val keyboardButtons = mutableListOf<KeyboardButton>()
        buttons.forEachIndexed { rowIndex, row ->
            addSpaceIfNeeded(buttonSize, style, rowIndex)
            row.forEach { character ->
                val cell = if (character.visible) {
                    add(
                        KeyboardButton(
                            character.char,
                            KeyboardButtonType.CHARACTER,
                            style,
                            visibilityControllerListener
                        )
                    )
                } else {
                    add()
                }
                cell.width(buttonSize).height(buttonSize).spaceLeft(MARGIN)
            }
            addDeleteIfNeeded(buttonSize, style, rowIndex)

            row()
            add().height(MARGIN)
            row()

        }
        return keyboardButtons
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

        val space =
            KeyboardButton(null, KeyboardButtonType.SPACE, style, visibilityControllerListener)
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

        val delete =
            KeyboardButton(null, KeyboardButtonType.DELETE, style, visibilityControllerListener)
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

    private class ButtonVisibility(val char: Char, var visible: Boolean = true)

    private inner class VisibilityControllerListener(val listener: KeyboardListener) :
        KeyboardListener {
        override fun onButtonPressed(char: Char?, type: KeyboardButtonType) {
            if (type == KeyboardButtonType.CHARACTER && willButtonsDisappear) {
                buttons.forEach { row ->
                    row.forEach {
                        if (char == it.char) {
                            it.visible = false
                        }
                    }
                }
                addButtons()
            }
            listener.onButtonPressed(char, type)
        }
    }

    companion object {
        private const val MARGIN = 25f
        private const val LAST_ROW_INDEX = 2
    }
}
