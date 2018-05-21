/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YUpdate Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YUpdateStatement#getStruct <em>Struct</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YUpdateStatement#getSetBlock <em>Set Block</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YUpdateStatement#getSuccess <em>Success</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYUpdateStatement()
 * @model
 * @generated
 */
public interface YUpdateStatement extends YStatement
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
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYUpdateStatement_Struct()
   * @model containment="true"
   * @generated
   */
  YStructRefPair getStruct();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YUpdateStatement#getStruct <em>Struct</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Struct</em>' containment reference.
   * @see #getStruct()
   * @generated
   */
  void setStruct(YStructRefPair value);

  /**
   * Returns the value of the '<em><b>Set Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Set Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Set Block</em>' containment reference.
   * @see #setSetBlock(YBlock)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYUpdateStatement_SetBlock()
   * @model containment="true"
   * @generated
   */
  YBlock getSetBlock();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YUpdateStatement#getSetBlock <em>Set Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Set Block</em>' containment reference.
   * @see #getSetBlock()
   * @generated
   */
  void setSetBlock(YBlock value);

  /**
   * Returns the value of the '<em><b>Success</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Success</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Success</em>' containment reference.
   * @see #setSuccess(YBlock)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYUpdateStatement_Success()
   * @model containment="true"
   * @generated
   */
  YBlock getSuccess();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YUpdateStatement#getSuccess <em>Success</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Success</em>' containment reference.
   * @see #getSuccess()
   * @generated
   */
  void setSuccess(YBlock value);

} // YUpdateStatement
