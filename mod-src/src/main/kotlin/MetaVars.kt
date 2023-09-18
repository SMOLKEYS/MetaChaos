@file:Suppress("MayBeConstant")

package meta

import arc.Core
import arc.files.Fi
import arc.struct.Seq
import com.github.mnemotechnician.mkui.delegates.setting

@Suppress("MemberVisibilityCanBePrivate", "SpellCheckingInspection")
object MetaVars {
    val syn = "metachaos-"
    var filesLoaded = false

    var randomizeDisplayNames by setting(false, syn)
    var randomizeDescriptions by setting(false, syn)
    var randomizeAuthors by setting(false, syn)
    var randomizeSubtitles by setting(true, syn)

    val displayNames = Seq<String>()
    val authors = Seq<String>()
    val descriptions = Seq<String>()
    val subtitles = Seq<String>()

    fun handleFiles() {
        val dn = meta("displaynames.txt")
        val a = meta("authors.txt")
        val d = meta("descriptions.txt")
        val s = meta("subtitles.txt")

        if(!dn.exists()) dn.writeString("(check data dir)")
        if(!a.exists()) a.writeString("(check data dir)")
        if(!d.exists()) d.writeString("(check data dir)")
        if(!s.exists()) s.writeString("(check data dir)")

        if(filesLoaded) {
            displayNames.clear()
            authors.clear()
            descriptions.clear()
            subtitles.clear()
        }

        displayNames.addAll(dn.readLines())
        authors.addAll(a.readLines())
        descriptions.addAll(d.readLines())
        subtitles.addAll(s.readLines())

        filesLoaded = true
    }

    private fun meta(name: String) = Core.settings.dataDirectory.child("metachaos").child(name)

    private fun Fi.readLines() = this.readString().split('\n').filter {!it.startsWith("#")}
}