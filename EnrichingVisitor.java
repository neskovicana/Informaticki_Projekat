public class EnrichingVisitor extends MuBaseVisitor<UniversalNode> {

    @Override
    public UniversalNode visitParse(MuParser.ParseContext ctx) {
        UniversalNode node = new UniversalNode("Parse", "Program");
        UniversalNode blockNode = visit(ctx.block());
        node.addChild(blockNode);
        return node;
    }


    @Override
    public UniversalNode visitBlock(MuParser.BlockContext ctx) {
        UniversalNode node = new UniversalNode("Block", "Block");
        for (MuParser.StatContext statCtx : ctx.stat()) {
            node.addChild(visit(statCtx));
        }
        return node;
    }

    @Override
    public UniversalNode visitStat(MuParser.StatContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public UniversalNode visitAssignment(MuParser.AssignmentContext ctx) {
        UniversalNode node = new UniversalNode("Assignment", ctx.ID().getText());
        node.addChild(visit(ctx.expr()));
        return node;
    }

    @Override
    public UniversalNode visitIf_stat(MuParser.If_statContext ctx) {
        UniversalNode node = new UniversalNode("IfStat", "If Statement");

        // Process the initial condition block
        node.addChild(visit(ctx.condition_block(0)));

        // Handle chained else-if conditions
        int i = 1;
        while (i < ctx.condition_block().size()) {
            UniversalNode elseIfNode = new UniversalNode("ElseIf", "Else If Statement");
            elseIfNode.addChild(visit(ctx.condition_block(i)));
            node.addChild(elseIfNode);
            i++;
        }

        // Handle the final else block if present
        if (ctx.stat_block() != null) {
            UniversalNode elseNode = new UniversalNode("Else", "Else Statement");
            elseNode.addChild(visit(ctx.stat_block()));
            node.addChild(elseNode);
        }

        return node;
    }

    @Override
    public UniversalNode visitCondition_block(MuParser.Condition_blockContext ctx) {
        UniversalNode node = new UniversalNode("ConditionBlock", "Condition");
        node.addChild(visit(ctx.expr()));
        node.addChild(visit(ctx.stat_block()));
        return node;
    }

    @Override
    public UniversalNode visitStat_block(MuParser.Stat_blockContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public UniversalNode visitWhile_stat(MuParser.While_statContext ctx) {
        System.out.println("Visiting While Statement");
        UniversalNode node = new UniversalNode("WhileStat", "While Loop");
        node.addChild(visit(ctx.expr()));
        node.addChild(visit(ctx.stat_block()));
        return node;
    }

    @Override
    public UniversalNode visitLog(MuParser.LogContext ctx) {
        UniversalNode node = new UniversalNode("Log", "Log Statement");
        node.addChild(visit(ctx.expr()));
        return node;
    }

    @Override
    public UniversalNode visitNotExpr(MuParser.NotExprContext ctx) {
        UniversalNode node = new UniversalNode("NotExpr", "Not Expression");
        node.addChild(visit(ctx.expr()));
        return node;
    }

    @Override
    public UniversalNode visitUnaryMinusExpr(MuParser.UnaryMinusExprContext ctx) {
        UniversalNode node = new UniversalNode("UnaryMinusExpr", "Unary Minus");
        node.addChild(visit(ctx.expr()));
        return node;
    }

    @Override
    public UniversalNode visitMultiplicationExpr(MuParser.MultiplicationExprContext ctx) {
        UniversalNode node = new UniversalNode("MultiplicationExpr", ctx.op.getText());
        node.addChild(visit(ctx.expr(0)));
        node.addChild(visit(ctx.expr(1)));
        return node;
    }

    @Override
    public UniversalNode visitAtomExpr(MuParser.AtomExprContext ctx) {
        return visit(ctx.atom());
    }

    @Override
    public UniversalNode visitOrExpr(MuParser.OrExprContext ctx) {
        UniversalNode node = new UniversalNode("OrExpr", "OR");

        // Process the first expression
        node.addChild(visit(ctx.expr(0)));

        // Process the second expression
        if (ctx.expr().size() > 1) {
            node.addChild(visit(ctx.expr(1)));
        }

        return node;
    }


    @Override
    public UniversalNode visitAdditiveExpr(MuParser.AdditiveExprContext ctx) {
        UniversalNode node = new UniversalNode("AdditiveExpr", ctx.op.getText());
        node.addChild(visit(ctx.expr(0)));
        node.addChild(visit(ctx.expr(1)));
        return node;
    }

    @Override
    public UniversalNode visitPowExpr(MuParser.PowExprContext ctx) {
        UniversalNode node = new UniversalNode("PowExpr", "Power");
        node.addChild(visit(ctx.expr(0)));
        node.addChild(visit(ctx.expr(1)));
        return node;
    }

    @Override
    public UniversalNode visitRelationalExpr(MuParser.RelationalExprContext ctx) {
        UniversalNode node = new UniversalNode("RelationalExpr", ctx.op.getText());
        node.addChild(visit(ctx.expr(0)));
        node.addChild(visit(ctx.expr(1)));
        return node;
    }

    @Override
    public UniversalNode visitEqualityExpr(MuParser.EqualityExprContext ctx) {
        UniversalNode node = new UniversalNode("EqualityExpr", ctx.op.getText());
        node.addChild(visit(ctx.expr(0)));
        node.addChild(visit(ctx.expr(1)));
        return node;
    }

    @Override
    public UniversalNode visitAndExpr(MuParser.AndExprContext ctx) {
        UniversalNode node = new UniversalNode("AndExpr", ctx.AND().getText());
    
        // Process the first expression
        node.addChild(visit(ctx.expr(0)));
    
        // Process the second expression
        if (ctx.expr().size() > 1) {
            node.addChild(visit(ctx.expr(1)));
        }
    
        return node;
    }
    

    @Override
    public UniversalNode visitParExpr(MuParser.ParExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public UniversalNode visitNumberAtom(MuParser.NumberAtomContext ctx) {
        // Check if it's an integer or float and use the appropriate method
        if (ctx.INT() != null) {
            return new UniversalNode("Number", ctx.INT().getText());
        } else if (ctx.FLOAT() != null) {
            return new UniversalNode("Number", ctx.FLOAT().getText());
        } else {
            // Handle unexpected cases
            throw new IllegalArgumentException("Unexpected token type in NumberAtomContext");
        }
    }
    

    @Override
    public UniversalNode visitBooleanAtom(MuParser.BooleanAtomContext ctx) {
        // Check if the token is TRUE or FALSE
        if (ctx.TRUE() != null) {
            return new UniversalNode("Boolean", ctx.TRUE().getText());
        } else if (ctx.FALSE() != null) {
            return new UniversalNode("Boolean", ctx.FALSE().getText());
        } else {
            // Handle unexpected cases
            throw new IllegalArgumentException("Unexpected token type in BooleanAtomContext");
        }
    }
    

    @Override
    public UniversalNode visitIdAtom(MuParser.IdAtomContext ctx) {
        return new UniversalNode("ID", ctx.ID().getText());
    }

    @Override
    public UniversalNode visitStringAtom(MuParser.StringAtomContext ctx) {
        return new UniversalNode("String", ctx.STRING().getText());
    }

    @Override
    public UniversalNode visitNilAtom(MuParser.NilAtomContext ctx) {
        return new UniversalNode("Nil", "nil");
    }
}