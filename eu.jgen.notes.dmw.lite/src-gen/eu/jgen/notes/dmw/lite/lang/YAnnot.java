/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YAnnot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAnnot#getType <em>Type</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAnnot#getMsgtype <em>Msgtype</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnot()
 * @model
 * @generated
 */
public interface YAnnot extends EObject
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(YAnnot)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnot_Type()
   * @model containment="true"
   * @generated
   */
  YAnnot getType();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YAnnot#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(YAnnot value);

  /**
   * Returns the value of the '<em><b>Msgtype</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Msgtype</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Msgtype</em>' attribute.
   * @see #setMsgtype(String)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnot_Msgtype()
   * @model
   * @generated
   */
  String getMsgtype();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YAnnot#getMsgtype <em>Msgtype</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Msgtype</em>' attribute.
   * @see #getMsgtype()
   * @generated
   */
  void setMsgtype(String value);

} // YAnnot
