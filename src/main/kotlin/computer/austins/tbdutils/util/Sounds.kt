package computer.austins.tbdutils.util

import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound

object Sounds {
    object Admin {
        val SERVER_ANNOUNCEMENT = Sound.sound(Key.key("block.note_block.pling"), Sound.Source.MASTER, 1.0f, 1.0f)
        val RESTART_ANNOUNCEMENT = Sound.sound(Key.key("block.note_block.pling"), Sound.Source.MASTER, 1.0f, 1.0f)
        val ADMIN_MESSAGE = Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 1.0f, 2.0f)
    }
}