package gbcorp.c362.gemvault.pro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import gbcorp.c362.gemvault.pro.ui.composable.approot.AppRoot
import gbcorp.c362.gemvault.pro.ui.theme.ProductAppGMVTTheme

class GMVTMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppGMVTTheme {
                AppRoot()
            }
        }
    }
}