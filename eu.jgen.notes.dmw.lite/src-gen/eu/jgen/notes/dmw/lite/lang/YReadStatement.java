/**
 * generated by Xtext 2.15.0
 */
package eu.jgen.notes.dmw.lite.lang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>YRead Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YReadStatement#getStructs <em>Structs</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YReadStatement#getJoinclause <em>Joinclause</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YReadStatement#getWhereclause <em>Whereclause</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YReadStatement#getSuccessExpressions <em>Success Expressions</em>}</li>
 *   <li>{@link eu.jgen.notes.dmw.lite.lang.YReadStatement#getNotfoundExpressions <em>Notfound Expressions</em>}</li>
 * </ul>
 *
 * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYReadStatement()
 * @model
 * @generated
 */
public interface YReadStatement extends XExpression
{
  /**
   * Returns the value of the '<em><b>Structs</b></em>' containment reference list.
   * The list contents are of type {@link eu.jgen.notes.dmw.lite.lang.YStructRefPair}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Structs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Structs</em>' containment reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYReadStatement_Structs()
   * @model containment="true"
   * @generated
   */
  EList<YStructRefPair> getStructs();

  /**
   * Returns the value of the '<em><b>Joinclause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Joinclause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Joinclause</em>' containment reference.
   * @see #setJoinclause(YJoin)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYReadStatement_Joinclause()
   * @model containment="true"
   * @generated
   */
  YJoin getJoinclause();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YReadStatement#getJoinclause <em>Joinclause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Joinclause</em>' containment reference.
   * @see #getJoinclause()
   * @generated
   */
  void setJoinclause(YJoin value);

  /**
   * Returns the value of the '<em><b>Whereclause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Whereclause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Whereclause</em>' containment reference.
   * @see #setWhereclause(org.eclipse.xtext.xbase.XExpression)
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYReadStatement_Whereclause()
   * @model containment="true"
   * @generated
   */
  org.eclipse.xtext.xbase.XExpression getWhereclause();

  /**
   * Sets the value of the '{@link eu.jgen.notes.dmw.lite.lang.YReadStatement#getWhereclause <em>Whereclause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Whereclause</em>' containment reference.
   * @see #getWhereclause()
   * @generated
   */
  void setWhereclause(org.eclipse.xtext.xbase.XExpression value);

  /**
   * Returns the value of the '<em><b>Success Expressions</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.xbase.XExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Success Expressions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Success Expressions</em>' containment reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYReadStatement_SuccessExpressions()
   * @model containment="true"
   * @generated
   */
  EList<org.eclipse.xtext.xbase.XExpression> getSuccessExpressions();

  /**
   * Returns the value of the '<em><b>Notfound Expressions</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.xbase.XExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Notfound Expressions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Notfound Expressions</em>' containment reference list.
   * @see eu.jgen.notes.dmw.lite.lang.LangPackage#getYReadStatement_NotfoundExpressions()
   * @model containment="true"
   * @generated
   */
  EList<org.eclipse.xtext.xbase.XExpression> getNotfoundExpressions();

} // YReadStatement
