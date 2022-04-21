package by.godevelopment.alfarssreader.data.datamodels

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
class Channel {
    @PropertyElement
    var title: String= ""
    @PropertyElement
    var link: String= ""
    @PropertyElement
    var description: String= ""
    @PropertyElement
    var language: String= ""
    @PropertyElement
    var pubDate: String= ""
    @PropertyElement
    var docs: String= ""
    @PropertyElement
    var managingEditor: String= ""
    @PropertyElement
    var webMaster: String= ""
    @Element
    lateinit var item: List<Item>
}

// @Xml
//data class Channel (
//    val title: String?,
//    val link: String?,
//    val description: String?,
//    val language: String?,
//    val pubDate: String?,
//    val docs: String?,
//    val managingEditor: String?,
//    val webMaster: String?,
//    val item: List<Item>?
//)
