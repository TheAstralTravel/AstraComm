import android.content.Context
import info.guardianproject.tor.torcontrol.TorControlConnection
import info.guardianproject.tor.engine.TorEngine
import java.net.Socket

class TorManager(private val context: Context) {
    private var torEngine: TorEngine? = null
    private var controlConnection: TorControlConnection? = null

    // Инициализация Tor
    fun startTor(): String {
        torEngine = TorEngine.getInstance(context).apply {
            setConfig(
                TorEngine.Config()
                    .port(9050) // SOCKS-порт
                    .dnsPort(5400)
                    .disableNetwork(false)
            )
            start()
        }

        controlConnection = TorControlConnection().apply {
            connect("127.0.0.1", 9051) // ControlPort
            authenticate()
        }

        return waitForOnionAddress()
    }

    // Создание скрытого сервиса (.onion)
    private fun waitForOnionAddress(): String {
        return controlConnection?.getOnionAddress() ?: run {
            Thread.sleep(2000) // Ожидание инициализации
            controlConnection?.getOnionAddress() ?: throw IllegalStateException("Tor не запущен")
        }
    }

    // Создание сокета через Tor
    fun createSocket(targetOnion: String, port: Int): Socket {
        return Socket(Proxy(Proxy.Type.SOCKS, InetSocketAddress("127.0.0.1", 9050))).apply {
            connect(InetSocketAddress("$targetOnion.onion", port), 30000)
        }
    }

    // Остановка Tor
    fun stopTor() {
        controlConnection?.disconnect()
        torEngine?.stop()
    }
}