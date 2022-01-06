import SwiftUI

struct TimeText: View {
    var time: String
    
    var body: some View {
        Text(time)
            .font(.system(size: 40))
            .padding()
    }
}

struct TimeText_Previews: PreviewProvider {
    static var previews: some View {
        TimeText(time: "01:50:30")
    }
}
