package eu.jgen.notes.dmw.lite.utility

import com.google.common.base.CaseFormat
import eu.jgen.notes.dmw.lite.lang.LangFactory
import eu.jgen.notes.dmw.lite.lang.YAnnot
import eu.jgen.notes.dmw.lite.lang.YAnnotAbstractColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotAttr
import eu.jgen.notes.dmw.lite.lang.YAnnotColumn
import eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike
import eu.jgen.notes.dmw.lite.lang.YAnnotDecimal
import eu.jgen.notes.dmw.lite.lang.YAnnotEntity
import eu.jgen.notes.dmw.lite.lang.YAnnotForeignKey
import eu.jgen.notes.dmw.lite.lang.YAnnotId
import eu.jgen.notes.dmw.lite.lang.YAnnotLength
import eu.jgen.notes.dmw.lite.lang.YAnnotPrimaryKey
import eu.jgen.notes.dmw.lite.lang.YAnnotRel
import eu.jgen.notes.dmw.lite.lang.YAnnotTable
import java.util.List
import javax.inject.Inject

class LangDBUtil {

	@Inject extension LangUtil

	def String getColumnName(YAnnotColumn column) {
		(column.eContainer as YAnnotAbstractColumn).name
	}

	def String getColumnTypeName(YAnnotAbstractColumn abstractColumn) {
		if (abstractColumn.type instanceof YAnnotColumn) {
			return (abstractColumn.type as YAnnotColumn).type
		}
		// TODO like
		"yet unknown"
	}

	def int getColumnLength(YAnnotAbstractColumn abstractColumn) {
		if (abstractColumn.type instanceof YAnnotColumn) {
			return (abstractColumn.type as YAnnotColumn).extractLength
		}
		// TODO like
		0
	}

	def boolean isColumnOptional(YAnnotAbstractColumn abstractColumn) {
		if (abstractColumn.type instanceof YAnnotColumn) {
			val column = abstractColumn.type as YAnnotColumn
			if (column.optional === null) {
				return false
			} else if (column.optional == "?") {
				return true
			}
		}
		// TODO like
		false
	}

	def int getColumnDecimal(YAnnotAbstractColumn abstractColumn) {
		if (abstractColumn.type instanceof YAnnotColumn) {
			return (abstractColumn.type as YAnnotColumn).extractDecimal
		}
		0
	}

	def YAnnotAbstractColumn converAttributeIntoAbstractColumn(YAnnotAttr attribute) {
		val abstractColumn = LangFactory.eINSTANCE.createYAnnotAbstractColumn => [
			name = attribute.name.convertToTechnicalDesignName
			val column = LangFactory.eINSTANCE.createYAnnotColumn => [
				attrref = attribute
			]
			type = column
			doSelectColumnProperties(column, attribute)
		]
		return abstractColumn
	}

	def private void doSelectColumnProperties(YAnnotColumn column, YAnnotAttr attribute) {
		// Set default values in case annotation does not exist.
		if (attribute.yclass.name == "String") {
			column.type = "CHAR"
		} else if (attribute.yclass.name == "String") {
			column.type = "CHAR"
		} else if (attribute.yclass.name == "Short") {
			column.type = "SMALLINT"
		} else if (attribute.yclass.name == "Int") {
			column.type = "INTEGER"
		} else if (attribute.yclass.name == "Double") {
			column.type = "DECIMAL"
		} else if (attribute.yclass.name == "Date") {
			column.type = "DATE"
		} else if (attribute.yclass.name == "Time") {
			column.type = "TIME"
		} else if (attribute.yclass.name == "Timestamp") {
			column.type = "TIMESTAMP"
		} else if (attribute.yclass.name == "Blob") {
			column.type = "BLOB"
		}
		//TODO need to check this
		attribute.annots.forEach [ annot |
			val annotClone = annot.cloneYAnnot
			if(annotClone !== null) {
				column.annots.add(annotClone)	
			}
					
		]
		if (attribute.optional !== null && attribute.optional == "?") {
			column.optional = "?"
		}
	}

