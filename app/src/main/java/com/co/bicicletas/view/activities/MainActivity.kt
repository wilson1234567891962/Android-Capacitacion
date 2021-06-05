package com.co.bicicletas.view.activities

import OneTimeRequestWorker
import PeriodicRequestWorker
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

import androidx.work.NetworkType
import com.co.bicicletas.R
import com.co.bicicletas.application.BcicletasApplications

import com.co.bicicletas.model.entities.database.Login
import com.co.bicicletas.utils.BigPictureStyleMockData
import com.co.bicicletas.utils.InboxStyleMockData
import com.co.bicicletas.utils.NotificationUtil
import com.co.bicicletas.viewmodel.LoginViewModel
import com.co.bicicletas.viewmodel.LoginViewModelFactory
import hideLoader
import android.widget.Button
import android.widget.TextView
import androidx.work.*
import com.co.bicicletas.model.entities.LoginDTO
import showLoader
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var usuario:EditText
    private lateinit var pass:EditText
    private lateinit var olvidoC:TextView
    private lateinit var buttonAcc: Button
    private lateinit var checkboxUser: CheckBox
    var loginDatabase = Login(-1,"","", false)

    private val NOTIFICATION_ID = 888
    private lateinit var mNotificationManagerCompat: NotificationManagerCompat
//    private lateinit var loginViewModel: LoginViewModel
private val loginViewModel: LoginViewModel by viewModels {
    LoginViewModelFactory((this.application as BcicletasApplications).repository)
}

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d("PRUEBA","A1")
        setContentView(R.layout.activity_main)
        usuario = findViewById<EditText>(R.id.editText2)
        pass = findViewById<EditText>(R.id.editText)
        olvidoC = findViewById<TextView>(R.id.textView3)
        buttonAcc = findViewById<Button>(R.id.button1)
        checkboxUser= findViewById<CheckBox>(R.id.checkboxLogin)

        buttonAcc.setOnClickListener(::login)



        olvidoC.setOnClickListener(::resetPass);

        //inicializar
        mNotificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)

        this.getUserById()

    }

