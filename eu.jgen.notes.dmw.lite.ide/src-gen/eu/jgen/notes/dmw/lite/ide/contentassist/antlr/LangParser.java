/*
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.ide.contentassist.antlr;

import com.google.inject.Inject;
import eu.jgen.notes.dmw.lite.ide.contentassist.antlr.internal.InternalLangParser;
import eu.jgen.notes.dmw.lite.services.LangGrammarAccess;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class LangParser extends AbstractContentAssistParser {

	@Inject
	private LangGrammarAccess grammarAccess;

	private Map<AbstractElement, String> nameMappings;

	@Override
	protected InternalLangParser createParser() {
		InternalLangParser result = new InternalLangParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getYArgumentValueAccess().getAlternatives(), "rule__YArgumentValue__Alternatives");
					put(grammarAccess.getYMemberAccess().getAlternatives(), "rule__YMember__Alternatives");
					put(grammarAccess.getYStatementAccess().getAlternatives(), "rule__YStatement__Alternatives");
					put(grammarAccess.getYSymbolAccess().getAlternatives(), "rule__YSymbol__Alternatives");
					put(grammarAccess.getYNamedElementAccess().getAlternatives(), "rule__YNamedElement__Alternatives");
					put(grammarAccess.getYEqualityExpressionAccess().getOpAlternatives_1_1_0(), "rule__YEqualityExpression__OpAlternatives_1_1_0");
					put(grammarAccess.getOpCompareAccess().getAlternatives(), "rule__OpCompare__Alternatives");
					put(grammarAccess.getYAdditiveExpressionAccess().getAlternatives_1_0(), "rule__YAdditiveExpression__Alternatives_1_0");
					put(grammarAccess.getOpAddAccess().getAlternatives(), "rule__OpAdd__Alternatives");
					put(grammarAccess.getYMultiplicativeExpressionAccess().getOpAlternatives_1_1_0(), "rule__YMultiplicativeExpression__OpAlternatives_1_1_0");
					put(grammarAccess.getYPrimaryAccess().getAlternatives(), "rule__YPrimary__Alternatives");
					put(grammarAccess.getYTerminalExpressionAccess().getAlternatives(), "rule__YTerminalExpression__Alternatives");
					put(grammarAccess.getYTerminalExpressionAccess().getValueAlternatives_2_1_0(), "rule__YTerminalExpression__ValueAlternatives_2_1_0");
					put(grammarAccess.getYAnnotAccess().getTypeAlternatives_1_0(), "rule__YAnnot__TypeAlternatives_1_0");
					put(grammarAccess.getYAnnotTopAccess().getTypeAlternatives_0(), "rule__YAnnotTop__TypeAlternatives_0");
					put(grammarAccess.getYAnnotDefaultTypeAccess().getAlternatives(), "rule__YAnnotDefaultType__Alternatives");
					put(grammarAccess.getYAnnotEntityInnerAccess().getAlternatives(), "rule__YAnnotEntityInner__Alternatives");
					put(grammarAccess.getYAnnotIdInnerAccess().getAlternatives(), "rule__YAnnotIdInner__Alternatives");
					put(grammarAccess.getYAnnotColumnAccess().getTypeAlternatives_4_0(), "rule__YAnnotColumn__TypeAlternatives_4_0");
					put(grammarAccess.getYAnnotColumnAccess().getAnnotsAlternatives_6_0(), "rule__YAnnotColumn__AnnotsAlternatives_6_0");
					put(grammarAccess.getYAnnotAbstractColumnAccess().getTypeAlternatives_3_0(), "rule__YAnnotAbstractColumn__TypeAlternatives_3_0");
					put(grammarAccess.getYAccessLevelAccess().getAlternatives(), "rule__YAccessLevel__Alternatives");
					put(grammarAccess.getYWidgetAccess().getGroup(), "rule__YWidget__Group__0");
					put(grammarAccess.getYWidgetAccess().getGroup_0(), "rule__YWidget__Group_0__0");
					put(grammarAccess.getYImportAccess().getGroup(), "rule__YImport__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup(), "rule__QualifiedName__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup_1(), "rule__QualifiedName__Group_1__0");
					put(grammarAccess.getQualifiedNameWithWildcardAccess().getGroup(), "rule__QualifiedNameWithWildcard__Group__0");
					put(grammarAccess.getYArgumentAccess().getGroup(), "rule__YArgument__Group__0");
					put(grammarAccess.getYClassAccess().getGroup(), "rule__YClass__Group__0");
					put(grammarAccess.getYClassAccess().getGroup_3(), "rule__YClass__Group_3__0");
					put(grammarAccess.getYClassAccess().getGroup_4(), "rule__YClass__Group_4__0");
					put(grammarAccess.getYTypedDeclarationAccess().getGroup(), "rule__YTypedDeclaration__Group__0");
					put(grammarAccess.getYPropertyAccess().getGroup(), "rule__YProperty__Group__0");
					put(grammarAccess.getYPropertyAccess().getGroup_5(), "rule__YProperty__Group_5__0");
					put(grammarAccess.getYTuplesAccess().getGroup(), "rule__YTuples__Group__0");
					put(grammarAccess.getYTuplesAccess().getGroup_2(), "rule__YTuples__Group_2__0");
					put(grammarAccess.getYTuplesAccess().getGroup_2_1(), "rule__YTuples__Group_2_1__0");
					put(grammarAccess.getYFunctionAccess().getGroup(), "rule__YFunction__Group__0");
					put(grammarAccess.getYFunctionAccess().getGroup_4(), "rule__YFunction__Group_4__0");
					put(grammarAccess.getYFunctionAccess().getGroup_4_1(), "rule__YFunction__Group_4_1__0");
					put(grammarAccess.getYBlockAccess().getGroup(), "rule__YBlock__Group__0");
					put(grammarAccess.getYStatementAccess().getGroup_11(), "rule__YStatement__Group_11__0");
					put(grammarAccess.getYVariableDeclarationAccess().getGroup(), "rule__YVariableDeclaration__Group__0");
					put(grammarAccess.getYReturnAccess().getGroup(), "rule__YReturn__Group__0");
					put(grammarAccess.getYIfStatementAccess().getGroup(), "rule__YIfStatement__Group__0");
					put(grammarAccess.getYIfStatementAccess().getGroup_5(), "rule__YIfStatement__Group_5__0");
					put(grammarAccess.getYSwitchStatementAccess().getGroup(), "rule__YSwitchStatement__Group__0");
					put(grammarAccess.getYSwitchStatementAccess().getGroup_5(), "rule__YSwitchStatement__Group_5__0");
					put(grammarAccess.getYSwitchCaseAccess().getGroup(), "rule__YSwitchCase__Group__0");
					put(grammarAccess.getYSwitchCaseAccess().getGroup_1(), "rule__YSwitchCase__Group_1__0");
					put(grammarAccess.getYAssignmentAccess().getGroup(), "rule__YAssignment__Group__0");
					put(grammarAccess.getYAssignmentAccess().getGroup_1(), "rule__YAssignment__Group_1__0");
					put(grammarAccess.getYSelectionExpressionAccess().getGroup(), "rule__YSelectionExpression__Group__0");
					put(grammarAccess.getYSelectionExpressionAccess().getGroup_1(), "rule__YSelectionExpression__Group_1__0");
					put(grammarAccess.getYSelectionExpressionAccess().getGroup_1_3(), "rule__YSelectionExpression__Group_1_3__0");
					put(grammarAccess.getYSelectionExpressionAccess().getGroup_1_3_1(), "rule__YSelectionExpression__Group_1_3_1__0");
					put(grammarAccess.getYSelectionExpressionAccess().getGroup_1_3_1_1(), "rule__YSelectionExpression__Group_1_3_1_1__0");
					put(grammarAccess.getYOrExpressionAccess().getGroup(), "rule__YOrExpression__Group__0");
					put(grammarAccess.getYOrExpressionAccess().getGroup_1(), "rule__YOrExpression__Group_1__0");
					put(grammarAccess.getYAndExpressionAccess().getGroup(), "rule__YAndExpression__Group__0");
					put(grammarAccess.getYAndExpressionAccess().getGroup_1(), "rule__YAndExpression__Group_1__0");
					put(grammarAccess.getYEqualityExpressionAccess().getGroup(), "rule__YEqualityExpression__Group__0");
					put(grammarAccess.getYEqualityExpressionAccess().getGroup_1(), "rule__YEqualityExpression__Group_1__0");
					put(grammarAccess.getYComparisonExpressionAccess().getGroup(), "rule__YComparisonExpression__Group__0");
					put(grammarAccess.getYComparisonExpressionAccess().getGroup_1(), "rule__YComparisonExpression__Group_1__0");
					put(grammarAccess.getYAdditiveExpressionAccess().getGroup(), "rule__YAdditiveExpression__Group__0");
					put(grammarAccess.getYAdditiveExpressionAccess().getGroup_1(), "rule__YAdditiveExpression__Group_1__0");
					put(grammarAccess.getYAdditiveExpressionAccess().getGroup_1_0_0(), "rule__YAdditiveExpression__Group_1_0_0__0");
					put(grammarAccess.getYAdditiveExpressionAccess().getGroup_1_0_1(), "rule__YAdditiveExpression__Group_1_0_1__0");
					put(grammarAccess.getYMultiplicativeExpressionAccess().getGroup(), "rule__YMultiplicativeExpression__Group__0");
					put(grammarAccess.getYMultiplicativeExpressionAccess().getGroup_1(), "rule__YMultiplicativeExpression__Group_1__0");
					put(grammarAccess.getYPrimaryAccess().getGroup_0(), "rule__YPrimary__Group_0__0");
					put(grammarAccess.getYPrimaryAccess().getGroup_1(), "rule__YPrimary__Group_1__0");
					put(grammarAccess.getYTerminalExpressionAccess().getGroup_0(), "rule__YTerminalExpression__Group_0__0");
					put(grammarAccess.getYTerminalExpressionAccess().getGroup_1(), "rule__YTerminalExpression__Group_1__0");
					put(grammarAccess.getYTerminalExpressionAccess().getGroup_2(), "rule__YTerminalExpression__Group_2__0");
					put(grammarAccess.getYTerminalExpressionAccess().getGroup_3(), "rule__YTerminalExpression__Group_3__0");
					put(grammarAccess.getYTerminalExpressionAccess().getGroup_4(), "rule__YTerminalExpression__Group_4__0");
					put(grammarAccess.getYTerminalExpressionAccess().getGroup_5(), "rule__YTerminalExpression__Group_5__0");
					put(grammarAccess.getYTerminalExpressionAccess().getGroup_6(), "rule__YTerminalExpression__Group_6__0");
					put(grammarAccess.getYTerminalExpressionAccess().getGroup_7(), "rule__YTerminalExpression__Group_7__0");
					put(grammarAccess.getYTerminalExpressionAccess().getGroup_7_4(), "rule__YTerminalExpression__Group_7_4__0");
					put(grammarAccess.getYTerminalExpressionAccess().getGroup_7_4_1(), "rule__YTerminalExpression__Group_7_4_1__0");
					put(grammarAccess.getYReadStatementAccess().getGroup(), "rule__YReadStatement__Group__0");
					put(grammarAccess.getYReadStatementAccess().getGroup_2(), "rule__YReadStatement__Group_2__0");
					put(grammarAccess.getYReadEachStatementAccess().getGroup(), "rule__YReadEachStatement__Group__0");
					put(grammarAccess.getYReadEachStatementAccess().getGroup_3(), "rule__YReadEachStatement__Group_3__0");
					put(grammarAccess.getYCreateStatementAccess().getGroup(), "rule__YCreateStatement__Group__0");
					put(grammarAccess.getYUpdateStatementAccess().getGroup(), "rule__YUpdateStatement__Group__0");
					put(grammarAccess.getYDeleteStatementAccess().getGroup(), "rule__YDeleteStatement__Group__0");
					put(grammarAccess.getYAssociateStatementAccess().getGroup(), "rule__YAssociateStatement__Group__0");
					put(grammarAccess.getYStructRefPairAccess().getGroup(), "rule__YStructRefPair__Group__0");
					put(grammarAccess.getYJoinAccess().getGroup(), "rule__YJoin__Group__0");
					put(grammarAccess.getYJoinAccess().getGroup_3(), "rule__YJoin__Group_3__0");
					put(grammarAccess.getYJoinDefAccess().getGroup(), "rule__YJoinDef__Group__0");
					put(grammarAccess.getYWhereAccess().getGroup(), "rule__YWhere__Group__0");
					put(grammarAccess.getYWhileStatementAccess().getGroup(), "rule__YWhileStatement__Group__0");
					put(grammarAccess.getYRepeatWhileStatementAccess().getGroup(), "rule__YRepeatWhileStatement__Group__0");
					put(grammarAccess.getYForInStatementAccess().getGroup(), "rule__YForInStatement__Group__0");
					put(grammarAccess.getYAnnotAccess().getGroup(), "rule__YAnnot__Group__0");
					put(grammarAccess.getYAnnotLengthAccess().getGroup(), "rule__YAnnotLength__Group__0");
					put(grammarAccess.getYAnnotMaxAccess().getGroup(), "rule__YAnnotMax__Group__0");
					put(grammarAccess.getYAnnotDecimalAccess().getGroup(), "rule__YAnnotDecimal__Group__0");
					put(grammarAccess.getYAnnotDefaultAccess().getGroup(), "rule__YAnnotDefault__Group__0");
					put(grammarAccess.getYAnnotActionAccess().getGroup(), "rule__YAnnotAction__Group__0");
					put(grammarAccess.getYAnnotMessageAccess().getGroup(), "rule__YAnnotMessage__Group__0");
					put(grammarAccess.getYAnnotMsgTypeAccess().getGroup(), "rule__YAnnotMsgType__Group__0");
					put(grammarAccess.getYAnnotEntityAccess().getGroup(), "rule__YAnnotEntity__Group__0");
					put(grammarAccess.getYAnnotEntityAccess().getGroup_2(), "rule__YAnnotEntity__Group_2__0");
					put(grammarAccess.getYAnnotAttrAccess().getGroup(), "rule__YAnnotAttr__Group__0");
					put(grammarAccess.getYAnnotRelAccess().getGroup(), "rule__YAnnotRel__Group__0");
					put(grammarAccess.getYAnnotRelAccess().getGroup_8(), "rule__YAnnotRel__Group_8__0");
					put(grammarAccess.getYAnnotIdAccess().getGroup(), "rule__YAnnotId__Group__0");
					put(grammarAccess.getYAnnotIdAccess().getGroup_5(), "rule__YAnnotId__Group_5__0");
					put(grammarAccess.getYAnnotTechnicalDesignAccess().getGroup(), "rule__YAnnotTechnicalDesign__Group__0");
					put(grammarAccess.getYAnnotTableAccess().getGroup(), "rule__YAnnotTable__Group__0");
					put(grammarAccess.getYAnnotColumnAccess().getGroup(), "rule__YAnnotColumn__Group__0");
					put(grammarAccess.getYAnnotColumnLikeAccess().getGroup(), "rule__YAnnotColumnLike__Group__0");
					put(grammarAccess.getYAnnotAbstractColumnAccess().getGroup(), "rule__YAnnotAbstractColumn__Group__0");
					put(grammarAccess.getYAnnotPrimaryKeyAccess().getGroup(), "rule__YAnnotPrimaryKey__Group__0");
					put(grammarAccess.getYAnnotPrimaryKeyAccess().getGroup_4(), "rule__YAnnotPrimaryKey__Group_4__0");
					put(grammarAccess.getYAnnotForeignKeyAccess().getGroup(), "rule__YAnnotForeignKey__Group__0");
					put(grammarAccess.getYAnnotForeignKeyAccess().getGroup_5(), "rule__YAnnotForeignKey__Group_5__0");
					put(grammarAccess.getYAnnotJavaAccess().getGroup(), "rule__YAnnotJava__Group__0");
					put(grammarAccess.getYAnnotJavaAccess().getGroup_2(), "rule__YAnnotJava__Group_2__0");
					put(grammarAccess.getYAnnotSwiftAccess().getGroup(), "rule__YAnnotSwift__Group__0");
					put(grammarAccess.getYAnnotSwiftAccess().getGroup_4(), "rule__YAnnotSwift__Group_4__0");
					put(grammarAccess.getYAnnotDatabaseAccess().getGroup(), "rule__YAnnotDatabase__Group__0");
					put(grammarAccess.getYWidgetAccess().getNameAssignment_0_1(), "rule__YWidget__NameAssignment_0_1");
					put(grammarAccess.getYWidgetAccess().getImportsAssignment_1(), "rule__YWidget__ImportsAssignment_1");
					put(grammarAccess.getYWidgetAccess().getAnnotationsAssignment_2(), "rule__YWidget__AnnotationsAssignment_2");
					put(grammarAccess.getYWidgetAccess().getClassesAssignment_3(), "rule__YWidget__ClassesAssignment_3");
					put(grammarAccess.getYImportAccess().getImportedNamespaceAssignment_1(), "rule__YImport__ImportedNamespaceAssignment_1");
					put(grammarAccess.getYArgumentAccess().getNameAssignment_0(), "rule__YArgument__NameAssignment_0");
					put(grammarAccess.getYArgumentAccess().getValueAssignment_2(), "rule__YArgument__ValueAssignment_2");
					put(grammarAccess.getYClassAccess().getNameAssignment_2(), "rule__YClass__NameAssignment_2");
					put(grammarAccess.getYClassAccess().getSuperclassAssignment_3_1(), "rule__YClass__SuperclassAssignment_3_1");
					put(grammarAccess.getYClassAccess().getEntityAssignment_4_1(), "rule__YClass__EntityAssignment_4_1");
					put(grammarAccess.getYClassAccess().getInnersAssignment_6(), "rule__YClass__InnersAssignment_6");
					put(grammarAccess.getYClassAccess().getMembersAssignment_7(), "rule__YClass__MembersAssignment_7");
					put(grammarAccess.getYTypedDeclarationAccess().getNameAssignment_0(), "rule__YTypedDeclaration__NameAssignment_0");
					put(grammarAccess.getYTypedDeclarationAccess().getTypeAssignment_2(), "rule__YTypedDeclaration__TypeAssignment_2");
					put(grammarAccess.getYPropertyAccess().getAccessAssignment_0(), "rule__YProperty__AccessAssignment_0");
					put(grammarAccess.getYPropertyAccess().getTuplesAssignment_3(), "rule__YProperty__TuplesAssignment_3");
					put(grammarAccess.getYPropertyAccess().getOptionalAssignment_4(), "rule__YProperty__OptionalAssignment_4");
					put(grammarAccess.getYPropertyAccess().getAttrAssignment_5_1(), "rule__YProperty__AttrAssignment_5_1");
					put(grammarAccess.getYPropertyAccess().getAnnotationsAssignment_6(), "rule__YProperty__AnnotationsAssignment_6");
					put(grammarAccess.getYTuplesAccess().getIncludesAssignment_2_0(), "rule__YTuples__IncludesAssignment_2_0");
					put(grammarAccess.getYTuplesAccess().getIncludesAssignment_2_1_1(), "rule__YTuples__IncludesAssignment_2_1_1");
					put(grammarAccess.getYFunctionAccess().getAccessAssignment_0(), "rule__YFunction__AccessAssignment_0");
					put(grammarAccess.getYFunctionAccess().getNameAssignment_2(), "rule__YFunction__NameAssignment_2");
					put(grammarAccess.getYFunctionAccess().getParamsAssignment_4_0(), "rule__YFunction__ParamsAssignment_4_0");
					put(grammarAccess.getYFunctionAccess().getParamsAssignment_4_1_1(), "rule__YFunction__ParamsAssignment_4_1_1");
					put(grammarAccess.getYFunctionAccess().getReturnvalueAssignment_6(), "rule__YFunction__ReturnvalueAssignment_6");
					put(grammarAccess.getYFunctionAccess().getTypeAssignment_7(), "rule__YFunction__TypeAssignment_7");
					put(grammarAccess.getYFunctionAccess().getBodyAssignment_8(), "rule__YFunction__BodyAssignment_8");
					put(grammarAccess.getYBlockAccess().getStatementsAssignment_2(), "rule__YBlock__StatementsAssignment_2");
					put(grammarAccess.getYVariableDeclarationAccess().getExpressionAssignment_2(), "rule__YVariableDeclaration__ExpressionAssignment_2");
					put(grammarAccess.getYReturnAccess().getExpressionAssignment_2(), "rule__YReturn__ExpressionAssignment_2");
					put(grammarAccess.getYIfStatementAccess().getExpressionAssignment_2(), "rule__YIfStatement__ExpressionAssignment_2");
					put(grammarAccess.getYIfStatementAccess().getThenBlockAssignment_4(), "rule__YIfStatement__ThenBlockAssignment_4");
					put(grammarAccess.getYIfStatementAccess().getElseBlockAssignment_5_1(), "rule__YIfStatement__ElseBlockAssignment_5_1");
					put(grammarAccess.getYSwitchStatementAccess().getSwitchExpressionAssignment_2(), "rule__YSwitchStatement__SwitchExpressionAssignment_2");
					put(grammarAccess.getYSwitchStatementAccess().getCasesAssignment_4(), "rule__YSwitchStatement__CasesAssignment_4");
					put(grammarAccess.getYSwitchStatementAccess().getDefaultAssignment_5_2(), "rule__YSwitchStatement__DefaultAssignment_5_2");
					put(grammarAccess.getYSwitchCaseAccess().getCaseExpressionAssignment_1_1(), "rule__YSwitchCase__CaseExpressionAssignment_1_1");
					put(grammarAccess.getYSwitchCaseAccess().getThenAssignment_3(), "rule__YSwitchCase__ThenAssignment_3");
					put(grammarAccess.getYAssignmentAccess().getRightAssignment_1_2(), "rule__YAssignment__RightAssignment_1_2");
					put(grammarAccess.getYSelectionExpressionAccess().getMemberAssignment_1_2(), "rule__YSelectionExpression__MemberAssignment_1_2");
					put(grammarAccess.getYSelectionExpressionAccess().getFunctioninvocationAssignment_1_3_0(), "rule__YSelectionExpression__FunctioninvocationAssignment_1_3_0");
					put(grammarAccess.getYSelectionExpressionAccess().getArgsAssignment_1_3_1_0(), "rule__YSelectionExpression__ArgsAssignment_1_3_1_0");
					put(grammarAccess.getYSelectionExpressionAccess().getArgsAssignment_1_3_1_1_1(), "rule__YSelectionExpression__ArgsAssignment_1_3_1_1_1");
					put(grammarAccess.getYOrExpressionAccess().getRightAssignment_1_2(), "rule__YOrExpression__RightAssignment_1_2");
					put(grammarAccess.getYAndExpressionAccess().getRightAssignment_1_2(), "rule__YAndExpression__RightAssignment_1_2");
					put(grammarAccess.getYEqualityExpressionAccess().getOpAssignment_1_1(), "rule__YEqualityExpression__OpAssignment_1_1");
					put(grammarAccess.getYEqualityExpressionAccess().getRightAssignment_1_2(), "rule__YEqualityExpression__RightAssignment_1_2");
					put(grammarAccess.getYComparisonExpressionAccess().getOpAssignment_1_1(), "rule__YComparisonExpression__OpAssignment_1_1");
					put(grammarAccess.getYComparisonExpressionAccess().getRightAssignment_1_2(), "rule__YComparisonExpression__RightAssignment_1_2");
					put(grammarAccess.getYAdditiveExpressionAccess().getRightAssignment_1_1(), "rule__YAdditiveExpression__RightAssignment_1_1");
					put(grammarAccess.getYMultiplicativeExpressionAccess().getOpAssignment_1_1(), "rule__YMultiplicativeExpression__OpAssignment_1_1");
					put(grammarAccess.getYMultiplicativeExpressionAccess().getRightAssignment_1_2(), "rule__YMultiplicativeExpression__RightAssignment_1_2");
					put(grammarAccess.getYPrimaryAccess().getAAssignment_0_2(), "rule__YPrimary__AAssignment_0_2");
					put(grammarAccess.getYPrimaryAccess().getExpressionAssignment_1_2(), "rule__YPrimary__ExpressionAssignment_1_2");
					put(grammarAccess.getYTerminalExpressionAccess().getValueAssignment_0_1(), "rule__YTerminalExpression__ValueAssignment_0_1");
					put(grammarAccess.getYTerminalExpressionAccess().getValueAssignment_1_1(), "rule__YTerminalExpression__ValueAssignment_1_1");
					put(grammarAccess.getYTerminalExpressionAccess().getValueAssignment_2_1(), "rule__YTerminalExpression__ValueAssignment_2_1");
					put(grammarAccess.getYTerminalExpressionAccess().getSymbolAssignment_6_1(), "rule__YTerminalExpression__SymbolAssignment_6_1");
					put(grammarAccess.getYTerminalExpressionAccess().getTypeAssignment_7_2(), "rule__YTerminalExpression__TypeAssignment_7_2");
					put(grammarAccess.getYTerminalExpressionAccess().getArgumentsAssignment_7_4_0(), "rule__YTerminalExpression__ArgumentsAssignment_7_4_0");
					put(grammarAccess.getYTerminalExpressionAccess().getArgumentsAssignment_7_4_1_1(), "rule__YTerminalExpression__ArgumentsAssignment_7_4_1_1");
					put(grammarAccess.getYReadStatementAccess().getStructsAssignment_1(), "rule__YReadStatement__StructsAssignment_1");
					put(grammarAccess.getYReadStatementAccess().getStructsAssignment_2_1(), "rule__YReadStatement__StructsAssignment_2_1");
					put(grammarAccess.getYReadStatementAccess().getJoinclauseAssignment_3(), "rule__YReadStatement__JoinclauseAssignment_3");
					put(grammarAccess.getYReadStatementAccess().getWhereclauseAssignment_4(), "rule__YReadStatement__WhereclauseAssignment_4");
					put(grammarAccess.getYReadStatementAccess().getSuccessAssignment_6(), "rule__YReadStatement__SuccessAssignment_6");
					put(grammarAccess.getYReadStatementAccess().getNotfoundAssignment_9(), "rule__YReadStatement__NotfoundAssignment_9");
					put(grammarAccess.getYReadEachStatementAccess().getStructsAssignment_2(), "rule__YReadEachStatement__StructsAssignment_2");
					put(grammarAccess.getYReadEachStatementAccess().getStructsAssignment_3_1(), "rule__YReadEachStatement__StructsAssignment_3_1");
					put(grammarAccess.getYReadEachStatementAccess().getJoinclauseAssignment_4(), "rule__YReadEachStatement__JoinclauseAssignment_4");
					put(grammarAccess.getYReadEachStatementAccess().getWhereclauseAssignment_5(), "rule__YReadEachStatement__WhereclauseAssignment_5");
					put(grammarAccess.getYReadEachStatementAccess().getTargetAssignment_7(), "rule__YReadEachStatement__TargetAssignment_7");
					put(grammarAccess.getYReadEachStatementAccess().getSuccessAssignment_8(), "rule__YReadEachStatement__SuccessAssignment_8");
					put(grammarAccess.getYCreateStatementAccess().getStructAssignment_1(), "rule__YCreateStatement__StructAssignment_1");
					put(grammarAccess.getYCreateStatementAccess().getSetBlockAssignment_2(), "rule__YCreateStatement__SetBlockAssignment_2");
					put(grammarAccess.getYCreateStatementAccess().getSuccessAssignment_4(), "rule__YCreateStatement__SuccessAssignment_4");
					put(grammarAccess.getYCreateStatementAccess().getAlreadyExistAssignment_7(), "rule__YCreateStatement__AlreadyExistAssignment_7");
					put(grammarAccess.getYUpdateStatementAccess().getStructAssignment_1(), "rule__YUpdateStatement__StructAssignment_1");
					put(grammarAccess.getYUpdateStatementAccess().getSetBlockAssignment_2(), "rule__YUpdateStatement__SetBlockAssignment_2");
					put(grammarAccess.getYUpdateStatementAccess().getSuccessAssignment_4(), "rule__YUpdateStatement__SuccessAssignment_4");
					put(grammarAccess.getYDeleteStatementAccess().getStructAssignment_1(), "rule__YDeleteStatement__StructAssignment_1");
					put(grammarAccess.getYAssociateStatementAccess().getJoinrefAssignment_1(), "rule__YAssociateStatement__JoinrefAssignment_1");
					put(grammarAccess.getYStructRefPairAccess().getStructpropertyAssignment_0(), "rule__YStructRefPair__StructpropertyAssignment_0");
					put(grammarAccess.getYStructRefPairAccess().getStructclassAssignment_2(), "rule__YStructRefPair__StructclassAssignment_2");
					put(grammarAccess.getYJoinAccess().getJoindefsAssignment_2(), "rule__YJoin__JoindefsAssignment_2");
					put(grammarAccess.getYJoinAccess().getJoindefsAssignment_3_1(), "rule__YJoin__JoindefsAssignment_3_1");
					put(grammarAccess.getYJoinDefAccess().getFromViewAssignment_0(), "rule__YJoinDef__FromViewAssignment_0");
					put(grammarAccess.getYJoinDefAccess().getRelRefAssignment_2(), "rule__YJoinDef__RelRefAssignment_2");
					put(grammarAccess.getYJoinDefAccess().getToViewAssignment_4(), "rule__YJoinDef__ToViewAssignment_4");
					put(grammarAccess.getYWhereAccess().getExpressionAssignment_2(), "rule__YWhere__ExpressionAssignment_2");
					put(grammarAccess.getYWhileStatementAccess().getExpressionAssignment_2(), "rule__YWhileStatement__ExpressionAssignment_2");
					put(grammarAccess.getYWhileStatementAccess().getBodyAssignment_4(), "rule__YWhileStatement__BodyAssignment_4");
					put(grammarAccess.getYRepeatWhileStatementAccess().getBodyAssignment_1(), "rule__YRepeatWhileStatement__BodyAssignment_1");
					put(grammarAccess.getYRepeatWhileStatementAccess().getExpressionAssignment_4(), "rule__YRepeatWhileStatement__ExpressionAssignment_4");
					put(grammarAccess.getYForInStatementAccess().getItemAssignment_1(), "rule__YForInStatement__ItemAssignment_1");
					put(grammarAccess.getYForInStatementAccess().getCollectionAssignment_3(), "rule__YForInStatement__CollectionAssignment_3");
					put(grammarAccess.getYForInStatementAccess().getBodyAssignment_4(), "rule__YForInStatement__BodyAssignment_4");
					put(grammarAccess.getYAnnotAccess().getTypeAssignment_1(), "rule__YAnnot__TypeAssignment_1");
					put(grammarAccess.getYAnnotTopAccess().getTypeAssignment(), "rule__YAnnotTop__TypeAssignment");
					put(grammarAccess.getYAnnotLengthAccess().getLengthAssignment_3(), "rule__YAnnotLength__LengthAssignment_3");
					put(grammarAccess.getYAnnotMaxAccess().getLengthAssignment_3(), "rule__YAnnotMax__LengthAssignment_3");
					put(grammarAccess.getYAnnotDecimalAccess().getLengthAssignment_3(), "rule__YAnnotDecimal__LengthAssignment_3");
					put(grammarAccess.getYAnnotDecimalAccess().getDecimalAssignment_5(), "rule__YAnnotDecimal__DecimalAssignment_5");
					put(grammarAccess.getYAnnotDefaultAccess().getTypeAssignment_3(), "rule__YAnnotDefault__TypeAssignment_3");
					put(grammarAccess.getYAnnotDefaultTextAccess().getValueAssignment(), "rule__YAnnotDefaultText__ValueAssignment");
					put(grammarAccess.getYAnnotDefaultNumberAccess().getValueAssignment(), "rule__YAnnotDefaultNumber__ValueAssignment");
					put(grammarAccess.getYAnnotActionAccess().getActionAssignment_3(), "rule__YAnnotAction__ActionAssignment_3");
					put(grammarAccess.getYAnnotMessageAccess().getMsgAssignment_3(), "rule__YAnnotMessage__MsgAssignment_3");
					put(grammarAccess.getYAnnotMsgTypeAccess().getMsgtypeAssignment_2(), "rule__YAnnotMsgType__MsgtypeAssignment_2");
					put(grammarAccess.getYAnnotEntityAccess().getNameAssignment_1(), "rule__YAnnotEntity__NameAssignment_1");
					put(grammarAccess.getYAnnotEntityAccess().getSuperannotAssignment_2_1(), "rule__YAnnotEntity__SuperannotAssignment_2_1");
					put(grammarAccess.getYAnnotEntityAccess().getAnnotsAssignment_4(), "rule__YAnnotEntity__AnnotsAssignment_4");
					put(grammarAccess.getYAnnotAttrAccess().getNameAssignment_2(), "rule__YAnnotAttr__NameAssignment_2");
					put(grammarAccess.getYAnnotAttrAccess().getYclassAssignment_4(), "rule__YAnnotAttr__YclassAssignment_4");
					put(grammarAccess.getYAnnotAttrAccess().getOptionalAssignment_5(), "rule__YAnnotAttr__OptionalAssignment_5");
					put(grammarAccess.getYAnnotAttrAccess().getAnnotsAssignment_6(), "rule__YAnnotAttr__AnnotsAssignment_6");
					put(grammarAccess.getYAnnotRelAccess().getParentAssignment_2(), "rule__YAnnotRel__ParentAssignment_2");
					put(grammarAccess.getYAnnotRelAccess().getNameAssignment_3(), "rule__YAnnotRel__NameAssignment_3");
					put(grammarAccess.getYAnnotRelAccess().getOptionalAssignment_4(), "rule__YAnnotRel__OptionalAssignment_4");
					put(grammarAccess.getYAnnotRelAccess().getTargetAssignment_6(), "rule__YAnnotRel__TargetAssignment_6");
					put(grammarAccess.getYAnnotRelAccess().getManyAssignment_7(), "rule__YAnnotRel__ManyAssignment_7");
					put(grammarAccess.getYAnnotRelAccess().getInverseAssignment_8_1(), "rule__YAnnotRel__InverseAssignment_8_1");
					put(grammarAccess.getYAnnotIdAccess().getNameAssignment_2(), "rule__YAnnotId__NameAssignment_2");
					put(grammarAccess.getYAnnotIdAccess().getAnnotsAssignment_4(), "rule__YAnnotId__AnnotsAssignment_4");
					put(grammarAccess.getYAnnotIdAccess().getAnnotsAssignment_5_1(), "rule__YAnnotId__AnnotsAssignment_5_1");
					put(grammarAccess.getYAnnotTechnicalDesignAccess().getDatabaseAssignment_3(), "rule__YAnnotTechnicalDesign__DatabaseAssignment_3");
					put(grammarAccess.getYAnnotTechnicalDesignAccess().getFeaturesAssignment_5(), "rule__YAnnotTechnicalDesign__FeaturesAssignment_5");
					put(grammarAccess.getYAnnotTableAccess().getNameAssignment_2(), "rule__YAnnotTable__NameAssignment_2");
					put(grammarAccess.getYAnnotTableAccess().getEntityrefAssignment_4(), "rule__YAnnotTable__EntityrefAssignment_4");
					put(grammarAccess.getYAnnotTableAccess().getColumnsAssignment_6(), "rule__YAnnotTable__ColumnsAssignment_6");
					put(grammarAccess.getYAnnotTableAccess().getPrimarykeyAssignment_7(), "rule__YAnnotTable__PrimarykeyAssignment_7");
					put(grammarAccess.getYAnnotTableAccess().getForeignkeysAssignment_8(), "rule__YAnnotTable__ForeignkeysAssignment_8");
					put(grammarAccess.getYAnnotColumnAccess().getAttrrefAssignment_2(), "rule__YAnnotColumn__AttrrefAssignment_2");
					put(grammarAccess.getYAnnotColumnAccess().getTypeAssignment_4(), "rule__YAnnotColumn__TypeAssignment_4");
					put(grammarAccess.getYAnnotColumnAccess().getOptionalAssignment_5(), "rule__YAnnotColumn__OptionalAssignment_5");
					put(grammarAccess.getYAnnotColumnAccess().getAnnotsAssignment_6(), "rule__YAnnotColumn__AnnotsAssignment_6");
					put(grammarAccess.getYAnnotColumnLikeAccess().getColumnrefAssignment_2(), "rule__YAnnotColumnLike__ColumnrefAssignment_2");
					put(grammarAccess.getYAnnotAbstractColumnAccess().getNameAssignment_2(), "rule__YAnnotAbstractColumn__NameAssignment_2");
					put(grammarAccess.getYAnnotAbstractColumnAccess().getTypeAssignment_3(), "rule__YAnnotAbstractColumn__TypeAssignment_3");
					put(grammarAccess.getYAnnotPrimaryKeyAccess().getColumnsAssignment_3(), "rule__YAnnotPrimaryKey__ColumnsAssignment_3");
					put(grammarAccess.getYAnnotPrimaryKeyAccess().getColumnsAssignment_4_1(), "rule__YAnnotPrimaryKey__ColumnsAssignment_4_1");
					put(grammarAccess.getYAnnotForeignKeyAccess().getRelationshipAssignment_2(), "rule__YAnnotForeignKey__RelationshipAssignment_2");
					put(grammarAccess.getYAnnotForeignKeyAccess().getColumnsAssignment_4(), "rule__YAnnotForeignKey__ColumnsAssignment_4");
					put(grammarAccess.getYAnnotForeignKeyAccess().getColumnsAssignment_5_1(), "rule__YAnnotForeignKey__ColumnsAssignment_5_1");
					put(grammarAccess.getYAnnotJavaAccess().getDatabaseAssignment_2_1(), "rule__YAnnotJava__DatabaseAssignment_2_1");
					put(grammarAccess.getYAnnotSwiftAccess().getNameAssignment_3(), "rule__YAnnotSwift__NameAssignment_3");
					put(grammarAccess.getYAnnotSwiftAccess().getDatabaseAssignment_4_1(), "rule__YAnnotSwift__DatabaseAssignment_4_1");
					put(grammarAccess.getYAnnotDatabaseAccess().getNameAssignment_2(), "rule__YAnnotDatabase__NameAssignment_2");
				}
			};
		}
		return nameMappings.get(element);
	}
			
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public LangGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(LangGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
