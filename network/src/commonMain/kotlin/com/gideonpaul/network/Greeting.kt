package com.gideonpaul.network

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}