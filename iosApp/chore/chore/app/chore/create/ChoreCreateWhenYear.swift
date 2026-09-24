import App
import SwiftUI

struct ChoreCreateWhenYear: View {

    @Binding var items: [SelectableItem<Month>]

    var body: some View {
        List(items.enumerated(), id: \.offset) { index, item in
            Toggle(item.value.format(), isOn: $items[index].isOn)
        }
        .toggleStyle(.button)
    }
}

#Preview {
    ChoreCreateWhenYear(
        items: Binding.constant(generateSelectableItems(selected: [.february, .july, .november])),
    )
}
