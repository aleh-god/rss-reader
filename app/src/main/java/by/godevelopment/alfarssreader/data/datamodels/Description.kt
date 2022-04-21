package by.godevelopment.alfarssreader.data.datamodels

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
class Description {
    @PropertyElement(name = "CDATA")
    var cdata: String = ""
}