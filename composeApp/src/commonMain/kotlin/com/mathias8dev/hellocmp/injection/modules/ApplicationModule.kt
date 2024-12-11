package com.mathias8dev.hellocmp.injection.modules

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module


@Module(includes = [HttpClientModule::class])
@ComponentScan
class ApplicationModule