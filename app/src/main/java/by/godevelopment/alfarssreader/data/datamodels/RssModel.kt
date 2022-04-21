package by.godevelopment.alfarssreader.data.datamodels

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml
class RssModel {
    @Attribute(name = "rss version")
    var version: String = ""

    @Element
    lateinit var channel: Channel
}