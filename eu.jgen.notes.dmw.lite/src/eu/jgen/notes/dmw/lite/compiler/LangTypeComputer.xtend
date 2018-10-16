package eu.jgen.notes.dmw.lite.compiler

import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsTypeComputer
import eu.jgen.notes.dmw.lite.lang.YCreateStatement
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputationState
import eu.jgen.notes.dmw.lite.lang.YReadStatement
import org.eclipse.xtext.xbase.XExpression

class LangTypeComputer extends XbaseWithAnnotationsTypeComputer {

	def dispatch computeTypes(YCreateStatement expression, ITypeComputationState state) {
		expression.setExpressions.forEach [ element |
			state.withNonVoidExpectation.computeTypes(element)
		]

		expression.successExpressions.forEach [ element |
			state.withNonVoidExpectation.computeTypes(element)
		]

		expression.alreadyExistExpressions.forEach [ element |
			state.withNonVoidExpectation.computeTypes(element)
		]

		state.acceptActualType(getPrimitiveVoid(state))
	}

	def dispatch computeTypes(YReadStatement expression, ITypeComputationState state) {
		if (expression.whereclause !== null) {
			val whereExpectation = state.withExpectation(getRawTypeForName(Boolean.TYPE, state));
			whereExpectation.computeTypes(expression.whereclause);
		}
		state.acceptActualType(getPrimitiveVoid(state))
	}
}
