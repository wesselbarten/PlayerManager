package nl.wesselbarten.playermanager.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import nl.wesselbarten.playermanager.databinding.FragmentPlayersListBinding

@AndroidEntryPoint
class PlayersListFragment : Fragment() {

    private val playersAdapter: PlayersAdapter = PlayersAdapter()
    private val viewModel: PlayersViewModel by viewModels({requireActivity()})

    private lateinit var binding: FragmentPlayersListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.rcvPlayers.adapter = playersAdapter
        setupClickListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
    }

    private fun setupClickListeners() {
        binding.btnSelectPlayersSortOrder.setOnClickListener {
            SelectPlayersSortOrderBottomSheetFragment().show(
                parentFragmentManager,
                SelectPlayersSortOrderBottomSheetFragment::class.simpleName
            )
        }
    }

    private fun subscribeUi() {
        viewModel.getPlayersState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is GetPlayersState.Success -> {
                    playersAdapter.submitList(state.players)
                }
                is GetPlayersState.Error -> {

                }
            }
        })
    }
}