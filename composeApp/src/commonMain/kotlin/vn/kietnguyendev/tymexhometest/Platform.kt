package vn.kietnguyendev.tymexhometest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform