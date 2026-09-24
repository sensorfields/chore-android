import App
import SwiftUI

struct ChoreCreateWhenMonth: View {

    @Binding var items: [SelectableItem<Int>]

    var body: some View {
        Grid {
            ForEach(0..<AppConfig.companion.MONTH_ROWS.int, id: \.self) { row in
                GridRow {
                    ForEach(0..<AppConfig.companion.MONTH_COLUMNS.int, id: \.self) { column in
                        let index = row * AppConfig.companion.MONTH_COLUMNS.int + column
                        if items.indices.contains(index) {
                            let item = items[index]
                            Toggle(
                                item.value.int32.formatDayOfMonth(padding: false),
                                isOn: $items[index].isOn,
                            )
                        }
                    }
                }
            }
        }
        .toggleStyle(.button)
    }
}

#Preview {
    ChoreCreateWhenMonth(
        items: Binding.constant(
            generateSelectableItems(
                range: AppConfig.companion.DAY_OF_MONTH_RANGE.range,
                selected: [5, 13, 27],
            ),
        ),
    )
}
