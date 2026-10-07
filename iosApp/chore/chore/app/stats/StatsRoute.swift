import App
import SwiftUI

struct StatsRoute: View {
    var body: some View {
        Text(Res.string.shared.stats_title.format())
    }
}

#Preview {
    StatsRoute()
}
