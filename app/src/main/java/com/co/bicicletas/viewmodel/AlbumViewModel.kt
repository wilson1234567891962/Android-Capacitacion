package com.co.bicicletas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co.bicicletas.model.entities.ForgotDTO
import com.co.bicicletas.model.entities.ForgotResponseDTO
import com.co.bicicletas.model.network.BackendApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class AlbumViewModel: ViewModel() {

    private val backendApiService = BackendApiService()

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
    val forgotResponse = MutableLiveData<ForgotResponseDTO>()

    val randomDishLoadingError = MutableLiveData<Boolean>()


    fun getForgot( body : ForgotDTO) {
        // Define the value of the load random dish.
        loadRandomDish.value = true
        // Adds a Disposable to this container or disposes it if the container has been disposed.
        compositeDisposable.add(
            // Call the RandomDish method of RandomDishApiService class.
            backendApiService.doForgot(body)
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
                .subscribeWith(object : DisposableSingleObserver<ForgotResponseDTO>() {
                    override fun onSuccess(value: ForgotResponseDTO?) {
                        // Update the values with response in the success method.
                        loadRandomDish.value = false
                        forgotResponse.value = value
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