private fun resetPass(p : View? ) {
    val myIntent = Intent(this, frogotPass::class.java)


    this.startActivity(myIntent)

}

    fun login(p : View?){
    this.showLoader()
        loginViewModel.getLogin(LoginDTO(pass.text.toString() , usuario.text.toString()))
        getViewModelObserver()

//        generateInboxStyleNotification()
        onePirierManager()
//        periodWork()
    }

    fun getViewModelObserver() {

        loginViewModel.loginResponse.observe(this) { login ->
            login.let {
                //Log.d("login","A2")
//                Toast.makeText(applicationContext, it.data.token, Toast.LENGTH_SHORT).show()
                this.hideLoader()
                val myIntent = Intent(this, HomeActivity::class.java)
                this.startActivity(myIntent)
            }
        }

    }

    fun getUserById() {

        loginViewModel.getUserById.observe(this) { login ->
            login.let {
                if (login.isNotEmpty()) {
                    val userFilter = login.first()

                    checkboxUser.isChecked = userFilter.state
                    if(userFilter.state) {
                        (this.usuario as TextView).text = userFilter.email
                        (this.pass as TextView).text = userFilter.pass
                    }
                    this.loginDatabase = userFilter
                }
            }

            this.checkboxListener()


        }
    }

    fun checkboxListener(){
        checkboxUser.setOnCheckedChangeListener { checked, isChecked ->
            this.insertUserWhenHeChecksTheCheckBox(isChecked)

        }

    }

    private fun insertUserWhenHeChecksTheCheckBox(state: Boolean) {
        if(loginDatabase.id !== -1) {
            loginDatabase.pass = pass.text.toString()
            loginDatabase.email = usuario.text.toString()
            loginDatabase.state = state
            loginDatabase.id = 1
            loginViewModel.updateUser(loginDatabase)
        } else {
            loginDatabase.state = state
            loginDatabase.email = usuario.text.toString()
            loginDatabase.state = state
            loginDatabase.id = 1
            loginViewModel.insertUser(loginDatabase)
        }
    }

    /*
     * Generates a INBOX_STYLE Notification that supports both phone/tablet and wear. For devices
     * on API level 16 (4.1.x - Jelly Bean) and after, displays INBOX_STYLE. Otherwise, displays a
     * basic notification.
     */
    private fun generateInboxStyleNotification() {
        // Main steps for building a INBOX_STYLE notification:
        //      0. Get your data
        //      1. Create/Retrieve Notification Channel for O and beyond devices (26+)
        //      2. Build the INBOX_STYLE
        //      3. Set up main Intent for notification
        //      4. Build and issue the notification
        // 1. Create/Retrieve Notification Channel for O and beyond devices (26+).
        val notificationChannelId: String =
            NotificationUtil().createInboxStyleNotificationChannel(this)
        // 2. Build the INBOX_STYLE.
        val inboxStyle =
            NotificationCompat.InboxStyle() // This title is slightly different than regular title, since I know INBOX_STYLE is
                // available.
                .setBigContentTitle(InboxStyleMockData.mBigContentTitle)
                .setSummaryText(InboxStyleMockData.mSummaryText)
        // Add each summary line of the new emails, you can add up to 5.
        for (summary in InboxStyleMockData.mIndividualEmailSummary()) {
            inboxStyle.addLine(summary)
        }
        // 3. Set up main Intent for notification that is the Activity that you want to launch when user tap on notification.
        val mainIntent = Intent(this, MainActivity::class.java)
        // Gets a PendingIntent containing the intent.
        val mainPendingIntent = PendingIntent.getActivity(
            this,
            0,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        // 4. Build and issue the notification.
        // Because we want this to be a new notification (not updating a previous notification), we
        // create a new Builder. However, we don't need to update this notification later, so we
        // will not need to set a global builder for access to the notification later.
        val notificationCompatBuilder = NotificationCompat.Builder(
            applicationContext,
            notificationChannelId
        )
        notificationCompatBuilder // INBOX_STYLE sets title and content for API 16+ (4.1 and after) when the
            // notification is expanded.
            .setStyle(inboxStyle) // Title for API <16 (4.0 and below) devices and API 16+ (4.1 and after) when the
            // notification is collapsed.
            .setContentTitle(InboxStyleMockData.mContentTitle) // Content for API <24 (7.0 and below) devices and API 16+ (4.1 and after) when the
            // notification is collapsed.
            .setContentText(InboxStyleMockData.mContentText)
            .setSmallIcon(R.drawable.ejemplo)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ejemplo
                )
            )
            .setContentIntent(mainPendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL) // Set primary color (important for Wear 2.0 Notifications).
            .setColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.purple_500
                )
            ) // SIDE NOTE: Auto-bundling is enabled for 4 or more notifications on API 24+ (N+)
            // devices and all Wear devices. If you have more than one notification and
            // you prefer a different summary notification, set a group key and create a
            // summary notification via
            // .setGroupSummary(true)
            // .setGroup(GROUP_KEY_YOUR_NAME_HERE)
            // Sets large number at the right-hand side of the notification for API <24 devices.
            .setSubText(InboxStyleMockData.mNumberOfNewEmails.toString())
            .setCategory(Notification.CATEGORY_EMAIL) // Sets priority for 25 and below. For 26 and above, 'priority' is deprecated for
            // 'importance' which is set in the NotificationChannel. The integers representing
            // 'priority' are different from 'importance', so make sure you don't mix them.
            .setPriority(InboxStyleMockData.mPriority) // Sets lock-screen visibility for 25 and below. For 26 and above, lock screen
            // visibility is set in the NotificationChannel.
            .setVisibility(InboxStyleMockData.mChannelLockscreenVisibility)
        // If the phone is in "Do not disturb mode, the user will still be notified if
        // the sender(s) is starred as a favorite.
        for (name in InboxStyleMockData.mParticipants()) {
            notificationCompatBuilder.addPerson(name)
        }
        val notification = notificationCompatBuilder.build()
        mNotificationManagerCompat.notify(NOTIFICATION_ID, notification)
    }
    // TODO Step 5: Create a function to generate and launch the BigPictureStyle notification.