	def YAnnot cloneYAnnot(YAnnot annot) {
		switch (annot) {
			case annot.type instanceof YAnnotLength: {
				val clone = LangFactory.eINSTANCE.createYAnnotLength => [
					length = (annot.type as YAnnotLength).length
				]
				return clone;
			}
			case annot.type instanceof YAnnotDecimal: {
				val clone = LangFactory.eINSTANCE.createYAnnotDecimal => [
					length = (annot.type as YAnnotDecimal).length
					decimal = (annot.type as YAnnotDecimal).decimal
				]
				return clone;
			}
			default: {
			}
		}
	}

	def YAnnotTable converEntityIntoTable(YAnnotEntity entity) {
		val table = LangFactory.eINSTANCE.createYAnnotTable => [
			name = entity.name.convertToTechnicalDesignName
			entityref = entity
		]
		return table
	}

	def YAnnotForeignKey converRelationshipIntoForeignKeys(YAnnotRel relationship) {
		doCreateForeignKeyForRelationship(relationship, relationship.inverse)
	}

	def private YAnnotForeignKey doCreateForeignKeyForRelationship(YAnnotRel thisrelationship,
		YAnnotRel targetRelationship) {
		val foreignKey = LangFactory.eINSTANCE.createYAnnotForeignKey => [
			val list = doConvertRelationshipIntoFKColumns(targetRelationship)
			for (abstractColumn : list) {
				columns.add(abstractColumn)
			}
			relationship = thisrelationship
		]
		return foreignKey
	}

	def private List<YAnnotAbstractColumn> doConvertRelationshipIntoFKColumns(YAnnotRel relationship) {
		val list = newArrayList()
		val parentEntity = relationship.eContainer as YAnnotEntity
		val parentTable = parentEntity.getImplementingTable
		if (parentTable === null || parentTable.primarykey === null) {
			return list
		}
		for (abstractColumn : parentTable.primarykey.columns) {
			val newAbstractColumn = LangFactory.eINSTANCE.createYAnnotAbstractColumn => [
				name = "FK_" + parentTable.name + "__" + abstractColumn.name
				val annotColumnLike = LangFactory.eINSTANCE.createYAnnotColumnLike => [
					// TODO need testing 
					columnref = abstractColumn
				]
				type = annotColumnLike
			]
			list.add(newAbstractColumn)
		}
		return list
	}

	def YAnnotPrimaryKey converIdentifierIntoPrimaryKey(YAnnotId identifier) {
		val primaryKey = LangFactory.eINSTANCE.createYAnnotPrimaryKey => [
			val list = doConvertIdentifierIntoColumns(identifier)
			for (abstarctColumn : list) {
				columns.add(abstarctColumn)
			}

		]
		return primaryKey
	}

	def private List<YAnnotAbstractColumn> doConvertIdentifierIntoColumns(YAnnotId identifier) {
		val List<YAnnotAbstractColumn> list = newArrayList()
		for (identifierPart : identifier.annots) {
			if (identifierPart instanceof YAnnotAttr) {
				val attribute = (identifierPart as YAnnotAttr)
				val parentTable = (attribute.eContainer as YAnnotEntity).getImplementingTable
				for (abstractColumn : parentTable.columns) {
					if (abstractColumn.type instanceof YAnnotColumn) {
						val column = abstractColumn.type as YAnnotColumn
						if (column.attrref == attribute) {
							list.add(abstractColumn)
						}
					}
				}
			} else if (identifierPart instanceof YAnnotRel) {
				val relationship = (identifierPart as YAnnotRel)
				val parentTable = (relationship.eContainer as YAnnotEntity).getImplementingTable
				for (foreignKey : parentTable.foreignkeys) {
					if (foreignKey.relationship == relationship) {
						for (abstractColumn : foreignKey.columns) {
							if (abstractColumn.type instanceof YAnnotColumnLike) {
								list.add(abstractColumn)
							}
						}
					}
				}
			}
		}
		return list
	}

	def public String convertToTechnicalDesignName(String name) {
		CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name)
	}

	def public int extractLength(YAnnotColumn column) {
		val annot = column.annots.filter(YAnnotLength).head
		if (annot !== null) {
			return annot.length
		}
		val annot2 = column.annots.filter(YAnnotDecimal).head
		if (annot2 !== null) {
			return annot2.length
		}
		return 0
	}

	def public int extractDecimal(YAnnotColumn column) {
		val annot = column.annots.filter(YAnnotDecimal).head
		if (annot !== null) {
			return annot.decimal
		}
		return 0
	}

}
