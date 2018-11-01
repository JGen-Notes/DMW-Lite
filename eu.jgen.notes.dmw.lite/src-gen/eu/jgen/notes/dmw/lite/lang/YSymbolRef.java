/**
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.lang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YSymbol Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YSymbolRef#getSymbol <em>Symbol</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYSymbolRef()
 * @model
 * @generated
 */
public interface YSymbolRef extends YExpression
{
  /**
   * Returns the value of the '<em><b>Symbol</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbol</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol</em>' reference.
   * @see #setSymbol(YSymbol)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYSymbolRef_Symbol()
   * @model
   * @generated
   */
  YSymbol getSymbol();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YSymbolRef#getSymbol <em>Symbol</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symbol</em>' reference.
   * @see #getSymbol()
   * @generated
   */
  void setSymbol(YSymbol value);

} // YSymbolRef
