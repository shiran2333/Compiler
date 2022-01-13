// Generated from .\lab.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link labParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface labVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link labParser#compUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompUnit(labParser.CompUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(labParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#funcType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncType(labParser.FuncTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(labParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(labParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(labParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(labParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#addExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExp(labParser.AddExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#mulExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExp(labParser.MulExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(labParser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(labParser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(labParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link labParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(labParser.NumberContext ctx);
}