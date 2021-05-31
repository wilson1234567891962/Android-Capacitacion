import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.co.bicicletas.utils.loader.LoaderFragment
fun FragmentActivity.showLoader() {
    if (isFinishing || isDestroyed) return
    hideLoader()
    val loaderFragment: LoaderFragment = LoaderFragment.newInstance()
    if (!loaderFragment.isVisible)
        loaderFragment.show(supportFragmentManager, "loaderFragment")
}
fun <T : Fragment> FragmentActivity.findFragment(tag: String): T? {
    return supportFragmentManager.findFragmentByTag(tag) as? T
}
fun FragmentActivity.fragmentTransaction(func: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply(func).commit()
}
fun FragmentActivity.hideLoader() {
    if (isFinishing || isDestroyed) return
    val loaderFragment: DialogFragment? = findFragment("loaderFragment")
    loaderFragment?.let {
        it.dismiss()
        fragmentTransaction {
            remove(it)
        }
    }
}