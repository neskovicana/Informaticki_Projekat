public class EnrichingVisitor extends MuBaseVisitor<Node> implements MuVisitor<Node> {

    @Override
    public Node visitParse(MuParser.ParseContext ctx) {
        Node node = new Node("COMPILATION_UNIT");
        Node blockNode = visit(ctx.block());
        node.addChild(blockNode);
        return node;
    }

    // @Override
    // public Node visitBlock(MuParser.BlockContext ctx) {
    // Node node = new Node("BLOCK_SCOPE");
    // node.addChild(new Node("{"));
    // for (MuParser.StatContext statCtx : ctx.stat()) {
    // node.addChild(visit(statCtx));
    // }
    // node.addChild(new Node("}"));
    // return node;
    // }

    @Override
    public Node visitBlock(MuParser.BlockContext ctx) {
        Node node = new Node("BLOCK_SCOPE");
        boolean cbAdded = false;

        if (ctx.getStart().getText().equals("{")) {
            node.addChild(new Node("{"));
            cbAdded = true;
        }

        for (MuParser.StatContext statCtx : ctx.stat()) {
            node.addChild(visit(statCtx));
        }

        if (cbAdded) {
            node.addChild(new Node("}"));
        }
        return node;
    }

    @Override
    public Node visitStat(MuParser.StatContext ctx) {
        Node statementNode = new Node("STATEMENT");
        Node childNode = visitChildren(ctx);
        statementNode.addChild(childNode);
        return statementNode;
    }

    @Override
    public Node visitAssignment(MuParser.AssignmentContext ctx) {
        Node assignmentNode = new Node("ASSIGNMENT");

        Node operator = new Node("OPERATOR");
        Node operatorNode = new Node("=");
        operator.addChild(operatorNode);

        Node nameNode = new Node(ctx.ID().getText());
        Node nameParent = new Node("NAME");
        nameParent.addChild(nameNode);
        operatorNode.addChild(nameParent);

        Node exprNode = visit(ctx.expr());
        operatorNode.addChild(exprNode);

        assignmentNode.addChild(operator);
        return assignmentNode;
    }

    @Override
    public Node visitIf_stat(MuParser.If_statContext ctx) {
        Node branchNode = new Node("BRANCH_STATEMENT");

        Node ifKeywordNode = new Node("KEYWORD");
        ifKeywordNode.addChild(new Node("if"));
        branchNode.addChild(ifKeywordNode);

        branchNode.addChild(visit(ctx.condition_block(0)));

        for (int i = 1; i < ctx.condition_block().size(); i++) {
            Node elseIfKeywordNode = new Node("KEYWORD");
            elseIfKeywordNode.addChild(new Node("else if"));
            branchNode.addChild(elseIfKeywordNode);

            branchNode.addChild(visit(ctx.condition_block(i)));
        }

        if (ctx.stat_block() != null) {
            Node elseKeywordNode = new Node("KEYWORD");
            elseKeywordNode.addChild(new Node("else"));
            branchNode.addChild(elseKeywordNode);

            branchNode.addChild(visit(ctx.stat_block()));
        }

        return branchNode;
    }

    @Override
    public Node visitCondition_block(MuParser.Condition_blockContext ctx) {
        Node conditionNode = visit(ctx.expr());

        Node statementBlockNode = visit(ctx.stat_block());

        Node conditionBlockNode = new Node("CONDITION_BLOCK");
        conditionBlockNode.addChild(new Node("("));
        conditionBlockNode.addChild(conditionNode);
        conditionBlockNode.addChild(new Node(")"));
        conditionBlockNode.addChild(statementBlockNode);

        return conditionBlockNode;
    }

    @Override
    public Node visitStat_block(MuParser.Stat_blockContext ctx) {
        Node blockScopeNode = new Node("STATEMENT");
        Node blockNode = visit(ctx.block());
        blockScopeNode.addChild(blockNode);
        return blockScopeNode;
    }

    @Override
    public Node visitWhile_stat(MuParser.While_statContext ctx) {
        Node loopNode = new Node("LOOP_STATEMENT");

        Node whileKeywordNode = new Node("KEYWORD");
        whileKeywordNode.addChild(new Node("while"));
        loopNode.addChild(whileKeywordNode);

        Node conditionNode = new Node("CONDITION");
        conditionNode.addChild(new Node("("));
        conditionNode.addChild(visit(ctx.expr()));
        conditionNode.addChild(new Node(")"));
        loopNode.addChild(conditionNode);

        loopNode.addChild(visit(ctx.stat_block()));

        return loopNode;
    }

    @Override
    public Node visitLog(MuParser.LogContext ctx) {
        Node operator = new Node("KEYWORD");
        Node logNode = new Node("log");
        operator.addChild(logNode);
        logNode.addChild(visit(ctx.expr()));
        return operator;
    }

    @Override
    public Node visitNotExpr(MuParser.NotExprContext ctx) {
        Node notExprNode = new Node("EXPRESSION");
        notExprNode.addChild(visit(ctx.expr()));
        return notExprNode;
    }

    @Override
    public Node visitUnaryMinusExpr(MuParser.UnaryMinusExprContext ctx) {
        Node unaryMinusNode = new Node("EXPRESSION");
        unaryMinusNode.addChild(visit(ctx.expr()));
        return unaryMinusNode;
    }

    @Override
    public Node visitMultiplicationExpr(MuParser.MultiplicationExprContext ctx) {
        Node multiplicationNode = new Node("EXPRESSION");
        Node operator = new Node("OPERATOR");
        multiplicationNode.addChild(operator);
        Node multiplication = new Node(ctx.op.getText());
        multiplication.addChild(visit(ctx.expr(0)));
        multiplication.addChild(visit(ctx.expr(1)));
        operator.addChild(multiplication);
        return multiplicationNode;
    }

    @Override
    public Node visitAtomExpr(MuParser.AtomExprContext ctx) {
        return visit(ctx.atom());
    }

    @Override
    public Node visitOrExpr(MuParser.OrExprContext ctx) {
        Node orNode = new Node("EXPRESSION");
        orNode.addChild(visit(ctx.expr(0)));
        if (ctx.expr().size() > 1) {
            orNode.addChild(visit(ctx.expr(1)));
        }
        return orNode;
    }

    @Override
    public Node visitAdditiveExpr(MuParser.AdditiveExprContext ctx) {
        Node additiveNode = new Node("EXPRESSION");
        Node operator = new Node("OPERATOR");
        additiveNode.addChild(operator);
        Node addition = new Node(ctx.op.getText());
        addition.addChild(visit(ctx.expr(0)));
        addition.addChild(visit(ctx.expr(1)));
        operator.addChild(addition);
        return additiveNode;
    }

    @Override
    public Node visitPowExpr(MuParser.PowExprContext ctx) {
        Node powNode = new Node("EXPRESSION");
        powNode.addChild(visit(ctx.expr(0)));
        powNode.addChild(visit(ctx.expr(1)));
        return powNode;
    }

    @Override
    public Node visitRelationalExpr(MuParser.RelationalExprContext ctx) {
        Node relationalNode = new Node("EXPRESSION");
        Node operator = new Node("OPERATOR");
        relationalNode.addChild(operator);
        Node relation = new Node(ctx.op.getText());
        relation.addChild(visit(ctx.expr(0)));
        relation.addChild(visit(ctx.expr(1)));
        operator.addChild(relation);
        return relationalNode;
    }

    @Override
    public Node visitEqualityExpr(MuParser.EqualityExprContext ctx) {
        Node equalityNode = new Node("EXPRESSION");
        Node operator = new Node("OPERATOR");
        equalityNode.addChild(operator);
        Node equality = new Node(ctx.op.getText());
        equality.addChild(visit(ctx.expr(0)));
        equality.addChild(visit(ctx.expr(1)));
        operator.addChild(equality);
        return equalityNode;
    }

    @Override
    public Node visitAndExpr(MuParser.AndExprContext ctx) {
        Node andNode = new Node("EXPRESSION");
        andNode.addChild(visit(ctx.expr(0)));
        if (ctx.expr().size() > 1) {
            andNode.addChild(visit(ctx.expr(1)));
        }
        return andNode;
    }

    @Override
    public Node visitParExpr(MuParser.ParExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Node visitNumberAtom(MuParser.NumberAtomContext ctx) {
        String type = ctx.INT() != null ? "INT" : "FLOAT";
        Node numberNode = new Node("NUMBER");
        numberNode.addChild(new Node(ctx.getText()));
        return numberNode;
    }

    @Override
    public Node visitBooleanAtom(MuParser.BooleanAtomContext ctx) {
        Node booleanNode = new Node("BOOLEAN");
        booleanNode.addChild(new Node(ctx.getText()));
        return booleanNode;
    }

    @Override
    public Node visitIdAtom(MuParser.IdAtomContext ctx) {
        Node idNode = new Node("ID");
        idNode.addChild(new Node(ctx.ID().getText()));
        return idNode;
    }

    @Override
    public Node visitStringAtom(MuParser.StringAtomContext ctx) {
        Node stringNode = new Node("STRING");
        stringNode.addChild(new Node(ctx.STRING().getText()));
        return stringNode;
    }

    @Override
    public Node visitNilAtom(MuParser.NilAtomContext ctx) {
        Node nilNode = new Node("NIL");
        nilNode.addChild(new Node("nil"));
        return nilNode;
    }
}
