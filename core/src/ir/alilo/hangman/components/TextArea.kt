package ir.alilo.hangman.components

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Group

class TextArea(font: BitmapFont, initialText: String = "", private val initialRevealed: Boolean) :
    Group() {
    private val characters: MutableList<CharacterVisibility> = mutableListOf()
    private val characterStyle = CharacterArea.buildCharacterStyle(font)
    private val spaceStyle = CharacterArea.buildSpaceStyle(font)

    init {
        initialText.forEach { characters.add(CharacterVisibility(it, initialRevealed)) }
        rebuildCharacters()
    }

    fun addCharacter(char: Char) {
        characters.add(CharacterVisibility(char, initialRevealed))
        rebuildCharacters()
    }

    fun addSpace() {
        characters.add(CharacterVisibility(SPACE_PLACE_HOLDER, initialRevealed))
        rebuildCharacters()
    }

    fun removeCharacter() {
        if (characters.isEmpty()) {
            return
        }
        characters.removeAt(characters.size - 1)
        rebuildCharacters()
    }

    fun revealCharacter(char: Char): Boolean {
        /**
         * Reveals the characters matching the given one and returns true if at least one character
         * got reveled in the process
         */
        val revealedCharacters = characters.count {
            it.revealIfMatches(char)
        }
        if (revealedCharacters > 0) {
            rebuildCharacters()
        }

        return revealedCharacters > 0
    }

    fun reveal() {
        characters.forEach { it.reveal() }
        rebuildCharacters()
    }

    fun isRevealed(): Boolean {
        return characters.all { it.revealed }
    }

    fun getText(): String {
        val stringBuilder = StringBuilder()
        characters.forEach { stringBuilder.append(it.char) }
        return stringBuilder.toString()
    }

    private fun rebuildCharacters() {
        clearChildren()
        characters.forEachIndexed { index, char ->
            val actor = if (char.char == SPACE_PLACE_HOLDER) {
                CharacterArea(spaceStyle, char.char, index, characters.size)
            } else if (!char.revealed) {
                CharacterArea(characterStyle, HIDDEN_CHARACTER, index, characters.size)
            } else {
                CharacterArea(characterStyle, char.char, index, characters.size)
            }
            addActor(actor)
        }
    }

    private class CharacterVisibility(val char: Char, revealed: Boolean) {
        var revealed = revealed
            private set
            get() {
                return field or (char == SPACE_PLACE_HOLDER)
            }

        fun revealIfMatches(givenChar: Char): Boolean {
            /**
             * Reveals the character if the given character matches with this character (in a
             * lexicographical manner). Returns true if character was successfully matched or false
             * otherwise.
             */
            if (givenChar == char) {
                revealed = true
                return true
            }

            return false
        }

        fun reveal() {
            revealed = true
        }
    }

    private companion object {
        private const val SPACE_PLACE_HOLDER = ' '
        private const val HIDDEN_CHARACTER = ' '
    }
}
