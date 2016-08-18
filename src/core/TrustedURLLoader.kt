package core

import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class TrustedURLLoader
{

    fun init()
    {
        // Create a new trust manager that trust all certificates
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate>? {
                return null
            }

            override fun checkClientTrusted(
                    certs: Array<java.security.cert.X509Certificate>, authType: String) {
            }

            override fun checkServerTrusted(
                    certs: Array<java.security.cert.X509Certificate>, authType: String) {
            }
        })

        // Activate the new trust manager
        try {
            val sc = SSLContext.getInstance("SSL")
            sc.init(null, trustAllCerts, java.security.SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
        } catch (e: Exception) {

        }
    }
}
