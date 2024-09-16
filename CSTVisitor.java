import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.TokenStream;

public class CSTVisitor extends MuBaseVisitor<ParseTree> {

    TokenStream tokens;
    protected ParserRuleContext enrichedTree;

    // @Override
    // public ParseTree visit(ParseTree tree) {
    // enrichedTree = (ParserRuleContext) super.visit(tree); // Posetimo originalno
    // stablo
    // return enrichedTree;
    // }

    @Override
    public ParseTree visit(ParseTree tree) {
        // Kreiraj i postavi inicijalni enrichedTree
        enrichedTree = (ParserRuleContext) visit(tree); // Posetimo originalno stablo
        return enrichedTree;
    }

    public ParseTree getEnrichedTree() {
        return enrichedTree;
    }

    // @Override
    // public ParseTree visitParse(MuParser.ParseContext ctx) {
        
    //     ParserRuleContext parent = ctx.getParent();
    //     ParserRuleContext parseNode = new ParserRuleContext();
    //     parseNode.setParent(parent);
    //     parent.addChild(parseNode);

    //     ParserRuleContext compilationUnit = new ParserRuleContext();
    //     compilationUnit.getText().
    //     parseNode.addChild(compilationUnit);

    //     ParserRuleContext blockNode = (ParserRuleContext) visit(ctx.block());

    //     compilationUnit.ad

    //     return parseNode; // Vrati obogaćeni čvor

    // }

    // @Override
    // public ParseTree visitBlock(MuParser.BlockContext ctx) {
    //     // Kreiraj novi čvor za "BLOCK_STATEMENT"
    //     ParserRuleContext blockNode = new ParserRuleContext();
    //     blockNode.addChild(new TerminalNodeImpl(new CommonToken(MuParser.RULE_block, "BLOCK_SCOPE")));

    //     // Iteriraj kroz sve statemente unutar bloka i poseti ih
    //     for (MuParser.StatContext statCtx : ctx.stat()) {
    //         ParserRuleContext statNode = (ParserRuleContext) visit(statCtx);
    //         blockNode.addChild(statNode); // Dodaj svaki stat kao dete
    //     }

    //     // Dodaj obogaćeni čvor u enrichedTree
    //     enrichedTree.addChild(blockNode);
    //     ctx.chil
    //     return blockNode;
    // }

    @Override
    public ParseTree visitStat(MuParser.StatContext ctx) {
        // Kreiraj novi čvor za "STATEMENT"
        ParserRuleContext statNode = new ParserRuleContext();
        statNode.addChild(new TerminalNodeImpl(new CommonToken(MuParser.RULE_stat, "STATEMENT")));

        // Poseti decu čvorova
        ParserRuleContext childrenNode = (ParserRuleContext) visitChildren(ctx);
        statNode.addChild(childrenNode);

        return statNode;
    }

    @Override
    public ParseTree visitAssignment(MuParser.AssignmentContext ctx) {
        // Kreiraj novi čvor za "ASSIGNMENT_STATEMENT"
        ParserRuleContext assignmentNode = new ParserRuleContext();
        assignmentNode.addChild(new TerminalNodeImpl(new CommonToken(MuParser.ASSIGN, "ASSIGNMENT")));

        // Poseti izraz i dodaj u obogaćeno stablo
        ParserRuleContext exprNode = (ParserRuleContext) visit(ctx.expr());
        assignmentNode.addChild(exprNode);

        return assignmentNode;
    }

    @Override
    public ParseTree visitIf_stat(MuParser.If_statContext ctx) {
        // Kreiraj novi čvor za "IF_STATEMENT"
        ParserRuleContext ifNode = new ParserRuleContext();
        ifNode.addChild(new TerminalNodeImpl(new CommonToken(MuParser.IF, "IF_STATEMENT")));

        // Poseti prvi if blok i dodaj ga u stablo
        ParserRuleContext conditionNode = (ParserRuleContext) visit(ctx.condition_block(0));
        ifNode.addChild(conditionNode);

        // Poseti else if blokove
        for (int i = 1; i < ctx.condition_block().size(); i++) {
            ParserRuleContext elifNode = (ParserRuleContext) visit(ctx.condition_block(i));
            ifNode.addChild(elifNode);
        }

        // Poseti else blok, ako postoji
        if (ctx.stat_block() != null) {
            ParserRuleContext elseNode = (ParserRuleContext) visit(ctx.stat_block());
            ifNode.addChild(elseNode);
        }

        return ifNode;
    }

