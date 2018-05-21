/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YAnnot Swift</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAnnotSwift#getName <em>Name</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YAnnotSwift#getDatabase <em>Database</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotSwift()
 * @model
 * @generated
 */
public interface YAnnotSwift extends EObject
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
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotSwift_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YAnnotSwift#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Database</b></em>' reference list.
   * The list contents are of type {@link eu.jgen.notes.dmw.lite.lang.YAnnotDatabase}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Database</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Database</em>' reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYAnnotSwift_Database()
   * @model
   * @generated
   */
  EList<YAnnotDatabase> getDatabase();

} // YAnnotSwift
