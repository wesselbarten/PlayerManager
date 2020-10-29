package nl.wesselbarten.playermanager.players

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import nl.wesselbarten.playermanager.R
import nl.wesselbarten.playermanager.databinding.ActivityPlayersBinding

@AndroidEntryPoint
class PlayersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_players)
        binding.lifecycleOwner = this
        showPlayersList()
    }

    private fun showPlayersList() {
        supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainerPlayers.id, PlayersListFragment(), PlayersListFragment::class.simpleName)
                .commit()
    }
}