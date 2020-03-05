package es.iessaladillo.pedrojoya.quilloque

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setBottonNavigationView()
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            startFragment()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(AppBarConfiguration(navController.graph)) || super.onSupportNavigateUp()
    }

    private fun startFragment() = navController.navigate(R.id.dialFragment)

    private fun setBottonNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mnuDial -> navController.navigate(R.id.dialFragment)
                R.id.mnuRecent -> navController.navigate(R.id.recentFragment)
                R.id.mnuContacts -> navController.navigate(R.id.contactsFragment)
                else -> true
            }
            true
        }
    }

}
