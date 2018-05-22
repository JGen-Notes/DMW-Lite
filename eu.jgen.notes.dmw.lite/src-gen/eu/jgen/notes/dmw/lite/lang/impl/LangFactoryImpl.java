/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang.impl;

import eu.jgen.notes.dmw.lite.lang.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LangFactoryImpl extends EFactoryImpl implements LangFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static LangFactory init()
  {
    try
    {
      LangFactory theLangFactory = (LangFactory)EPackage.Registry.INSTANCE.getEFactory(LangPackage.eNS_URI);
      if (theLangFactory != null)
      {
        return theLangFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new LangFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LangFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case LangPackage.YWIDGET: return createYWidget();
      case LangPackage.YIMPORT: return createYImport();
      case LangPackage.YPARAMETER: return createYParameter();
      case LangPackage.YARGUMENT: return createYArgument();
      case LangPackage.YCLASS: return createYClass();
      case LangPackage.YMEMBER: return createYMember();
      case LangPackage.YPROPERTY: return createYProperty();
      case LangPackage.YTUPLES: return createYTuples();
      case LangPackage.YFUNCTION: return createYFunction();
      case LangPackage.YBLOCK: return createYBlock();
      case LangPackage.YSTATEMENT: return createYStatement();
      case LangPackage.YVARIABLE_DECLARATION: return createYVariableDeclaration();
      case LangPackage.YRETURN: return createYReturn();
      case LangPackage.YIF_STATEMENT: return createYIfStatement();
      case LangPackage.YSWITCH_STATEMENT: return createYSwitchStatement();
      case LangPackage.YSWITCH_CASE: return createYSwitchCase();
      case LangPackage.YSYMBOL: return createYSymbol();
      case LangPackage.YNAMED_ELEMENT: return createYNamedElement();
      case LangPackage.YEXPRESSION: return createYExpression();
      case LangPackage.YREAD_STATEMENT: return createYReadStatement();
      case LangPackage.YREAD_EACH_STATEMENT: return createYReadEachStatement();
      case LangPackage.YCREATE_STATEMENT: return createYCreateStatement();
      case LangPackage.YUPDATE_STATEMENT: return createYUpdateStatement();
      case LangPackage.YDELETE_STATEMENT: return createYDeleteStatement();
      case LangPackage.YASSOCIATE_STATEMENT: return createYAssociateStatement();
      case LangPackage.YSTRUCT_REF_PAIR: return createYStructRefPair();
      case LangPackage.YJOIN: return createYJoin();
      case LangPackage.YJOIN_DEF: return createYJoinDef();
      case LangPackage.YWHERE: return createYWhere();
      case LangPackage.YWHILE_STATEMENT: return createYWhileStatement();
      case LangPackage.YREPEAT_WHILE_STATEMENT: return createYRepeatWhileStatement();
      case LangPackage.YFOR_IN_STATEMENT: return createYForInStatement();
      case LangPackage.YANNOT: return createYAnnot();
      case LangPackage.YANNOT_TOP: return createYAnnotTop();
      case LangPackage.YANNOT_ENTITY_INNER: return createYAnnotEntityInner();
      case LangPackage.YANNOT_ENTITY: return createYAnnotEntity();
      case LangPackage.YANNOT_ATTR: return createYAnnotAttr();
      case LangPackage.YANNOT_REL: return createYAnnotRel();
      case LangPackage.YANNOT_ID_INNER: return createYAnnotIdInner();
      case LangPackage.YANNOT_ID: return createYAnnotId();
      case LangPackage.YANNOT_TECHNICAL_DESIGN: return createYAnnotTechnicalDesign();
      case LangPackage.YANNOT_TABLE: return createYAnnotTable();
      case LangPackage.YANNOT_COLUMN: return createYAnnotColumn();
      case LangPackage.YANNOT_COLUMN_LIKE: return createYAnnotColumnLike();
      case LangPackage.YANNOT_ABSTRACT_COLUMN: return createYAnnotAbstractColumn();
      case LangPackage.YANNOT_PRIMARY_KEY: return createYAnnotPrimaryKey();
      case LangPackage.YANNOT_FOREIGN_KEY: return createYAnnotForeignKey();
      case LangPackage.YANNOT_JAVA: return createYAnnotJava();
      case LangPackage.YANNOT_SWIFT: return createYAnnotSwift();
      case LangPackage.YANNOT_DATABASE: return createYAnnotDatabase();
      case LangPackage.YASSIGNMENT: return createYAssignment();
      case LangPackage.YMEMBER_SELECTION: return createYMemberSelection();
      case LangPackage.YOR_EXPRESSION: return createYOrExpression();
      case LangPackage.YAND_EXPRESSION: return createYAndExpression();
      case LangPackage.YEQUALITY_EXPRESSION: return createYEqualityExpression();
      case LangPackage.YCOMPARISON_EXPRESSION: return createYComparisonExpression();
      case LangPackage.YPLUS: return createYPlus();
      case LangPackage.YMINUS: return createYMinus();
      case LangPackage.YMUL_OR_DIV: return createYMulOrDiv();
      case LangPackage.YSTRING_CONSTANT: return createYStringConstant();
      case LangPackage.YINT_CONSTANT: return createYIntConstant();
      case LangPackage.YBOOL_CONSTANT: return createYBoolConstant();
      case LangPackage.YSELF: return createYSelf();
      case LangPackage.YSUPER: return createYSuper();
      case LangPackage.YNULL: return createYNull();
      case LangPackage.YSYMBOL_REF: return createYSymbolRef();
      case LangPackage.YNEW: return createYNew();
      case LangPackage.YANNOT_LENGTH: return createYAnnotLength();
      case LangPackage.YANNOT_DECIMAL: return createYAnnotDecimal();
      case LangPackage.YANNOT_ACTION: return createYAnnotAction();
      case LangPackage.YANNOT_MESSAGE: return createYAnnotMessage();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case LangPackage.YACCESS_LEVEL:
        return createYAccessLevelFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case LangPackage.YACCESS_LEVEL:
        return convertYAccessLevelToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YWidget createYWidget()
  {
    YWidgetImpl yWidget = new YWidgetImpl();
    return yWidget;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YImport createYImport()
  {
    YImportImpl yImport = new YImportImpl();
    return yImport;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YParameter createYParameter()
  {
    YParameterImpl yParameter = new YParameterImpl();
    return yParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YArgument createYArgument()
  {
    YArgumentImpl yArgument = new YArgumentImpl();
    return yArgument;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YClass createYClass()
  {
    YClassImpl yClass = new YClassImpl();
    return yClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YMember createYMember()
  {
    YMemberImpl yMember = new YMemberImpl();
    return yMember;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YProperty createYProperty()
  {
    YPropertyImpl yProperty = new YPropertyImpl();
    return yProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YTuples createYTuples()
  {
    YTuplesImpl yTuples = new YTuplesImpl();
    return yTuples;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YFunction createYFunction()
  {
    YFunctionImpl yFunction = new YFunctionImpl();
    return yFunction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YBlock createYBlock()
  {
    YBlockImpl yBlock = new YBlockImpl();
    return yBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YStatement createYStatement()
  {
    YStatementImpl yStatement = new YStatementImpl();
    return yStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YVariableDeclaration createYVariableDeclaration()
  {
    YVariableDeclarationImpl yVariableDeclaration = new YVariableDeclarationImpl();
    return yVariableDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YReturn createYReturn()
  {
    YReturnImpl yReturn = new YReturnImpl();
    return yReturn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YIfStatement createYIfStatement()
  {
    YIfStatementImpl yIfStatement = new YIfStatementImpl();
    return yIfStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YSwitchStatement createYSwitchStatement()
  {
    YSwitchStatementImpl ySwitchStatement = new YSwitchStatementImpl();
    return ySwitchStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YSwitchCase createYSwitchCase()
  {
    YSwitchCaseImpl ySwitchCase = new YSwitchCaseImpl();
    return ySwitchCase;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YSymbol createYSymbol()
  {
    YSymbolImpl ySymbol = new YSymbolImpl();
    return ySymbol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YNamedElement createYNamedElement()
  {
    YNamedElementImpl yNamedElement = new YNamedElementImpl();
    return yNamedElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YExpression createYExpression()
  {
    YExpressionImpl yExpression = new YExpressionImpl();
    return yExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YReadStatement createYReadStatement()
  {
    YReadStatementImpl yReadStatement = new YReadStatementImpl();
    return yReadStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YReadEachStatement createYReadEachStatement()
  {
    YReadEachStatementImpl yReadEachStatement = new YReadEachStatementImpl();
    return yReadEachStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YCreateStatement createYCreateStatement()
  {
    YCreateStatementImpl yCreateStatement = new YCreateStatementImpl();
    return yCreateStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YUpdateStatement createYUpdateStatement()
  {
    YUpdateStatementImpl yUpdateStatement = new YUpdateStatementImpl();
    return yUpdateStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YDeleteStatement createYDeleteStatement()
  {
    YDeleteStatementImpl yDeleteStatement = new YDeleteStatementImpl();
    return yDeleteStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAssociateStatement createYAssociateStatement()
  {
    YAssociateStatementImpl yAssociateStatement = new YAssociateStatementImpl();
    return yAssociateStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YStructRefPair createYStructRefPair()
  {
    YStructRefPairImpl yStructRefPair = new YStructRefPairImpl();
    return yStructRefPair;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YJoin createYJoin()
  {
    YJoinImpl yJoin = new YJoinImpl();
    return yJoin;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YJoinDef createYJoinDef()
  {
    YJoinDefImpl yJoinDef = new YJoinDefImpl();
    return yJoinDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YWhere createYWhere()
  {
    YWhereImpl yWhere = new YWhereImpl();
    return yWhere;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YWhileStatement createYWhileStatement()
  {
    YWhileStatementImpl yWhileStatement = new YWhileStatementImpl();
    return yWhileStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YRepeatWhileStatement createYRepeatWhileStatement()
  {
    YRepeatWhileStatementImpl yRepeatWhileStatement = new YRepeatWhileStatementImpl();
    return yRepeatWhileStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YForInStatement createYForInStatement()
  {
    YForInStatementImpl yForInStatement = new YForInStatementImpl();
    return yForInStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnot createYAnnot()
  {
    YAnnotImpl yAnnot = new YAnnotImpl();
    return yAnnot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotTop createYAnnotTop()
  {
    YAnnotTopImpl yAnnotTop = new YAnnotTopImpl();
    return yAnnotTop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotEntityInner createYAnnotEntityInner()
  {
    YAnnotEntityInnerImpl yAnnotEntityInner = new YAnnotEntityInnerImpl();
    return yAnnotEntityInner;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotEntity createYAnnotEntity()
  {
    YAnnotEntityImpl yAnnotEntity = new YAnnotEntityImpl();
    return yAnnotEntity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotAttr createYAnnotAttr()
  {
    YAnnotAttrImpl yAnnotAttr = new YAnnotAttrImpl();
    return yAnnotAttr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotRel createYAnnotRel()
  {
    YAnnotRelImpl yAnnotRel = new YAnnotRelImpl();
    return yAnnotRel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotIdInner createYAnnotIdInner()
  {
    YAnnotIdInnerImpl yAnnotIdInner = new YAnnotIdInnerImpl();
    return yAnnotIdInner;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotId createYAnnotId()
  {
    YAnnotIdImpl yAnnotId = new YAnnotIdImpl();
    return yAnnotId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotTechnicalDesign createYAnnotTechnicalDesign()
  {
    YAnnotTechnicalDesignImpl yAnnotTechnicalDesign = new YAnnotTechnicalDesignImpl();
    return yAnnotTechnicalDesign;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotTable createYAnnotTable()
  {
    YAnnotTableImpl yAnnotTable = new YAnnotTableImpl();
    return yAnnotTable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotColumn createYAnnotColumn()
  {
    YAnnotColumnImpl yAnnotColumn = new YAnnotColumnImpl();
    return yAnnotColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotColumnLike createYAnnotColumnLike()
  {
    YAnnotColumnLikeImpl yAnnotColumnLike = new YAnnotColumnLikeImpl();
    return yAnnotColumnLike;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotAbstractColumn createYAnnotAbstractColumn()
  {
    YAnnotAbstractColumnImpl yAnnotAbstractColumn = new YAnnotAbstractColumnImpl();
    return yAnnotAbstractColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotPrimaryKey createYAnnotPrimaryKey()
  {
    YAnnotPrimaryKeyImpl yAnnotPrimaryKey = new YAnnotPrimaryKeyImpl();
    return yAnnotPrimaryKey;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotForeignKey createYAnnotForeignKey()
  {
    YAnnotForeignKeyImpl yAnnotForeignKey = new YAnnotForeignKeyImpl();
    return yAnnotForeignKey;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotJava createYAnnotJava()
  {
    YAnnotJavaImpl yAnnotJava = new YAnnotJavaImpl();
    return yAnnotJava;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotSwift createYAnnotSwift()
  {
    YAnnotSwiftImpl yAnnotSwift = new YAnnotSwiftImpl();
    return yAnnotSwift;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotDatabase createYAnnotDatabase()
  {
    YAnnotDatabaseImpl yAnnotDatabase = new YAnnotDatabaseImpl();
    return yAnnotDatabase;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAssignment createYAssignment()
  {
    YAssignmentImpl yAssignment = new YAssignmentImpl();
    return yAssignment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YMemberSelection createYMemberSelection()
  {
    YMemberSelectionImpl yMemberSelection = new YMemberSelectionImpl();
    return yMemberSelection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YOrExpression createYOrExpression()
  {
    YOrExpressionImpl yOrExpression = new YOrExpressionImpl();
    return yOrExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAndExpression createYAndExpression()
  {
    YAndExpressionImpl yAndExpression = new YAndExpressionImpl();
    return yAndExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YEqualityExpression createYEqualityExpression()
  {
    YEqualityExpressionImpl yEqualityExpression = new YEqualityExpressionImpl();
    return yEqualityExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YComparisonExpression createYComparisonExpression()
  {
    YComparisonExpressionImpl yComparisonExpression = new YComparisonExpressionImpl();
    return yComparisonExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YPlus createYPlus()
  {
    YPlusImpl yPlus = new YPlusImpl();
    return yPlus;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YMinus createYMinus()
  {
    YMinusImpl yMinus = new YMinusImpl();
    return yMinus;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YMulOrDiv createYMulOrDiv()
  {
    YMulOrDivImpl yMulOrDiv = new YMulOrDivImpl();
    return yMulOrDiv;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YStringConstant createYStringConstant()
  {
    YStringConstantImpl yStringConstant = new YStringConstantImpl();
    return yStringConstant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YIntConstant createYIntConstant()
  {
    YIntConstantImpl yIntConstant = new YIntConstantImpl();
    return yIntConstant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YBoolConstant createYBoolConstant()
  {
    YBoolConstantImpl yBoolConstant = new YBoolConstantImpl();
    return yBoolConstant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YSelf createYSelf()
  {
    YSelfImpl ySelf = new YSelfImpl();
    return ySelf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YSuper createYSuper()
  {
    YSuperImpl ySuper = new YSuperImpl();
    return ySuper;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YNull createYNull()
  {
    YNullImpl yNull = new YNullImpl();
    return yNull;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YSymbolRef createYSymbolRef()
  {
    YSymbolRefImpl ySymbolRef = new YSymbolRefImpl();
    return ySymbolRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YNew createYNew()
  {
    YNewImpl yNew = new YNewImpl();
    return yNew;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotLength createYAnnotLength()
  {
    YAnnotLengthImpl yAnnotLength = new YAnnotLengthImpl();
    return yAnnotLength;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotDecimal createYAnnotDecimal()
  {
    YAnnotDecimalImpl yAnnotDecimal = new YAnnotDecimalImpl();
    return yAnnotDecimal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotAction createYAnnotAction()
  {
    YAnnotActionImpl yAnnotAction = new YAnnotActionImpl();
    return yAnnotAction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAnnotMessage createYAnnotMessage()
  {
    YAnnotMessageImpl yAnnotMessage = new YAnnotMessageImpl();
    return yAnnotMessage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YAccessLevel createYAccessLevelFromString(EDataType eDataType, String initialValue)
  {
    YAccessLevel result = YAccessLevel.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertYAccessLevelToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LangPackage getLangPackage()
  {
    return (LangPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static LangPackage getPackage()
  {
    return LangPackage.eINSTANCE;
  }

} //LangFactoryImpl