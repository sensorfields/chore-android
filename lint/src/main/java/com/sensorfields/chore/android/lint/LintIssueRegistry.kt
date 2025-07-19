package com.sensorfields.chore.android.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import java.util.EnumSet

class LintIssueRegistry : IssueRegistry() {

    override val api: Int = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(testIssue)
}

private val testIssue = Issue.create(
    id = "test",
    briefDescription = "Test",
    explanation = "Test",
    category = Category.CORRECTNESS,
    priority = 3,
    severity = Severity.WARNING,
    implementation = Implementation(
        TestIssueDetector::class.java,
        EnumSet.of(Scope.JAVA_FILE),
    ),
)

class TestIssueDetector : Detector(), SourceCodeScanner