    @Override
    public ParseTree visitCondition_block(MuParser.Condition_blockContext ctx) {
        // Kreiraj novi čvor za "CONDITION_BLOCK"
        ParserRuleContext conditionNode = new ParserRuleContext();
        conditionNode.addChild(new TerminalNodeImpl(new CommonToken(MuParser.RULE_condition_block, "CONDITION_BLOCK")));

        // Poseti izraz i blok
        ParserRuleContext exprNode = (ParserRuleContext) visit(ctx.expr());
        conditionNode.addChild(exprNode);

        ParserRuleContext blockNode = (ParserRuleContext) visit(ctx.stat_block());
        conditionNode.addChild(blockNode);

        return conditionNode;
    }

    @Override
    public ParseTree visitWhile_stat(MuParser.While_statContext ctx) {
        // Kreiramo novi čvor za "LOOP_STATEMENT"
        ParserRuleContext loopStatementNode = new ParserRuleContext();
        loopStatementNode.addChild(new TerminalNodeImpl(new CommonToken(MuParser.WHILE, "LOOP_STATEMENT")));

        // Poseti izraz u while petlji i telo petlje
        ParserRuleContext exprNode = (ParserRuleContext) visit(ctx.expr());
        loopStatementNode.addChild(exprNode);

        ParserRuleContext statBlockNode = (ParserRuleContext) visit(ctx.stat_block());
        loopStatementNode.addChild(statBlockNode);

        // Dodaj obogaćeni čvor u enrichedTree
        enrichedTree.addChild(loopStatementNode);

        return loopStatementNode;
    }

    @Override
    public ParseTree visitLog(MuParser.LogContext ctx) {
        // Kreiraj novi čvor za "LOG_STATEMENT"
        ParserRuleContext logNode = new ParserRuleContext();
        logNode.addChild(new TerminalNodeImpl(new CommonToken(MuParser.LOG, "LOG_STATEMENT")));

        // Poseti izraz za log i dodaj u stablo
        ParserRuleContext exprNode = (ParserRuleContext) visit(ctx.expr());
        logNode.addChild(exprNode);

        return logNode;
    }

    @Override
    public ParseTree visitAdditiveExpr(MuParser.AdditiveExprContext ctx) {
        // Kreiraj novi čvor za "ADDITIVE_EXPRESSION"
        ParserRuleContext additiveExprNode = new ParserRuleContext();
        additiveExprNode.addChild(new TerminalNodeImpl(new CommonToken(MuParser.PLUS, "ADDITIVE_EXPRESSION")));

        // Poseti leve i desne izraze i dodaj ih
        ParserRuleContext leftExpr = (ParserRuleContext) visit(ctx.expr(0));
        ParserRuleContext rightExpr = (ParserRuleContext) visit(ctx.expr(1));
        additiveExprNode.addChild(leftExpr);
        additiveExprNode.addChild(rightExpr);

        return additiveExprNode;
    }

    @Override
    public ParseTree visitNumberAtom(MuParser.NumberAtomContext ctx) {
        // Vraćamo broj kao atom
        return ctx;
    }

    @Override
    public ParseTree visitIdAtom(MuParser.IdAtomContext ctx) {
        // Vraćamo identifikator kao atom
        return ctx;
    }

    @Override
    public ParseTree visitStringAtom(MuParser.StringAtomContext ctx) {
        // Vraćamo string kao atom
        return ctx;
    }

    @Override
    public ParseTree visitNilAtom(MuParser.NilAtomContext ctx) {
        // Vraćamo nil kao atom
        return ctx;
    }
}
