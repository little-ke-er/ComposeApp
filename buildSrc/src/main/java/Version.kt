


private object LibraryVersion {
    const val RETROFIT = "2.9.0"
    const val OK_HTTP = "4.9.0"
    const val APP_COMPAT = "1.2.0"
    const val MATERIAL = "1.1.0"
    const val CONSTRAINT_LAYOUT = "2.0.4"
    const val VECTOR_DRAWABLE = "1.1.0"
    const val CORE_KTX = "1.3.2"
    const val PAGING = "3.0.0-alpha10"
    const val COIL = "1.1.1"
    const val ROOM = "2.3.0-alpha03"
    const val DATA_STORE = "1.0.0-alpha05"
    const val PROTOBUF = "3.11.0"
    const val LIVE_BUS = "1.7.3"
    const val LIVEDATA_KTX = "2.2.0"
}

object LibraryDependency {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT}"
    const val RETROFIT_MOSHI_CONVERTER =
        "com.squareup.retrofit2:converter-moshi:${LibraryVersion.RETROFIT}"
    const val OK_HTTP = "com.squareup.okhttp3:okhttp:${LibraryVersion.OK_HTTP}"
    const val LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${LibraryVersion.OK_HTTP}"
}