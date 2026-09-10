import App
import SwiftUI

struct SettingsRoute: View {
    var body: some View {
        Text(Res.string.shared.settings_title.format())
    }
}

#Preview {
    SettingsRoute()
}
