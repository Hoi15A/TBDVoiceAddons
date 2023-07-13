package computer.austins.tbdutils.util.batboat

import computer.austins.tbdutils.plugin
import computer.austins.tbdutils.util.Chat

import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

/** Represents a Bat-Boat entity combination. */
class BatBoat(player: Player, attach: Boolean) {
    private companion object {
        /** The duration the [BatBoat] should remain active before it is automatically destroyed. */
        private const val ENTITY_DURATION_SECONDS: Long = 60L
    }

    /** The bat [Entity] component. */
    private val bat: Entity

    /** The boat [Entity] component. */
    private val boat: Entity

    init {
        bat = spawnComponent(EntityType.BAT, player)
        boat = spawnComponent(EntityType.BOAT, player)

        bat.addPassenger(boat)
        if(attach) boat.addPassenger(player)

        BatBoatManager.registerInstance(player.uniqueId, this)
        Chat.broadcastDev("<notifcolour>${player.name} <green>created<reset> a <light_purple>Bat Boat<reset> at <yellow>${player.location.blockX}<reset>, <yellow>${player.location.blockY}<reset>, <yellow>${player.location.blockZ}<reset>.", true)

        object : BukkitRunnable() {
            override fun run() {
                BatBoatManager.unregisterInstance(player.uniqueId)
                Chat.broadcastDev("<notifcolour>${player.name}<reset>'s <light_purple>Bat Boat<red> despawned<reset> at <yellow>${player.location.blockX}<reset>, <yellow>${player.location.blockY}<reset>, <yellow>${player.location.blockZ}<reset>.", true)
                remove()
            }
        }.runTaskLater(plugin, ENTITY_DURATION_SECONDS * 20)
    }

    /** Spawns a component of given [type] at [player]'s location, returning the [Entity]. */
    private fun spawnComponent(type: EntityType, player: Player): Entity {
        val entity = player.world.spawnEntity(player.location, type)
        entity.isInvulnerable = true
        return entity
    }

    /** Removes this [BatBoat] instance. */
    fun remove() {
        boat.remove()
        bat.remove()
    }
}
