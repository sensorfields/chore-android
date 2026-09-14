import App
import SwiftUI

struct ChoreCreateWhenWeek: View {

    @Binding var items: [SelectableItem<DayOfWeek>]

    var body: some View {
        List(items.enumerated(), id: \.offset) { index, item in
            Toggle(item.value.format(), isOn: $items[index].isOn)
        }
    }
}

#Preview {
    ChoreCreateWhenWeek(
        items: Binding.constant([]),
    )
}
