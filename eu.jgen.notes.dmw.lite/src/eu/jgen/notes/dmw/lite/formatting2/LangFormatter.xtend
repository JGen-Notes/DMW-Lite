/*
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.formatting2

import com.google.inject.Inject
import eu.jgen.notes.dmw.lite.lang.YAnnotTop
import eu.jgen.notes.dmw.lite.lang.YClass
import eu.jgen.notes.dmw.lite.lang.YImport
import eu.jgen.notes.dmw.lite.lang.YMember
import eu.jgen.notes.dmw.lite.lang.YWidget
import eu.jgen.notes.dmw.lite.services.LangGrammarAccess
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument

class LangFormatter extends AbstractFormatter2 {
	
	@Inject extension LangGrammarAccess

	def dispatch void format(YWidget yWidget, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (YImport yImport : yWidget.getImports()) {
			yImport.format;
		}
		for (YAnnotTop yAnnotTop : yWidget.getAnnotations()) {
			yAnnotTop.format;
		}
		for (YClass yClass : yWidget.getClasses()) {
			yClass.format;
		}
	}

	def dispatch void format(YClass yClass, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (YClass _yClass : yClass.getInners()) {
			_yClass.format;
		}
		for (YMember yMember : yClass.getMembers()) {
			yMember.format;
		}
	}
	
	// TODO: implement for YProperty, YFunction, YBlock, YVariableDeclaration, YReturn, YIfStatement, YSwitchStatement, YSwitchCase, YAssignment, YMemberSelection, YOrExpression, YAndExpression, YEqualityExpression, YComparisonExpression, YMulOrDiv, YNew, YReadStatement, YReadEachStatement, YCreateStatement, YUpdateStatement, YDeleteStatement, YAssociateStatement, YJoin, YWhere, YWhileStatement, YRepeatWhileStatement, YForInStatement, YAnnotTop, YAnnotEntity, YAnnotAttr, YAnnotTechnicalDesign, YAnnotTable, YAnnotColumn, YAnnotAbstractColumn, YAnnotForeignKey, YPlus, YMinus
}