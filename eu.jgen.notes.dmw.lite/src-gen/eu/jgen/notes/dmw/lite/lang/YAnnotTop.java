/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YAnnot Top</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAnnotTop#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotTop()
 * @model
 * @generated
 */
public interface YAnnotTop extends EObject
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
   * @see #setType(EObject)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotTop_Type()
   * @model containment="true"
   * @generated
   */
  EObject getType();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YAnnotTop#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(EObject value);

} // YAnnotTop