import org.antlr.v4.runtime.tree.ParseTree;

public class CSTListener extends MuBaseListener {

    @Override
    public void enterParse(MuParser.ParseContext ctx) {
        System.out.println("COMPILATION_UNIT");
    }

    @Override
    public void exitParse(MuParser.ParseContext ctx) {
    }

    @Override
    public void enterBlock(MuParser.BlockContext ctx) {
        System.out.println("BLOCK_SCOPE");
        System.out.println("{");
    }

    @Override
    public void exitBlock(MuParser.BlockContext ctx) {
        System.out.println("}");
    }

    @Override
    public void enterStat(MuParser.StatContext ctx) {
        System.out.println("STATEMENT");
    }

    @Override
    public void exitStat(MuParser.StatContext ctx) {
    }

    @Override
    public void enterAssignment(MuParser.AssignmentContext ctx) {
        System.out.println("ASSIGNMENT");
        System.out.println("OPERATOR");
        System.out.println("=");
        System.out.println("NAME");
        System.out.println(ctx.ID().getText());
    }

    @Override
    public void exitAssignment(MuParser.AssignmentContext ctx) {
    }

    @Override
    public void enterIf_stat(MuParser.If_statContext ctx) {
        System.out.println("BRANCH_STATEMENT");
    }

    @Override
    public void exitIf_stat(MuParser.If_statContext ctx) {
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
                        } else {
                            // Ovo je else if deo
                            System.out.println("KEYWORD");
                            System.out.println("else if");
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
    }

    @Override
    public void enterStat_block(MuParser.Stat_blockContext ctx) {
        ParseTree parent = ctx.getParent();

        if (parent instanceof MuParser.If_statContext) {
            MuParser.If_statContext ifContext = (MuParser.If_statContext) parent;

            if (ifContext.stat_block() != null) {
                System.out.println("KEYWORD");
                System.out.println("else");
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
    }

    @Override
    public void exitWhile_stat(MuParser.While_statContext ctx) {
    }

    @Override
    public void enterLog(MuParser.LogContext ctx) {
        System.out.println("KEYWORD");
        System.out.println("log");
    }

    @Override
    public void exitLog(MuParser.LogContext ctx) {
    }

    @Override
    public void enterNotExpr(MuParser.NotExprContext ctx) {
        System.out.println("EXPRESSION");
    }

    @Override
    public void exitNotExpr(MuParser.NotExprContext ctx) {
    }

    @Override
    public void enterUnaryMinusExpr(MuParser.UnaryMinusExprContext ctx) {
        System.out.println("EXPRESSION");
    }

    @Override
    public void exitUnaryMinusExpr(MuParser.UnaryMinusExprContext ctx) {
    }

    @Override
    public void enterMultiplicationExpr(MuParser.MultiplicationExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println("OPERATOR");
        System.out.println(ctx.op.getText());
        System.out.println(ctx.expr(0));
        System.out.println(ctx.expr(1));
    }

    @Override
    public void exitMultiplicationExpr(MuParser.MultiplicationExprContext ctx) {
    }

    @Override
    public void enterAtomExpr(MuParser.AtomExprContext ctx) {
    }

    @Override
    public void exitAtomExpr(MuParser.AtomExprContext ctx) {
    }

    @Override
    public void enterOrExpr(MuParser.OrExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println(ctx.expr(0).getText());
        if (ctx.expr().size() > 1) {
            System.out.println(ctx.expr(1).getText());
        }
    }

    @Override
    public void exitOrExpr(MuParser.OrExprContext ctx) {
    }

    @Override
    public void enterAdditiveExpr(MuParser.AdditiveExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println("OPERATOR");
        System.out.println(ctx.op.getText());
    }

    @Override
    public void exitAdditiveExpr(MuParser.AdditiveExprContext ctx) {
    }

    @Override
    public void enterPowExpr(MuParser.PowExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println(ctx.expr(0).getText());
        System.out.println(ctx.expr(1).getText());
    }

    @Override
    public void exitPowExpr(MuParser.PowExprContext ctx) {
    }

    @Override
    public void enterRelationalExpr(MuParser.RelationalExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println("OPERATOR");
        System.out.println(ctx.op.getText());
    }

    @Override
    public void exitRelationalExpr(MuParser.RelationalExprContext ctx) {
    }

    @Override
    public void enterEqualityExpr(MuParser.EqualityExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println("OPERATOR");
        System.out.println(ctx.op.getText());
    }

    @Override
    public void exitEqualityExpr(MuParser.EqualityExprContext ctx) {
    }

    @Override
    public void enterAndExpr(MuParser.AndExprContext ctx) {
        System.out.println("EXPRESSION");
        System.out.println(ctx.expr(0).getText());
        if (ctx.expr().size() > 1) {
            System.out.println(ctx.expr(1).getText());
        }
    }

    @Override
    public void exitAndExpr(MuParser.AndExprContext ctx) {
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
    }

    @Override
    public void exitNumberAtom(MuParser.NumberAtomContext ctx) {
    }

    @Override
    public void enterBooleanAtom(MuParser.BooleanAtomContext ctx) {
        System.out.println(ctx.getText());
    }

    @Override
    public void exitBooleanAtom(MuParser.BooleanAtomContext ctx) {
    }

    @Override
    public void enterIdAtom(MuParser.IdAtomContext ctx) {
        System.out.println("NAME");
        System.out.println(ctx.getText());
    
    }

    @Override
    public void exitIdAtom(MuParser.IdAtomContext ctx) {
    }

    @Override
    public void enterStringAtom(MuParser.StringAtomContext ctx) {
        System.out.println(ctx.getText());
    }

    @Override
    public void exitStringAtom(MuParser.StringAtomContext ctx) {
    }

    @Override
    public void enterNilAtom(MuParser.NilAtomContext ctx) {
        System.out.println(ctx.getText());
    }

    @Override
    public void exitNilAtom(MuParser.NilAtomContext ctx) {
    }
}
