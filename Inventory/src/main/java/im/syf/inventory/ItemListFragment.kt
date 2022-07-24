package im.syf.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import im.syf.inventory.databinding.ItemListFragmentBinding

/**
 * Main fragment displaying details for all items in the database.
 */
class ItemListFragment : Fragment() {

    private var _binding: ItemListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InventoryViewModel by activityViewModels {
        val inventoryApplication = activity?.application as InventoryApplication
        InventoryViewModelFactory(inventoryApplication.database.itemDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemListAdapter {
            val action = ItemListFragmentDirections.toItemDetailFragment(it.id)
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter
        binding.floatingActionButton.setOnClickListener {
            val action = ItemListFragmentDirections.toAddItemFragment(
                getString(R.string.add_fragment_title)
            )
            findNavController().navigate(action)
        }

        viewModel.allItems.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
