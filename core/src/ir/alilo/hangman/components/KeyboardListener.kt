package ir.alilo.hangman.components

interface KeyboardListener {
    fun onButtonPressed(char: Char?, type: KeyboardButtonType)
}
