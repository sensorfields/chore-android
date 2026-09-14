import App
import SwiftUI

struct ChoreCreateWhen: View {

    let onRepeatClick: (ChoreCreateState.Repeat) -> Void

    var body: some View {
        ScrollView(.vertical) {
            VStack {
                ForEach(ChoreCreateState.Repeat.allCases, id: \.ordinal) { item in
                    Button(item.format(), action: { onRepeatClick(item) })
                }
                .buttonStyle(BorderedProminentButtonStyle())
            }
        }
    }
}

#Preview {
    ChoreCreateWhen(
        onRepeatClick: { _ in },
    )
}
