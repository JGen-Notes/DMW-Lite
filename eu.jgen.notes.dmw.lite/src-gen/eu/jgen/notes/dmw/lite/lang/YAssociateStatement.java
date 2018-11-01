/**
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.lang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YAssociate Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAssociateStatement#getStruct <em>Struct</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAssociateStatement#getJoinref <em>Joinref</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAssociateStatement()
 * @model
 * @generated
 */
public interface YAssociateStatement extends YStatement
{
  /**
   * Returns the value of the '<em><b>Struct</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Struct</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Struct</em>' containment reference.
   * @see #setStruct(YStructRefPair)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAssociateStatement_Struct()
   * @model containment="true"
   * @generated
   */
  YStructRefPair getStruct();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YAssociateStatement#getStruct <em>Struct</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Struct</em>' containment reference.
   * @see #getStruct()
   * @generated
   */
  void setStruct(YStructRefPair value);

  /**
   * Returns the value of the '<em><b>Joinref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Joinref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Joinref</em>' containment reference.
   * @see #setJoinref(YJoinDef)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAssociateStatement_Joinref()
   * @model containment="true"
   * @generated
   */
  YJoinDef getJoinref();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YAssociateStatement#getJoinref <em>Joinref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Joinref</em>' containment reference.
   * @see #getJoinref()
   * @generated
   */
  void setJoinref(YJoinDef value);

} // YAssociateStatement
