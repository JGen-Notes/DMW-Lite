/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YAnnot Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAnnotEntity#getName <em>Name</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAnnotEntity#getSuperannot <em>Superannot</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAnnotEntity#getAnnots <em>Annots</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotEntity()
 * @model
 * @generated
 */
public interface YAnnotEntity extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotEntity_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YAnnotEntity#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Superannot</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Superannot</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Superannot</em>' reference.
   * @see #setSuperannot(YAnnotEntity)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotEntity_Superannot()
   * @model
   * @generated
   */
  YAnnotEntity getSuperannot();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YAnnotEntity#getSuperannot <em>Superannot</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Superannot</em>' reference.
   * @see #getSuperannot()
   * @generated
   */
  void setSuperannot(YAnnotEntity value);

  /**
   * Returns the value of the '<em><b>Annots</b></em>' containment reference list.
   * The list contents are of type {@link eu.jgen.notes.dmw.lite.lang.YAnnotEntityInner}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annots</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annots</em>' containment reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotEntity_Annots()
   * @model containment="true"
   * @generated
   */
  EList<YAnnotEntityInner> getAnnots();

} // YAnnotEntity