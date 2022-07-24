package im.syf.inventory

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import im.syf.inventory.data.Item
import im.syf.inventory.databinding.FragmentAddItemBinding

/**
 * Fragment to add or update an item in the Inventory database.
 */
class AddItemFragment : Fragment() {

    private val args: ItemDetailFragmentArgs by navArgs()

    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InventoryViewModel by activityViewModels {
        val inventoryApplication = activity?.application as InventoryApplication
        InventoryViewModelFactory(inventoryApplication.database.itemDao())
    }

    lateinit var item: Item

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.itemId > 0) {
            viewModel.retrieveItem(args.itemId).observe(viewLifecycleOwner) {
                item = it
                bind(it)
            }
        } else {
            binding.saveAction.setOnClickListener {
                addNewItem()
            }
        }
    }

    private fun addNewItem() {
        if (isEntryValid()) {
            viewModel.addNewItem(
                binding.itemName.text.toString(),
                binding.itemPrice.text.toString(),
                binding.itemCount.text.toString(),
            )

            findNavController().navigate(AddItemFragmentDirections.toItemListFragment())
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.itemName.text.toString(),
            binding.itemPrice.text.toString(),
            binding.itemCount.text.toString()
        )
    }

    private fun bind(item: Item) {
        val price = "%.2f".format(item.itemPrice)

        with(binding) {
            itemName.setText(item.itemName, TextView.BufferType.SPANNABLE)
            itemPrice.setText(price, TextView.BufferType.SPANNABLE)
            itemCount.setText(item.quantityInStock.toString(), TextView.BufferType.SPANNABLE)
            saveAction.setOnClickListener { updateItem() }
        }
    }

    private fun updateItem() {
        if (isEntryValid()) {
            viewModel.updateItem(
                args.itemId,
                binding.itemName.text.toString(),
                binding.itemPrice.text.toString(),
                binding.itemCount.text.toString()
            )

            findNavController().navigate(AddItemFragmentDirections.toItemListFragment())
        }
    }

    /**
     * Called before fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()

        // Hide keyboard.
        val inputMethodManager = requireActivity()
            .getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)

        _binding = null
    }
}
