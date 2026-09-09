import App
import SwiftUI

struct ChoreCreateWhen: View {

    let onRepeatClick: (ChoreCreateState.When.WhenRepeat) -> Void

    var body: some View {
        VStack {
            ForEach(ChoreCreateState.When.WhenRepeat.allCases, id: \.ordinal) { item in
                Button(item.name, action: { onRepeatClick(item) })
            }
            .buttonStyle(BorderedProminentButtonStyle())
        }
    }
}

#Preview {
    ChoreCreateWhen(
        onRepeatClick: { _ in },
    )
}
