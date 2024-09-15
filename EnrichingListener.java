// import org.antlr.v4.runtime.tree.TerminalNode;

// public class EnrichingListener extends MuBaseListener {

//     private Node root; // Koreni čvor
//     private Node currentParent; // Trenutni roditelj čvor
//     private Node rootNode;

//     public Node getRoot() {
//         return root;
//     }

//     @Override
//     public void enterParse(MuParser.ParseContext ctx) {
//         root = new Node("COMPILATION_UNIT");
//         currentParent = root;
//     }

//     @Override
//     public void exitParse(MuParser.ParseContext ctx) {
//         // No additional action needed on exit
//     }

//     @Override
//     public void enterBlock(MuParser.BlockContext ctx) {
//         Node blockNode = new Node("BLOCK_SCOPE");
//         currentParent.addChild(blockNode);
//         currentParent = blockNode;
//     }

//     @Override
//     public void exitBlock(MuParser.BlockContext ctx) {
//         currentParent = currentParent.getParent(); // Vraćamo se na roditelja
//     }

//     @Override
//     public void enterStat(MuParser.StatContext ctx) {
//         Node statNode = new Node("STATEMENT");
//         currentParent.addChild(statNode);
//         currentParent = statNode;
//     }

//     @Override
//     public void exitStat(MuParser.StatContext ctx) {
//         currentParent = currentParent.getParent(); // Vraćamo se na roditelja
//     }

//     @Override
//     public void enterAssignment(MuParser.AssignmentContext ctx) {
//         Node assignmentNode = new Node("ASSIGNMENT");
//         currentParent.addChild(assignmentNode);
//         currentParent = assignmentNode;
//     }

//     // @Override
//     // public void exitAssignment(MuParser.AssignmentContext ctx) {
//     //     // Dodajemo izraz ako postoji
//     //     Node exprNode = new Node("EXPRESSION");
//     //     currentParent.addChild(exprNode);

//     //     // Pretpostavljamo da koristimo metodu processExpression za obrada izraza
//     //     if (ctx.expr() != null) {
//     //         exprNode.addChild(processExpression(ctx.expr()));
//     //     }

//     //     currentParent = currentParent.getParent(); // Vraćamo se na roditelja
//     // }

//     @Override
//     public void enterIf_stat(MuParser.If_statContext ctx) {
//         Node branchNode = new Node("BRANCH_STATEMENT");
//         currentParent.addChild(branchNode);
//         currentParent = branchNode;
//     }

//     @Override
//     public void exitIf_stat(MuParser.If_statContext ctx) {
//         currentParent = currentParent.getParent(); // Vraćamo se na roditelja
//     }

//     @Override
//     public void enterCondition_block(MuParser.Condition_blockContext ctx) {
//         Node conditionNode = new Node("CONDITION_BLOCK");
//         currentParent.addChild(conditionNode);
//         currentParent = conditionNode;
//     }

//     @Override
//     public void exitCondition_block(MuParser.Condition_blockContext ctx) {
//         currentParent = currentParent.getParent(); // Vraćamo se na roditelja
//     }

//     @Override
//     public void enterWhile_stat(MuParser.While_statContext ctx) {
//         Node loopNode = new Node("LOOP_STATEMENT");
//         currentParent.addChild(loopNode);
//         currentParent = loopNode;
//     }

//     @Override
//     public void exitWhile_stat(MuParser.While_statContext ctx) {
//         currentParent = currentParent.getParent(); // Vraćamo se na roditelja
//     }

//     @Override
//     public void enterLog(MuParser.LogContext ctx) {
//         Node logNode = new Node("LOG");
//         currentParent.addChild(logNode);
//         currentParent = logNode;
//     }

//     @Override
//     public void exitLog(MuParser.LogContext ctx) {
//         if (ctx.expr() != null) {
//             Node exprNode = new Node("EXPRESSION");
//             logNode.addChild(processExpression(ctx.expr()));
//         }
//         currentParent = currentParent.getParent(); // Vraćamo se na roditelja
//     }

//     @Override
//     public void enterAtomExpr(MuParser.AtomExprContext ctx) {
//         Node atomNode = processExpression(ctx.atom());
//         currentParent.addChild(atomNode);
//     }

