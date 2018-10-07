package eu.jgen.notes.dmw.lite.generator

import org.eclipse.xtext.generator.IGenerator
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import eu.jgen.notes.dmw.lite.lang.YAnnotTechnicalDesign
import eu.jgen.notes.dmw.lite.lang.YAnnotTable
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike
import eu.jgen.notes.dmw.lite.lang.YAnnotLength

class LangTechicalDesignGenerator implements IGenerator {

	override doGenerate(Resource input, IFileSystemAccess fsa) {
		input.allContents.filter[element|element instanceof YAnnotTechnicalDesign].forEach [ element |
			val technicalDesign = element as YAnnotTechnicalDesign
			generateDDLForDerby(fsa, technicalDesign)
//			if (technicalDesign.database.name == "MySQL") {
//					generateDDLForMySQL(fsa, technicalDesign)
//			}	
		]
	}

//	protected def String generateDDLString(IFileSystemAccess fsa, YAnnotTechnicalDesign technicalDesign) {
//		'''			
//			«FOR table : technicalDesign.features»
//				«generateTables(table)»
//			«ENDFOR»
//		'''
//	}

	protected def void generateDDLForDerby(IFileSystemAccess fsa, YAnnotTechnicalDesign technicalDesign) {
		for (table : technicalDesign.features) {
			generateTableForDerby(fsa, table)
		}
	}

//	protected def void generateDDLForMySQL(IFileSystemAccess fsa, YAnnotTechnicalDesign technicalDesign) {
//		fsa.generateFile(
//			"mysql/" + "createdatabase" + ".ddl",
//			LangOutputProvider.DDL,
//			'''				
//				«FOR table : technicalDesign.features»
//					«generateTables(table)»		
//				«ENDFOR»
//			'''
//		)
//	}
	def private void generateTableForDerby(IFileSystemAccess fsa, YAnnotTable table) {
		val text = '''
			CREATE TABLE "«table.name»" (
				«FOR abstractColumn : table.columns SEPARATOR ',' AFTER ','»
					«generateColumnForDerby(abstractColumn)»
				«ENDFOR»
				«FOR foreignKey : table.foreignkeys SEPARATOR ',' AFTER ','»
					«generateForeignKeyColumns(foreignKey)»
				«ENDFOR»
				«IF table.primarykey !== null»«generatePrimaryKey(table)»«ENDIF»
			)
		'''
		fsa.generateFile(
			"derby/" + table.name + ".ddl",
			LangOutputProvider.DDL,
			text
		)

	}

	def private String generateColumnForDerby(YAnnotAbstractColumn abstractColumn) {
		val text = '''
			"«abstractColumn.name.toUpperCase»" «generateColumnTypeForDerby(abstractColumn)»
		'''
		return text
	}

	def private String generateForeignKeyColumns(YAnnotForeignKey foreignKey) {
		val text = '''
			«FOR abstractColumn : foreignKey.columns»
				"«abstractColumn.name.toUpperCase»" «generateColumnTypeForDerby(abstractColumn)»
			«ENDFOR»					
		'''
		return text
	}

	def private String generateColumnTypeForDerby(YAnnotAbstractColumn abstractColumn) {
		if (abstractColumn.type instanceof YAnnotColumn) {
			val column = abstractColumn.type as YAnnotColumn
			val text = '''
				«column.type»«IF column.type == "CHAR" ||  column.type == "VARCHAR"»(«column.generateLengthForDerby»)«ENDIF» «column.generateNullFlag»
			'''
			return text
		} else {
			generateColumnTypeForDerby((abstractColumn.type as YAnnotColumnLike).columnref)
		}
	}

	def private String generateLengthForDerby(YAnnotColumn column) {
		for (annot : column.annots) {
			if (annot instanceof YAnnotLength) {
				val a = annot as YAnnotLength
				return a.length.toString
			}
		}
		return ""
	}

	def private String generateNullFlag(YAnnotColumn column) {
		if (column.optional != "?") {
			return "NOT NULL"
		}
	}

	def private String generatePrimaryKey(YAnnotTable table) {
		val text = '''
			CONSTRAINT "«"PK_" + table.name»"
			PRIMARY KEY («FOR abstractColumn : table.primarykey.columns SEPARATOR ','»"«abstractColumn.name.toUpperCase»"«ENDFOR»)
		'''
		return text
	}

}
