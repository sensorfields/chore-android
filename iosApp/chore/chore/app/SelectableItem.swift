import App
import Foundation

struct SelectableItem<Element: Any> {
    let value: Element
    var isOn: Bool
}

func generateSelectableItems<Element: CaseIterable>(
    selected: Set<Element>,
) -> [SelectableItem<Element>] {
    return Element.allCases.map { value in
        SelectableItem(value: value, isOn: selected.contains(value))
    }
}
