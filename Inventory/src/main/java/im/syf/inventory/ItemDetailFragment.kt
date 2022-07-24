package im.syf.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import im.syf.inventory.data.Item
import im.syf.inventory.data.getFormattedPrice
import im.syf.inventory.databinding.FragmentItemDetailBinding

/**
 * [ItemDetailFragment] displays the details of the selected item.
 */
class ItemDetailFragment : Fragment() {

    private val args: ItemDetailFragmentArgs by navArgs()

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var item: Item

    private val viewModel: InventoryViewModel by activityViewModels {
        val inventoryApplication = activity?.application as InventoryApplication
        InventoryViewModelFactory(inventoryApplication.database.itemDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.retrieveItem(args.itemId).observe(viewLifecycleOwner) {
            item = it
            bind(it)
        }
    }

    private fun bind(item: Item) = with(binding) {
        itemName.text = item.itemName
        itemPrice.text = item.getFormattedPrice()
        itemCount.text = item.quantityInStock.toString()

        sellItem.isEnabled = viewModel.isStockAvailable(item)
        sellItem.setOnClickListener {
            viewModel.sellItem(item)
        }

        deleteItem.setOnClickListener {
            showConfirmationDialog()
        }
    }

    /**
     * Displays an alert dialog to get the user's confirmation before deleting the item.
     */
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteItem()
            }
            .show()
    }

    /**
     * Deletes the current item and navigates to the list fragment.
     */
    private fun deleteItem() {
        viewModel.deleteItem(item)
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
