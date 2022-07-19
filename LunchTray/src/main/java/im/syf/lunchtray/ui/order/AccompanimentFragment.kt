package im.syf.lunchtray.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import im.syf.lunchtray.databinding.FragmentAccompanimentBinding
import im.syf.lunchtray.model.OrderViewModel

/**
 * [AccompanimentFragment] allows people to add an accompaniment to their order or cancel the
 * order.
 */
class AccompanimentFragment : Fragment() {

    // Binding object instance corresponding to the fragment_accompaniment.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: FragmentAccompanimentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccompanimentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            // TODO: initialize the AccompanimentMenuFragment variables
            accompanimentFragment = this@AccompanimentFragment
        }
    }

    /**
     * Navigate to the checkout fragment.
     */
    fun goToNextScreen() {
        // TODO: Navigate to the CheckoutFragment
        findNavController().navigate(AccompanimentFragmentDirections.toCheckoutFragment())
    }

    /**
     * Cancel the order and start over.
     */
    fun cancelOrder() {
        // TODO: Reset order in view model
        // TODO: Navigate back to the [StartFragment] to start over
        findNavController().navigate(AccompanimentFragmentDirections.toStartOrderFragment())
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
