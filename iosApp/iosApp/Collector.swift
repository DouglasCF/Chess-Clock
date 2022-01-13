import Foundation
import shared

class Collector<T>: Kotlinx_coroutines_coreFlowCollector {
    
    let callback:(T) -> Void
    
    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }
    
    
    func emit(value: Any?, completionHandler: @escaping (KotlinUnit?, Error?) -> Void) {
        print("ios received flow: " + String(describing: value))
        callback(value as! T)
        completionHandler(KotlinUnit(), nil)
    }
}
