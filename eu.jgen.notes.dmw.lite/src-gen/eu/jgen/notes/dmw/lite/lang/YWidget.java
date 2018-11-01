/**
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.lang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YWidget</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YWidget#getName <em>Name</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YWidget#getImports <em>Imports</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YWidget#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YWidget#getClasses <em>Classes</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYWidget()
 * @model
 * @generated
 */
public interface YWidget extends EObject
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
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYWidget_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YWidget#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
   * The list contents are of type {@link eu.jgen.notes.dmw.lite.lang.YImport}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Imports</em>' containment reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYWidget_Imports()
   * @model containment="true"
   * @generated
   */
  EList<YImport> getImports();

  /**
   * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
   * The list contents are of type {@link eu.jgen.notes.dmw.lite.lang.YAnnotTop}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' containment reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYWidget_Annotations()
   * @model containment="true"
   * @generated
   */
  EList<YAnnotTop> getAnnotations();

  /**
   * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
   * The list contents are of type {@link eu.jgen.notes.dmw.lite.lang.YClass}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Classes</em>' containment reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYWidget_Classes()
   * @model containment="true"
   * @generated
   */
  EList<YClass> getClasses();

} // YWidget
