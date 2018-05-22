/**
 * generated by Xtext 2.12.0
 */
package eu.jgen.notes.dmw.lite.lang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YClass</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YClass#getSuperclass <em>Superclass</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YClass#getEntity <em>Entity</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YClass#getInners <em>Inners</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YClass#getMembers <em>Members</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYClass()
 * @model
 * @generated
 */
public interface YClass extends YNamedElement
{
  /**
   * Returns the value of the '<em><b>Superclass</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Superclass</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Superclass</em>' reference.
   * @see #setSuperclass(YClass)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYClass_Superclass()
   * @model
   * @generated
   */
  YClass getSuperclass();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YClass#getSuperclass <em>Superclass</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Superclass</em>' reference.
   * @see #getSuperclass()
   * @generated
   */
  void setSuperclass(YClass value);

  /**
   * Returns the value of the '<em><b>Entity</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity</em>' reference.
   * @see #setEntity(YAnnotEntity)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYClass_Entity()
   * @model
   * @generated
   */
  YAnnotEntity getEntity();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YClass#getEntity <em>Entity</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Entity</em>' reference.
   * @see #getEntity()
   * @generated
   */
  void setEntity(YAnnotEntity value);

  /**
   * Returns the value of the '<em><b>Inners</b></em>' containment reference list.
   * The list contents are of type {@link eu.jgen.notes.dmw.lite.lang.YClass}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inners</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inners</em>' containment reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYClass_Inners()
   * @model containment="true"
   * @generated
   */
  EList<YClass> getInners();

  /**
   * Returns the value of the '<em><b>Members</b></em>' containment reference list.
   * The list contents are of type {@link eu.jgen.notes.dmw.lite.lang.YMember}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Members</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Members</em>' containment reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYClass_Members()
   * @model containment="true"
   * @generated
   */
  EList<YMember> getMembers();

} // YClass