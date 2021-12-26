import SwiftUI

struct TimeText: View {
    var timer: TimeInterval
    
    var body: some View {
        Text("\(timer.stringFromTimeInterval())")
            .font(.system(size: 40))
            .padding()
    }
}

extension TimeInterval {
    
    func stringFromTimeInterval() -> String {
        
        let time = NSInteger(self)
        
        let seconds = time/100 % 60
        let minutes = (time / 60/100) % 60
        let hours = (time / 3600/100)
        let ms = time % 100
        
        if hours > 0 {
            return String(format:"%0.2d:%0.2d:%0.2d", hours, minutes, seconds, ms)
        } else if minutes > 0 {
            return String(format: "%0.2d:%0.2d", minutes, seconds, ms)
        } else {
            return String(format: "%0.2d", seconds, ms)
        }
    }
}

struct TimeText_Previews: PreviewProvider {
    static var previews: some View {
        TimeText(timer: TimeInterval(5*60*100))
    }
}
