package nl.wesselbarten.playermanager.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import nl.wesselbarten.playermanager.databinding.BottomSheetFragmentSelectPlayersSortOrderBinding
import nl.wesselbarten.playermanager.domain.model.PlayerSortOrder

@AndroidEntryPoint
class SelectPlayersSortOrderBottomSheetFragment : BottomSheetDialogFragment(),
    PlayerSortOrdersAdapter.OnClickListener {

    private val playerSortOrdersAdapter = PlayerSortOrdersAdapter(this)
    private val viewModel: PlayersViewModel by viewModels({ requireActivity() })

    private lateinit var binding: BottomSheetFragmentSelectPlayersSortOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetFragmentSelectPlayersSortOrderBinding.inflate(LayoutInflater.from(requireContext()), null, false)
        binding.lifecycleOwner = this
        binding.rcvPlayerSortOrders.adapter = playerSortOrdersAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
    }

    override fun onPlayerSortOrderClick(playerSortOrder: PlayerSortOrder) {
        viewModel.sortPlayers(playerSortOrder)
        dismiss()
    }

    private fun subscribeUi() {
        viewModel.playerSortOrders.observe(viewLifecycleOwner, {
            playerSortOrdersAdapter.submitList(it)
        })
    }
}