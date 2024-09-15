import org.antlr.v4.runtime.tree.ParseTree;
import java.util.Stack;

public class EnrichingListener extends MuBaseListener {

    private Stack<TreeNode> nodeStack = new Stack<>();
    private TreeNode root;

    public TreeNode getRoot() {
        return this.root;
    }

    @Override
    public void enterParse(MuParser.ParseContext ctx) {
        System.out.println("COMPILATION_UNIT");

        root = new TreeNode("COMPILATION_UNIT");
        nodeStack.push(root);
    }

    @Override
    public void exitParse(MuParser.ParseContext ctx) {

    }

    @Override
    public void enterBlock(MuParser.BlockContext ctx) {
        System.out.println("BLOCK_SCOPE");
        System.out.println("{");

        TreeNode blockNode = new TreeNode("BLOCK_SCOPE");
        blockNode.addChild(new TreeNode("{"));
        nodeStack.peek().addChild(blockNode);
        nodeStack.push(blockNode);
    }

    @Override
    public void exitBlock(MuParser.BlockContext ctx) {
        System.out.println("}");
        nodeStack.pop();
    }

    @Override
    public void enterStat(MuParser.StatContext ctx) {
        System.out.println("STATEMENT");

        TreeNode parent = nodeStack.peek();
        TreeNode statNode = new TreeNode("STATEMENT");
        parent.addChild(statNode);

        nodeStack.push(statNode);
    }

