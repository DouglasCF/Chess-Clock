import SwiftUI
import shared

struct ContentView: View {
    @StateObject var homeViewModel = HomeViewModel()
    @StateObject var settingsViewModel = SettingsViewModel()
    
	var body: some View {
        NavigationView {
            HomeView()
        }
        .environmentObject(homeViewModel)
        .environmentObject(settingsViewModel)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
