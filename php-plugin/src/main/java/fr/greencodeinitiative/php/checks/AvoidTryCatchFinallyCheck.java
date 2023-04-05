package fr.greencodeinitiative.php.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.statement.TryStatementTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;
@Rule(
        key = "S34",
        name = "Developpement",
        description = AvoidTryCatchFinallyCheck.MESSAGE,
        priority = Priority.MINOR,
        tags = {"bug", "ecocode", "eco-design"})
public class AvoidTryCatchFinallyCheck  extends PHPVisitorCheck {
    public static final String KEY = "AvoidUsingFinallyInTryCatchCheck";
    public static final String MESSAGE = "Avoid using try-catch-finally";


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
