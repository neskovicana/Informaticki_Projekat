import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class CSTVisitor implements MuVisitor {

    @Override
    public Object visit(ParseTree arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public Object visitChildren(RuleNode arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitChildren'");
    }

    @Override
    public Object visitErrorNode(ErrorNode arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitErrorNode'");
    }

    @Override
    public Object visitTerminal(TerminalNode arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitTerminal'");
    }

    @Override
    public Object visitParse(MuParser.ParseContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitParse'");
    }

    @Override
    public Object visitBlock(MuParser.BlockContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitBlock'");
    }

    @Override
    public Object visitStat(MuParser.StatContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitStat'");
    }

    @Override
    public Object visitAssignment(MuParser.AssignmentContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitAssignment'");
    }

    @Override
    public Object visitIf_stat(MuParser.If_statContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitIf_stat'");
    }

    @Override
    public Object visitCondition_block(MuParser.Condition_blockContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitCondition_block'");
    }

    @Override
    public Object visitStat_block(MuParser.Stat_blockContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitStat_block'");
    }

    @Override
    public Object visitWhile_stat(MuParser.While_statContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitWhile_stat'");
    }

    @Override
    public Object visitLog(MuParser.LogContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitLog'");
    }

    @Override
    public Object visitNotExpr(MuParser.NotExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitNotExpr'");
    }

    @Override
    public Object visitUnaryMinusExpr(MuParser.UnaryMinusExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitUnaryMinusExpr'");
    }

    @Override
    public Object visitMultiplicationExpr(MuParser.MultiplicationExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitMultiplicationExpr'");
    }

    @Override
    public Object visitAtomExpr(MuParser.AtomExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitAtomExpr'");
    }

    @Override
    public Object visitOrExpr(MuParser.OrExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitOrExpr'");
    }

    @Override
    public Object visitAdditiveExpr(MuParser.AdditiveExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitAdditiveExpr'");
    }

    @Override
    public Object visitPowExpr(MuParser.PowExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitPowExpr'");
    }

    @Override
    public Object visitRelationalExpr(MuParser.RelationalExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitRelationalExpr'");
    }

    @Override
    public Object visitEqualityExpr(MuParser.EqualityExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitEqualityExpr'");
    }

    @Override
    public Object visitAndExpr(MuParser.AndExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitAndExpr'");
    }

    @Override
    public Object visitParExpr(MuParser.ParExprContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitParExpr'");
    }

    @Override
    public Object visitNumberAtom(MuParser.NumberAtomContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitNumberAtom'");
    }

    @Override
    public Object visitBooleanAtom(MuParser.BooleanAtomContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitBooleanAtom'");
    }

    @Override
    public Object visitIdAtom(MuParser.IdAtomContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitIdAtom'");
    }

    @Override
    public Object visitStringAtom(MuParser.StringAtomContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitStringAtom'");
    }

    @Override
    public Object visitNilAtom(MuParser.NilAtomContext ctx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitNilAtom'");
    }
    
}
