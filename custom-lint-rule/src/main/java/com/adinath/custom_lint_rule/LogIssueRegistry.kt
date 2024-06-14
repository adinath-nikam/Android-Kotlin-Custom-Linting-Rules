package com.adinath.custom_lint_rule

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class LogIssueRegistry: IssueRegistry() {
    override val issues:List<Issue> = listOf(LogDetector.ISSUE)
    override val api: Int = CURRENT_API
}