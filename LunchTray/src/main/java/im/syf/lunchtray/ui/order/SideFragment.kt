package im.syf.lunchtray.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import im.syf.lunchtray.databinding.FragmentSideBinding
import im.syf.lunchtray.model.OrderViewModel

/**
 * [SideFragment] allows people to add a side to the order or cancel the order.
 */
class SideFragment : Fragment() {

    // Binding object instance corresponding to the fragment_side.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: FragmentSideBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            // TODO: initialize the SideMenuFragment variables
            sideFragment = this@SideFragment
        }
    }

    /**
     * Navigate to the accompaniments menu fragment
     */
    fun goToNextScreen() {
        // TODO: Navigate to the AccompanimentMenuFragment
        findNavController().navigate(SideFragmentDirections.toAccompanimentFragment())
    }

    /**
     * Cancel the order and start over.
     */
    fun cancelOrder() {
        // TODO: Reset order in view model
        sharedViewModel.resetOrder()
        // TODO: Navigate back to the [StartFragment] to start over
        findNavController().navigate(SideFragmentDirections.toStartOrderFragment())
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
