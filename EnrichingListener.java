import java.util.Stack;

public class EnrichingListener extends MuBaseListener {

    // Stack to hold nodes during tree traversal
    private Stack<Node> nodeStack = new Stack<>();
    
    // Root node for the CST tree
    private Node root;

    public Node getRoot() {
        return root;
    }

    @Override
    public void enterParse(MuParser.ParseContext ctx) {
        nodeStack.push(new Node("COMPILATION_UNIT"));
    }

    @Override
    public void exitParse(MuParser.ParseContext ctx) {
        root = nodeStack.pop();  // The root of the entire CST
    }

    @Override
    public void enterBlock(MuParser.BlockContext ctx) {
        Node blockNode = new Node("BLOCK_SCOPE");
        nodeStack.push(blockNode);
    }

    @Override
    public void exitBlock(MuParser.BlockContext ctx) {
        Node blockNode = nodeStack.pop();
        blockNode.addChild(new Node("{"));
        
        // Add all statements from the block
        for (int i = 0; i < ctx.stat().size(); i++) {
            blockNode.addChild(nodeStack.pop());
        }

        blockNode.addChild(new Node("}"));
        nodeStack.peek().addChild(blockNode);  // Add the block to the parent node
    }

    @Override
    public void enterStat(MuParser.StatContext ctx) {
        nodeStack.push(new Node("STATEMENT"));
    }

    @Override
    public void exitStat(MuParser.StatContext ctx) {
        Node statementNode = nodeStack.pop();
        nodeStack.peek().addChild(statementNode);
    }

    @Override
    public void enterAssignment(MuParser.AssignmentContext ctx) {
        Node assignmentNode = new Node("ASSIGNMENT");
        
        // Create operator node for '='
        Node operatorNode = new Node("OPERATOR");
        Node operator = new Node("=");
        operatorNode.addChild(operator);
        
        // Add ID as NAME node
        Node nameNode = new Node("NAME");
        Node nameChild = new Node(ctx.ID().getText());
        nameNode.addChild(nameChild);
        assignmentNode.addChild(nameNode);
        
        // Push operator and assignment nodes
        assignmentNode.addChild(operatorNode);
        nodeStack.push(assignmentNode);
    }

    @Override
    public void exitAssignment(MuParser.AssignmentContext ctx) {
        Node exprNode = nodeStack.pop(); // Pop the expression node
        Node assignmentNode = nodeStack.pop(); // Pop the assignment node
        
        // Add expression as the right-hand side of the operator
        assignmentNode.getChildren().get(1).addChild(exprNode); // Add exprNode to OPERATOR node
        
        // Attach the assignment node to the parent
        nodeStack.peek().addChild(assignmentNode);
    }

    @Override
    public void enterIf_stat(MuParser.If_statContext ctx) {
        nodeStack.push(new Node("BRANCH_STATEMENT"));
    }

    @Override
    public void exitIf_stat(MuParser.If_statContext ctx) {
        Node branchNode = nodeStack.pop();

        // Add "if" keyword
        Node ifKeywordNode = new Node("KEYWORD");
        Node ifNode = new Node("if");
        ifKeywordNode.addChild(ifNode);
        branchNode.addChild(ifKeywordNode);

        // Add the condition block
        branchNode.addChild(nodeStack.pop());

        // Handle "else if" blocks
        for (int i = 1; i < ctx.condition_block().size(); i++) {
            Node elseIfKeywordNode = new Node("KEYWORD");
            elseIfKeywordNode.addChild(new Node("else if"));
            branchNode.addChild(elseIfKeywordNode);
            branchNode.addChild(nodeStack.pop());
        }

        // Handle "else" block if exists
        if (ctx.stat_block() != null) {
            Node elseKeywordNode = new Node("KEYWORD");
            elseKeywordNode.addChild(new Node("else"));
            branchNode.addChild(elseKeywordNode);
            branchNode.addChild(nodeStack.pop());
        }

        nodeStack.peek().addChild(branchNode);
    }

    @Override
    public void enterCondition_block(MuParser.Condition_blockContext ctx) {
        Node conditionBlockNode = new Node("CONDITION_BLOCK");
        nodeStack.push(conditionBlockNode);
    }

    @Override
    public void exitCondition_block(MuParser.Condition_blockContext ctx) {
        Node conditionBlockNode = nodeStack.pop();

        // Wrap the condition in parentheses
        conditionBlockNode.addChild(new Node("("));
        conditionBlockNode.addChild(nodeStack.pop());
        conditionBlockNode.addChild(new Node(")"));

        // Add the statement block after the condition
        conditionBlockNode.addChild(nodeStack.pop());

        nodeStack.peek().addChild(conditionBlockNode);
    }

    @Override
    public void enterWhile_stat(MuParser.While_statContext ctx) {
        Node whileNode = new Node("LOOP_STATEMENT");
        Node keyword = new Node("KEYWORD");
        keyword.addChild(new Node("while"));
        whileNode.addChild(keyword);

        nodeStack.push(whileNode);
    }

    @Override
    public void exitWhile_stat(MuParser.While_statContext ctx) {
        Node whileNode = nodeStack.pop();
        
        // Add condition and block to the while node
        whileNode.addChild(new Node("("));
        whileNode.addChild(nodeStack.pop()); // Condition
        whileNode.addChild(new Node(")"));
        whileNode.addChild(nodeStack.pop()); // Block

        nodeStack.peek().addChild(whileNode);
    }

    @Override
    public void enterLog(MuParser.LogContext ctx) {
        Node logNode = new Node("LOG_STATEMENT");
        Node keyword = new Node("KEYWORD");
        logNode.addChild(keyword);
        keyword.addChild(new Node("log"));
        nodeStack.push(logNode);
    }

    @Override
    public void exitLog(MuParser.LogContext ctx) {
        Node logNode = nodeStack.pop();
        logNode.addChild(nodeStack.pop());  // Add expression to the log statement
        nodeStack.peek().addChild(logNode);
    }

    @Override
    public void exitAdditiveExpr(MuParser.AdditiveExprContext ctx) {
        Node additiveNode = new Node("EXPRESSION");
        Node operatorNode = new Node("OPERATOR");
        Node operator = new Node(ctx.op.getText());
        operatorNode.addChild(operator);

        operatorNode.addChild(nodeStack.pop());  // Right operand
        operatorNode.addChild(nodeStack.pop());  // Left operand

        additiveNode.addChild(operatorNode);
        nodeStack.push(additiveNode);
    }

    @Override
    public void exitAtomExpr(MuParser.AtomExprContext ctx) {
        // No additional logic needed here
    }

    @Override
    public void exitNumberAtom(MuParser.NumberAtomContext ctx) {
        Node numberNode = new Node("NUMBER");
        numberNode.addChild(new Node(ctx.getText()));
        nodeStack.push(numberNode);
    }

    @Override
    public void exitIdAtom(MuParser.IdAtomContext ctx) {
        Node idNode = new Node("ID");
        idNode.addChild(new Node(ctx.getText()));
        nodeStack.push(idNode);
    }
}
