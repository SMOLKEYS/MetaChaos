package meta

import arc.Events
import arc.util.Timer
import mindustry.Vars
import mindustry.game.EventType.ClientLoadEvent
import mindustry.mod.*

class MetaChaos : Mod() {

	init {
		Events.on(ClientLoadEvent::class.java) {
			MetaVars.handleFiles()
			MetaSettings.load()

			loop(1f) {
				Vars.mods.eachEnabled {
					it.meta.apply {
						if(MetaVars.randomizeAuthors) author = MetaVars.authors.random()
						if(MetaVars.randomizeDisplayNames) displayName = MetaVars.displayNames.random()
						if(MetaVars.randomizeDescriptions) description = MetaVars.descriptions.random()
						if(MetaVars.randomizeSubtitles) subtitle = MetaVars.subtitles.random()
					}
				}
			}
		}
	}

	@Suppress("SameParameterValue")
	private fun loop(sec: Float, run: () -> Unit): Timer.Task = Timer.schedule(run, sec, sec, -1)
}
