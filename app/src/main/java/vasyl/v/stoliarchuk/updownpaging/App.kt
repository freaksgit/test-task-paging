package vasyl.v.stoliarchuk.updownpaging

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import vasyl.v.stoliarchuk.updownpaging.di.component.DaggerAppComponent

class App : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .application(this)
                .build()
    }
}