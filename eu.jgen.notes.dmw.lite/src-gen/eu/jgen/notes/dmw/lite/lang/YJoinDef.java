/**
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.lang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YJoin Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YJoinDef#getFromView <em>From View</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YJoinDef#getRelRef <em>Rel Ref</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YJoinDef#getToView <em>To View</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYJoinDef()
 * @model
 * @generated
 */
public interface YJoinDef extends YParameter
{
  /**
   * Returns the value of the '<em><b>From View</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>From View</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>From View</em>' reference.
   * @see #setFromView(YProperty)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYJoinDef_FromView()
   * @model
   * @generated
   */
  YProperty getFromView();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YJoinDef#getFromView <em>From View</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>From View</em>' reference.
   * @see #getFromView()
   * @generated
   */
  void setFromView(YProperty value);

  /**
   * Returns the value of the '<em><b>Rel Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rel Ref</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rel Ref</em>' reference.
   * @see #setRelRef(YAnnotRel)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYJoinDef_RelRef()
   * @model
   * @generated
   */
  YAnnotRel getRelRef();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YJoinDef#getRelRef <em>Rel Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rel Ref</em>' reference.
   * @see #getRelRef()
   * @generated
   */
  void setRelRef(YAnnotRel value);

  /**
   * Returns the value of the '<em><b>To View</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>To View</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>To View</em>' reference.
   * @see #setToView(YProperty)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYJoinDef_ToView()
   * @model
   * @generated
   */
  YProperty getToView();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YJoinDef#getToView <em>To View</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>To View</em>' reference.
   * @see #getToView()
   * @generated
   */
  void setToView(YProperty value);

} // YJoinDef
