/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YAnnot Column Like</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike#getColumnref <em>Columnref</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotColumnLike()
 * @model
 * @generated
 */
public interface YAnnotColumnLike extends EObject
{
  /**
   * Returns the value of the '<em><b>Columnref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Columnref</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Columnref</em>' reference.
   * @see #setColumnref(YAnnotAbstractColumn)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotColumnLike_Columnref()
   * @model
   * @generated
   */
  YAnnotAbstractColumn getColumnref();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YAnnotColumnLike#getColumnref <em>Columnref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Columnref</em>' reference.
   * @see #getColumnref()
   * @generated
   */
  void setColumnref(YAnnotAbstractColumn value);

} // YAnnotColumnLike
