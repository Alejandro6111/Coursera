package com.example.miapp

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.miapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Usar View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Configurar el comportamiento de la actividad
        setupUI()
        
        // Verificar la orientación y tamaño de pantalla
        logScreenInfo()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        
        // La actividad es rotada, el diseño se ajusta automáticamente
        logScreenInfo()
    }

    /**
     * Configura la interfaz de usuario
     */
    private fun setupUI() {
        // Configurar listeners o lógica adicional si es necesario
        binding.titleText.text = getString(R.string.welcome_title)
        binding.messageText.text = getString(R.string.welcome_message)
    }

    /**
     * Registra información sobre la pantalla actual
     */
    private fun logScreenInfo() {
        val displayMetrics = resources.displayMetrics
        val density = displayMetrics.density
        val widthDp = (displayMetrics.widthPixels / density).toInt()
        val heightDp = (displayMetrics.heightPixels / density).toInt()
        val orientation = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            "Landscape"
        } else {
            "Portrait"
        }
        
        Log.d("MainActivity", "Screen size: ${widthDp}x${heightDp}dp, Orientation: $orientation")
    }
}
