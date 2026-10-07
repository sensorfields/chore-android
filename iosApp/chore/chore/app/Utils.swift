import App
import Foundation

extension Int32 {
    var int: Int { Int(self) }
}

extension Int {
    var int32: Int32 { Int32(self) }
}

extension KotlinIntRange {
    var range: CountableRange<Int> { first.int..<last.int }
}