// START
/*
     * Generates a BIG_PICTURE_STYLE Notification that supports both phone/tablet and wear. For
     * devices on API level 16 (4.1.x - Jelly Bean) and after, displays BIG_PICTURE_STYLE.
     * Otherwise, displays a basic notification.
     *
     * This example Notification is a social post.
     */
    private fun generateBigPictureStyleNotification() {
        // Main steps for building a BIG_PICTURE_STYLE notification:
        //      0. Get your data
        //      1. Create/Retrieve Notification Channel for O and beyond devices (26+)
        //      2. Build the BIG_PICTURE_STYLE
        //      3. Set up main Intent for notification
        //      4. Set up RemoteInput, so users can input (keyboard and voice) from notification
        //      5. Build and issue the notification
        // 0. Get your data (everything unique per Notification).
        // 1. Create/Retrieve Notification Channel for O and beyond devices (26+).
        val notificationChannelId: String =
            NotificationUtil().createBigPictureStyleNotificationChannel(this@MainActivity)
        // 2. Build the BIG_PICTURE_STYLE.
        val bigPictureStyle =
            NotificationCompat.BigPictureStyle() // Provides the bitmap for the BigPicture notification.
                .bigPicture(
                    BitmapFactory.decodeResource(
                        resources,
                        BigPictureStyleMockData.mBigImage
                    )
                ) // Overrides ContentTitle in the big form of the template.
                .setBigContentTitle(BigPictureStyleMockData.mBigContentTitle) // Summary line after the detail section in the big form of the template.
                .setSummaryText(BigPictureStyleMockData.mSummaryText)
        // 3. Set up main Intent for notification.
        val mainIntent = Intent(this, MainActivity::class.java)
        // Gets a PendingIntent containing the mainIntent.
        val mainPendingIntent = PendingIntent.getActivity(
            this,
            0,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        // 5. Build and issue the notification.
        // Because we want this to be a new notification (not updating a previous notification), we
        // create a new Builder. Later, we use the same global builder to get back the notification
        // we built here for a comment on the post.
        val notificationCompatBuilder = NotificationCompat.Builder(
            applicationContext, notificationChannelId
        )
        notificationCompatBuilder // BIG_PICTURE_STYLE sets title and content for API 16 (4.1 and after).
            .setStyle(bigPictureStyle) // Title for API <16 (4.0 and below) devices.
            .setContentTitle(BigPictureStyleMockData.mContentTitle) // Content for API <24 (7.0 and below) devices.
            .setContentText(BigPictureStyleMockData.mContentText)
            .setSmallIcon(R.drawable.ejemplo)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ejemplo
                )
            )
            .setContentIntent(mainPendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL) // Set primary color (important for Wear 2.0 Notifications).
            .setColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.purple_500
                )
            ) // SIDE NOTE: Auto-bundling is enabled for 4 or more notifications on API 24+ (N+)
            // devices and all Wear devices. If you have more than one notification and
            // you prefer a different summary notification, set a group key and create a
            // summary notification via
            // .setGroupSummary(true)
            // .setGroup(GROUP_KEY_YOUR_NAME_HERE)
            .setSubText(1.toString())
            .setCategory(Notification.CATEGORY_SOCIAL) // Sets priority for 25 and below. For 26 and above, 'priority' is deprecated for
            // 'importance' which is set in the NotificationChannel. The integers representing
            // 'priority' are different from 'importance', so make sure you don't mix them.
            .setPriority(BigPictureStyleMockData.mPriority) // Sets lock-screen visibility for 25 and below. For 26 and above, lock screen
            // visibility is set in the NotificationChannel.
            .setVisibility(BigPictureStyleMockData.mChannelLockscreenVisibility)
        // If the phone is in "Do not disturb mode, the user will still be notified if
        // the sender(s) is starred as a favorite.
        for (name in BigPictureStyleMockData.mParticipants()) {
            notificationCompatBuilder.addPerson(name)
        }
        val notification = notificationCompatBuilder.build()
        // TODO Step 6: Notify the user using notification id and Notification builder with notification manager.
        // START
        mNotificationManagerCompat.notify(NOTIFICATION_ID, notification)
        // END
    }



    override fun onStart() {
        super.onStart()
        Log.d("PRUEBA","A2")
    }

    override fun onResume() {
        super.onResume()
        Log.d("PRUEBA","A3")

    }

    override fun onPause() {
        super.onPause()
        Log.d("PRUEBA","A4")
    }

    override fun onStop() {
        super.onStop()
        Log.d("PRUEBA","A5")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PRUEBA","A6")

    }


