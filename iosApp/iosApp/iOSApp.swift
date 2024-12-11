import ComposeApp
import SwiftUI

@main
struct iOSApp: App {
    init() {
        KoinInitializerKt.doInitKoin()
        NapierKt.debugBuild()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}