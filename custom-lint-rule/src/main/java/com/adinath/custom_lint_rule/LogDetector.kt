package com.adinath.custom_lint_rule

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression
class LogDetector: Detector(), SourceCodeScanner {
    override fun getApplicableMethodNames(): List<String> {
        return listOf("w", "e", "d")
    }

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)
        val evaluator = context.evaluator
        if(evaluator.isMemberInClass(method, "android.util.Log")){
            reportIssue(context, node)
        }
    }

    private fun reportIssue(context: JavaContext, node: UCallExpression){
        context.report(
            issue = ISSUE,
            scope = node,
            location = context.getCallLocation(
                call = node,
                includeReceiver = true,
                includeArguments = true
            ),
            message = "Avoid Usage Log Function Call"
        )
    }

    companion object {
        val ISSUE: Issue = Issue.create(
            id = "idRestrictedLogUsage",
            briefDescription = "Log Usage is not Recommended in this Project.",
            explanation = "Please Avoid Usage of Log Statements in this Project.",
            category = Category.CORRECTNESS,
            priority = 5,
            severity = Severity.WARNING,
            implementation = Implementation(
                LogDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}