import SwiftUI

struct ContentView: View {
    let viewModelFactory = ViewModelFactory()
    
	var body: some View {
        NavigationView {
            HomeView()
        }
        .environmentObject(viewModelFactory.makeHomeViewModel())
        .environmentObject(viewModelFactory.makeSettingsViewModel())
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}