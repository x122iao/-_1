package com.tongshang.cloudphone.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File

object CacheUtils {

    // 获取缓存大小（单位：字节）
    fun getCacheSize(context: Context): Long {
        var size: Long = 0
        try {
            // 计算内部缓存大小
            val cacheDir = context.cacheDir
            size += getDirSize(cacheDir)

            // 计算外部缓存大小
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                val externalCacheDir = context.externalCacheDir
                externalCacheDir?.let {
                    size += getDirSize(it)
                }
            }

            Log.d("CacheUtils", "缓存总大小: $size 字节")
        } catch (e: Exception) {
            Log.e("CacheUtils", "计算缓存大小失败", e)
        }
        return size
    }

    // 获取目录的大小
    private fun getDirSize(dir: File): Long {
        var size: Long = 0
        if (dir.exists() && dir.isDirectory) {
            val files = dir.listFiles()
            files?.forEach { file ->
                size += if (file.isDirectory) {
                    getDirSize(file)
                } else {
                    file.length()
                }
            }
        }
        return size
    }

    // 将字节转换为更易读的格式（KB、MB、GB）
    fun formatSize(size: Long): String {
        if (size <= 0) return "0B"
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
        Log.e("CacheUtils",String.format("%.2f %s", size / Math.pow(1024.0, digitGroups.toDouble()), units[digitGroups]))
        return String.format("%.2f %s", size / Math.pow(1024.0, digitGroups.toDouble()), units[digitGroups])
    }

    // 清除应用缓存
    fun clearCache(context: Context) {
        try {
            // 清除内部缓存
            val cacheDir = context.cacheDir
            deleteDir(cacheDir)

            // 清除外部缓存
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                val externalCacheDir = context.externalCacheDir
                externalCacheDir?.let {
                    deleteDir(it)
                }
            }
            ToastUtil.showShortToast("缓存清除成功")

            Log.d("CacheUtils", "缓存已清除")
        } catch (e: Exception) {
            ToastUtil.showShortToast("清除缓存失败")

            Log.e("CacheUtils", "清除缓存失败", e)
        }
    }

    // 删除目录中的所有文件
    private fun deleteDir(dir: File): Boolean {
        if (dir.isDirectory) {
            val children = dir.list()
            children?.forEach { child ->
                val success = deleteDir(File(dir, child))
                if (!success) return false
            }
        }
        return dir.delete()
    }



}
