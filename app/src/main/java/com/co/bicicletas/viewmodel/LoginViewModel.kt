package com.co.bicicletas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co.bicicletas.model.entities.BodyLogin
import com.co.bicicletas.model.entities.BodyRemember
import com.co.bicicletas.model.entities.ResponseLogin
import com.co.bicicletas.model.entities.ResponseRemember
import com.co.bicicletas.model.network.BackendAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel: ViewModel() {


    val service = BackendAPIService()

    /**
     * A disposable container that can hold onto multiple other Disposables and
     * offers time complexity for add(Disposable), remove(Disposable) and delete(Disposable)
     * operations.
     */
    private val compositeDisposable = CompositeDisposable()

    /**
     * Creates a MutableLiveData with no value assigned to it.
     */
    val loadLogin = MutableLiveData<Boolean>()
    val loginResponse = MutableLiveData<ResponseLogin>()
    val loginLoadingError = MutableLiveData<Boolean>()
    fun login(body:BodyLogin){

            // Define the value of the load random dish.
            loadLogin.value = true
            // Adds a Disposable to this container or disposes it if the container has been disposed.
            compositeDisposable.add(
                // Call the RandomDish method of RandomDishApiService class.
                service.doLogin(body)
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
                    .subscribeWith(object : DisposableSingleObserver<ResponseLogin>() {
                        override fun onSuccess(value: ResponseLogin?) {
                            // Update the values with response in the success method.
                            loadLogin.value = false
                            loginResponse.value = value
                            loginLoadingError.value = false
                        }
                        override fun onError(e: Throwable?) {
                            // Update the values in the response in the error method
                            loadLogin.value = false
                            loginLoadingError.value = true
                        }
                    })
            )
        }




}