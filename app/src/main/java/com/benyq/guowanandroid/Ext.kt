package com.benyq.guowanandroid

import android.util.Log
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.tencent.mmkv.MMKV
import java.io.*
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KProperty

/**
 * @author benyq
 * @date 2021/8/5
 * @email 1520063035@qq.com
 */

class MMKVValue<T>(val name:String, private val default:T) {

    companion object {
        private var kv = MMKV.defaultMMKV()
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return decode(name, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        encode(name, value)
    }

    private fun encode(key: String, value: T) {
        when (value) {
            is String -> kv.encode(key, value)
            is Float -> kv.encode(key, value)
            is Boolean -> kv.encode(key, value)
            is Int -> kv.encode(key, value)
            is Long -> kv.encode(key, value)
            is Double -> kv.encode(key, value)
            is ByteArray -> kv.encode(key, value)
            else -> kv.encode(name,serialize(value))
        }
    }

    private fun decode(name: String, default: T): T = with(kv) {
        val res: Any? = when (default) {
            is Long -> decodeLong(name, default)
            is String -> decodeString(name, default)
            is Int -> decodeInt(name, default)
            is Boolean -> decodeBool(name, default)
            is Float -> decodeFloat(name, default)
            else ->  deSerialization(decodeString(name,serialize(default)))
        }
        return res as T ?: default
    }


    /**
     * 序列化对象

     * @param person
     * *
     * @return
     * *
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun<A> serialize(obj: A): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(
            byteArrayOutputStream)
        objectOutputStream.writeObject(obj)
        var serStr = byteArrayOutputStream.toString("ISO-8859-1")
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8")
        objectOutputStream.close()
        byteArrayOutputStream.close()
        return serStr
    }

    /**
     * 反序列化对象

     * @param str
     * *
     * @return
     * *
     * @throws IOException
     * *
     * @throws ClassNotFoundException
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IOException::class, ClassNotFoundException::class)
    private fun<A> deSerialization(str: String?): A? {
        if (str == null) {
            return null
        }
        val redStr = java.net.URLDecoder.decode(str, "UTF-8")
        val byteArrayInputStream = ByteArrayInputStream(
            redStr.toByteArray(charset("ISO-8859-1")))
        val objectInputStream = ObjectInputStream(
            byteArrayInputStream)
        val obj = objectInputStream.readObject() as A
        objectInputStream.close()
        byteArrayInputStream.close()
        return obj
    }

    fun removeKey(key: String) {
        kv.removeValueForKey(key)
    }

    fun clearAll() {
        kv.clearAll()
    }
}

fun String.isJson(): Boolean {
    val jsonParser = JsonParser()
    return try {
        val jsonElement = jsonParser.parse(this)
        jsonElement.isJsonObject
    }catch (e: JsonSyntaxException){
        false
    }
}



fun <T> getClass(t: Any, index: Int = 0): Class<T> {
    // 通过反射 获取父类泛型 (T) 对应 Class类
    return (t.javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[index]
            as Class<T>
}

fun ioClose(closeable: Closeable?) {
    if (closeable == null) return
    try {
        closeable.close()
    } catch (e: IOException) {
        e.printStackTrace()
        //close error
    }

}

inline fun tryCatch(tryBlock: () -> Unit, catchBlock: (Throwable) -> Unit = {}, finalBlock: ()->Unit = {}) {
    try {
        tryBlock()
    } catch (t: Throwable) {
        t.printStackTrace()
        catchBlock(t)
        Log.e("IOExt", "tryCatch Throwable ${t.message}")
    }finally {
        finalBlock()
    }
}