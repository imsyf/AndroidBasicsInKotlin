package im.syf.lunchtray.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import im.syf.lunchtray.R
import im.syf.lunchtray.databinding.FragmentCheckoutBinding
import im.syf.lunchtray.model.OrderViewModel

/**
 * [CheckoutFragment] allows people to apply a coupon to their order, submit order, or cancel order.
 */
class CheckoutFragment : Fragment() {

    // Binding object instance corresponding to the fragment_checkout.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: FragmentCheckoutBinding? = null

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
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)

        // Calculate tax and total upon creating the CheckoutFragment view
        sharedViewModel.calculateTaxAndTotal()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            // TODO: initialize the OrderViewModel and CheckoutFragment variables
            viewModel = sharedViewModel
            checkoutFragment = this@CheckoutFragment
        }
    }

    /**
     * Cancel the order and start over.
     */
    fun cancelOrder() {
        // TODO: Reset order in view model
        sharedViewModel.resetOrder()
        // TODO: Navigate back to the [StartFragment] to start over
        findNavController().navigate(CheckoutFragmentDirections.toStartOrderFragment())
    }

    /**
     * Submit order and navigate to home screen.
     */
    fun submitOrder() {
        // Show snackbar to "confirm" order
        Snackbar.make(binding.root, R.string.submit_order, Snackbar.LENGTH_SHORT).show()
        // TODO: Reset order in view model
        sharedViewModel.resetOrder()
        // TODO: Navigate back to the [StartFragment] to start over
        findNavController().navigate(CheckoutFragmentDirections.toStartOrderFragment())
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
