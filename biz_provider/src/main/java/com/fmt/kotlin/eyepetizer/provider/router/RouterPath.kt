package com.fmt.kotlin.eyepetizer.provider.router

/**
 * 全局路由路径
 */
object RouterPath {

    class Daily {
        companion object {
            const val PATH_DAILY_HOME = "/daily/home"
        }
    }

    class Discover {
        companion object {
            const val PATH_DISCOVER_HOME = "/discover/home"
        }
    }

    class Hot {
        companion object {
            const val PATH_HOT_HOME = "/hot/home"
        }
    }

    class Person {
        companion object {
            const val PATH_PERSON_HOME = "/person/home"
        }
    }

    class Video {
        companion object {
            const val PATH_VIDEO_HOME = "/video/home"
            const val PATH_VIDEO_WATCH = "/video/watch"
        }
    }

}