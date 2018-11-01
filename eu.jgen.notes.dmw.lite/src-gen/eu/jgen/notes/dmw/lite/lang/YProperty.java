/**
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.lang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YProperty</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YProperty#getTuples <em>Tuples</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YProperty#isOptional <em>Optional</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YProperty#getAttrRef <em>Attr Ref</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YProperty#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYProperty()
 * @model
 * @generated
 */
public interface YProperty extends YMember
{
  /**
   * Returns the value of the '<em><b>Tuples</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuples</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuples</em>' containment reference.
   * @see #setTuples(YTuples)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYProperty_Tuples()
   * @model containment="true"
   * @generated
   */
  YTuples getTuples();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YProperty#getTuples <em>Tuples</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuples</em>' containment reference.
   * @see #getTuples()
   * @generated
   */
  void setTuples(YTuples value);

  /**
   * Returns the value of the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Optional</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Optional</em>' attribute.
   * @see #setOptional(boolean)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYProperty_Optional()
   * @model
   * @generated
   */
  boolean isOptional();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YProperty#isOptional <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Optional</em>' attribute.
   * @see #isOptional()
   * @generated
   */
  void setOptional(boolean value);

  /**
   * Returns the value of the '<em><b>Attr Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attr Ref</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attr Ref</em>' reference.
   * @see #setAttrRef(YAnnotAttr)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYProperty_AttrRef()
   * @model
   * @generated
   */
  YAnnotAttr getAttrRef();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YProperty#getAttrRef <em>Attr Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attr Ref</em>' reference.
   * @see #getAttrRef()
   * @generated
   */
  void setAttrRef(YAnnotAttr value);

  /**
   * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
   * The list contents are of type {@link eu.jgen.notes.dmw.lite.lang.YAnnot}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' containment reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYProperty_Annotations()
   * @model containment="true"
   * @generated
   */
  EList<YAnnot> getAnnotations();

} // YProperty
