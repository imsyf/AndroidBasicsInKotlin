package im.syf.devbyte.playlist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import im.syf.devbyte.DevByteApp
import im.syf.devbyte.R
import im.syf.devbyte.databinding.FragmentDevByteBinding

class DevByteFragment : Fragment() {

    private val playlistViewModel: PlaylistViewModel by viewModels {
        val devByteApp = activity?.application as DevByteApp
        PlaylistViewModelFactory(devByteApp.container.service)
    }

    private var _binding: FragmentDevByteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dev_byte, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.adapter = DevByteVideoAdapter {
                val packageManager = context?.packageManager

                if (packageManager != null) {
                    // Try to generate a direct intent to the YouTube app
                    var intent = Intent(Intent.ACTION_VIEW, it.launchUri)

                    if (intent.resolveActivity(packageManager) == null) {
                        // YouTube app isn't found, use the web url
                        intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
                    }

                    startActivity(intent)
                }
            }

            lifecycleOwner = viewLifecycleOwner
            viewModel = playlistViewModel
        }

        playlistViewModel.eventNetworkError.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                if (!playlistViewModel.isNetworkErrorShown.value!!) {
                    Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
                    playlistViewModel.onNetworkErrorShown()
                }
            }
        }
    }

    /**
     * Helper method to generate YouTube app links
     */
    private val DevByteVideo.launchUri: Uri
        get() {
            val httpUri = Uri.parse(url)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