//        loginViewModel.loadRandomDish.observe(this, Observer { loader ->
//            loader?.let {
//                // Show the progress dialog if the SwipeRefreshLayout is not visible and hide when the usage is completed.
//                if (loader) {
//                    this.showLoader()
//                } else {
//                    this.hideLoader()
//                }
//            }
//        })
//            }

    fun onePrimerManager(){
        /**
         * A specification of the requirements that need to be met before a WorkRequest can run.  By
         * default, WorkRequests do not have any requirements and can run immediately.  By adding
         * requirements, you can make sure that work only runs in certain situations - for example, when you
         * have an unmetered network and are charging.
         */
        val oneTimeRequestConstraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        // Define the input data for work manager
        // A persistable set of key/value pairs which are used as inputs and outputs for ListenableWorkers.
        val data = Data.Builder()
        data.putString("inputKey", "Input Value")
        // Create an one time work request
        /**
         * A WorkRequest for non-repeating work.
         * OneTimeWorkRequests can be put in simple or complex graphs of work by using methods.
         */
        val sampleWork = OneTimeWorkRequest
            .Builder(OneTimeRequestWorker::class.java)
            /**
             * Adds input Data to the work.  If a worker has prerequisites in its chain, this
             * Data will be merged with the outputs of the prerequisites using an InputMerger.
             *
             * @param inputData key/value pairs that will be provided to the worker
             */
            .setInputData(data.build())
            /**
             * Adds constraints to the WorkRequest.
             */
            .setConstraints(oneTimeRequestConstraints)
            // Builds a {@link WorkRequest} based on this {@link Builder}
            .build()
        // Retrieves the default singleton instance of WorkManager and Enqueues one item for background processing.
        WorkManager.getInstance(this@MainActivity).enqueue((sampleWork))
        // Gets a LiveData of the WorkInfo for a given work id.
        WorkManager.getInstance(this@MainActivity).getWorkInfoByIdLiveData(sampleWork.id)
            .observe(this, Observer { workInfo ->
                OneTimeRequestWorker.Companion.logger(workInfo.state.name)
                if (workInfo != null) {
                    when (workInfo.state) {
                        WorkInfo.State.ENQUEUED -> {
                            // Show the work state in text view
                            olvidoC.text = "Task enqueued."
                        }
                        WorkInfo.State.BLOCKED -> {
                            olvidoC.text = "Task blocked."
                        }
                        WorkInfo.State.RUNNING -> {
                            olvidoC.text = "Task running."
                        }
                        else -> {
                            olvidoC.text = "Task state else part."
                        }
                    }
                }
                // When work finished
                if (workInfo != null && workInfo.state.isFinished) {
                    when (workInfo.state) {
                        WorkInfo.State.SUCCEEDED -> {
                            olvidoC.text = "Task successful."
                            // Get the output data
                            val successOutputData = workInfo.outputData
                            val outputText = successOutputData.getString("outputKey")
                            Log.i("Worker Output", "$outputText")
                        }
                        WorkInfo.State.FAILED -> {
                            olvidoC.text = "Task Failed."
                        }
                        WorkInfo.State.CANCELLED -> {
                            olvidoC.text = "Task cancelled."
                        }
                        else -> {
                            olvidoC.text = "Task state isFinished else part."
                        }
                    }
                }
            })
    }

    fun periodWork(){
        /**
         * Constraints ensure that work is deferred until optimal conditions are met.
         *
         * A specification of the requirements that need to be met before a WorkRequest can run.
         * By default, WorkRequests do not have any requirements and can run immediately.
         * By adding requirements, you can make sure that work only runs in certain situations
         * - for example, when you have an unmetered network and are charging.
         */
        // For more details visit the link https://medium.com/androiddevelopers/introducing-workmanager-2083bcfc4712
        val periodicRequestConstraints = Constraints.Builder()
            .setRequiresBatteryNotLow(false)
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()
        // Create an Periodic Work Request
        /**
         * You can use any of the work request builder that are available to use.
         * We will you the PeriodicWorkRequestBuilder as we want to execute the code periodically.
         *
         * The minimum time you can set is 15 minutes. You can check the same on the below link.
         * https://developer.android.com/reference/androidx/work/PeriodicWorkRequest
         *
         * You can also set the TimeUnit as per your requirement. for example SECONDS, MINUTES, or HOURS.
         */
        // setting period to 15 Minutes
        val periodicWorkRequest =
            PeriodicWorkRequest.Builder(PeriodicRequestWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(periodicRequestConstraints)
                .build()
        /* Enqueue a work, ExistingPeriodicWorkPolicy.KEEP means that if this work already exists, it will be kept
        if the value is ExistingPeriodicWorkPolicy.REPLACE, then the work will be replaced */
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "Periodic Work Request",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }


}