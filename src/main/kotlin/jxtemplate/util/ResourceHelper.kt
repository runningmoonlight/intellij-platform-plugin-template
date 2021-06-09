package jxtemplate.util

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URI
import java.net.URL


/**
 * Created by liuheng on 2021/6/8.
 */
object ResourceHelper {

    fun getResURL(resFile: String): URL {
        return URL(this.javaClass.getResource(resFile).file)
    }

    fun getResInputStream(resFile: String): InputStream {
        val inputStream = this.javaClass.getResourceAsStream(resFile)
        return inputStream
    }

    fun copyResourceFile(resFile: String, toFile: File) {
        val inputStream = this.javaClass.getResourceAsStream(resFile)
        if (!toFile.parentFile.exists()) {
            toFile.parentFile.mkdirs()
        }
        val outputStream = toFile.outputStream()
        inputStream.copyTo(outputStream)
        outputStream.flush()
    }

}