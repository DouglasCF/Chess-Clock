import Foundation
import UIKit

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        // Prevent device from dimming and turning off
        UIApplication.shared.isIdleTimerDisabled = true
        return true
    }
}
