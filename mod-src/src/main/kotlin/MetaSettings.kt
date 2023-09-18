package meta

import com.github.mnemotechnician.mkui.extensions.dsl.textButton
import mindustry.Vars
import mindustry.ui.dialogs.SettingsMenuDialog
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable.Setting

object MetaSettings {
    fun load(){
        Vars.ui.settings.addCategory("MetaChaos"){t ->
            t.checkPref("randomdisplaynames", false){
                MetaVars.randomizeDisplayNames = it
            }

            t.checkPref("randomdescriptions", false){
                MetaVars.randomizeDescriptions = it
            }

            t.checkPref("randomauthors", false){
                MetaVars.randomizeAuthors = it
            }

            t.checkPref("randomsubtitles", true){
                MetaVars.randomizeSubtitles = it
            }

            t.buttonPref("reloadtexts"){
                MetaVars.handleFiles()
            }
        }
    }

    fun SettingsTable.buttonPref(name: String, onClick: () -> Unit) = this.pref(ButtonSetting(name, onClick))

    open class ButtonSetting(name: String, var onClick: () -> Unit): Setting(name){

        override fun add(table: SettingsMenuDialog.SettingsTable) {
            table.textButton(title, wrap = false){
                onClick()
            }.growX().row()
        }
    }
}