/**
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.lang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YDelete Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YDeleteStatement#getStruct <em>Struct</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYDeleteStatement()
 * @model
 * @generated
 */
public interface YDeleteStatement extends YStatement
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
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYDeleteStatement_Struct()
   * @model containment="true"
   * @generated
   */
  YStructRefPair getStruct();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YDeleteStatement#getStruct <em>Struct</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Struct</em>' containment reference.
   * @see #getStruct()
   * @generated
   */
  void setStruct(YStructRefPair value);

} // YDeleteStatement
