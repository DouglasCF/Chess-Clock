import SwiftUI

struct SettingsView: View {
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text("Game Mode")
                    .fontWeight(.bold)
                    .padding([.leading, .top])
                
                ScrollView(.horizontal, showsIndicators: false) {
                    LazyHStack(spacing: 12) {
                        Spacer()
                        GameModeItem(title: "2 | 1")
                        GameModeItem(title: "5 min")
                        GameModeItem(title: "10 | 10")
                        GameModeItem(title: "2 | 1")
                        GameModeItem(title: "5 min")
                        GameModeItem(title: "10 | 10")
                        GameModeItem(title: "2 | 1")
                        GameModeItem(title: "5 min")
                        GameModeItem(title: "10 | 10")
                    }
                }
                .frame(height: 70)

                Spacer()
            }
        }
        .navigationBarTitle("Settings")
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView()
    }
}
