package ir.alilo.hangman.components

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Group

class TextArea(private val font: BitmapFont) : Group() {
    private var text = ""

    fun addCharacter(char: Char) {
        text += char
        rebuildCharacters()
    }

    fun addSpace() {
        text += ' '
        rebuildCharacters()
    }

    fun removeCharacter() {
        if (text.isEmpty()) {
            return
        }
        text = text.substring(0, text.length - 1)
        rebuildCharacters()
    }

    private fun rebuildCharacters() {
        clearChildren()
        val characterStyle = CharacterArea.buildCharacterStyle(font)
        val spaceStyle = CharacterArea.buildSpaceStyle(font)
        text.toList().forEachIndexed { index, char ->
            val actor = if (char == ' ') {
                CharacterArea(spaceStyle, char, index, text.length)
            } else {
                CharacterArea(characterStyle, char, index, text.length)
            }
            addActor(actor)
        }
    }
}
