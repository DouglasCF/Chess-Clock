import SwiftUI

struct GameModeItem: View {
    var title: String
    
    var body: some View {
        Text(title)
            .padding()
            .background(.red)
            .cornerRadius(100)
            .foregroundColor(.white)
    }
}

struct GameModeItem_Previews: PreviewProvider {
    static var previews: some View {
        GameModeItem(title: "2 | 1")
    }
}