    @Override
    public void exitStat(MuParser.StatContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterAssignment(MuParser.AssignmentContext ctx) {
        System.out.println("ASSIGNMENT");
        System.out.println("OPERATOR");
        System.out.println("=");
        System.out.println("NAME");
        System.out.println(ctx.ID().getText());

        TreeNode parent = nodeStack.peek();
        TreeNode assignment = new TreeNode("ASSIGNMENT");
        TreeNode operator = new TreeNode("OPERATOR");
        TreeNode equals = new TreeNode("=");
        TreeNode name = new TreeNode("NAME");
        TreeNode value = new TreeNode(ctx.ID().getText());

        parent.addChild(assignment);
        assignment.addChild(operator);
        operator.addChild(equals);
        equals.addChild(name);
        equals.addChild(value);

        nodeStack.push(assignment);
    }

    @Override
    public void exitAssignment(MuParser.AssignmentContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterIf_stat(MuParser.If_statContext ctx) {
        System.out.println("BRANCH_STATEMENT");
        
        TreeNode parent = nodeStack.peek();
        TreeNode statNode = new TreeNode("BRANCH_STATEMENT");
        parent.addChild(statNode);

        nodeStack.push(statNode);
    }

    @Override
    public void exitIf_stat(MuParser.If_statContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterCondition_block(MuParser.Condition_blockContext ctx) {
        ParseTree parent = ctx.getParent();

        // Prolazimo kroz roditelje dok ne pronađemo If_statContext
        while (parent != null) {
            if (parent instanceof MuParser.If_statContext) {
                MuParser.If_statContext ifContext = (MuParser.If_statContext) parent;

                // Prolazimo kroz sve condition_blocks unutar ifContext
                for (int i = 0; i < ifContext.condition_block().size(); i++) {
                    MuParser.Condition_blockContext conditionBlock = ifContext.condition_block(i);

                    if (conditionBlock == ctx) {
                        if (i == 0) {
                            // Ovo je if deo
                            System.out.println("KEYWORD");
                            System.out.println("if");

                            TreeNode parentTree = nodeStack.peek();
                            TreeNode keyword = new TreeNode("KEYWORD");
                            parentTree.addChild(keyword);
                            TreeNode ifNode = new TreeNode("if");
                            keyword.addChild(ifNode);

                            TreeNode condBlockNode = new TreeNode("CONDITION_BLOCK");
                            ifNode.addChild(condBlockNode);

                            nodeStack.push(keyword);
                        } else {
                            // Ovo je else if deo
                            System.out.println("KEYWORD");
                            System.out.println("else if");

                            TreeNode parentTree = nodeStack.peek();
                            TreeNode keyword = new TreeNode("KEYWORD");
                            parentTree.addChild(keyword);
                            TreeNode elseIfNode = new TreeNode("else if");
                            keyword.addChild(elseIfNode);

                            TreeNode condBlockNode = new TreeNode("CONDITION_BLOCK");
                            elseIfNode.addChild(condBlockNode);

                            nodeStack.push(keyword);
                        }
                        break;
                    }
                }
                break;
            }
            parent = parent.getParent();
        }

        System.out.println("CONDITION BLOCK");

        System.out.println("(");
    }

    @Override
    public void exitCondition_block(MuParser.Condition_blockContext ctx) {
        System.out.println(")");
        nodeStack.pop();
    }

    @Override
    public void enterStat_block(MuParser.Stat_blockContext ctx) {
        ParseTree parent = ctx.getParent();

        if (parent instanceof MuParser.If_statContext) {
            MuParser.If_statContext ifContext = (MuParser.If_statContext) parent;

            if (ifContext.stat_block() != null) {
                System.out.println("KEYWORD");
                System.out.println("else");

                TreeNode parentTree = nodeStack.peek();
                TreeNode keyword = new TreeNode("KEYWORD");
                parentTree.addChild(keyword);
                TreeNode elseIfNode = new TreeNode("else");
                keyword.addChild(elseIfNode);

                TreeNode stat = new TreeNode("STATEMENT");
                elseIfNode.addChild(stat);
            } else {
                TreeNode stat = new TreeNode("STATEMENT");
                TreeNode parentTree = nodeStack.peek();
                parentTree.addChild(stat);
            }
        }

        System.out.println("STATEMENT");

    }

    @Override
    public void exitStat_block(MuParser.Stat_blockContext ctx) {
    }

    @Override
    public void enterWhile_stat(MuParser.While_statContext ctx) {
        System.out.println("LOOP_STATEMENT");
        System.out.println("KEYWORD");
        System.out.println("while");
        System.out.println("CONDITION");

        TreeNode parent = nodeStack.peek();
        TreeNode statNode = new TreeNode("LOOP_STATEMENT");
        TreeNode keyNode = new TreeNode("KEYWORD");
        TreeNode whileNode = new TreeNode("while");
        TreeNode condNode = new TreeNode("CONDITION");

        parent.addChild(statNode);
        statNode.addChild(keyNode);
        keyNode.addChild(whileNode);
        whileNode.addChild(condNode);

        nodeStack.push(statNode);
    }

    @Override
    public void exitWhile_stat(MuParser.While_statContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterLog(MuParser.LogContext ctx) {
        System.out.println("KEYWORD");
        System.out.println("log");

        TreeNode parent = nodeStack.peek();
        TreeNode key = new TreeNode("KEYWORD");
        TreeNode log = new TreeNode("log");
        parent.addChild(key);
        key.addChild(log);

        nodeStack.push(key);
    }

    @Override
    public void exitLog(MuParser.LogContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterNotExpr(MuParser.NotExprContext ctx) {
        System.out.println("EXPRESSION");

        TreeNode parent = nodeStack.peek();
        TreeNode expr = new TreeNode("EXPRESSION");

        parent.addChild(expr);

        nodeStack.push(expr);
    }

    @Override
    public void exitNotExpr(MuParser.NotExprContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterUnaryMinusExpr(MuParser.UnaryMinusExprContext ctx) {
        System.out.println("EXPRESSION");

        TreeNode parent = nodeStack.peek();
        TreeNode expr = new TreeNode("EXPRESSION");

        parent.addChild(expr);

        nodeStack.push(expr);
    }

    @Override
    public void exitUnaryMinusExpr(MuParser.UnaryMinusExprContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterMultiplicationExpr(MuParser.MultiplicationExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println("OPERATOR");
        System.out.println(ctx.op.getText());
        System.out.println(ctx.expr(0));
        System.out.println(ctx.expr(1));

        TreeNode parent = nodeStack.peek();
        TreeNode expr = new TreeNode("EXPRESSION");
        TreeNode opNode = new TreeNode("OPERATOR");
        TreeNode op = new TreeNode(ctx.op.getText());

        parent.addChild(expr);
        expr.addChild(opNode);
        opNode.addChild(op);

        nodeStack.push(expr);
    }

    @Override
    public void exitMultiplicationExpr(MuParser.MultiplicationExprContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterAtomExpr(MuParser.AtomExprContext ctx) {
        // TreeNode parent = nodeStack.peek();
        // TreeNode atomNode = new TreeNode(ctx.getText());
        // parent.addChild(atomNode);

        // nodeStack.push(atomNode);
    }

    @Override
    public void exitAtomExpr(MuParser.AtomExprContext ctx) {
        // nodeStack.pop();
    }

    @Override
    public void enterOrExpr(MuParser.OrExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println(ctx.expr(0).getText());
        if (ctx.expr().size() > 1) {
            System.out.println(ctx.expr(1).getText());
        }

        TreeNode parent = nodeStack.peek();
        TreeNode expr = new TreeNode("EXPRESSION");
        TreeNode zero = new TreeNode(ctx.expr(0).getText());

        parent.addChild(expr);
        expr.addChild(zero);

        if (ctx.expr().size() > 1) {
            TreeNode one = new TreeNode(ctx.expr(1).getText());
            expr.addChild(one);
        }

        nodeStack.push(expr);
    }

    @Override
    public void exitOrExpr(MuParser.OrExprContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterAdditiveExpr(MuParser.AdditiveExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println("OPERATOR");
        System.out.println(ctx.op.getText());

        TreeNode parent = nodeStack.peek();
        TreeNode expr = new TreeNode("EXPRESSION");
        TreeNode opNode = new TreeNode("OPERATOR");
        TreeNode op = new TreeNode(ctx.op.getText());

        parent.addChild(expr);
        expr.addChild(opNode);
        opNode.addChild(op);

        nodeStack.push(expr);
    }

    @Override
    public void exitAdditiveExpr(MuParser.AdditiveExprContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterPowExpr(MuParser.PowExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println(ctx.expr(0).getText());
        System.out.println(ctx.expr(1).getText());

        TreeNode parent = nodeStack.peek();
        TreeNode expr = new TreeNode("EXPRESSION");
        TreeNode zero = new TreeNode(ctx.expr(0).getText());
        TreeNode one = new TreeNode(ctx.expr(1).getText());

        parent.addChild(expr);
        expr.addChild(zero);
        expr.addChild(one);

        nodeStack.push(expr);
    }

    @Override
    public void exitPowExpr(MuParser.PowExprContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterRelationalExpr(MuParser.RelationalExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println("OPERATOR");
        System.out.println(ctx.op.getText());

        TreeNode parent = nodeStack.peek();
        TreeNode expr = new TreeNode("EXPRESSION");
        TreeNode opNode = new TreeNode("OPERATOR");
        TreeNode op = new TreeNode(ctx.op.getText());

        parent.addChild(expr);
        expr.addChild(opNode);
        opNode.addChild(op);

        nodeStack.push(expr);
    }

    @Override
    public void exitRelationalExpr(MuParser.RelationalExprContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterEqualityExpr(MuParser.EqualityExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println("OPERATOR");
        System.out.println(ctx.op.getText());

        TreeNode parent = nodeStack.peek();
        TreeNode expr = new TreeNode("EXPRESSION");
        TreeNode opNode = new TreeNode("OPERATOR");
        TreeNode op = new TreeNode(ctx.op.getText());

        parent.addChild(expr);
        expr.addChild(opNode);
        opNode.addChild(op);

        nodeStack.push(expr);
    }

    @Override
    public void exitEqualityExpr(MuParser.EqualityExprContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterAndExpr(MuParser.AndExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println(ctx.expr(0).getText());
        if (ctx.expr().size() > 1) {
            System.out.println(ctx.expr(1).getText());
        }

        TreeNode parent = nodeStack.peek();
        TreeNode expr = new TreeNode("EXPRESSION");
        TreeNode zero = new TreeNode(ctx.expr(0).getText());

        parent.addChild(expr);
        expr.addChild(zero);

        if (ctx.expr().size() > 1) {
            TreeNode one = new TreeNode(ctx.expr(1).getText());
            expr.addChild(one);
        }

        nodeStack.push(expr);
    }

    @Override
    public void exitAndExpr(MuParser.AndExprContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterParExpr(MuParser.ParExprContext ctx) {
    }

    @Override
    public void exitParExpr(MuParser.ParExprContext ctx) {
    }

    @Override
    public void enterNumberAtom(MuParser.NumberAtomContext ctx) {
        System.out.println("NUMBER");
        System.out.println(ctx.getText());

        TreeNode parent = nodeStack.peek();
        TreeNode num = new TreeNode("NUMBER");
        TreeNode number = new TreeNode(ctx.getText());

        parent.addChild(num);
        num.addChild(number);

        nodeStack.push(num);
    }

    @Override
    public void exitNumberAtom(MuParser.NumberAtomContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterBooleanAtom(MuParser.BooleanAtomContext ctx) {
        System.out.println(ctx.getText());

        TreeNode parent = nodeStack.peek();
        TreeNode value = new TreeNode(ctx.getText());

        parent.addChild(value);

        nodeStack.push(value);
    }

    @Override
    public void exitBooleanAtom(MuParser.BooleanAtomContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterIdAtom(MuParser.IdAtomContext ctx) {
        System.out.println("NAME");
        System.out.println(ctx.getText());
    
        TreeNode parent = nodeStack.peek();
        TreeNode nam = new TreeNode("NAME");
        TreeNode name = new TreeNode(ctx.getText());

        parent.addChild(nam);
        nam.addChild(name);

        nodeStack.push(nam);
    }

    @Override
    public void exitIdAtom(MuParser.IdAtomContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterStringAtom(MuParser.StringAtomContext ctx) {
        System.out.println(ctx.getText());

        TreeNode parent = nodeStack.peek();
        TreeNode value = new TreeNode(ctx.getText());

        parent.addChild(value);

        nodeStack.push(value);
    }

    @Override
    public void exitStringAtom(MuParser.StringAtomContext ctx) {
        nodeStack.pop();
    }

    @Override
    public void enterNilAtom(MuParser.NilAtomContext ctx) {
        System.out.println(ctx.getText());

        TreeNode parent = nodeStack.peek();
        TreeNode value = new TreeNode(ctx.getText());

        parent.addChild(value);

        nodeStack.push(value);
    }

    @Override
    public void exitNilAtom(MuParser.NilAtomContext ctx) {
        nodeStack.pop();
    }

}