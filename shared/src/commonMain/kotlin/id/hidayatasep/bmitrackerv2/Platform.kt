package id.hidayatasep.bmitrackerv2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform