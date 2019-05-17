package ir.alilo.hangman

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import ir.alilo.hangman.screens.ScreenBuilder
import ir.alilo.libgdxrtl.ArUtils


class Hangman : Game() {
    lateinit var batch: SpriteBatch
    lateinit var largeFont: BitmapFont
    lateinit var smallFont: BitmapFont

    override fun create() {
        batch = SpriteBatch()
        generateFonts()
        setScreen(ScreenBuilder.buildLoadingScreen(this))
    }

    private fun generateFonts() {
        val generator = FreeTypeFontGenerator(Gdx.files.internal(Resources.Fonts.yekan))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.characters += ArUtils.getAllChars().toString("")
        parameter.color = BLACK
        parameter.minFilter = Texture.TextureFilter.Linear
        parameter.magFilter = Texture.TextureFilter.Linear

        parameter.size = 64
        largeFont = generator.generateFont(parameter)

        parameter.size = 42
        smallFont = generator.generateFont(parameter)
    }

    override fun dispose() {
        batch.dispose()
        largeFont.dispose()
    }
}
