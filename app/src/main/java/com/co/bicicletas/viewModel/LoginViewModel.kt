package com.co.bicicletas.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co.bicicletas.model.entities.BodyResponse
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.model.network.networkApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel: ViewModel() {
    private val backendApiService = networkApiService()

   /**
     * A disposable container that can hold onto multiple other Disposables and
     * offers time complexity for add(Disposable), remove(Disposable) and delete(Disposable)
     * operations.
     */
    private val compositeDisposable = CompositeDisposable()
    /**
     * Creates a MutableLiveData with no value assigned to it.
     */
    val loadRandomDish = MutableLiveData<Boolean>()
    val loginResponse = MutableLiveData<BodyResponse>()
    val randomDishLoadingError = MutableLiveData<Boolean>()

    fun getLogin(body:LoginDTO) {
        // Define the value of the load random dish.
        loadRandomDish.value = true
        // Adds a Disposable to this container or disposes it if the container has been disposed.
        compositeDisposable.add(
            // Call the RandomDish method of RandomDishApiService class.
            backendApiService.doLogin(body)
                // Asynchronously subscribes SingleObserver to this Single on the specified Scheduler.
                /**
                 * Static factory methods for returning standard Scheduler instances.
                 *
                 * The initial and runtime values of the various scheduler types can be overridden via the
                 * {RxJavaPlugins.setInit(scheduler name)SchedulerHandler()} and
                 * {RxJavaPlugins.set(scheduler name)SchedulerHandler()} respectively.
                 */
                .subscribeOn(Schedulers.newThread())
                /**
                 * Signals the success item or the terminal signals of the current Single on the specified Scheduler,
                 * asynchronously.
                 *
                 * A Scheduler which executes actions on the Android main thread.
                 */
                .observeOn(AndroidSchedulers.mainThread())
                /**
                 * Subscribes a given SingleObserver (subclass) to this Single and returns the given
                 * SingleObserver as is.
                 */
                .subscribeWith(object : DisposableSingleObserver<BodyResponse>() {
                    override fun onSuccess(value: BodyResponse?) {
                        // Update the values with response in the success method.
                        loadRandomDish.value = false
                        loginResponse.value = value
                        randomDishLoadingError.value = false
                    }
                    override fun onError(e: Throwable?) {
                        // Update the values in the response in the error method
                        loadRandomDish.value = false
                        randomDishLoadingError.value = true
                    }
                })
        )
    }

}