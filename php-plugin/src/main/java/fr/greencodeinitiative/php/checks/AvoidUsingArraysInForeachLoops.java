package fr.greencodeinitiative.php.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.expression.AssignmentExpressionTree;
import org.sonar.plugins.php.api.tree.expression.ExpressionTree;
import org.sonar.plugins.php.api.tree.statement.ForEachStatementTree;
import org.sonar.plugins.php.api.visitors.PHPSubscriptionCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sonar.plugins.php.api.tree.Tree.Kind;

@Rule(
        key = AvoidUsingArraysInForeachLoops.RULE_KEY,
        name = AvoidUsingArraysInForeachLoops.ERROR_MESSAGE,
        description = AvoidUsingArraysInForeachLoops.ERROR_MESSAGE,
        priority = Priority.MINOR,
        tags = {"eco-design", "ecocode"})
public class AvoidUsingArraysInForeachLoops extends PHPSubscriptionCheck {

    public static final String RULE_KEY = "EC53";
    public static final String ERROR_MESSAGE = "Avoid using arrays in foreach loops";

    public static  ExpressionTree variable[] = {};
    public static ArrayList<ExpressionTree> variablesList = new ArrayList<ExpressionTree>(Arrays.asList(variable));

    @Override
    public List<Kind> nodesToVisit() {
        return Arrays.asList(Kind.FOREACH_STATEMENT, Kind.VARIABLE_IDENTIFIER, Kind.ASSIGNMENT);
    }

    @Override
    public void visitNode(Tree tree) {

        if (tree.is(Kind.ASSIGNMENT)) {
            AssignmentExpressionTree expressionTree = (AssignmentExpressionTree) tree;
            if(expressionTree.value().getKind() == Kind.ARRAY_INITIALIZER_BRACKET ||
                    expressionTree.value().getKind() == Kind.ARRAY_INITIALIZER_FUNCTION ||
                    expressionTree.value().getKind() == Kind.ARRAY_ASSIGNMENT_PATTERN_ELEMENT){
                variablesList.add(expressionTree.variable());
            }
        }
        if(tree.is(Kind.FOREACH_STATEMENT)) {
            ForEachStatementTree method = (ForEachStatementTree) tree;
            ExpressionTree expression = method.expression();
            for(int i =0; i < variablesList.size(); i++){
                if(expression.toString().equals(variablesList.get(i).toString())){
                    context().newIssue(this, method, ERROR_MESSAGE);
                }
            }
        }
    }


}
