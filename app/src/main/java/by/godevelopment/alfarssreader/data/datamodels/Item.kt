package by.godevelopment.alfarssreader.data.datamodels

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
class Item {
    @PropertyElement
    var title: String = ""

    @PropertyElement
    var link: String = ""

    @Element
    lateinit var description: Description

    @PropertyElement
    var pubDate: String = ""

    @PropertyElement
    var guid: String = ""
}

// @Xml
//data class Item (
//    @PropertyElement
//    val title: String?,
//    @PropertyElement
//    val link: String?,
//    @Element
//    val description: Description?,
//    @PropertyElement
//    val pubDate: String?,
//    @PropertyElement
//    val guid: String?
//)
