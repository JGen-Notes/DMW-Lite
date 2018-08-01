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

class LangTechicalDesignGenerator implements IGenerator {

	override doGenerate(Resource input, IFileSystemAccess fsa) {
		input.allContents.filter[element|element instanceof YAnnotTechnicalDesign].forEach [ element |
			val technicalDesign = element as YAnnotTechnicalDesign
			generateDDLForDerby(fsa, technicalDesign)
			if (technicalDesign.database.name == "MySQL") {
					generateDDLForMySQL(fsa, technicalDesign)
			}	
		]
	}

	protected def String generateDDLString(IFileSystemAccess fsa, YAnnotTechnicalDesign technicalDesign) {
		'''			
			«FOR table : technicalDesign.features»
				«generateTables(table)»
			«ENDFOR»
		'''
	}
	
		protected def void generateDDLForDerby(IFileSystemAccess fsa, YAnnotTechnicalDesign technicalDesign) {
		fsa.generateFile(
			"derby/" + "createdatabase" + ".ddl",
			LangOutputProvider.DDL,
			'''				
				«FOR table : technicalDesign.features»
					«generateTables(table)»		
				«ENDFOR»
			'''
		)
	}

	protected def void generateDDLForMySQL(IFileSystemAccess fsa, YAnnotTechnicalDesign technicalDesign) {
		fsa.generateFile(
			"mysql/" + "createdatabase" + ".ddl",
			LangOutputProvider.DDL,
			'''				
				«FOR table : technicalDesign.features»
					«generateTables(table)»		
				«ENDFOR»
			'''
		)
	}

	def private String generateTables(YAnnotTable table) {
		val text = '''
			CREATE TABLE `«table.name.toUpperCase»` (
				«FOR abstractColumn : table.columns SEPARATOR ',' AFTER ','»
					«generateColumns(abstractColumn)»
				«ENDFOR»
				«FOR foreignKey : table.foreignkeys SEPARATOR ',' AFTER ','»
					«generateForeignKeyColumns(foreignKey)»
				«ENDFOR»
				«IF table.primarykey !== null»«generatePrimaryKey(table)»«ENDIF»
			);
		'''
		return text
	}

	def private String generateColumns(YAnnotAbstractColumn abstractColumn) {
		val text = '''
			`«abstractColumn.name.toUpperCase»` «generateColumnType(abstractColumn)»
		'''
		return text
	}

	def private String generateForeignKeyColumns(YAnnotForeignKey foreignKey) {
		val text = '''
			«FOR abstractColumn : foreignKey.columns»
				`«abstractColumn.name.toUpperCase»` «generateColumnType(abstractColumn)»
			«ENDFOR»					
		'''
		return text
	}

	def private String generateColumnType(YAnnotAbstractColumn abstractColumn) {
		if (abstractColumn.type instanceof YAnnotColumn) {
			val column = abstractColumn.type as YAnnotColumn
			val text = '''
				«column.type»«IF column.type == "CHAR" ||  column.type == "VARCHAR"»(«column.generateLength»)«ENDIF» «column.generateNullFlag»
			'''
			return text
		} else if (abstractColumn.type instanceof YAnnotColumnLike) {
			val columnLike = abstractColumn.type as YAnnotColumnLike
			val column = columnLike.columnref
			// val column = abstractColumn2.type as JGColumn
			val text = '''
				«column.type»«IF column.type == "CHAR" ||  column.type == "VARCHAR"»(«column.generateLength»)«ENDIF»«column.generateNullFlag»
			'''
			return text
		}
	}

	def private String generateLength(YAnnotColumn column) {
	}

	def private String generateNullFlag(YAnnotColumn column) {
	}

	def private String generatePrimaryKey(YAnnotTable table) {
		val text = '''
			CONSTRAINT `«"pk_" + table.name»`
			PRIMARY KEY («FOR abstractColumn : table.primarykey.columns SEPARATOR ','»`«abstractColumn.name.toUpperCase»`«ENDFOR»)
		'''
		return text
	}

}
