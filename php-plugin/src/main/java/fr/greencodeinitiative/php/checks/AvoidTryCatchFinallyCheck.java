package fr.greencodeinitiative.php.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.statement.TryStatementTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;
@Rule(
        key = AvoidTryCatchFinallyCheck.RULE_KEY,
        name = AvoidTryCatchFinallyCheck.ERROR_MESSAGE,
        description = AvoidTryCatchFinallyCheck.MESSAGE,
        priority = Priority.MINOR,
        tags = {"eco-design", "ecocode"})
public class AvoidTryCatchFinallyCheck  extends PHPVisitorCheck {
    public static final String RULE_KEY = "S34";
    public static final String KEY = "AvoidUsingFinallyInTryCatchCheck";
    public static final String MESSAGE = "Avoid using try-catch-finally";
    public static final String ERROR_MESSAGE = "Avoid using try-catch-finally";


    @Override
    public void visitTryStatement(TryStatementTree tree) {
        if(tree.finallyToken() != null){
            StringBuilder sb =  new StringBuilder();
            sb.append(tree.finallyToken().toString());
            sb.append(tree.finallyBlock().toString());
            context().newIssue(this, tree.finallyToken(), String.format(MESSAGE, sb));

        }
    }
}
