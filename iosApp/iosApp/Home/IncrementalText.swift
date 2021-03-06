import SwiftUI

struct IncrementalText: View {
    var value: String
    
    var body: some View {
        VStack {
            Spacer()
            HStack(spacing: 2) {
                Image(systemName: "arrow.up")
                    .font(.system(size: 14))
                Text(value)
                    .font(.system(size: 16))
            }
            .opacity(value == "0" ? 0.3 : 1)
            .padding()
        }
    }
}

struct IncrementalText_Previews: PreviewProvider {
    static var previews: some View {
        IncrementalText(value: "3")
    }
}