//     @Override
//     public void enterNumberAtom(MuParser.NumberAtomContext ctx) {
//         Node numberNode = new Node("NUMBER");
//         numberNode.addChild(new Node(ctx.getText()));
//         currentParent.addChild(numberNode);
//     }

//     @Override
//     public void enterIdAtom(MuParser.IdAtomContext ctx) {
//         Node idNode = new Node("ID");
//         idNode.addChild(new Node(ctx.getText()));
//         currentParent.addChild(idNode);
//     }

//     @Override
//     public void enterStringAtom(MuParser.StringAtomContext ctx) {
//         Node stringNode = new Node("STRING");
//         stringNode.addChild(new Node(ctx.getText()));
//         currentParent.addChild(stringNode);
//     }

//     @Override
//     public void enterNilAtom(MuParser.NilAtomContext ctx) {
//         Node nilNode = new Node("NIL");
//         nilNode.addChild(new Node("nil"));
//         currentParent.addChild(nilNode);
//     }

//     // private Node processExpression(MuParser.ExprContext ctx) {
//     //     // Implement this method to handle various expression contexts
//     //     if (ctx.numberAtom() != null) {
//     //         Node numberNode = new Node("NUMBER");
//     //         numberNode.addChild(new Node(ctx.numberAtom().getText()));
//     //         return numberNode;
//     //     } else if (ctx.idAtom() != null) {
//     //         Node idNode = new Node("ID");
//     //         idNode.addChild(new Node(ctx.idAtom().getText()));
//     //         return idNode;
//     //     } else if (ctx.op != null) {
//     //         Node operatorNode = new Node("OPERATOR");
//     //         operatorNode.addChild(new Node(ctx.op.getText()));
//     //         if (ctx.expr().size() > 0) {
//     //             Node leftExpr = processExpression(ctx.expr(0));
//     //             operatorNode.addChild(leftExpr);
//     //         }
//     //         if (ctx.expr().size() > 1) {
//     //             Node rightExpr = processExpression(ctx.expr(1));
//     //             operatorNode.addChild(rightExpr);
//     //         }
//     //         return operatorNode;
//     //     }
//     //     // Add additional cases for other types of expressions
//     //     return new Node("UNKNOWN");
//     // }

//     @Override
//     public void exitAssignment(MuParser.AssignmentContext ctx) {
//         Node assignmentNode = new Node("ASSIGNMENT");
//         rootNode = assignmentNode;
//         Node exprNode = processExpression(ctx.expr()); // Preveri da li je `ctx.expr()` validan
//         assignmentNode.addChild(exprNode);
//     }

//     @Override
//     public void exitExpr(MuParser.ExprContext ctx) {
//         Node exprNode = new Node("EXPRESSION");
        
//         if (ctx.numberAtom() != null) {
//             Node numberNode = new Node("NUMBER", ctx.numberAtom().getText());
//             exprNode.addChild(numberNode);
//         } else if (ctx.idAtom() != null) {
//             Node idNode = new Node("ID", ctx.idAtom().getText());
//             exprNode.addChild(idNode);
//         } else if (ctx.op != null) {
//             Node operatorNode = new Node("OPERATOR", ctx.op.getText());
//             exprNode.addChild(operatorNode);
            
//             if (ctx.expr().size() > 0) {
//                 Node leftExpr = processExpression(ctx.expr(0));
//                 exprNode.addChild(leftExpr);
//             }
//             if (ctx.expr().size() > 1) {
//                 Node rightExpr = processExpression(ctx.expr(1));
//                 exprNode.addChild(rightExpr);
//             }
//         }
//     }

//     private Node processExpression(MuParser.ExprContext ctx) {
//         // Pretpostavljamo da se izrazi obrađuju ovako
//         Node exprNode = new Node("EXPRESSION");
//         if (ctx.numberAtom() != null) {
//             exprNode.addChild(new Node("NUMBER", ctx.numberAtom().getText()));
//         } else if (ctx.idAtom() != null) {
//             exprNode.addChild(new Node("ID", ctx.idAtom().getText()));
//         } else if (ctx.op != null) {
//             Node operatorNode = new Node("OPERATOR", ctx.op.getText());
//             exprNode.addChild(operatorNode);
//         }
//         return exprNode;
//     }
// }
